package org.d3if0107.assesment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0107.assesment2.model.Barang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://gh.d3ifcool.org/"

private  val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface BarangApiService {
    @GET("hewan.php")
    suspend fun getHewan() : List<Barang>
}
object BarangApi {
    val service: BarangApiService by lazy {
        retrofit.create(BarangApiService::class.java)
    }

    fun getBarangUrl(imageId: String): String{
        return "${BASE_URL}image.php?id=$imageId"
    }
    enum class ApiStatus{ LOADING, SUCCESS, FAILED }
}