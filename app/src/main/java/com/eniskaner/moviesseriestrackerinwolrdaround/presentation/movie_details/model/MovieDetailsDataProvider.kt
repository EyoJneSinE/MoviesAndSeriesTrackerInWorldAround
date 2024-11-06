package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model

import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_cast.MovieCast
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_cast.MovieCrew
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_video.MoviesTrailerResult
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.state.MovieDetailState
import javax.inject.Inject

class MovieDetailsDataProvider @Inject constructor() {

    fun getMovieDetailsData(
        movieDetailsState: MovieDetailState,
        movieDetailsCastState: MovieDetailState,
        movieDetailsCrewState: MovieDetailState,
        movieDetailsTrailerState: MovieDetailState
    ) : List<MovieDetails> {
        val movieDetailsList = mutableListOf<MovieDetails>()
        val movieDetails = mutableListOf<MovieDetails.GetMovieDetailsFromId>()
        val movieDetailsCastList = mutableListOf<MovieDetails.GetMovieDetailsCast>()
        val movieDetailsCrewList = mutableListOf<MovieDetails.GetMovieDetailsCrew>()
        val movieDetailsTrailerList = mutableListOf<MovieDetails.GetMovieDetailsTrailer>()

        //Movie Details Data
        val movieDetailsData = movieDetailsState.movieDetails?.let {
            getMovieDetailsFromIdToMovieDetails(
                it
            )
        }
        if (movieDetailsData != null) {
            movieDetails.add(movieDetailsData)
        }
        movieDetailsList.addAll(movieDetails)

        //Movie Details Cast Data
        val movieDetailsCastData = movieDetailsCastState.movieCast?.cast
        movieDetailsCastData?.let {
            movieDetailsCastList.addAll(
                movieDetailsCastData.map { getMovieDetailsCastToMovieDetails(it) }
            )
            val movieDetailsCastListData = MovieDetails.MovieDetailsCastList(
                movieCastList = movieDetailsCastList
            )
            movieDetailsList.add(movieDetailsCastListData)
        }

        //Movie Details Crew Data
        val movieDetailsCrewData = movieDetailsCrewState.movieCrew?.crew
        movieDetailsCrewData?.let {
            movieDetailsCrewList.addAll(
                movieDetailsCrewData.map { getMovieDetailsCrewToMovieDetails(it) }
            )
            val movieDetailsCrewListData = MovieDetails.MovieDetailsCrewList(
                movieCrewList = movieDetailsCrewList
            )
            movieDetailsList.add(movieDetailsCrewListData)
        }

        //Movie Details Trailer Data
        val movieDetailsTrailerData = movieDetailsTrailerState.movieTrailer
        movieDetailsTrailerData?.let {
            movieDetailsTrailerList.addAll(
                movieDetailsTrailerData.map { getMovieDetailsTrailerToMovieDetails(it) }
            )
            val movieDetailsTrailerListData = MovieDetails.MovieDetailsTrailerList(
                movieTrailerList = movieDetailsTrailerList
            )
            movieDetailsList.add(movieDetailsTrailerListData)
        }
        return movieDetailsList
    }

    private fun getMovieDetailsCastToMovieDetails(
        getMovieDetailsCast: MovieCast
    ): MovieDetails.GetMovieDetailsCast {
        return MovieDetails.GetMovieDetailsCast(
            movieDetailsCastPoster = getMovieDetailsCast.moviesCastProfilePath ?: "",
            movieDetailsCastName = getMovieDetailsCast.moviesCastName ?: "",
            movieDetailsCastCharacterName = getMovieDetailsCast.moviesCastCharacter ?: ""
        )
    }

    private fun getMovieDetailsCrewToMovieDetails(
        getMovieDetailsCrew: MovieCrew
    ): MovieDetails.GetMovieDetailsCrew {
        return MovieDetails.GetMovieDetailsCrew(
            movieDetailsCrewPoster = getMovieDetailsCrew.moviesCrewProfilePath ?: "",
            movieDetailsCrewName = getMovieDetailsCrew.moviesCrewName ?: "",
            movieDetailsCrewJob = getMovieDetailsCrew.moviesCrewJob ?: ""
        )
    }

    private fun getMovieDetailsTrailerToMovieDetails(
        getMovieDetailsTrailer: MoviesTrailerResult
    ): MovieDetails.GetMovieDetailsTrailer {
        return MovieDetails.GetMovieDetailsTrailer(
            movieDetailsTrailerKey = getMovieDetailsTrailer.key ?: "",
            movieDetailsTrailerName = getMovieDetailsTrailer.name ?: "",
            movieDetailsTrailerType = getMovieDetailsTrailer.type ?: ""
        )
    }

    private fun getMovieDetailsFromIdToMovieDetails(
        getMovieDetailsFromId: GetMovieDetailsFromId
    ): MovieDetails.GetMovieDetailsFromId {
        return MovieDetails.GetMovieDetailsFromId(
            movieDetailsHorizontalPoster = getMovieDetailsFromId.backdropPath ?: "",
            movieDetailsVerticalPoster = getMovieDetailsFromId.posterPath ?: "",
            movieDetailsGenre =  getMovieDetailsFromId.genres.joinToString(", ") { it.name.toString() },
            movieDetailsOriginalTitle = getMovieDetailsFromId.originalTitle ?: "",
            movieDetailsOverview = getMovieDetailsFromId.overview ?: "",
            movieDetailsReleaseDate = getMovieDetailsFromId.releaseDate ?: "",
            movieDetailsTagline = getMovieDetailsFromId.tagline ?: ""
        )
    }
}
