package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.view

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.DefaultPlayerUiController
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.listeners.AbstractYouTubePlayerListener
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.listeners.FullscreenListener
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.listeners.PlayerConstants
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.listeners.YouTubePlayer
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.options.IFramePlayerOptions
import com.eniskaner.moviesseriestrackerinwolrdaround.customyoutubeplayer.views.YouTubePlayerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.FragmentBottomSheetTrailerBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.MainActivity
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.base.BaseBottomSheetFragment
import com.eniskaner.moviesseriestrackerinwolrdaround.util.launchAndRepeatWithViewLifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.launch

class BottomSheetTrailerFragment : BaseBottomSheetFragment<FragmentBottomSheetTrailerBinding>() {

    private val args: BottomSheetTrailerFragmentArgs by navArgs()

    lateinit var youtubePlayerView: YouTubePlayerView
    private lateinit var youTubePlayer: YouTubePlayer
    private var isFullScreen = false
    private var isItExitFullScreen = true 
    private val navController: NavController by lazy {
        findNavController()
    }
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isItExitFullScreen && !isFullScreen) {
                navController.popBackStack()
            }

            if (isFullScreen) {
                if (isItExitFullScreen) {
                    navController.popBackStack()
                }
                isItExitFullScreen = false
                //if the player is in fullscreen, then exit fullscreen
                youTubePlayer.toggleFullscreen()
                /*val displayMetrics = DisplayMetrics()
                val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val screenWidth = displayMetrics.widthPixels
                val screenHeight = displayMetrics.heightPixels
                youTubePlayer.setSize(screenWidth, screenHeight)*/

                /*youtubePlayerView.wrapContent()
                (activity as? MainActivity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR*/

            } else  {
                /*(activity as? MainActivity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR*/
                /*navController.popBackStack()*/
                isItExitFullScreen = true
                youtubePlayerView.wrapContent()
                if (youtubePlayerView.layoutParams.height == LayoutParams.WRAP_CONTENT) {
                    (activity as? MainActivity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
                }
            }
        }
    }



    override fun setBinding(): FragmentBottomSheetTrailerBinding =
        FragmentBottomSheetTrailerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? MainActivity)?.setFullscreenFlags()
        (activity as? MainActivity)?.binding?.bottomNavigationView?.visibility = View.GONE
        (activity as? MainActivity)?.binding?.toolbar?.visibility = View.GONE
        (activity as? MainActivity)?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        (activity as? MainActivity)?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.binding?.bottomNavigationView?.visibility = View.VISIBLE
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trailerId = args.trailerKey
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        launchAndRepeatWithViewLifecycle {
            launch { getMovieTrailer(trailerId) }
        }
    }

    private fun getMovieTrailer(trailerId: String) {
        val fullscreenListeners = mutableListOf<FullscreenListener>()
        youtubePlayerView = binding.moviesDetailsYoutubePlayerView
        youtubePlayerView.enableAutomaticInitialization = false
        val options : IFramePlayerOptions = IFramePlayerOptions.Builder().controls(1).fullscreen(0).build()
        /*youtubePlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullScreen = true
                youtubePlayerView.layoutParams.width = LayoutParams.MATCH_PARENT
                youtubePlayerView.layoutParams.height = LayoutParams.MATCH_PARENT
                youtubePlayerView.matchParent()
                *//*youtubePlayerView.visibility = View.GONE*//*
                *//*binding.movieDetailsTrailerConstraintLayout.visibility = View.VISIBLE*//*
                *//*val displayMetrics = DisplayMetrics()
                val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val screenWidth = displayMetrics.widthPixels
                val screenHeight = displayMetrics.heightPixels
                youTubePlayer.setSize(screenWidth, screenHeight)*//*
                *//*binding.enterFullscreenButton?.visibility = View.GONE
                binding.movieDetailsTrailerConstraintLayout.addView(fullscreenView)*//*
                *//*requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE*//*
                *//*youTubePlayer.toggleFullscreen()*//*
                *//*requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE*//*
                *//*(activity as? MainActivity)?.binding?.bottomNavigationView?.visibility = View.GONE
                (activity as? MainActivity)?.binding?.toolbar?.visibility = View.GONE*//*

            }

            override fun onExitFullscreen() {
                isFullScreen = false
                youtubePlayerView.wrapContent()
                youtubePlayerView.layoutParams.width = LayoutParams.MATCH_PARENT
                youtubePlayerView.layoutParams.height = LayoutParams.WRAP_CONTENT
                *//*youtubePlayerView.visibility = View.VISIBLE*//*
                *//*binding.enterFullscreenButton?.visibility = View.GONE
                binding.movieDetailsTrailerConstraintLayout.visibility = View.GONE
                binding.movieDetailsTrailerConstraintLayout.removeAllViews()*//*
                *//*requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR*//*
            }
        })*/
        /*youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
            }
        })*/

        val webViewFullScreenListener : FullscreenListener = object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                /*isItExitFullScreen = false*/
                isFullScreen = true
                youtubePlayerView.matchParent()
                youtubePlayerView.visibility = View.GONE
                binding.movieDetailsTrailerConstraintLayout.visibility = View.VISIBLE
                binding.movieDetailsTrailerConstraintLayout.addView(fullscreenView)
                fullscreenListeners.forEach {
                    it.onEnterFullscreen(fullscreenView, exitFullscreen)
                }
            }

            override fun onExitFullscreen() {
                /*isItExitFullScreen = true*/
                isFullScreen = false
                youtubePlayerView.wrapContent()
                youtubePlayerView.visibility = View.VISIBLE
                binding.movieDetailsTrailerConstraintLayout.visibility = View.GONE
                binding.movieDetailsTrailerConstraintLayout.removeAllViews()
                fullscreenListeners.forEach {
                    it.onExitFullscreen()
                }
            }
        }

        val playerListener: AbstractYouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@BottomSheetTrailerFragment.youTubePlayer = youTubePlayer
                youtubePlayerView.setCustomPlayerUi(DefaultPlayerUiController(youtubePlayerView, youTubePlayer, (activity as? MainActivity)).rootView)
                youTubePlayer.cueVideo(trailerId ?: "", 0f)
                /*youTubePlayer.toggleFullscreen()*/
                /*binding.movieDetailsTrailerConstraintLayout.visibility = View.GONE*/
                /*val enterFullscreenButton = binding.enterFullscreenButton
                enterFullscreenButton?.setOnClickListener {
                    youTubePlayer.toggleFullscreen()
                }*/
            }
        }
        youtubePlayerView.addFullscreenListener(webViewFullScreenListener)
        youtubePlayerView.initialize(playerListener, options)
        viewLifecycleOwner.lifecycle.addObserver(youtubePlayerView)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youtubePlayerView.layoutParams.width = LayoutParams.MATCH_PARENT
            youtubePlayerView.layoutParams.height = LayoutParams.MATCH_PARENT
            youtubePlayerView.matchParent()

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            youtubePlayerView.layoutParams.width = LayoutParams.MATCH_PARENT
            youtubePlayerView.layoutParams.height = LayoutParams.WRAP_CONTENT
            youtubePlayerView.wrapContent()
        }
    }
}



