package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentMovieDetailsBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsAdapterListener
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetailsDataProvider
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MovieDetailsViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MoviesCastViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MoviesCrewViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewModel.MoviesTrailerViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(),
    MovieDetailsAdapterListener {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val movieDetailsCastViewModel: MoviesCastViewModel by viewModels()
    private val movieDetailsCrewViewModel: MoviesCrewViewModel by viewModels()
    private val movieDetailsTrailerViewModel: MoviesTrailerViewModel by viewModels()

    @Inject
    lateinit var dataProvider: MovieDetailsDataProvider

    private val movieDetailsAdapter: MovieDetailsAdapter by lazy {
        MovieDetailsAdapter(this@MovieDetailsFragment)
    }

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun setBinding(): FragmentMovieDetailsBinding =
        FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieDetailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val movieId = arguments?.getInt("moviesId", 0)
        movieId?.let {
            movieDetailsViewModel.getMovieDetails(it)
            movieDetailsCastViewModel.getMovieCast(it)
            movieDetailsCrewViewModel.getMovieCrew(it)
            movieDetailsTrailerViewModel.getMovieTrailer(it)
        }
        init()
        getMovieDetailsData()
    }

    private fun init() {
        setTrendingRecyclerView()
    }

    private fun setTrendingRecyclerView() {
        binding.movieDetailsRecyclerView.run {
            hasFixedSize()
            adapter = movieDetailsAdapter
        }
    }

    private fun getMovieDetailsData() {
        launchAndRepeatWithViewLifecycle {
            launch {
                combine(
                    movieDetailsViewModel.stateMovieDetails,
                    movieDetailsCastViewModel.stateMovieCast,
                    movieDetailsCrewViewModel.stateMovieCrew,
                    movieDetailsTrailerViewModel.stateMovieTrailer
                ) { movieDetailsState, movieDetailsCastState, movieDetailsCrewState, movieDetailsTrailerState ->
                    dataProvider.getMovieDetailsData(
                        movieDetailsState,
                        movieDetailsCastState,
                        movieDetailsCrewState,
                        movieDetailsTrailerState
                    )
                }.collect { movieDetailListData ->
                    movieDetailListData.let {
                        movieDetailsAdapter.submitList(movieDetailListData)
                    }
                }
            }
        }
    }

    private fun navigateToMovieDetails(trailer: MovieDetails.GetMovieDetailsTrailer?) {
        val bundle = bundleOf(
            "trailerName" to trailer?.movieDetailsTrailerName,
            "trailerKey" to trailer?.movieDetailsTrailerKey,
            "trailerType" to trailer?.movieDetailsTrailerType
        )
        navController.navigate(
            R.id.action_movieDetailsFragment_to_bottomSheetTrailerFragment,
            bundle
        )
    }

    override fun onTrailerClick(trailer: MovieDetails.GetMovieDetailsTrailer?) {
        trailer?.let {
            navigateToMovieDetails(trailer)
        }
    }
}
