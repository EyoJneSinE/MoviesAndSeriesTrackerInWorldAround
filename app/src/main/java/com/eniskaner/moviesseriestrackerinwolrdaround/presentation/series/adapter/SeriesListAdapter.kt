package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.SeriesRecyclerRowBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.model.TopRatedSeries
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.series.model.TopRatedSeries.*
import com.eniskaner.moviesseriestrackerinwolrdaround.util.Constants.POSTER_URL
import com.eniskaner.moviesseriestrackerinwolrdaround.util.load

class SeriesListAdapter(
    private val onItemClick: (Series) -> Unit
) : ListAdapter<Series, SeriesListAdapter.SeriesViewHolder>(SeriesListDiffCallBack()) {

    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = SeriesRecyclerRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = getItem(position)
        holder.bindSeries(series)
    }

    fun setSelectedItemPosition(position: Int) {
        selectedItemPosition = position
    }

    inner class SeriesViewHolder(private val seriesBinding: SeriesRecyclerRowBinding) :
        RecyclerView.ViewHolder(seriesBinding.root) {
        init {
            seriesBinding.root.setOnClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val series = getItem(position)
                    onItemClick(series)
                    setSelectedItemPosition(position)
                }
            }
        }

        fun bindSeries(series: Series) {
            val seriesGenreText = series.topRatedSeriesGenre.joinToString(",\n")
            seriesBinding.apply {
                seriesGenreTypeTextView.text = seriesGenreText
                seriesOriginalTitleTextView.text = series.topRatedSeriesTitle
                seriesReleaseDateTextView.text = series.topRatedSeriesFirstAirDate
                seriesVerticalPoster.load(POSTER_URL + series.topRatedSeriesPoster)
            }
        }
    }

}
