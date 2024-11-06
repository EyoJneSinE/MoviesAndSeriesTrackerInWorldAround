package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarouselViewPagerBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.TrendingMoviesAndSeriesElementsRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIES_AND_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.HorizontalViewPagerViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.MoviesAndSeriesViewHolder

class TrendingDataAdapter(private val trendingAdapterListener: TrendingAdapterListener) :
    ListAdapter<TrendingDataModel, RecyclerView.ViewHolder>(TrendingDataDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            TYPE_TRENDING_HORIZONTAL -> {
                val horizontalViewPagerBinding =
                    CarouselViewPagerBinding.inflate(layoutInflater, parent, false)
                HorizontalViewPagerViewHolder(horizontalViewPagerBinding)
            }

            TYPE_TRENDING_MOVIES_AND_SERIES -> {
                val movieAndSeriesBinding =
                    TrendingMoviesAndSeriesElementsRowBinding.inflate(layoutInflater, parent, false)
                MoviesAndSeriesViewHolder(movieAndSeriesBinding, trendingAdapterListener)
            }

            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)

        when (holder) {
            is HorizontalViewPagerViewHolder -> {
                holder.bindTrendingHorizontalViewPager(item as TrendingDataModel.TrendingHorizontal)
            }

            is MoviesAndSeriesViewHolder -> {
                holder.bindTrendingMoviesAndSeries(item as TrendingDataModel.TrendingMoviesAndSeries)
            }
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).type()

}
