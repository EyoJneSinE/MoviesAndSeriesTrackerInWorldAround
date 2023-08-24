package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state

import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.MovieCrew
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_video.MoviesTrailerResult

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movie : GetMovieDetailsFromId? = null,
    val cast : CastingForMovieFromTMDB? = null,
    val crew : List<MovieCrew> = emptyList(),
    val genre : GenresFromTMDB? = null,
    val trailer : List<MoviesTrailerResult> = emptyList(),
    val error : String = ""
)
