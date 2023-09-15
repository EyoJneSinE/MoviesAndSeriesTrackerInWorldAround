package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarauselImageBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarouselRecyclerViewBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.TrendingItem
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.BACKDROP_URL
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class HorizontalViewPagerViewHolder(
    private val horizontalViewPagerBinding: CarauselImageBinding
) : RecyclerView.ViewHolder(horizontalViewPagerBinding.root)  {


    fun bindTrendingHorizontalViewPager(horizontalViewPagerItem: TrendingDataModel.TrendingHorizontal){

        val horizontalItems = horizontalViewPagerItem.trendingHorizontal

        for (i in horizontalItems.indices) {
            horizontalViewPagerBinding.carouselImageTitle.text = horizontalItems[i].horizontalViewPagerTitle
            val horizontalViewPagerPoster = BACKDROP_URL + horizontalItems[i].horizontalViewPagerPoster
            horizontalViewPagerBinding.carouselImageView.load(horizontalViewPagerPoster)
        }

    }

}
/*horizontalViewPagerBinding.carouselImageTitle.text = horizontalViewPagerItem.horizontalViewPagerTitle
        val horizontalViewPagerPoster = BACKDROP_URL + horizontalViewPagerItem.horizontalViewPagerPoster
        horizontalViewPagerBinding.carouselImageView.load(horizontalViewPagerPoster)*/

/*val horizontalItems = horizontalViewPagerItem.trendingHorizontal

for (i in horizontalItems.indices) {
    horizontalViewPagerBinding.carouselImageTitle.text = horizontalItems[i].horizontalViewPagerTitle
    val horizontalViewPagerPoster = BACKDROP_URL + horizontalItems[i].horizontalViewPagerPoster
    horizontalViewPagerBinding.carouselImageView.load(horizontalViewPagerPoster)
}*/

/*horizontalViewPagerBinding.carouselImageTitle.text = horizontalViewPagerItem.trendingHorizontal.map { it.horizontalViewPagerTitle }.firstOrNull()
val horizontalViewPagerPoster = BACKDROP_URL + horizontalViewPagerItem.trendingHorizontal.map { it.horizontalViewPagerPoster }.firstOrNull()
horizontalViewPagerBinding.carouselImageView.load(horizontalViewPagerPoster)*/
