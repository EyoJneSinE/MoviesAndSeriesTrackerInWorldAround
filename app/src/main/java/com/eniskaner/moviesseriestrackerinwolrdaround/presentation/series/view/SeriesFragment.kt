package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.series_genre.SeriesGenre
import com.eniskaner.moviesseriestrackerinwolrdaround.data.remote.seriesdb.top_rated_series.SeriesResult
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentSeriesBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.adapter.SeriesListAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.event.SeriesEvent
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.model.TopRatedSeries
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel.SeriesGenreViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel.SeriesSearchViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel.SeriesViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.viewModel.TopRatedSeriesViewModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.launchAndRepeatWithViewLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {

    private val seriesGenreViewModel: SeriesGenreViewModel by viewModels()
    private val seriesSearchViewModel: SeriesSearchViewModel by viewModels()
    private val seriesViewModel: SeriesViewModel by viewModels()
    private val topRatedSeriesViewModel: TopRatedSeriesViewModel by viewModels()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun setBinding(): FragmentSeriesBinding =
        FragmentSeriesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewsWithSeriesSearch()
        observeSeriesGenreViewModel()
        observeSeriesViewModel()
    }

    private fun setupViewsWithSeriesSearch() {
        binding.apply {
            seriesSearchEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val searchingSeriesText = s.toString()
                    val isHintDisplayed = searchingSeriesText.isEmpty()
                    if (isHintDisplayed) {
                        binding.seriesSearchEditText.setHint(R.string.search_hint)
                    } else {
                        binding.seriesSearchEditText.setHint(R.string.empty_hint)
                    }

                    seriesSearchViewModel.onEvent(SeriesEvent.SearchSeries(searchingSeriesText))
                    searchingSeries(searchingSeriesText)

                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
            seriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun searchingSeries(search: String) {
        viewLifecycleOwner.lifecycleScope.apply {
            launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    seriesSearchViewModel.apply {
                        stateSearchSeries.collect { searchingResource ->
                            val searchingMovieList =
                                searchingResource.searchingSeries.map { searchResult ->
                                    seriesResultToTopRatedSeries(searchResult)
                                }
                            val results = searchingMovieList.filter {
                                it.topRatedSeriesTitle.lowercase()
                                    .contains(search.lowercase().trim(), ignoreCase = true)
                            }
                            searchingMovieList?.let {
                                if (search.isNotEmpty()) {
                                    binding.seriesRecyclerView.adapter =
                                        SeriesListAdapter { searchingSeries ->
                                            navigateToSeriesDetailsFragment(searchingSeries)
                                        }.apply { submitList(results) }
                                } else {
                                    observeSeriesViewModel()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeSeriesViewModel() {
        launchAndRepeatWithViewLifecycle {
            launch {
                seriesViewModel.apply {
                    stateSeries.collect { resource ->

                        val topRatedSeriesList = resource.series.map { seriesResult ->
                            seriesResultToTopRatedSeries(seriesResult)
                        }

                        topRatedSeriesList.let {
                            binding.seriesRecyclerView.adapter = SeriesListAdapter { series ->
                                navigateToSeriesDetailsFragment(series)
                            }.apply { submitList(topRatedSeriesList) }
                        }
                    }
                }
            }
        }
    }

    private fun observeSeriesGenreViewModel() {
        launchAndRepeatWithViewLifecycle {
            launch {
                seriesGenreViewModel.apply {
                    val genreResource = stateSeriesGenre.value
                    if (genreResource != null && genreResource.seriesGenre.isNotEmpty()) {
                        val genreMovieList = genreResource.seriesGenre.map {
                            seriesGenresToTopRatedSeries(it)
                        }
                    }
                }
            }
        }
    }

    private fun seriesGenresToTopRatedSeries(seriesGenre: SeriesGenre): SeriesGenre {
        return SeriesGenre(
            id = seriesGenre.id ?: 0,
            name = seriesGenre.name ?: ""
        )
    }

    private fun seriesResultToTopRatedSeries(seriesResult: SeriesResult): TopRatedSeries.Series {

        val seriesGenre =
            seriesGenreViewModel.stateSeriesGenre.value.seriesGenre.filter { moviesGenre ->
                seriesResult.genreIds.any { it == moviesGenre.id }
            }

        val seriesGenreName = seriesGenre.map { moviesGenreName ->
            moviesGenreName.name
        }

        return TopRatedSeries.Series(
            topRatedSeriesId = seriesResult.id ?: 0,
            topRatedSeriesTitle = seriesResult.name ?: "",
            topRatedSeriesGenre = seriesGenreName ?: emptyList(),
            topRatedSeriesFirstAirDate = seriesResult.firstAirDate ?: "",
            topRatedSeriesPoster = seriesResult.posterPath ?: ""
        )
    }

    private fun navigateToSeriesDetailsFragment(series: TopRatedSeries.Series) {
        val bundle = bundleOf(
            "seriesId" to series.topRatedSeriesId
        )
        navController.navigate(R.id.action_seriesFragment_to_seriesDetailsFragment, bundle)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.d("Navigation", "Navigating to destination: ${destination.label}")
        }
    }

}
