package com.example.youtube_api.ui.playlist.untils

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_api.R
import com.example.youtube_api.vm.BaseActivity
import com.example.youtube_api.main.network.inits.InternetConnection
import com.example.youtube_api.main.network.extensions.showToast
import com.example.youtube_api.main.network.inits.NetworkStatus
import com.example.youtube_api.data.remote.model.Items
import com.example.youtube_api.data.remote.model.itemList
import com.example.youtube_api.databinding.ActivityTitleBinding

class TitleActivity : BaseActivity<ContentViewModel,ActivityTitleBinding>() {
    private fun dataInit(data: itemList) {
        if (intent!=null){
            title=intent.getStringExtra(KEY_OF_TITLE).toString()}
        binding.tvContentTitle.text=title
        binding.tvContentInfo.text=data.snippet?.description
        binding.tvNum.text= data.items?.size.toString().plus(" ").plus(getString(R.string.video_series))
    }

    override fun viewListeners() {
    }

    override fun initListener() {
        binding.tvBack.setOnClickListener {
            onBackPressed()
        }
    }
    companion object{
        const val KEY="ooo"
        const val KEY_OF_TITLE="aaa"
    }
    private val internetConnection: InternetConnection by lazy {
        InternetConnection(application = application) }
    private  val adapter: Adapter by lazy {
        Adapter()
    }
    private val id:String?
    get() = intent.getStringExtra(KEY)
    private lateinit var title:String

    override val viewModel: ContentViewModel by lazy {
        ViewModelProvider(this)[ContentViewModel::class.java]}
    override fun viewBinding(inflater: LayoutInflater):ActivityTitleBinding {
        return ActivityTitleBinding.inflate(layoutInflater)}
    override fun viewModel() {
        super.viewModel()
        viewModel.mutableLiveData.observe(this){
            binding.progressBar.isVisible=it
        }
        viewModel.getItemOfPlaylist(id.toString()).observe(this){
            when(it.networkStatus){
                NetworkStatus.SUCCESS->{
                    if (it.data!=null){
                        viewModel.mutableLiveData.postValue(false)
                        binding.rvItems.adapter=adapter
                        adapter.addItem(it.data?.items as ArrayList<Items>)
                        dataInit(it.data)
                    }
                }
                NetworkStatus.LOADING->{
                    viewModel.mutableLiveData.postValue(true)
                    binding.toolbar.isVisible=false
                    binding.btnPlay.isVisible=false
                }
                NetworkStatus.FAILED->{
                    viewModel.mutableLiveData.postValue(true)
                    showToast("NO Internet Connection")
                }
            }
        }
    }
    override fun internetConnection() {
        super.internetConnection()
        internetConnection.observe(this) { isConnected ->
            if (isConnected) {
                binding.rvItems.visibility = View.VISIBLE
                binding.networkConnection.root.visibility = View.GONE
            } else {
                binding.rvItems.visibility = View.GONE
                binding.networkConnection.root.visibility = View.VISIBLE
            }
        }
    }
}