package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.model.NowPlayingMovies

class MovieListDiffCallBack : DiffUtil.ItemCallback<NowPlayingMovies.Movies>() {

    override fun areItemsTheSame(
        oldItem: NowPlayingMovies.Movies,
        newItem: NowPlayingMovies.Movies
    ): Boolean =
        oldItem.nowPlayingMoviesTitle == newItem.nowPlayingMoviesTitle ||
                oldItem.nowPlayingMoviesId == newItem.nowPlayingMoviesId ||
                oldItem.nowPlayingMoviesGenre == newItem.nowPlayingMoviesGenre ||
                oldItem.nowPlayingMoviesPoster == newItem.nowPlayingMoviesPoster ||
                oldItem.nowPlayingMoviesReleaseDate == newItem.nowPlayingMoviesReleaseDate

    override fun areContentsTheSame(
        oldItem: NowPlayingMovies.Movies,
        newItem: NowPlayingMovies.Movies
    ): Boolean = oldItem == newItem

}
