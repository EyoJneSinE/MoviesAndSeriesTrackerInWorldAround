package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.TrendingMoviesAndSeriesElementsRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIES_AND_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.POSTER_URL
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class MoviesAndSeriesViewHolder(
    private val trendingMoviesAndSeriesElementsRowBinding: TrendingMoviesAndSeriesElementsRowBinding
): RecyclerView.ViewHolder(trendingMoviesAndSeriesElementsRowBinding.root)  {

    fun bindTrendingMoviesAndSeries(trendingMoviesAndSeriesItem: TrendingDataModel.TrendingMoviesAndSeries) {
        val movie = trendingMoviesAndSeriesItem.movie
        val series = trendingMoviesAndSeriesItem.series

        trendingMoviesAndSeriesElementsRowBinding.apply {
            trendingMoviesTitleTextView.text = movie?.moviesTitle
            trendingMoviesAirDateTextView.text = movie?.moviesAirDate
            val trendingMoviePoster = POSTER_URL + movie?.moviesPoster
            trendingMoviesImageView.load(trendingMoviePoster)
            trendingSeriesTitleTextView.text = series?.seriesTitle
            trendingSeriesAirDateTextView.text = series?.seriesFirstAirDate
            val trendingSeriesPoster = POSTER_URL + series?.seriesPoster
            trendingSeriesImageView.load(trendingSeriesPoster)
        }
    }
}

//viewbinding
//gridview
//listadapter
//type'ların tekillestirmesi
//viewholder'ların düzenlenmesi