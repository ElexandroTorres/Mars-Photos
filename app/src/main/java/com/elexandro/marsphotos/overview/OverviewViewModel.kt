package com.elexandro.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elexandro.marsphotos.network.MarsApi
import com.elexandro.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()

    public val status: LiveData<String> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    public val photos : LiveData<List<MarsPhoto>> = _photos


    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        //_status.value = "SET OS ESTADOS DA API AQUI"
        viewModelScope.launch {
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                //_status.value = "Sucessful: ${listResult.size} photos downloaded from API"
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = "Frist image URL: ${_photos.value!!.imgSrcUrl}"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }

        }
    }


}