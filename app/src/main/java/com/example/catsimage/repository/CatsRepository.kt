package com.example.catsimage.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.catsimage.data.CatsPagingSource
import com.example.catsimage.data.remote.retrofit.ApiService
import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import javax.inject.Inject


class CatsRepository @Inject constructor(private val catsApi: ApiService) {
    fun getSearchResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatsPagingSource(catsApi) }
        ).liveData

    suspend fun getCat(id: String): CatsPhoto {
       return catsApi.getCat(id)
    }
}