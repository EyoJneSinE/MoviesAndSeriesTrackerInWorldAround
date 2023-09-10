package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel

class TrendingDataDiffCallback : DiffUtil.ItemCallback<TrendingDataModel>() {

    override fun areItemsTheSame(oldItem: TrendingDataModel, newItem: TrendingDataModel): Boolean {
        return when {
            (oldItem is TrendingDataModel.TrendingHorizontalViewPager && newItem is TrendingDataModel.TrendingHorizontalViewPager) -> {
                oldItem.horizontalViewPagerPoster == newItem.horizontalViewPagerPoster ||
                        oldItem.horizontalViewPagerTitle == newItem.horizontalViewPagerTitle
            }
            (oldItem is TrendingDataModel.TrendingMoviesAndSeries && newItem is TrendingDataModel.TrendingMoviesAndSeries) -> {
                oldItem.movie == newItem.movie ||
                        oldItem.series == newItem.series
            }

            (oldItem is TrendingDataModel.TrendingMovies && newItem is TrendingDataModel.TrendingMovies) -> {
                oldItem.moviesTitle == newItem.moviesTitle ||
                        oldItem.moviesAirDate == newItem.moviesAirDate ||
                        oldItem.moviesPoster == newItem.moviesPoster
            }
            (oldItem is TrendingDataModel.TrendingSeries && newItem is TrendingDataModel.TrendingSeries) -> {
                oldItem.seriesTitle == newItem.seriesTitle ||
                        oldItem.seriesFirstAirDate == newItem.seriesFirstAirDate ||
                        oldItem.seriesPoster == newItem.seriesPoster
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: TrendingDataModel, newItem: TrendingDataModel): Boolean {
        return oldItem == newItem
    }
}