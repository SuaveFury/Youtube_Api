package com.example.youtube_api.ui.playlist.untils.units2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.main.network.inits.InternetConnection
import com.example.youtube_api.R
import com.example.youtube_api.vm.BaseActivity
import com.example.youtube_api.databinding.ActivityPlaylistBinding
import com.example.youtube_api.main.network.extensions.showToast
import com.example.youtube_api.main.network.inits.NetworkStatus
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.ui.playlist.untils.TitleActivity

class PlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistBinding>() {
    private lateinit var adapter: AdapterPlaylists
    private val internetConnection: InternetConnection by lazy {
        InternetConnection(application = application)}
    private val registerForActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]}
    override fun viewBinding(inflater: LayoutInflater): ActivityPlaylistBinding {
        return ActivityPlaylistBinding.inflate(layoutInflater)}

    private fun itemClick(items: Items) {
        val intent = Intent(this, TitleActivity::class.java).apply {
            putExtra(KEY, items.id)
            putExtra(KEY_OF_TITLE,items.snippet.title)}
        registerForActivity.launch(intent)
    }

    override fun initListener() {
        binding.network.btnTryAgain.setOnClickListener {
            showToast(getString(R.string.no_connection))}
    }

    override fun viewModel() {
        super.viewModel()
        viewModel.mutableLiveData.observe(this){
            binding.progressBar.isVisible=it}
        viewModel.playlist().observe(this) {
            when(it.networkStatus){
                NetworkStatus.SUCCESS->{
                    adapter= AdapterPlaylists(this::itemClick,it)
                    binding.rvPlaylists.adapter = adapter
                    viewModel.mutableLiveData.postValue(false)}
                NetworkStatus.LOADING->{
                    viewModel.mutableLiveData.postValue(true)}
                NetworkStatus.FAILED->{
                    viewModel.mutableLiveData.postValue(true)
                    showToast(it.message.toString())}
            }
        }
    }
    override fun internetConnection() {
        internetConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.rvPlaylists.visibility = View.VISIBLE
                binding.network.root.visibility = View.GONE
            } else {
                binding.rvPlaylists.visibility = View.GONE
                binding.network.root.visibility = View.VISIBLE}
        }
    }
    companion object{
        const val KEY="ooo"
        const val KEY_OF_TITLE="aaa"
    }
}
