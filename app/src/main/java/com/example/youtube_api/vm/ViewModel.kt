package com.example.youtube_api.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ViewModel :ViewModel(){
    val mutableLiveData =MutableLiveData<Boolean>()
}