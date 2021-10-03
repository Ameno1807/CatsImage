package com.example.catsimage.ui.viewModel

import android.nfc.tech.MifareUltralight
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.catsimage.data.CatsPagingSource
import com.example.catsimage.data.remote.retrofit.responce.CatsPhoto
import com.example.catsimage.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val repository: CatsRepository
) : ViewModel() {
    private val _catPhoto = MutableLiveData<CatsPhoto>()
    val catPhoto: LiveData<CatsPhoto> get() = _catPhoto

    val flow =
        repository.getSearchResults().asFlow().cachedIn(viewModelScope)

    fun getCat(id: String) {
        viewModelScope.launch {
            val result = repository.getCat(id)
            _catPhoto.value = result
        }
    }

}