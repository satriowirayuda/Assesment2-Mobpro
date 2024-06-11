package org.d3if0107.assesment2.ui.Ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if0107.assesment2.model.Hewan
import org.d3if0107.assesment2.network.HewanApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Hewan>())
        private set
    var status = MutableStateFlow(HewanApi.ApiStatus.LOADING)
        private set
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO){
            status.value = HewanApi.ApiStatus.LOADING
            try{
                data.value = HewanApi.service.getHewan()
                status.value = HewanApi.ApiStatus.SUCCESS
            } catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}