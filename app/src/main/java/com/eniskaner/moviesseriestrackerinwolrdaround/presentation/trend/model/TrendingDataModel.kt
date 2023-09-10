package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model

import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIE
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIES_AND_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_SERIES

sealed class TrendingDataModel: DisplayItem {
    data class TrendingMoviesAndSeries(
        val movie: TrendingMovies?,
        val series: TrendingSeries?
    ) : TrendingDataModel(), DisplayItem {
        override fun type(): Int {
            return TYPE_TRENDING_MOVIES_AND_SERIES
        }
    }

    data class TrendingMovies(
        val moviesPoster: String,
        val moviesTitle: String,
        val moviesAirDate: String
        ) : TrendingDataModel(), DisplayItem {
        override fun type(): Int {
            return TYPE_TRENDING_MOVIE
        }
    }
    data class TrendingSeries(
        val seriesPoster: String,
        val seriesTitle: String,
        val seriesFirstAirDate: String
    ) : TrendingDataModel(), DisplayItem {
        override fun type(): Int {
            return TYPE_TRENDING_SERIES
        }
    }
    data class TrendingHorizontal(
        val trendingHorizontal: TrendingHorizontalViewPager?
    ) : TrendingDataModel(), DisplayItem {
        override fun type(): Int {
            return TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
        }
    }

    data class TrendingHorizontalViewPager(
        val horizontalViewPagerPoster: String,
        val horizontalViewPagerTitle: String
    ) : TrendingDataModel(), DisplayItem {
        override fun type(): Int {
            return TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
        }
    }
}
