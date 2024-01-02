package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movie_details.GetMovieDetailsFromId
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.MovieCast
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_cast.MovieCrew
import com.eniskaner.eyojmovietrackerwithcompose.data.remote.moviedb.movies_video.MoviesTrailerResult
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentMovieDetailsBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MovieDetailsViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MoviesCastViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MoviesCrewViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MoviesTrailerViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val movieDetailsCastViewModel: MoviesCastViewModel by viewModels()
    private val movieDetailsCrewViewModel: MoviesCrewViewModel by viewModels()
    private val movieDetailsTrailerViewModel: MoviesTrailerViewModel by viewModels()
    /*private val movieDetailsAdapter: MovieDetailsAdapter by lazy {
        MovieDetailsAdapter()
    }*/
   private val movieDetailsList = mutableListOf<MovieDetails>()

    override fun setBinding(): FragmentMovieDetailsBinding =
        FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val movieId = arguments?.getInt("moviesId", 0)
        movieId?.let {
            movieDetailsViewModel.getMovieDetails(it)
        }
        observeMovieDetailsViewModel()
    }

    private fun observeMovieDetailsViewModel() {
        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieDetailsViewModel.apply {
                        stateMovieDetails.collect { resource ->

                            val movieDetailList = resource.movieDetails
                            movieDetailList?.let {
                                getMovieDetailsFromIdToMovieDetails(movieDetailList)
                            }
                            movieDetailList?.let {
                                if (movieDetailList.genres.isNotEmpty()) {
                                    val movieDetailsData = MovieDetails.GetMovieDetailsFromId(
                                        movieDetailsTagline = movieDetailList.tagline ?: "",
                                        movieDetailsReleaseDate = movieDetailList.releaseDate ?: "",
                                        movieDetailsOverview = movieDetailList.overview ?: "",
                                        movieDetailsOriginalTitle = movieDetailList.originalTitle ?: "",
                                        movieDetailsGenre =  "",
                                        movieDetailsVerticalPoster = movieDetailList.posterPath ?: "",
                                        movieDetailsHorizontalPoster = movieDetailList.backdropPath ?: ""
                                    )
                                    movieDetailsList.add(movieDetailsData)
                                    binding.movieDetailsRecyclerView.adapter = MovieDetailsAdapter {movieDetails ->

                                    }.apply { submitList(movieDetailsList) }
                                }
                            }
                        }
                    }
                }
            }
        }
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
            movieDetailsTrailerKey = getMovieDetailsTrailer.key ?: ""
        )
    }

    private fun getMovieDetailsFromIdToMovieDetails(
        getMovieDetailsFromId: GetMovieDetailsFromId
    ): MovieDetails.GetMovieDetailsFromId {
        return MovieDetails.GetMovieDetailsFromId(
            movieDetailsHorizontalPoster = getMovieDetailsFromId.backdropPath ?: "",
            movieDetailsVerticalPoster = getMovieDetailsFromId.posterPath ?: "",
            movieDetailsGenre =  "",
            movieDetailsOriginalTitle = getMovieDetailsFromId.originalTitle ?: "",
            movieDetailsOverview = getMovieDetailsFromId.overview ?: "",
            movieDetailsReleaseDate = getMovieDetailsFromId.releaseDate ?: "",
            movieDetailsTagline = getMovieDetailsFromId.tagline ?: ""
        )
    }
}