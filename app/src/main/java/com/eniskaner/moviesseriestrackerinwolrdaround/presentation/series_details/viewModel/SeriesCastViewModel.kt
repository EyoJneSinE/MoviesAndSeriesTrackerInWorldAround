package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesDetailsCastAndCrewUseCase
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
class SeriesCastViewModel @Inject constructor(
    private val seriesCastUseCase: GetSeriesDetailsCastAndCrewUseCase,
    private val savedSeriesCastStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateSeriesCast = MutableStateFlow<SeriesDetailState>(SeriesDetailState())
    val stateSeriesCast : StateFlow<SeriesDetailState> = _stateSeriesCast

    private var jobSeriesCast : Job? = null

    init {
        savedSeriesCastStateHandle.get<String>(SERIES_ID.toString())?.let {
            getSeriesCast(it.toInt())
        }
    }

    private fun getSeriesCast(seriesId: Int) {
        jobSeriesCast?.cancel()
        jobSeriesCast = seriesCastUseCase.executeGetSeriesCastFromTMDB(seriesId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeriesCast.value = SeriesDetailState(seriesCast = it.data)
                }
                is Resource.Error -> {
                    _stateSeriesCast.value = SeriesDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateSeriesCast.value = SeriesDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}