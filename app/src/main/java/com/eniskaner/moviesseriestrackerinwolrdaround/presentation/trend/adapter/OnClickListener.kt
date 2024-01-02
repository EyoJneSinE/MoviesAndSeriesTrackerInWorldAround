package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel

interface OnClickListener {
    fun onItemClick(item: TrendingDataModel)
}