package com.example.youtube_api.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube_api.BuildConfig
import com.example.youtube_api.main.network.inits.RetrofitClient
import com.example.youtube_api.main.network.inits.Resource
import com.example.youtube_api.data.remote.model.Api
import com.example.youtube_api.data.remote.model.itemList
import com.example.youtube_api.data.remote.model.Playlists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: Api by lazy {
        RetrofitClient.apiRetrofit()}
    fun itemOfPlaylist(playlistId:String): LiveData<Resource<itemList>> {
        val item = MutableLiveData<Resource<itemList>>()
        apiService.getItemOfPlaylist(
            BuildConfig.API_KEY,
            playlistId = playlistId,
            "snippet,contentDetails",
            40
        ).enqueue(object : Callback<itemList> {
            override fun onResponse(call: Call<itemList>, response: Response<itemList>) {
                item.value =Resource.success(response.body())}
            override fun onFailure(call: Call<itemList>, t: Throwable) {
            item.value=Resource.failed(t.message,null,null)}
        })
        return item
    }
    fun resourceLiveData(): LiveData<Resource<Playlists>> {
        val liveData = MutableLiveData<Resource<Playlists>>()
        liveData.value=Resource.loading()
        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "UCWOA1ZGywLbqmigxE4Qlvuw",
            "snippet,contentDetails",
            30
        )
            .enqueue(object : Callback<Playlists> {
                override fun onResponse(call: Call<Playlists>, response: Response<Playlists>) {
                    liveData.value =Resource.success(response.body())}
                override fun onFailure(call: Call<Playlists>, t: Throwable){
                    liveData.value =Resource.failed(t.message,null,null)}
            })
        return liveData
    }
}