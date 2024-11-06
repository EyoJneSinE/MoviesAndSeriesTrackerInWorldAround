package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.state.MoviesState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _stateMovies = MutableStateFlow(MoviesState())
    val stateMovies = _stateMovies.asStateFlow()

    private var jobMovies: Job? = null

    init {
        getMoviesFromTMBD()
    }

    private fun getMoviesFromTMBD() {
        jobMovies?.cancel()
        jobMovies = getMoviesUseCase.executeGetMoviesFromTMDB().onEach {
            when (it) {
                is Resource.Success -> {
                    _stateMovies.value = MoviesState(movies = it.data?.movies ?: emptyList())
                }

                is Resource.Error -> {
                    _stateMovies.value = MoviesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateMovies.value = MoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}
