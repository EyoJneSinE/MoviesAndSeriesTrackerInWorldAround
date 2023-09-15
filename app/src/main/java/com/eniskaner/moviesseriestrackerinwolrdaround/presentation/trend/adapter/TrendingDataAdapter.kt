package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.CarauselImageBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.TrendingMoviesAndSeriesElementsRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIE
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_MOVIES_AND_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.adapter.DisplayItem.Companion.TYPE_TRENDING_SERIES
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.model.TrendingDataModel
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.HorizontalViewPagerViewHolder
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.trend.viewHolder.MoviesAndSeriesViewHolder

class TrendingDataAdapter  :
    ListAdapter<TrendingDataModel, RecyclerView.ViewHolder>(TrendingDataDiffCallback()) {
    private lateinit var horizontalViewPagerAdapter: HorizontalViewPagerAdapter



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_TRENDING_HORIZONTAL -> {
                val horizontalViewPagerBinding =
                    CarauselImageBinding.inflate(layoutInflater, parent, false)
                HorizontalViewPagerViewHolder(horizontalViewPagerBinding)
            }

            TYPE_TRENDING_MOVIES_AND_SERIES -> {
                val movieAndSeriesBinding =
                    TrendingMoviesAndSeriesElementsRowBinding.inflate(layoutInflater, parent, false)
                MoviesAndSeriesViewHolder(movieAndSeriesBinding)
            }

            TYPE_TRENDING_MOVIE -> {
                val movieBinding =
                    TrendingMoviesAndSeriesElementsRowBinding.inflate(layoutInflater, parent, false)
                MoviesAndSeriesViewHolder(movieBinding)
            }

            TYPE_TRENDING_SERIES -> {
                val seriesBinding =
                    TrendingMoviesAndSeriesElementsRowBinding.inflate(layoutInflater, parent, false)
                MoviesAndSeriesViewHolder(seriesBinding)
            }

            else -> throw IllegalArgumentException("Invalid Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.type()) {
            TYPE_TRENDING_HORIZONTAL -> {
                val trendingHorizontalViewPagerViewHolder = holder as HorizontalViewPagerViewHolder
                horizontalViewPagerAdapter = HorizontalViewPagerAdapter(item as TrendingDataModel.TrendingHorizontal)
                trendingHorizontalViewPagerViewHolder.bindingAdapter
                horizontalViewPagerAdapter.bindViewHolder(trendingHorizontalViewPagerViewHolder, position)
                //trendingHorizontalViewPagerViewHolder.bindTrendingHorizontalViewPager(item as TrendingDataModel.TrendingHorizontal)
                /*horizontalViewPagerAdapter.onBindViewHolder(trendingHorizontalViewPagerViewHolder, position)*/

            }

            TYPE_TRENDING_MOVIES_AND_SERIES -> {
                val trendingMovieAndSeriesHolder = holder as MoviesAndSeriesViewHolder
                trendingMovieAndSeriesHolder.bindTrendingMoviesAndSeries(item as TrendingDataModel.TrendingMoviesAndSeries)
            }

            TYPE_TRENDING_MOVIE -> {
                val trendingMovieHolder = holder as MoviesAndSeriesViewHolder
                trendingMovieHolder.bindTrendingMovies(item as TrendingDataModel.TrendingMovies)
            }

            TYPE_TRENDING_SERIES -> {
                val trendingSeriesHolder = holder as MoviesAndSeriesViewHolder
                trendingSeriesHolder.bindTrendingSeries(item as TrendingDataModel.TrendingSeries)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is TrendingDataModel.TrendingHorizontal -> TYPE_TRENDING_HORIZONTAL
            is TrendingDataModel.TrendingHorizontalViewPager -> TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
            is TrendingDataModel.TrendingMoviesAndSeries -> TYPE_TRENDING_MOVIES_AND_SERIES
            is TrendingDataModel.TrendingMovies -> TYPE_TRENDING_MOVIE
            is TrendingDataModel.TrendingSeries -> TYPE_TRENDING_SERIES
            else -> throw IllegalArgumentException("Invalid Type")
        }
    }


}
//shell
/*private var horizontalItems: ArrayList<TrendingDataModel.TrendingHorizontal> = ArrayList()*/

/*class TrendingDataHolder(binding: CarouselRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mRecyclerView: RecyclerView = binding.carouselRecyclerView
    }*/

/*fun addHorizontalItems(items: List<TrendingDataModel.TrendingHorizontal>) {
        horizontalItems.addAll(items)
        notifyDataSetChanged()
    }*/

/*inner class RecyclerItemViewHolder(private val binding: CarouselRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.carouselRecyclerView.setHasFixedSize(true)
            binding.carouselRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        }

        fun bindHorizontalViewPagerView(recyclerItemList: List<TrendingDataModel.TrendingHorizontalViewPager>) {
            val adapter = HorizontalViewPagerAdapter(
                DisplayItem.TYPE_TRENDING_HORIZONTAL_VIEW_PAGER,
                recyclerItemList = recyclerItemList
            )
            binding.carouselRecyclerView.adapter = adapter
        }
    }*/

/*val horizontalViewHolder = holder as HorizontalViewPagerViewHolder
                horizontalViewPagerAdapter.bindViewHolder(horizontalViewHolder, position)*/
/*horizontalViewHolder.bindTrendingHorizontalViewPager(item as TrendingDataModel.TrendingHorizontalViewPager)*/
/*horizontalViewHolder?.bindTrendingHorizontalViewPager(item as? TrendingDataModel.TrendingHorizontalViewPager)
horizontalViewHolder?.mRecyclerView?.layoutManager = LinearLayoutManager(
    holder.itemView.context,
    LinearLayoutManager.HORIZONTAL,
    false
)
horizontalViewHolder?.mRecyclerView?.adapter = horizontalViewPagerAdapter*/



