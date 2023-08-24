package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMovieDetailsCastAndCrewUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state.MovieDetailState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesCrewViewModel @Inject constructor(
    private val getMovieDetailsCrewUseCase: GetMovieDetailsCastAndCrewUseCase,
    private val savedMovieCrewStateHandle: SavedStateHandle
): ViewModel() {

    private val _stateMovieCrew = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovieCrew : StateFlow<MovieDetailState> = _stateMovieCrew

    private var jobCrews : Job? = null

    init {
        savedMovieCrewStateHandle.get<String>(Constants.MOVIE_ID.toString())?.let {
            getMovieCastFromTMDB(it.toInt())
        }
    }

    private fun getMovieCastFromTMDB(imdbId: Int) {
        jobCrews?.cancel()
        jobCrews = getMovieDetailsCrewUseCase.executeGetCastAndCrewFromTMDB(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCrew.value = MovieDetailState(movieCrew = it.data?.crew ?: emptyList())
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