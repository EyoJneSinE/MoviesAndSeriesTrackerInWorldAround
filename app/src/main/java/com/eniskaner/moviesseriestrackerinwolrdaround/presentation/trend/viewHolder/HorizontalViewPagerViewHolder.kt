package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarouselRecyclerViewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.HorizontalViewPagerAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel

class HorizontalViewPagerViewHolder(
    private val binding: CarouselRecyclerViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTrendingHorizontalViewPager(item: TrendingDataModel.TrendingHorizontal) {

        binding.carouselRecyclerView.adapter =
            HorizontalViewPagerAdapter().apply {
                submitList(item.trendingHorizontal)
            }

        binding.carouselRecyclerView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false);
    }
}

