package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies.MoviesResult
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.seriesdb.top_rated_series.SeriesResult
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentTrendingBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.HorizontalViewPagerAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.TrendingDataAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel.TrendingMoviesPartViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewModel.TrendingSeriesPartViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {

    private val trendingMoviesViewModel: TrendingMoviesPartViewModel by viewModels()
    private val trendingSeriesViewModel: TrendingSeriesPartViewModel by viewModels()
    private val trendingDataAdapter: TrendingDataAdapter by lazy {
        TrendingDataAdapter()
    }
    /*private val trendingHorizontalViewPagerAdapter: HorizontalViewPagerAdapter by lazy {
        HorizontalViewPagerAdapter()
    }*/

    override fun setBinding(): FragmentTrendingBinding =
        FragmentTrendingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val layoutManager = GridLayoutManager(binding.trendingRecyclerView.context, 4)

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position > 3) 1 else 2
            }
        }

        binding.trendingRecyclerView.apply {
            hasFixedSize()
            adapter = trendingDataAdapter
        }
        /*binding.trendingRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = trendingHorizontalViewPagerAdapter
        }*/

        Log.d("TrendingFragment", "ViewModels initialized")

        viewLifecycleOwner.lifecycleScope.launch {
            val trendingData = combine(
                trendingMoviesViewModel.trendingMoviesState,
                trendingSeriesViewModel.trendingSeriesState
            ) { moviesState, seriesState ->
                val trendingDataList = mutableListOf<TrendingDataModel>()

                // Horizontal ViewPager data ekle
                val trendingHorizontalViewPagerData =
                    moviesState.trendingMovies.map { movieHorizontalPager ->
                        TrendingDataModel.TrendingHorizontalViewPager(
                            horizontalViewPagerPoster = movieHorizontalPager.backdropPath ?: "",
                            horizontalViewPagerTitle = movieHorizontalPager.title ?: ""
                        )
                    }

                /*val trendingHorizontalPagerDataList =
                    moviesState.trendingMovies.map { movieHorizontalPager ->
                        TrendingDataModel.TrendingHorizontal(
                            trendingHorizontal = listOf(TrendingDataModel.TrendingHorizontalViewPager(
                                horizontalViewPagerPoster = movieHorizontalPager.backdropPath ?: "",
                                horizontalViewPagerTitle = movieHorizontalPager.title ?: ""
                            ))
                        )
                    }*/
                trendingDataList.addAll(trendingHorizontalViewPagerData)
                /*trendingDataList.addAll(trendingHorizontalViewPagerData)*/
                /*trendingDataAdapter.addHorizontalItems(trendingHorizontalPagerDataList)*/

                /*val trendingHorizontalData = moviesState.trendingMovies.map { horizontalResult ->
                    trendingHorizontalResult(horizontalResult)
                }

                val totalHorizontalItems = trendingHorizontalData.size

                if (trendingHorizontalData.isNotEmpty()) {
                    for (i in 0 until totalHorizontalItems) {
                        if (i < trendingHorizontalData.size) {
                            trendingDataList.add(TrendingDataModel.TrendingHorizontal(
                                trendingHorizontal = if (i < trendingHorizontalData.size) {
                                    TrendingDataModel.TrendingHorizontalViewPager(
                                        horizontalViewPagerTitle = trendingHorizontalData[i].horizontalViewPagerTitle,
                                        horizontalViewPagerPoster = trendingHorizontalData[i].horizontalViewPagerPoster
                                    )
                                } else null
                            ))
                        }
                    }
                }*/

                val moviesData = moviesState.trendingMovies.map { movieResult ->
                    moviesResultToTrendingMovies(movieResult)
                }

                val seriesData = seriesState.trendingSeries.map { seriesResult ->
                    seriesResultToTrendingSeries(seriesResult)
                }

                val totalItems = moviesData.size.coerceAtLeast(seriesData.size)

                if (moviesData.isNotEmpty() && seriesData.isNotEmpty()) {
                    for (i in 0 until totalItems) {
                        if (i < moviesData.size) {
                            trendingDataList.add(TrendingDataModel.TrendingMoviesAndSeries(
                                movie = if (i < moviesData.size) {
                                    TrendingDataModel.TrendingMovies(
                                        moviesPoster = moviesData[i].moviesPoster,
                                        moviesAirDate = moviesData[i].moviesAirDate,
                                        moviesTitle = moviesData[i].moviesTitle
                                    )
                                } else null,
                                series = if (i < seriesData.size) {
                                    TrendingDataModel.TrendingSeries(
                                        seriesPoster = seriesData[i].seriesPoster,
                                        seriesTitle = seriesData[i].seriesTitle,
                                        seriesFirstAirDate = seriesData[i].seriesFirstAirDate
                                    )
                                } else null
                            ))
                        }
                    }
                }


                trendingDataList
            }.collect { trendingData ->


                /*if (trendingData.contains(DisplayItem.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER as? TrendingDataModel.TrendingHorizontalViewPager)  ) {
                    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    binding.trendingRecyclerView.apply {
                        adapter = trendingHorizontalViewPagerAdapter
                        hasFixedSize()

                    }
                } else {
                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                }*/

                trendingDataAdapter.submitList(trendingData)
                Log.d("TrendingFragment", "Trending Data: $trendingData")
            }
        }
    }
    fun moviesResultToTrendingMovies(moviesResult: MoviesResult): TrendingDataModel.TrendingMovies {
        return TrendingDataModel.TrendingMovies(
            moviesPoster = moviesResult.posterPath ?: "",
            moviesTitle = moviesResult.title ?: "",
            moviesAirDate = moviesResult.releaseDate ?: ""
        )
    }

    fun seriesResultToTrendingSeries(seriesResult: SeriesResult): TrendingDataModel.TrendingSeries {
        return TrendingDataModel.TrendingSeries(
            seriesPoster = seriesResult.posterPath ?: "",
            seriesTitle = seriesResult.name ?: "",
            seriesFirstAirDate = seriesResult.firstAirDate ?: ""
        )
    }

    fun trendingHorizontalResult(trendingResult: MoviesResult): TrendingDataModel.TrendingHorizontalViewPager{
        return TrendingDataModel.TrendingHorizontalViewPager(
            horizontalViewPagerTitle = trendingResult.title ?: "",
            horizontalViewPagerPoster = trendingResult.posterPath ?: ""
        )
    }
}

    /*// Movies ve Series verilerini sırayla ekle
    val moviesData = moviesState.trendingMovies.map { movie ->

        TrendingDataModel.TrendingMovies(
            moviesPoster = movie.posterPath ?: "",
            moviesTitle = movie.title ?: "",
            moviesAirDate = movie.releaseDate ?: ""
        )
    }
    val seriesData = seriesState.trendingSeries.map { series ->

        TrendingDataModel.TrendingSeries(
            seriesPoster = series.posterPath ?: "",
            seriesTitle = series.name ?: "",
            seriesFirstAirDate = series.firstAirDate ?: ""
        )
    }
    TrendingDataModel.TrendingMoviesAndSeries(movie = moviesData, series = seriesData)
    val groupedTrendingData = groupMoviesAndSeries(moviesData, seriesData)
    groupedTrendingData
}.collect { trendingData ->
    trendingDataAdapter.setData(trendingData)
    Log.d("TrendingFragment", "Trending Data: $trendingData")
}*/
/*val combinedData = mutableListOf<TrendingDataModel>()

    var movieIndex = 0
    var seriesIndex = 0

    while (movieIndex < moviesData.size || seriesIndex < seriesData.size) {
        if (movieIndex < moviesData.size) {
            combinedData.add(moviesData[movieIndex])
            movieIndex++
        }
        if (seriesIndex < seriesData.size) {
            combinedData.add(seriesData[seriesIndex])
            seriesIndex++
        }
    }
    trendingDataList.addAll(trendingHorizontalViewPagerData)
    trendingDataList.addAll(combinedData)
    combinedData
}.collect { trendingData ->
    trendingDataAdapter.setData(trendingData)
    Log.d("TrendingFragment", "Trending Data: $trendingData")
}*/

