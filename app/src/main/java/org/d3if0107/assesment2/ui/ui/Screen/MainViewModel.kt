package org.d3if0107.assesment2.ui.ui.Screen

import androidx.lifecycle.ViewModel
import org.d3if0107.assesment2.model.ListCustomer

class MainViewModel : ViewModel() {
    private  fun getDataDummny(): List<ListCustomer>{
        val data = mutableListOf<ListCustomer>()
        for (i in 29 downTo 20){
            data.add(
                ListCustomer(
                    i.toLong(),
                    "Satrio Wirayuda $i",
                    "0895614951302 $i",
                    "$i",
                    "2024-05-$i 12:51:12"
                )
            )
        }
        return data
    }

}