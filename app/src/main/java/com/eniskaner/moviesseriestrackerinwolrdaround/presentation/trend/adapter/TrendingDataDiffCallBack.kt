package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel

class TrendingDataDiffCallback : DiffUtil.ItemCallback<TrendingDataModel>() {

    override fun areItemsTheSame(oldItem: TrendingDataModel, newItem: TrendingDataModel): Boolean {
        return when {
            (oldItem is TrendingDataModel.TrendingHorizontalViewPager && newItem is TrendingDataModel.TrendingHorizontalViewPager) -> {
                oldItem == newItem
            }

            (oldItem is TrendingDataModel.TrendingMoviesAndSeries && newItem is TrendingDataModel.TrendingMoviesAndSeries) -> {
                oldItem == newItem
            }

            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: TrendingDataModel,
        newItem: TrendingDataModel
    ) = oldItem == newItem
}

class CarouselDiffCallback :
    DiffUtil.ItemCallback<TrendingDataModel.TrendingHorizontalViewPager>() {
    override fun areItemsTheSame(
        oldItem: TrendingDataModel.TrendingHorizontalViewPager,
        newItem: TrendingDataModel.TrendingHorizontalViewPager
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: TrendingDataModel.TrendingHorizontalViewPager,
        newItem: TrendingDataModel.TrendingHorizontalViewPager
    ) = oldItem == newItem

}
