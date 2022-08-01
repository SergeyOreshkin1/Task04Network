package com.example.task04network.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task04network.databinding.CatFactRecyclerViewItemBinding
import com.example.task04network.model.catFacts.CatFact

class CatFactsAdapter(private val facts: List<CatFact>) :
    RecyclerView.Adapter<CatFactsAdapter.CatFactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactViewHolder {
        return CatFactViewHolder(
            CatFactRecyclerViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = facts.size

    override fun onBindViewHolder(holder: CatFactViewHolder, position: Int) {
        val item = facts[position]
        holder.bind(item)
    }

    class CatFactViewHolder(rvItemLayoutBinding: CatFactRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(rvItemLayoutBinding.root) {

        private val binding = rvItemLayoutBinding

        fun bind(item: CatFact) {
            binding.factText.text = item.fact
        }
    }
}
