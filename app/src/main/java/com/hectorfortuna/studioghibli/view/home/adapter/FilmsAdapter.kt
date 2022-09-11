package com.hectorfortuna.studioghibli.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hectorfortuna.studioghibli.databinding.FilmsItemBinding
import com.hectorfortuna.studioghibli.model.FilmsResponse

class FilmsAdapter(
    private val results: List<FilmsResponse>,
    private val itemClick: ((item: FilmsResponse) -> Unit)
) : RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val binding = FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmsViewHolder(binding, itemClick)
    }

    override fun getItemCount() = results.count()

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        holder.bindView(results[position])
    }

    class FilmsViewHolder(
        private val binding: FilmsItemBinding,
        private val itemClick: ((itemClick: FilmsResponse) -> Unit)
    ): RecyclerView.ViewHolder(binding.root) {
        fun bindView(films: FilmsResponse){
            binding.run {
                txtMovieName.text = films.title

                txtMovieNameJapanese.text = films.originalTitle

                txtMovieNameRomanised.text = films.originalTitleRomanised

                Glide.with(itemView)
                    .load(films.movieBanner)
                    .centerCrop()
                    .into(imgMovie)

                itemView.setOnClickListener{
                    itemClick.invoke(films)
                }
            }
        }
    }
}