package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series_details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentSeriesDetailsBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>() {
    override fun setBinding(): FragmentSeriesDetailsBinding =
        FragmentSeriesDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}