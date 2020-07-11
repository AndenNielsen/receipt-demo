package com.demo.receipt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.receipt.databinding.ReceiptListItemBinding
import com.demo.receipt.ui.Receipt


class ReceiptsListAdapter :
    ListAdapter<Receipt, ReceiptsListAdapter.ReceiptListItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ReceiptListItemBinding =
            ReceiptListItemBinding.inflate(layoutInflater, parent, false)
        return ReceiptListItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReceiptListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<Receipt>() {
        override fun areItemsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Receipt, newItem: Receipt): Boolean {
            return oldItem == newItem
        }
    }

    inner class ReceiptListItemViewHolder(private val binding: ReceiptListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Receipt) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}

