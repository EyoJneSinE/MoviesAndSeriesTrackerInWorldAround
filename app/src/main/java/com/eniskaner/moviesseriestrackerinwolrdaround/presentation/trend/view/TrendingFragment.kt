package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentTrendingBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.TrendingDataAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModelProvider
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel.TrendingSeriesAndMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {

    private val trendingSeriesAndMoviesViewModel: TrendingSeriesAndMoviesViewModel by viewModels()
    private val trendingDataAdapter: TrendingDataAdapter by lazy {
        TrendingDataAdapter()
    }

    @Inject
    lateinit var dataProvider: TrendingDataModelProvider

    override fun setBinding(): FragmentTrendingBinding =
        FragmentTrendingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        collectTrendingData()
    }

    private fun init() {
        setTrendingRecyclerView()
    }

    private fun setTrendingRecyclerView() {
        binding.trendingRecyclerView.run {
            hasFixedSize()
            adapter = trendingDataAdapter
        }
    }

    private fun collectTrendingData() {
        viewLifecycleOwner.lifecycleScope.launch {
            combine(
                trendingSeriesAndMoviesViewModel.trendingMoviesState,
                trendingSeriesAndMoviesViewModel.trendingSeriesState
                ) {moviesState, seriesState ->
                dataProvider.getTrendingData(
                    moviesState,seriesState
                )
            }.collect { trendingData ->
                trendingDataAdapter.submitList(trendingData)
            }
        }
    }
}
