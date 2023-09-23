package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarouselImageBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class CarouselViewHolder(
    private val binding: CarouselImageBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTrendingHorizontalViewPager(item: TrendingDataModel.TrendingHorizontalViewPager) =
        with(binding) {
            carouselImageTitle.text = item.horizontalViewPagerTitle
            carouselImageView.load(Constants.BACKDROP_URL.plus(item.horizontalViewPagerPoster))
        }
}