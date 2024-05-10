package org.d3if0107.assesment2.ui.ui.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if0107.assesment2.database.PesananDao
import org.d3if0107.assesment2.model.ListCustomer

class MainViewModel(dao: PesananDao) : ViewModel() {

    val data: StateFlow<List<ListCustomer>> = dao.getPesanan().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}