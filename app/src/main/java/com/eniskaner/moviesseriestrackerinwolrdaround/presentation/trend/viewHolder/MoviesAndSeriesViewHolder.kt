package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.TrendingMoviesAndSeriesElementsRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.TrendingAdapterListener
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.POSTER_URL
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class MoviesAndSeriesViewHolder(
    private val binding: TrendingMoviesAndSeriesElementsRowBinding,
    private val trendingAdapterListener: TrendingAdapterListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTrendingMoviesAndSeries(trendingMoviesAndSeriesItem: TrendingDataModel.TrendingMoviesAndSeries) {
        val movie = trendingMoviesAndSeriesItem.movie
        val series = trendingMoviesAndSeriesItem.series

        binding.run {
            trendingMoviesTitleTextView.text = movie?.moviesTitle
            trendingMoviesAirDateTextView.text = movie?.moviesAirDate
            val trendingMoviePoster = POSTER_URL + movie?.moviesPoster
            trendingMoviesImageView.load(trendingMoviePoster)
            trendingSeriesTitleTextView.text = series?.seriesTitle
            trendingSeriesAirDateTextView.text = series?.seriesFirstAirDate
            val trendingSeriesPoster = POSTER_URL + series?.seriesPoster
            trendingSeriesImageView.load(trendingSeriesPoster)
        }

        binding.trendingMoviesImageView.setOnClickListener {
            trendingAdapterListener.onMoviesClick(movie)
        }

        binding.trendingSeriesImageView.setOnClickListener {
            trendingAdapterListener.onSeriesClick(series)
        }
    }
}
