package com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.eniskaner.moviesseriestrackerinwolrdaround.databinding.MovieDetailsTrailerChildItemBinding
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.adapter.MovieDetailsAdapterListener
import com.eniskaner.moviesseriestrackerinwolrdaround.presentation.movie_details.model.MovieDetails

class MovieDetailsTrailerViewHolder(
    private val binding: MovieDetailsTrailerChildItemBinding,
    private val trailerAdapterListener: MovieDetailsAdapterListener
): RecyclerView.ViewHolder(binding.root) {

    /*init {
        binding.root.setOnClickListener {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                *//*val movie = getItem(position)
                val movieType = getItem(position).type()
                if (movieType == 4) {
                    onItemClick(movie as MovieDetails.GetMovieDetailsTrailer)
                }*//*

            }
        }
    }*/

    fun bindMovieDetailsTrailer(item: MovieDetails.MovieDetailsTrailerList) {
        val movieDetailsTrailer = item.movieTrailerList.find { getMovieDetailsTrailer ->
            getMovieDetailsTrailer.movieDetailsTrailerType.lowercase().trim() == "Trailer".lowercase().trim()
        }
        binding.moviesDetailsOfficialTrailer.text = movieDetailsTrailer?.movieDetailsTrailerName
        binding.moviesDetailsChildCardView.setOnClickListener {
            trailerAdapterListener.onTrailerClick(movieDetailsTrailer)
        }
    }
}
