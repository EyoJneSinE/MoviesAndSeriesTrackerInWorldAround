package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_cast

data class CastingForMovieFromTMDB(
    val cast: List<MovieCast>,
    val crew: List<MovieCrew>,
    val id: Int?
)
