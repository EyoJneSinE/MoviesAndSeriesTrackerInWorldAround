package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.model.NowPlayingMovies

class MovieListDiffCallBack :DiffUtil.ItemCallback<NowPlayingMovies.Movies>() {
    override fun areItemsTheSame(
        oldItem: NowPlayingMovies.Movies,
        newItem: NowPlayingMovies.Movies
    ): Boolean {
        return oldItem.nowPlayingMoviesTitle == newItem.nowPlayingMoviesTitle
    }

    override fun areContentsTheSame(
        oldItem: NowPlayingMovies.Movies,
        newItem: NowPlayingMovies.Movies
    ): Boolean {
        return oldItem == newItem
    }

}
