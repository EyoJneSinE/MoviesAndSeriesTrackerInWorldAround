package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_cast.CastingForMovieFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_genre.GenresFromTMDB
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_video.MoviesTrailerResult

data class MovieDetailState(
    val isLoading : Boolean = false,
    val movieDetails :  GetMovieDetailsFromId? = null,
    val movieCast : CastingForMovieFromTMDB? = null,
    val movieCrew : CastingForMovieFromTMDB? = null,
    val movieGenre : GenresFromTMDB? = null,
    val movieTrailer : List<MoviesTrailerResult> = emptyList(),
    val error : String = ""
)
