package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.model.TopRatedSeries

class SeriesListDiffCallBack : DiffUtil.ItemCallback<TopRatedSeries.Series>() {

    override fun areItemsTheSame(
        oldItem: TopRatedSeries.Series,
        newItem: TopRatedSeries.Series
    ): Boolean = oldItem.topRatedSeriesTitle == newItem.topRatedSeriesTitle ||
            oldItem.topRatedSeriesPoster == newItem.topRatedSeriesPoster ||
            oldItem.topRatedSeriesId == newItem.topRatedSeriesId ||
            oldItem.topRatedSeriesGenre == newItem.topRatedSeriesGenre ||
            oldItem.topRatedSeriesFirstAirDate == newItem.topRatedSeriesFirstAirDate

    override fun areContentsTheSame(
        oldItem: TopRatedSeries.Series,
        newItem: TopRatedSeries.Series
    ): Boolean = oldItem == newItem

}
