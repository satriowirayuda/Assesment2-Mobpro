package org.d3if0107.assesment2.ui.ui.Screen

import androidx.lifecycle.ViewModel
import org.d3if0107.assesment2.model.ListCustomer

class MainViewModel : ViewModel() {
    val data = getDataDummy()

    private fun getDataDummy(): List<ListCustomer> {
        val data = mutableListOf<ListCustomer>()
        val namaList = listOf(
            "Satrio Wirayuda",
            "Dio Wijaya",
            "Fadillah Alfaruq",
            "Albara Afgan",
            "Chaeza Fauzia",
            "Mariana",
            "Yudha Anditya"
        )
        val orderan = listOf(
            "Ayam Goreng 2\nMie Aceh 1",
            "Ayam Bakar 1\nEs Teh 1",
            "Ikan Nila 2\nEs jeruk 1",
            "Ayam Goreng 1",
            "Mie Aceh 1",
            "Mie Goreng 2\nMie Aceh 2\n Es Teh 2",
            "Dimsum Goreng 1\nMie Aceh 2\n Es Jeruk 1"
        )
        val jenis = listOf(
            "Makan Ditempat",
            "Makan Ditempat",
            "Dibungkus",
            "Makan Ditempat",
            "Dibungkus",
            "Dibungkus",
            "Dibungkus"
        )

        for (i in namaList.indices){
            data.add(
                ListCustomer(
                    i.toLong(),
                    "Pesanan ke ${i+1}",
                    namaList[i],
                    orderan[i],
                    jenis[i],
                    "2024-03-${i+1} 14:42:56"
                )
            )
        }
        return data
    }
}