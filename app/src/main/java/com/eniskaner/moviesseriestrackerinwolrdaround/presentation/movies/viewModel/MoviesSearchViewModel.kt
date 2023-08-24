package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetSearchMoviesUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.event.MoviesEvent
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.state.MoviesState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesSearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase
): ViewModel() {

    private val _stateSearchMovies = MutableStateFlow<MoviesState>(MoviesState())
    val stateSearchMovies : StateFlow<MoviesState> = _stateSearchMovies

    private var jobSearchMovies : Job? = null

    init {
        getSearchMoviesFromTMDB(_stateSearchMovies.value.search)
    }

    private fun getSearchMoviesFromTMDB(search: String) {
        jobSearchMovies?.cancel()
        jobSearchMovies = getSearchMoviesUseCase.executeSearchMovieFromTMDB(search).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSearchMovies.value = MoviesState(movies = it.data?.movies ?: emptyList())
                }

                is Resource.Error -> {
                    _stateSearchMovies.value = MoviesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateSearchMovies.value = MoviesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onEvent(event : MoviesEvent) {
        when(event) {
            is MoviesEvent.SearchMovies -> {
                getSearchMoviesFromTMDB(event.searchMovies)
            }
        }
    }
}