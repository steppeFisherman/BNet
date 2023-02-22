package com.example.bnet.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bnet.R
import com.example.bnet.databinding.ItemRawBinding
import com.example.bnet.ui.model.DataUi
import com.example.bnet.utils.LoadImage

typealias Listener = (dataUi: DataUi) -> Unit

class MainFragmentAdapter(private val listener: Listener, private val loadImage: LoadImage) :
    ListAdapter<DataUi, MainFragmentAdapter.MainHolder>(ItemCallback), View.OnClickListener {

    override fun onClick(v: View) {
        val dataUi = v.tag as DataUi
        when (v.id) {
            R.id.container -> listener(dataUi)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = ItemRawBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        view.container.setOnClickListener(this)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            root.tag = item
            container.tag = item

            loadImage.load(holder.itemView.context, image, item.categoriesImage)
            name.text = item.categoriesName
            description.text = item.description
        }
    }

    class MainHolder(val binding: ItemRawBinding) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<DataUi>() {
        override fun areItemsTheSame(oldItem: DataUi, newItem: DataUi) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: DataUi, newItem: DataUi) = oldItem == newItem
    }
}