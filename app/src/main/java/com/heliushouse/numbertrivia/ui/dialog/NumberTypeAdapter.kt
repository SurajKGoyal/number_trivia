package com.heliushouse.numbertrivia.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.heliushouse.numbertrivia.R
import com.heliushouse.numbertrivia.callbacks.RecyclerItemClickListener
import com.heliushouse.numbertrivia.databinding.SelectionItemBinding

class NumberTypeAdapter(val list: ArrayList<String>, val listener: RecyclerItemClickListener) :
    RecyclerView.Adapter<NumberTypeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.selection_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.name.text = list[position]
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(val binding: SelectionItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}