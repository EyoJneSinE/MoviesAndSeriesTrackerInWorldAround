package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.model.TopRatedSeries

class SeriesListDiffCallBack : DiffUtil.ItemCallback<TopRatedSeries.Series>() {
    override fun areItemsTheSame(
        oldItem: TopRatedSeries.Series,
        newItem: TopRatedSeries.Series
    ): Boolean {
        return oldItem.topRatedSeriesTitle == newItem.topRatedSeriesTitle
    }

    override fun areContentsTheSame(
        oldItem: TopRatedSeries.Series,
        newItem: TopRatedSeries.Series
    ): Boolean {
        return oldItem == newItem
    }
}