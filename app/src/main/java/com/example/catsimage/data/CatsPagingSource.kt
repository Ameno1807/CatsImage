package com.example.catsimage.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catsimage.data.remote.retrofit.ApiService
import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class CatsPagingSource(
    private val catsApi: ApiService
) : PagingSource<Int, CatsPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, CatsPhoto>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatsPhoto> {
        return try {
            val count = params.key ?: 1
            val loadSize = params.loadSize
            val response = catsApi.getCatsList(loadSize, count)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = count + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}