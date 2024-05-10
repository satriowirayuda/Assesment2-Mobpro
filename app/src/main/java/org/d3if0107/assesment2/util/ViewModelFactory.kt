package org.d3if0107.assesment2.util

import androidx.lifecycle.ViewModel
import org.d3if0107.assesment2.database.PesananDao
import androidx.lifecycle.ViewModelProvider
import org.d3if0107.assesment2.ui.ui.Screen.DetailViewModel
import org.d3if0107.assesment2.ui.ui.Screen.MainViewModel

class ViewModelFactory (
    private val dao: PesananDao
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}