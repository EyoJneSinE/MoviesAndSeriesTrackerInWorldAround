package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentMoviesBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment


class MoviesFragment : BaseFragment<FragmentMoviesBinding>() {

    override fun setBinding(): FragmentMoviesBinding =
        FragmentMoviesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}