/*val groupedTrendingData = groupMoviesAndSeries(moviesData, seriesData)
groupedTrendingData
}.collect { trendingData ->
trendingDataAdapter.setData(trendingData)
Log.d("TrendingFragment", "Trending Data: $trendingData")
}*/
/*TrendingDataModel.TrendingMoviesAndSeries(
                        series = TrendingDataModel.TrendingSeries(
                            seriesFirstAirDate = "",
                            seriesPoster = "",
                            seriesTitle = "",
                        ),
                        movie = TrendingDataModel.TrendingMovies(
                            moviesTitle = movie.title ?: "",
                            moviesAirDate = movie.releaseDate ?: "",
                            moviesPoster = movie.posterPath ?: ""
                        )
                    )*/

/*TrendingDataModel.TrendingMoviesAndSeries(
                        series = TrendingDataModel.TrendingSeries(
                            seriesFirstAirDate = series.firstAirDate ?: "",
                            seriesPoster = series.posterPath ?: "",
                            seriesTitle = series.name ?: "",
                        ),
                        movie = TrendingDataModel.TrendingMovies(
                            moviesTitle = "",
                            moviesAirDate = "",
                            moviesPoster = ""
                        )
                    )*/
/*trendingDataList.addAll(moviesData)
trendingDataList.addAll(seriesData)

trendingDataList
}.collect { trendingData ->
trendingDataAdapter.setData(trendingData)
Log.d("TrendingFragment", "Trending Data: $trendingData")
}*/
/*viewLifecycleOwner.lifecycleScope.launch {
            trendingMoviesViewModel.trendingMoviesState.collect{trendingHorizontalPagerState ->
                val trendingHorizontalViewPagerData = trendingHorizontalPagerState.trendingMovies.map { movieHorizontalPager ->
                    TrendingDataModel.TrendingHorizontalViewPager(
                        horizontalViewPagerPoster = movieHorizontalPager.backdropPath ?: "",
                        horizontalViewPagerTitle = movieHorizontalPager.title ?: ""
                    )
                }
                trendingDataAdapter.setData(trendingHorizontalViewPagerData)
                Log.d("TrendingFragment", "Horizontal View Pager Data: $trendingHorizontalViewPagerData")
            }
        }*/
