package com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_video

data class GetTrailerFromMovieId(
    val id: Int?,
    val results: List<MoviesTrailerResult>
)
