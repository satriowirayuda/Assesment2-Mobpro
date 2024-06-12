package org.d3if0107.assesment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if0107.assesment2.model.Barang
import org.d3if0107.assesment2.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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
    suspend fun getBarang() : List<Barang>

    @Multipart
    @POST("hewan.php")
    suspend fun postBarang(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("namaLatin") namaLatin: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus
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