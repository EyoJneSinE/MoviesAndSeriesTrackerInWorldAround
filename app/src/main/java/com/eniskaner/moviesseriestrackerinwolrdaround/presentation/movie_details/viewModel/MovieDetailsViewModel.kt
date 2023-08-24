package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMovieDetailsUseCase
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
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedMovieDetailsStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _stateMovieDetails = MutableStateFlow<MovieDetailState>(MovieDetailState())
    val stateMovieDetails : StateFlow<MovieDetailState> = _stateMovieDetails

    private var jobMovieDetails : Job? = null

    init {
        savedMovieDetailsStateHandle.get<String>(MOVIE_ID.toString())?.let {
            getMovieDetailFromTMDB(it.toInt())
        }
    }

    private fun getMovieDetailFromTMDB(imdbId: Int) {
        jobMovieDetails?.cancel()
        jobMovieDetails = getMovieDetailsUseCase.executeGetMovieDetailsFromTMDB(imdbId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovieDetails.value = MovieDetailState(movie = it.data)
                }
                is Resource.Error -> {
                    _stateMovieDetails.value = MovieDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateMovieDetails.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}