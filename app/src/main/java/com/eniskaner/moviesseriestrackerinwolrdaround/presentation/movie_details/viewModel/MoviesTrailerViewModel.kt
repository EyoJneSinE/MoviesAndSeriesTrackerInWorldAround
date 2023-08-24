package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMovieDetailsVideosUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state.MovieDetailState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.MOVIE_ID
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesTrailerViewModel @Inject constructor(
    private val getMovieTrailerUseCase: GetMovieDetailsVideosUseCase,
    private val savedMovieTrailerStateHandle: SavedStateHandle,
): ViewModel() {
    private val _stateMovieTrailer = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovieTrailer : StateFlow<MovieDetailState> = _stateMovieTrailer

    private var jobMovieTrailers : Job? = null

    init {
        savedMovieTrailerStateHandle.get<String>(MOVIE_ID.toString())?.let {
            getMovieTrailer(it.toInt())
        }
    }

    private fun getMovieTrailer(imdbId: Int) {
        jobMovieTrailers?.cancel()
        jobMovieTrailers = getMovieTrailerUseCase.executeGetVideosFromTMDB(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieTrailer.value = MovieDetailState(movieTrailer = it.data?.results ?: emptyList())
                }
                is Resource.Error -> {
                    _stateMovieTrailer.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieTrailer.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}