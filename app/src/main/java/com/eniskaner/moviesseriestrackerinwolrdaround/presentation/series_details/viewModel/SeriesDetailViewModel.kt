package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eniskaner.moviesseriestrackerinwolrdaround.domain.series_usecase.GetSeriesDetailsUseCase
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
class SeriesDetailViewModel @Inject constructor(
    private val seriesDetailsUseCase: GetSeriesDetailsUseCase,
    private val savedSeriesDetailsStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateSeriesDetails = MutableStateFlow<SeriesDetailState>(SeriesDetailState())
    val stateSeriesDetails : StateFlow<SeriesDetailState> = _stateSeriesDetails

    private var jobSeriesDetails : Job? = null

    init {
        savedSeriesDetailsStateHandle.get<String>(SERIES_ID.toString())?.let {
            getSeriesDetails(it.toInt())
        }
    }

    private fun getSeriesDetails(seriesId: Int) {
        jobSeriesDetails?.cancel()
        jobSeriesDetails = seriesDetailsUseCase.executeGetSeriesDetailsFromTMDB(seriesId).onEach {
            when (it) {
                is Resource.Success -> {
                    _stateSeriesDetails.value = SeriesDetailState(seriesDetails = it.data)
                }
                is Resource.Error -> {
                    _stateSeriesDetails.value = SeriesDetailState(error = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _stateSeriesDetails.value = SeriesDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}