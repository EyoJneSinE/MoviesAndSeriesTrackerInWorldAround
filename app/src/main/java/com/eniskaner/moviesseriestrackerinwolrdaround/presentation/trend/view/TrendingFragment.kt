package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentTrendingBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIE
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIES_AND_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.TrendingAdapterListener
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.TrendingDataAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModelProvider
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel.TrendingSeriesAndMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TrendingFragment : BaseFragment<FragmentTrendingBinding>(), TrendingAdapterListener{

    private val navController: NavController by lazy {
        findNavController()
    }

    private val trendingSeriesAndMoviesViewModel: TrendingSeriesAndMoviesViewModel by viewModels()
    private val trendingDataAdapter: TrendingDataAdapter by lazy {
        TrendingDataAdapter(this@TrendingFragment)
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
                trendingData?.let {
                    trendingDataAdapter.submitList(trendingData)
                }
            }
        }
    }
    fun navigateToMovieDetails(movies: TrendingDataModel.TrendingMovies) {
        val actionMovies = TrendingFragmentDirections.actionTrendingFragmentToMovieDetailsFragment(moviesId = movies.moviesId)
        navController.navigate(actionMovies)
    }
    fun navigateToSeriesDetails(series: TrendingDataModel.TrendingSeries) {
        val actionSeries = TrendingFragmentDirections.actionTrendingFragmentToSeriesDetailsFragment(seriesId = series.seriesId)
        navController.navigate(actionSeries)
    }

    override fun onSeriesClick(series: TrendingDataModel.TrendingSeries?) {
        series?.let {
            navigateToSeriesDetails(series)
        }
    }

    override fun onMoviesClick(movie: TrendingDataModel.TrendingMovies?) {
        movie?.let { navigateToMovieDetails(movie) }
    }
}