/*// Verileri ViewModel'den doğrudan alma ve dönüştürme aşaması
viewLifecycleOwner.lifecycleScope.launch {
    trendingMoviesViewModel.trendingMoviesState.collect{trendingMoviesState ->
        val trendingMoviesData = trendingMoviesState.trendingMovies.map { movie->
            TrendingDataModel.TrendingMoviesAndSeries(
                moviesPoster = movie.posterPath ?: "",
                moviesTitle = movie.title ?: "",
                moviesAirDate = movie.releaseDate ?: "",
                seriesPoster = "",
                seriesTitle = "",
                seriesFirstAirDate = ""
            )
        }
        trendingDataAdapter.setData(trendingMoviesData)
        Log.d("TrendingFragment", "Trending Movies Data: $trendingMoviesData")
    }
}

viewLifecycleOwner.lifecycleScope.launch {
    trendingSeriesViewModel.trendingSeriesState.collect {trendingSeriesState ->
        val trendingSeriesData = trendingSeriesState.trendingSeries.map { series ->
            TrendingDataModel.TrendingMoviesAndSeries(
                seriesPoster = series.posterPath ?: "",
                seriesTitle = series.name ?: "",
                seriesFirstAirDate = series.firstAirDate ?: "",
                moviesPoster = "",
                moviesAirDate = "",
                moviesTitle = "",
            )
        }
        trendingDataAdapter.setData(trendingSeriesData)
        Log.d("TrendingFragment", "Trending Series Data: $trendingSeriesData")
    }
}*/

/*private fun getMockDataTrending(
    trendingMoviesData: List<TrendingDataModel.TrendingMovies>,
    trendingSeriesData: List<TrendingDataModel.TrendingSeries>,
    trendingHorizontalViewPagerData: List<TrendingDataModel.TrendingHorizontalViewPager>,
): List<TrendingDataModel> {
    val trendingHorizontalViewPagerPosterPath = trendingHorizontalViewPagerData.map { it.horizontalViewPagerPoster }
    Log.d("TrendingFragment", "Trending Horizontal View Pager Poster Data: $trendingHorizontalViewPagerPosterPath")
    val trendingHorizontalViewPagerTitle = trendingHorizontalViewPagerData.map { it.horizontalViewPagerTitle }
    Log.d("TrendingFragment", "Trending Horizontal View Pager Title Data: $trendingHorizontalViewPagerTitle")
    val trendingMoviesPosterPath = trendingMoviesData.map { it.moviesPoster }
    Log.d("TrendingFragment", "Trending Movie Poster Data: $trendingMoviesPosterPath")
    val trendingMoviesTitle = trendingMoviesData.map { it.moviesTitle }
    Log.d("TrendingFragment", "Trending Movie Title Data: $trendingMoviesTitle")
    val trendingMoviesReleaseDate = trendingMoviesData.map { it.moviesAirDate }
    Log.d("TrendingFragment", "Trending Movies Release Date Data: $trendingMoviesReleaseDate")
    val trendingSeriesPosterPath = trendingSeriesData.map { it.seriesPoster }
    Log.d("TrendingFragment", "Trending Series Poster Data: $trendingSeriesPosterPath")
    val trendingSeriesName = trendingSeriesData.map { it.seriesTitle }
    Log.d("TrendingFragment", "Trending Series Name Data: $trendingSeriesName")
    val trendingSeriesFirstAirDate = trendingSeriesData.map { it.seriesFirstAirDate }
    Log.d("TrendingFragment", "Trending Series First Air Date Data: $trendingSeriesFirstAirDate")


    return listOf(
        TrendingDataModel.TrendingHorizontalViewPager(
            horizontalViewPagerPoster = trendingHorizontalViewPagerPosterPath.firstOrNull() ?: "",
            horizontalViewPagerTitle = trendingHorizontalViewPagerTitle.firstOrNull() ?: ""
        ),
        TrendingDataModel.TrendingMovies(
            moviesPoster = trendingMoviesPosterPath.firstOrNull() ?: "",
            moviesTitle = trendingMoviesTitle.firstOrNull() ?: "",
            moviesAirDate = trendingMoviesReleaseDate.firstOrNull() ?: ""
        ),
        TrendingDataModel.TrendingSeries(
            seriesPoster = trendingSeriesPosterPath.firstOrNull() ?: "",
            seriesTitle = trendingSeriesName.firstOrNull() ?: "",
            seriesFirstAirDate = trendingSeriesFirstAirDate.firstOrNull() ?: ""
        )
    )
}*/
