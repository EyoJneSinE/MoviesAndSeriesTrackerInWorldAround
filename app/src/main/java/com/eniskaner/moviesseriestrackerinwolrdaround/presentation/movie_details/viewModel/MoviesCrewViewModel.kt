package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMovieDetailsCastAndCrewUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state.MovieDetailState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesCrewViewModel @Inject constructor(
    private val getMovieDetailsCrewUseCase: GetMovieDetailsCastAndCrewUseCase,
    private val savedMovieCrewStateHandle: SavedStateHandle
): ViewModel() {

    private val _stateMovieCrew = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovieCrew : StateFlow<MovieDetailState> = _stateMovieCrew

    private var jobMovieCrews : Job? = null

    init {
        savedMovieCrewStateHandle.get<String>(Constants.MOVIE_ID.toString())?.let {
            getMovieCrew(it.toInt())
        }
    }

    fun getMovieCrew(imdbId: Int) {
        jobMovieCrews?.cancel()
        jobMovieCrews = getMovieDetailsCrewUseCase.executeGetCastAndCrewFromTMDB(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCrew.value = MovieDetailState(movieCrew = it.data)
                }
                is Resource.Error -> {
                    _stateMovieCrew.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieCrew.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}