/*holder.apply {
    bindingAdapter as? HorizontalViewPagerAdapter
    val horizontalViewHolder = holder as? TrendingDataHolder
    horizontalViewHolder?.mRecyclerView?.layoutManager = LinearLayoutManager(
        this.itemView.context,
        LinearLayoutManager.HORIZONTAL,
        false
    )
    horizontalViewHolder?.mRecyclerView?.adapter as? HorizontalViewPagerAdapter
    val hViewHolder = holder as? HorizontalViewPagerViewHolder
    hViewHolder?.bindTrendingHorizontalViewPager(item as TrendingDataModel.TrendingHorizontalViewPager)
}*/
/*horizontalViewHolder.bindTrendingHorizontalViewPager(item as TrendingDataModel.TrendingHorizontalViewPager)*/

/*override fun getItemCount(): Int = data.size

override fun getItemViewType(position: Int): Int {
    val item = data[position]
    return when (item) {
        is TrendingDataModel.TrendingHorizontalViewPager -> TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
        is TrendingDataModel.TrendingMoviesAndSeries -> TYPE_TRENDING_MOVIES_AND_SERIES
        is TrendingDataModel.TrendingMovies -> TYPE_TRENDING_MOVIES_AND_SERIES
        is TrendingDataModel.TrendingSeries -> TYPE_TRENDING_MOVIES_AND_SERIES
        else -> throw IllegalArgumentException("Invalid item type at position $position")
    }
}

fun setData(newData: List<TrendingDataModel>) {
    data.clear()
    data.addAll(newData)
    notifyDataSetChanged()
}

companion object {
    private const val TYPE_TRENDING_HORIZONTAL_VIEW_PAGER = 0
    private const val TYPE_TRENDING_MOVIES_AND_SERIES = 1
    private const val TYPE_TRENDING_MOVIE = 2
    private const val TYPE_TRENDING_SERIES = 3
}*/

/*
class TrendingDataAdapter : ListAdapter<TrendingDataModel, TrendingDataAdapterViewHolder>(TrendingDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingDataAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            when (viewType) {
                TYPE_TRENDING_HORIZONTAL_VIEW_PAGER -> R.layout.carausel_image
                TYPE_TRENDING_MOVIE -> R.layout.trending_movies_and_series_elements_row
                TYPE_TRENDING_SERIES -> R.layout.trending_movies_and_series_elements_row
                else -> throw IllegalArgumentException("Invalid Type")
            },
            parent,
            false
        )
        return TrendingDataAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrendingDataAdapterViewHolder, position: Int) {
        holder.bindTrending(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is TrendingDataModel.TrendingHorizontalViewPager -> TYPE_TRENDING_HORIZONTAL_VIEW_PAGER
            is TrendingDataModel.TrendingMovies -> TYPE_TRENDING_MOVIE
            is TrendingDataModel.TrendingSeries -> TYPE_TRENDING_SERIES
        }
    }

    override fun submitList(list: List<TrendingDataModel>?) {
        // Reorder the list to move the TrendingHorizontalViewPager items to the top
        val reorderedList = mutableListOf<TrendingDataModel>()
        val horizontalItems = list?.filterIsInstance<TrendingDataModel.TrendingHorizontalViewPager>() ?: emptyList()
        val movieAndSeriesItems = list?.filter { it !is TrendingDataModel.TrendingHorizontalViewPager } ?: emptyList()

        reorderedList.addAll(horizontalItems)
        reorderedList.addAll(movieAndSeriesItems)

        super.submitList(reorderedList)
    }

    companion object {
        private const val TYPE_TRENDING_HORIZONTAL_VIEW_PAGER = 0
        private const val TYPE_TRENDING_MOVIE = 1
        private const val TYPE_TRENDING_SERIES = 2
    }
}*/
/*
class TrendingDataAdapter : RecyclerView.Adapter<TrendingDataAdapterViewHolder>() {

    private val adapterTrendingData = mutableListOf<TrendingDataModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TrendingDataAdapterViewHolder {
        val layoutTrending = when (viewType) {
            TYPE_TRENDING_HORIZONTAL_VIEW_PAGER -> {
                Log.d("TrendingDataAdapter", "Creating ViewHolder for HorizontalViewPager")
                R.layout.carausel_image
            }
            TYPE_TRENDING_MOVIE -> {
                Log.d("TrendingDataAdapter", "Creating ViewHolder for TrendingMovie")
                R.layout.trending_movies_and_series_elements_row
            }
            TYPE_TRENDING_SERIES -> {
                Log.d("TrendingDataAdapter", "Creating ViewHolder for TrendingSeries")
                R.layout.trending_movies_and_series_elements_row
            }
            else -> throw IllegalArgumentException("Invalid Type")
        }
        val viewTrending = LayoutInflater
            .from(parent.context)
            .inflate(layoutTrending, parent, false)
        return TrendingDataAdapterViewHolder(viewTrending)
    }

    override fun getItemCount(): Int =  adapterTrendingData.size

    override fun onBindViewHolder(holder: TrendingDataAdapterViewHolder, position: Int) {
        holder.bindTrending(adapterTrendingData[position])
    }
    fun setTrendingData(trendingData: List<TrendingDataModel>) {
        adapterTrendingData.apply {
            clear()
            addAll(trendingData)
        }
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_TRENDING_HORIZONTAL_VIEW_PAGER = 0
        private const val TYPE_TRENDING_MOVIE = 1
        private const val TYPE_TRENDING_SERIES = 2
    }
}*/
