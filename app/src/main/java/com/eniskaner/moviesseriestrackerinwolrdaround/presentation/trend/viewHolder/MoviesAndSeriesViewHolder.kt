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
    fun bindTrendingMovies(trendingMoviesItem: TrendingDataModel.TrendingMovies) {
        Log.d("TrendingDataAdapterViewHolder", "Binding TrendingMovies: Title - ${trendingMoviesItem.moviesTitle}")
        trendingMoviesAndSeriesElementsRowBinding.apply {
            trendingMoviesTitleTextView.text = trendingMoviesItem.moviesTitle
            trendingMoviesAirDateTextView.text = trendingMoviesItem.moviesAirDate
            val trendingMoviePoster = POSTER_URL + trendingMoviesItem.moviesPoster
            trendingMoviesImageView.load(trendingMoviePoster)
        }
    }
    fun bindTrendingSeries(trendingSeriesItem: TrendingDataModel.TrendingSeries) {
        //viewbinding
        //gridview
        //listadapter
        //type'ların tekillestirmesi
        //viewholder'ların düzenlenmesi

        Log.d("TrendingDataAdapterViewHolder", "Binding TrendingSeries: Title - ${trendingSeriesItem.seriesTitle}")
        trendingMoviesAndSeriesElementsRowBinding.apply {
            trendingSeriesTitleTextView.text = trendingSeriesItem.seriesTitle
            trendingSeriesAirDateTextView.text = trendingSeriesItem.seriesFirstAirDate
            val trendingSeriesPoster = POSTER_URL + trendingSeriesItem.seriesPoster
            trendingSeriesImageView.load(trendingSeriesPoster)
        }
    }

    fun bindTrendingMoviesAndSeries(trendingMoviesAndSeriesItem: TrendingDataModel.TrendingMoviesAndSeries) {
        Log.d("TrendingDataAdapterViewHolder", "Binding TrendingMoviesAndSeries: Title - $trendingMoviesAndSeriesItem")
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