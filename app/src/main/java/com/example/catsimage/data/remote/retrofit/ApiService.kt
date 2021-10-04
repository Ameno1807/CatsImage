package com.example.catsimage.data.remote.retrofit

import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "73f93d4e-b414-46c6-947f-b2132f643204"

interface ApiService {

    @Headers("x-api-key:$API_KEY")
    @GET("{image_id}")
    suspend fun getCat(@Path("image_id")id: String): CatsPhoto

    @Headers("x-api-key:$API_KEY")
    @GET("search?&order=desc?mime_types=jpg,png")
    suspend fun getCatsList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<CatsPhoto>

}