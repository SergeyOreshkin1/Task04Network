package com.example.task04network.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task04network.databinding.RecyclerViewItemBinding
import com.squareup.picasso.Picasso

class DogImagesAndCatFactsAdapter(private val data: List<DogsAndCatFacts>) :
    RecyclerView.Adapter<DogImagesAndCatFactsAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        return DogViewHolder(
            RecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    class DogViewHolder(rvItemLayoutBinding: RecyclerViewItemBinding) :
        RecyclerView.ViewHolder(rvItemLayoutBinding.root) {

        private val binding = rvItemLayoutBinding

        fun bind(item: DogsAndCatFacts) {
            if (item.image!!.isNotEmpty()) {
                Picasso.get().load(item.image).into(binding.dogImage)
            }
            binding.factText.text = item.fact
        }
    }
}



