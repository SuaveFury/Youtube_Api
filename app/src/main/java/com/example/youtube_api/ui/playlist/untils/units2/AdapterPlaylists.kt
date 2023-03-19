package com.example.youtube_api.ui.playlist.untils.units2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube_api.R
import com.example.youtube_api.databinding.ItemPlaylistsBinding
import com.example.youtube_api.main.network.extensions.glide
import com.example.youtube_api.main.network.inits.Resource
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.data.remote.model.Playlists
class AdapterPlaylists(
    private val onClick: (Items) -> Unit,
    private val playlistArray: Resource<Playlists>
) : RecyclerView.Adapter<AdapterPlaylists.ViewHolderPlaylists>(){
    inner class ViewHolderPlaylists(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemsBind: Items) {
            binding.ivItemPlaylists.glide(itemsBind.snippet.thumbnails.medium.url)
            binding.tvItemPlaylists.text = itemsBind.snippet.title
            binding.tvDesk.text = itemsBind.contentDetails.itemCount.toString().plus(" ").plus(
                itemView.context.getString(R.string.video_series))
            itemView.setOnClickListener {
                onClick(itemsBind)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPlaylists {
        return ViewHolderPlaylists(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }
    override fun onBindViewHolder(holder: ViewHolderPlaylists, position: Int) {
        holder.bind(playlistArray.data!!.items[position])
    }
    override fun getItemCount(): Int {
        return playlistArray.data!!.items.size }
}