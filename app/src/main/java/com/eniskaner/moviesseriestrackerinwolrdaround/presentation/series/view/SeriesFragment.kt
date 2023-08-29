package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentSeriesBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment

class SeriesFragment : BaseFragment<FragmentSeriesBinding>() {
    override fun setBinding(): FragmentSeriesBinding =
        FragmentSeriesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}