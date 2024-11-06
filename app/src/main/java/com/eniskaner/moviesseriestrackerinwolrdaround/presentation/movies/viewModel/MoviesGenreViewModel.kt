package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.movies_usecase.GetMoviesGenreUseCase
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
class MoviesGenreViewModel @Inject constructor(
    private val getMoviesGenreUseCase: GetMoviesGenreUseCase
) : ViewModel() {

    private val _stateMoviesGenre = MutableStateFlow(MoviesState())
    val stateMoviesGenre = _stateMoviesGenre.asStateFlow()

    private var jobMovieGenres: Job? = null

    init {
        getGenreMoviesFromTMDB()
    }

    private fun getGenreMoviesFromTMDB() {
        jobMovieGenres?.cancel()
        jobMovieGenres = getMoviesGenreUseCase.executeGenreMovieFromTMDB().onEach {

            when (it) {
                is Resource.Success -> {
                    _stateMoviesGenre.value =
                        MoviesState(moviesGenre = it.data?.genres ?: emptyList())
                }

                is Resource.Error -> {
                    _stateMoviesGenre.value = MoviesState(error = it.message ?: "Error!")
                }

                is Resource.Loading -> {
                    _stateMoviesGenre.value = MoviesState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}
