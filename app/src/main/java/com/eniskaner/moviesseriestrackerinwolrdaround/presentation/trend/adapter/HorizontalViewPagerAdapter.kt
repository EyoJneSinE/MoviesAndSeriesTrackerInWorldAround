package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarouselImageBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.CarouselViewHolder

class HorizontalViewPagerAdapter :
    ListAdapter<TrendingDataModel.TrendingHorizontalViewPager, CarouselViewHolder>(
        CarouselDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.carousel_image,
            parent,
            false
        )

        return CarouselViewHolder(CarouselImageBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bindTrendingHorizontalViewPager(
            getItem(position) as TrendingDataModel.TrendingHorizontalViewPager
        )
    }
}
