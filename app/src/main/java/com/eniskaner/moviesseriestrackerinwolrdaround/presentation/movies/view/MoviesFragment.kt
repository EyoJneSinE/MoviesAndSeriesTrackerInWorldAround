package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies.MoviesResult
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.moviedb.movies_genre.MoviesGenre
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentMoviesBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.adapter.MovieListAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.event.MoviesEvent
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.model.NowPlayingMovies
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel.MoviesGenreViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel.MoviesSearchViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel.MoviesViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.viewModel.NowPlayingMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    private val movieGenreViewModel: MoviesGenreViewModel by viewModels()
    private val movieSearchViewModel: MoviesSearchViewModel by viewModels()
    private val movieViewModel: MoviesViewModel by viewModels()
    private val nowPlayingMoviesViewModel: NowPlayingMoviesViewModel by viewModels()
    private val navController: NavController by lazy {
        findNavController()
    }

    override fun setBinding(): FragmentMoviesBinding =
        FragmentMoviesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewsWithSearch()
        observeGenreViewModel()
        observeMovieViewModel()

    }

    private fun setupViewsWithSearch() {
        binding.apply {
            moviesSearchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val searchingMovieText = s.toString()
                    val isHintDisplayed = searchingMovieText.isEmpty()
                    if (isHintDisplayed) {
                        binding.moviesSearchEditText.setHint(R.string.search_hint)
                    } else {
                        binding.moviesSearchEditText.setHint(R.string.empty_hint)
                    }

                    movieSearchViewModel.onEvent(MoviesEvent.SearchMovies(searchingMovieText))
                    searchingMovie(searchingMovieText)

                }

                override fun afterTextChanged(s: Editable?) {}
            })
            moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        }
    }

    private fun searchingMovie(search: String) {
        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieSearchViewModel.apply {
                        stateSearchMovies.collect { searchingResource ->
                            val searchingMovieList =
                                searchingResource.moviesSearching.map { searchResult ->
                                    moviesResultToNowPlayingMovies(searchResult)
                                }
                            val results = searchingMovieList.filter {
                                it.nowPlayingMoviesTitle.lowercase()
                                    .contains(search.lowercase().trim(), ignoreCase = true)
                            }
                            searchingMovieList.let {
                                if (search.isNotEmpty()) {
                                    binding.moviesRecyclerView.adapter =
                                        MovieListAdapter { searchingMovie ->
                                            navigateToMovieDetailsFragment(searchingMovie)
                                        }.apply { submitList(results) }
                                } else {
                                    observeMovieViewModel()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeMovieViewModel() {
        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                    nowPlayingMoviesViewModel.apply {
                        stateNowPlayingMovies.collect { resource ->

                            val nowPlayingMovieList = resource.moviesNowPlaying.map { movieResult ->
                                moviesResultToNowPlayingMovies(movieResult)
                            }
                            nowPlayingMovieList?.let {
                                binding.moviesRecyclerView.adapter = MovieListAdapter { movie ->
                                    navigateToMovieDetailsFragment(movie)
                                }.apply { submitList(nowPlayingMovieList) }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeGenreViewModel() {
        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieGenreViewModel.apply {
                        val genreResource = stateMoviesGenre.value
                        if (genreResource.moviesGenre.isNotEmpty()) {
                            val genreMovieList = genreResource.moviesGenre.map {
                                movieGenresToNowPlayingMovies(it)
                            }
                        }
                        /*stateMoviesGenre.collect {genreResource ->
                            val genreMovieList = genreResource.moviesGenre.map {
                                movieGenresToNowPlayingMovies(it)
                            }
                        }*/
                    }
                }
            }
        }
    }

    private fun movieGenresToNowPlayingMovies(moviesGenre: MoviesGenre): MoviesGenre {
        return MoviesGenre(
            id = moviesGenre.id ?: 0,
            name = moviesGenre.name ?: ""
        )
    }

    private fun moviesResultToNowPlayingMovies(moviesResult: MoviesResult): NowPlayingMovies.Movies {

        val movieGenre =
            movieGenreViewModel.stateMoviesGenre.value.moviesGenre.filter { moviesGenre ->
                moviesResult.genreIds.any { it == moviesGenre.id }
            }
        val movieGenreName = movieGenre.map { moviesGenreName ->
            moviesGenreName.name
        }

        return NowPlayingMovies.Movies(
            nowPlayingMoviesId = moviesResult.id ?: 0,
            nowPlayingMoviesTitle = moviesResult.originalTitle ?: "",
            nowPlayingMoviesGenre = movieGenreName ?: emptyList(),
            nowPlayingMoviesReleaseDate = moviesResult.releaseDate ?: "",
            nowPlayingMoviesPoster = moviesResult.posterPath ?: ""
        )
    }

    private fun navigateToMovieDetailsFragment(movies: NowPlayingMovies.Movies) {
        val bundle = bundleOf(
            "moviesId" to movies.nowPlayingMoviesId
        )
        navController.navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
    }

}
