package com.example.drumncode.ui.photodetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.drumncode.common.State
import com.example.drumncode.data.models.PhotoDetailsEntity
import com.example.drumncode.data.photo.PhotoDetailsRepo
import com.example.drumncode.di.DispatcherIo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject() constructor(
    private val details: PhotoDetailsRepo,
    @DispatcherIo private val workDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _photoList = MutableStateFlow<List<PhotoDetailsEntity>?>(null)


    val photoList: Flow<List<PhotoDetailsEntity>?> = _photoList
    private val _state = MutableStateFlow<State<Any, Any>>(State.Loading)
    var state: Flow<State<Any, Any>> = _state

    init {
        getDetails()
    }

    private fun getDetails() {
        viewModelScope.launch {
            details.getCachedItem
                .catch { e ->
                    _state.value = State.Error(Any())
                }
                .flowOn(workDispatcher)
                .collect {
                    _photoList.emit(it)
                    if (_photoList.value?.size?.equals(2) == true){
                        _state.value = State.Content(Any())
                    }
                }
        }
    }

    fun deleteList() {
        viewModelScope.launch {
            details.clearBase()
        }
    }

}