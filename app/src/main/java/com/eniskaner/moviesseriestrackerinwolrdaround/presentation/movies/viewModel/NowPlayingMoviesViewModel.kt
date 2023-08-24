package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetNowPlayingMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.state.MoviesState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NowPlayingMoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
): ViewModel() {

    private val _stateNowPlayingMovies = MutableStateFlow<MoviesState>(MoviesState())
    val stateNowPlayingMovies : StateFlow<MoviesState> = _stateNowPlayingMovies

    private var jobNowPlayingMovies : Job? = null

    init {
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        jobNowPlayingMovies?.cancel()
        jobNowPlayingMovies = getNowPlayingMoviesUseCase.executeGetNowPlayingMoviesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateNowPlayingMovies.value = MoviesState(movies = it.data?.movies ?: emptyList())
                }

                is Resource.Error -> {
                    _stateNowPlayingMovies.value = MoviesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateNowPlayingMovies.value = MoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}