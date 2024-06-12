package org.d3if0107.assesment2.ui.Ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0107.assesment2.model.Barang
import org.d3if0107.assesment2.network.BarangApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Barang>())
        private set
    var status = MutableStateFlow(BarangApi.ApiStatus.LOADING)
        private set
    init {
        retrieveData()
    }

    fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO){
            status.value = BarangApi.ApiStatus.LOADING
            try{
                data.value = BarangApi.service.getHewan()
                status.value = BarangApi.ApiStatus.SUCCESS
            } catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = BarangApi.ApiStatus.FAILED
            }
        }
    }
}