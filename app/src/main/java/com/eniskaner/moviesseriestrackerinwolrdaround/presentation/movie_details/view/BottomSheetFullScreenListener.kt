package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.view

import android.view.View
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.listeners.FullscreenListener
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetFullScreenListener(private val bottomSheetBehavior: BottomSheetBehavior<*>) :
    FullscreenListener {
    override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onExitFullscreen() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}