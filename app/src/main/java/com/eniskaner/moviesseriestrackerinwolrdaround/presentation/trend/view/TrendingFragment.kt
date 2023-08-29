package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentTrendingBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment


class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {
    override fun setBinding(): FragmentTrendingBinding =
        FragmentTrendingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}