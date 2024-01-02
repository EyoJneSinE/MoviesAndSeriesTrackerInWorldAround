package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel

interface TrendingAdapterListener {

    fun onSeriesClick(series: TrendingDataModel.TrendingSeries?)
    fun onMoviesClick(movie: TrendingDataModel.TrendingMovies?)

}