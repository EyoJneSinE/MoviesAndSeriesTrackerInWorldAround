package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarauselImageBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.HorizontalViewPagerViewHolder
import java.lang.IllegalArgumentException

class HorizontalViewPagerAdapter(
    private val recyclerItemList: TrendingDataModel.TrendingHorizontalViewPager,
) : RecyclerView.Adapter<HorizontalViewPagerViewHolder>() {
    var horizontalList = mutableListOf<TrendingDataModel.TrendingHorizontalViewPager>(recyclerItemList)



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HorizontalViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            when (viewType) {
                TYPE_TRENDING_HORIZONTAL_VIEW_PAGER -> R.layout.carousel_recycler_view
                else -> throw IllegalArgumentException("Invalid Type")
            },
            parent,
            false
        )
        return HorizontalViewPagerViewHolder(CarauselImageBinding.bind(itemView))
    }


    override fun getItemCount(): Int = horizontalList.size

    override fun onBindViewHolder(holder: HorizontalViewPagerViewHolder, position: Int) {
        holder.bindTrendingHorizontalViewPager(horizontalList[position])
    }

}

/*fun setData(data: List<TrendingDataModel.TrendingHorizontalViewPager>) {
    horizontalViewPagerData.clear()
    horizontalViewPagerData.addAll(data)
    notifyDataSetChanged()
}*/
