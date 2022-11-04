package com.example.dollarexchangerate.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dollarexchangerate.data.Record
import com.example.dollarexchangerate.databinding.ItemRateBinding

class DollarExchangeRateAdapter: ListAdapter<Record, ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Record>() {
            override fun areItemsTheSame(oldItem: Record, newItem: Record) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Record, newItem: Record) =
                oldItem.value == newItem.value
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            binding = ItemRateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ViewHolder(val binding: ItemRateBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(record: Record){
        binding.value.text = record.value
        binding.date.text = record.date
    }
}