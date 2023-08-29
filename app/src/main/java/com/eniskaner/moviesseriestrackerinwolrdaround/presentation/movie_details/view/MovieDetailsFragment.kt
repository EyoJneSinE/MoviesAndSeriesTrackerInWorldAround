package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.view

import android.os.Bundle
import android.view.View
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentMovieDetailsBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    override fun setBinding(): FragmentMovieDetailsBinding =
        FragmentMovieDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}