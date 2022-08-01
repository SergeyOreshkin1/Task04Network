package com.example.task04network.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task04network.databinding.RecyclerViewItemBinding
import com.squareup.picasso.Picasso

class DogImagesAdapter(private val images: List<String>) :
    RecyclerView.Adapter<DogImagesAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    class DogViewHolder(rvItemLayoutBinding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(rvItemLayoutBinding.root) {

        private val binding = rvItemLayoutBinding

        fun bind(item: String) {
            if (item.isNotEmpty()) {
                Picasso.get().load(item).into(binding.dogImage)
            }
        }
    }
}
