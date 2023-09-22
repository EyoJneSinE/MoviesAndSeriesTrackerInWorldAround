package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentBottomSheetTrailerBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseFragment

class BottomSheetTrailerFragment : BaseFragment<FragmentBottomSheetTrailerBinding>() {

    override fun setBinding(): FragmentBottomSheetTrailerBinding =
        FragmentBottomSheetTrailerBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}