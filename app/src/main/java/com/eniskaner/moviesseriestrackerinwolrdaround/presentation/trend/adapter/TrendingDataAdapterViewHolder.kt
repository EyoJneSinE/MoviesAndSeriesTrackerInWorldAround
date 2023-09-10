package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIE
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIES_AND_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.HorizontalViewPagerViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.MoviesAndSeriesViewHolder
/*
class TrendingDataAdapterViewHolder(trendingItemView: View) : RecyclerView.ViewHolder(trendingItemView) {

    val horizontalViewPagerData = mutableListOf<TrendingDataModel.TrendingHorizontalViewPager>()
    val movieAndSeriesData = mutableListOf<TrendingDataModel.TrendingMoviesAndSeries>()

    fun bindTrending(trendingDataModel: TrendingDataModel) {
        Log.d("TrendingDataAdapterViewHolder", "Binding Trending All Data Models - $trendingDataModel")
        when (trendingDataModel.type()) {
            TYPE_TRENDING_HORIZONTAL_VIEW_PAGER -> {
                val horizontalViewPagerItem = trendingDataModel as HorizontalViewPagerAdapter
                horizontalViewPagerItem.setData(horizontalViewPagerData)
            }
            TYPE_TRENDING_MOVIES_AND_SERIES -> {
                val trendingMovieAndSeriesItems = trendingDataModel as MoviesAndSeriesViewHolder
                trendingMovieAndSeriesItems.bindTrendingMoviesAndSeries(movieAndSeriesData)
            }
            TYPE_TRENDING_MOVIE -> {
                val trendingMovieItem = trendingDataModel as MoviesAndSeriesViewHolder
                trendingMovieItem.bindTrendingMovies(trendingMovieItem)
            }
            TYPE_TRENDING_SERIES -> {
                val trendingSeriesItem = trendingDataModel as MoviesAndSeriesViewHolder
                trendingSeriesItem.bindTrendingSeries(trendingSeriesItem)
            }
        }
    }

}*/
