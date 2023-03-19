package com.example.youtube_api.ui.playlist.untils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_api.databinding.ItemContentBinding
import com.example.youtube_api.main.network.extensions.glide
import com.example.youtube_api.data.remote.model.Items

class Adapter() : RecyclerView.Adapter<Adapter.ViewHolderContent>() {
    private var array = ArrayList<Items>()
    inner class ViewHolderContent(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemBind: Items) {
            binding.tvTime.text = itemBind.snippet.publishedAt.toString()
            binding.ivItemDetail.glide(itemBind.snippet.thumbnails.medium.url)
            binding.tvTitle.text = itemBind.snippet.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderContent {
        return ViewHolderContent(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }
    fun addItem(item:ArrayList<Items>) {
        array.addAll(item)
    }

    override fun onBindViewHolder(holder: ViewHolderContent, position: Int) {
        holder.bind(array[position])
    }
    override fun getItemCount(): Int {
        return array.size
    }
}
