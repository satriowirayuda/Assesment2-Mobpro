package org.d3if0107.assesment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0107.assesment2.model.ListCustomer
@Dao
interface PesananDao {
    @Insert
    suspend fun insert(pesanan: ListCustomer)

    @Update
    suspend fun update(pesanan: ListCustomer)

    @Query("SELECT * FROM pesanan ORDER BY tanggal DESC")
    fun getPesanan(): Flow<List<ListCustomer>>

    @Query("SELECT * FROM pesanan WHERE id= :id")
    suspend fun getPesananById(id: Long): ListCustomer?


}