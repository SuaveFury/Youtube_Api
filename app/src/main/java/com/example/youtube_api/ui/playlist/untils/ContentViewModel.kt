package com.example.youtube_api.ui.playlist.untils

import androidx.lifecycle.LiveData
import com.example.youtube_api.App.Companion.repository
import com.example.youtube_api.vm.ViewModel
import com.example.youtube_api.main.network.inits.Resource
import com.example.youtube_api.data.remote.model.itemList

class ContentViewModel : ViewModel() {
    fun getItemOfPlaylist(playlistId:String): LiveData<Resource<itemList>> {
        return repository.itemOfPlaylist(playlistId=playlistId)
    }
}