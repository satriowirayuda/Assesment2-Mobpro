package org.d3if0107.assesment2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pesanan")
data class ListCustomer(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val orderan: String,
    val nama : String,
    val pesanan: String,
    val jenis : String,
    val tanggal: String,
)
