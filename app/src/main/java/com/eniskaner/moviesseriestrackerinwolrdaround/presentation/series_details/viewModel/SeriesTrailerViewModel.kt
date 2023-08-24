package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesDetailsVideosUseCase
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.state.SeriesDetailState
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.SERIES_ID
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesTrailerViewModel @Inject constructor(
    private val getSeriesTrailerUseCase: GetSeriesDetailsVideosUseCase,
    private val savedSeriesTrailerStateHandle: SavedStateHandle
) : ViewModel() {
    private val _stateSeriesTrailer = MutableStateFlow<SeriesDetailState>(SeriesDetailState())
    val stateSeriesTrailer : StateFlow<SeriesDetailState> = _stateSeriesTrailer

    private var jobSeriesTrailer : Job? = null

    init {
        savedSeriesTrailerStateHandle.get<String>(SERIES_ID.toString())?.let {
            getMovieTrailerFromTMDB(it.toInt())
        }
    }

    private fun getMovieTrailerFromTMDB(seriesId: Int) {
        jobSeriesTrailer?.cancel()
        jobSeriesTrailer = getSeriesTrailerUseCase.executeGetSeriesVideosFromTMDB(seriesId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeriesTrailer.value = SeriesDetailState(seriesTrailer = it.data?.results ?: emptyList())
                }
                is Resource.Error -> {
                    _stateSeriesTrailer.value = SeriesDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateSeriesTrailer.value = SeriesDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}