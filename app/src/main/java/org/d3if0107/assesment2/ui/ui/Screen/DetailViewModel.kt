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

    suspend fun getPesanan(id: Long): ListCustomer?{
        return dao.getPesananById(id)
    }

    fun update(id: Long, orderan: String, nama: String, pesanan: String, jenis: String){
        val listCustomer = ListCustomer(
            id = id,
            tanggal = formatter.format(Date()),
            orderan = orderan,
            nama = nama,
            pesanan = pesanan,
            jenis = jenis,
        )
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(listCustomer)
        }
    }
}