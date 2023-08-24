package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMovieDetailsCastAndCrewUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state.MovieDetailState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.MOVIE_ID
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesCastViewModel @Inject constructor(
    private val getMovieDetailsCastUseCase: GetMovieDetailsCastAndCrewUseCase,
    private val savedMovieCastStateHandle: SavedStateHandle
): ViewModel() {

    private val _stateMovieCast = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovieCast : StateFlow<MovieDetailState> = _stateMovieCast

    private var jobCasts : Job? = null

    init {
        savedMovieCastStateHandle.get<String>(MOVIE_ID.toString())?.let {
            getMovieCastFromTMDB(it.toInt())
        }
    }

    private fun getMovieCastFromTMDB(imdbId: Int) {
        jobCasts?.cancel()
        jobCasts = getMovieDetailsCastUseCase.executeGetCastAndCrewFromTMDB(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieCast.value = MovieDetailState(movieCast = it.data)
                }
                is Resource.Error -> {
                    _stateMovieCast.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieCast.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}