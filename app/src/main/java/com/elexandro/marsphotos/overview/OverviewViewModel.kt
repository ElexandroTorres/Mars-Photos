package com.elexandro.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elexandro.marsphotos.network.MarsApi
import com.elexandro.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch


enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<MarsApiStatus>()

    public val status: LiveData<MarsApiStatus> = _status

    private val _photos = MutableLiveData<List<MarsPhoto>>()
    public val photos : LiveData<List<MarsPhoto>> = _photos


    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        //_status.value = "SET OS ESTADOS DA API AQUI"
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                val listResult = MarsApi.retrofitService.getPhotos()
                //_status.value = "Sucessful: ${listResult.size} photos downloaded from API"
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                //_status.value = "Failure: ${e.message}"
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }

        }
    }


}