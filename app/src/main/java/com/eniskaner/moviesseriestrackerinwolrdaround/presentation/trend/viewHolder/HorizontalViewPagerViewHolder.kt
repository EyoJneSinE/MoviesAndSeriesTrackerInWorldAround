package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder

import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarouselViewPagerBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.HorizontalViewPagerAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import kotlinx.coroutines.delay
import okhttp3.internal.concurrent.formatDuration
import kotlin.math.abs

class HorizontalViewPagerViewHolder(
    private val binding: CarouselViewPagerBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var isUserTouching = false

    private lateinit var handler : Handler

    private val runnable = object : Runnable {
        override fun run() {
            val currentItem = binding.carouselViewPager.currentItem
            if (currentItem < (binding.carouselViewPager.adapter?.itemCount ?: 0) - 1) {
                binding.carouselViewPager.currentItem = currentItem + 1
            } else {
                binding.carouselViewPager.currentItem = 0
            }
            handler.postDelayed(this, 3000)
        }
    }

    fun bindTrendingHorizontalViewPager(item: TrendingDataModel.TrendingHorizontal) {

        init(item)
        setUpTransformer()

        binding.carouselViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                val translationX = positionOffsetPixels.toFloat()
                binding.textContainer.translationX = -translationX

                binding.carouselViewPager.translationX = positionOffset



            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                //handler.removeCallbacks(runnable)
                //handler.postDelayed(runnable , 3000)

                updatePageNumberText(position + 1 ,binding.carouselViewPager.adapter?.itemCount ?: 0)

            }
        })



        /*binding.carouselViewPager.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    isUserTouching = true
                    handler.removeCallbacks(runnable)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isUserTouching = false
                    handler.postDelayed(runnable, 3000)
                }
            }
            false
        }*/





        /*binding.carouselRecyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)*/
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.75f + r * 0.24f
        }



        binding.carouselViewPager.setPageTransformer(transformer)
    }
    private fun init(item: TrendingDataModel.TrendingHorizontal){
        //handler = Handler(Looper.myLooper()!!)


        binding.carouselViewPager.adapter =
            HorizontalViewPagerAdapter().apply {
                submitList(item.trendingHorizontal)
            }
        binding.carouselViewPager.offscreenPageLimit = 3
        binding.carouselViewPager.clipToPadding = false
        binding.carouselViewPager.clipChildren = false
        binding.carouselViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        //handler.postDelayed(runnable, 3000)

    }

    private fun updatePageNumberText(currentPage: Int, totalPageCount: Int) {
        val pageNumberText = "$currentPage/$totalPageCount"
        binding.carouselTextView.text = pageNumberText

    }


}

