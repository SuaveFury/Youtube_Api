package com.example.youtube_api.ui.playlist.untils.units2

import androidx.lifecycle.LiveData
import com.example.youtube_api.App.Companion.repository
import com.example.youtube_api.vm.ViewModel
import com.example.youtube_api.data.remote.model.Playlists
import com.example.youtube_api.main.network.inits.Resource

class PlaylistViewModel : ViewModel() {
    fun playlist(): LiveData<Resource<Playlists>> {
        return repository.resourceLiveData()}
}