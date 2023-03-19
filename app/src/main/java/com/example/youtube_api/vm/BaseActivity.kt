package com.example.youtube_api.vm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM:ViewModel,VB:ViewBinding> : AppCompatActivity() {
    protected abstract val viewModel:VM
    protected lateinit var binding:VB
    open fun initListener(){}
    open fun internetConnection(){}
    open fun viewListeners(){}
    open fun viewModel(){}
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding=viewBinding(layoutInflater)
        setContentView(binding.root)
        internetConnection()
        initListener()
        viewModel()
        viewListeners()
    }
    protected abstract fun viewBinding(inflater: LayoutInflater):VB
}