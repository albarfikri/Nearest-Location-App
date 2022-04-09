package com.albar.nearestlocation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewbinding.BuildConfig
import com.albar.nearestlocation.data.model.details.ModelDetail
import com.albar.nearestlocation.data.model.nearby.ModelResults
import com.albar.nearestlocation.networking.ApiClient


class MainViewModel : ViewModel() {
    private val modelResultsMutableLiveData = MutableLiveData<ArrayList<ModelResults>>()
    private val modelDetailMutableLiveData = MutableLiveData<ModelDetail>()

    companion object{
        var strApiKey = BuildConfig.BUILD_TYPE
    }
    fun setMarkerLocation(strLocation: String) {
        val apiService = ApiClient.getClient()
        val call = apiService.getDataResult()
    }
}