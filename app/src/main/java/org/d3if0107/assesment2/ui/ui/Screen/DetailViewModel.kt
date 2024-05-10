package org.d3if0107.assesment2.ui.ui.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0107.assesment2.database.PesananDao
import org.d3if0107.assesment2.model.ListCustomer
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel (private val  dao: PesananDao) : ViewModel() {
    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(orderan: String, nama: String, pesanan: String, jenis: String) {
        val listCustomer = ListCustomer(
            tanggal = formatter.format(Date()),
            orderan = orderan,
            nama = nama,
            pesanan = pesanan,
            jenis = jenis,
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(listCustomer)
        }
    }
    fun getPesanan(id: Long): ListCustomer? {
        val pesanan = listOf(
            ListCustomer(0, "Pesanan ke 1", "Satrio Wirayuda", "Ayam Goreng 2\nMie Aceh 1", "Makan Ditempat", "2024-03-$id 14:42:56"),
            ListCustomer(1, "Pesanan ke 2", "Dio Wijaya", "Ayam Bakar 1\nEs Teh 1", "Makan Ditempat", "2024-03-$id 14:42:56"),
            ListCustomer(2,"Pesanan ke 3", "Fadillah Alfaruq", "Ikan Nila 2\nEs jeruk 1", "Dibungkus", "2024-03-$id 14:42:56" ),
            ListCustomer(3,"Pesanan ke 4", "Albara Afgan", "Ayam Goreng 1", "Makan Ditempat", "2024-03-$id 14:42:56"),
            ListCustomer(4, "Pesanan ke 5", "Chaeza Fauzia", "Mie Aceh 1","Dibungkus", "2024-03-$id 14:42:56"),
            ListCustomer(5,"Pesanan ke 6", "Mariana", "Mie Goreng 2\nMie Aceh 2\n Es Teh 2", "Dibungkus", "2024-03-$id 14:42:56" ),
            ListCustomer(6, "Pesanan ke 7", "Yudha Anditya", "Dimsum Goreng 1\nMie Aceh 2\n Es Jeruk 1", "Dibungkus", "2024-03-$id 14:42:56")
        )
        return pesanan.firstOrNull { it.id == id }
    }
}