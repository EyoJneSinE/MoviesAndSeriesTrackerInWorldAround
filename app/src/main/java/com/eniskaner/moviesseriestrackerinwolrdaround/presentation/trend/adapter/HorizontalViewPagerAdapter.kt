package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eniskaner.moviesseriestrackerinwolrdaround.R
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarauselImageBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.HorizontalViewPagerViewHolder

class HorizontalViewPagerAdapter(private var trendingHorizontal: TrendingDataModel.TrendingHorizontal) : ListAdapter<TrendingDataModel, HorizontalViewPagerViewHolder>(TrendingDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            R.layout.carousel_recycler_view,
            parent,
            false
        )
        return HorizontalViewPagerViewHolder(CarauselImageBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: HorizontalViewPagerViewHolder, position: Int) {
        /*trendingHorizontal = getItem(position)*/
        holder.bindTrendingHorizontalViewPager(trendingHorizontal as TrendingDataModel.TrendingHorizontal)
    }
}

/*
class HorizontalViewPagerAdapter(
    private val recyclerItemList: TrendingDataModel.TrendingHorizontal,
) : RecyclerView.Adapter<HorizontalViewPagerViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HorizontalViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            when (viewType) {
                TYPE_TRENDING_HORIZONTAL -> R.layout.carousel_recycler_view
                else -> throw IllegalArgumentException("Invalid Type")
            },
            parent,
            false
        )
        return HorizontalViewPagerViewHolder(CarauselImageBinding.bind(itemView))
    }


    override fun getItemCount(): Int = recyclerItemList.trendingHorizontal.size

    override fun onBindViewHolder(holder: HorizontalViewPagerViewHolder, position: Int) {
        holder.bindTrendingHorizontalViewPager(recyclerItemList)
    }

}
*/

/*fun setData(data: List<TrendingDataModel.TrendingHorizontalViewPager>) {
    horizontalViewPagerData.clear()
    horizontalViewPagerData.addAll(data)
    notifyDataSetChanged()
}*/
