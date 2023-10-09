package com.example.drumncode.ui.photolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drumncode.common.State
import com.example.drumncode.common.isNotNullOrEmpty
import com.example.drumncode.data.models.PhotoListEntity
import com.example.drumncode.data.photo.PhotoDetailsRepo
import com.example.drumncode.data.photo.PhotoRepo
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
class PhotoListViewModel @Inject constructor(
    private val photoRepo: PhotoRepo,
    @DispatcherIo private val workDispatcher: CoroutineDispatcher,
    private val details: PhotoDetailsRepo,
) : ViewModel() {
    private val _savedMovie: MutableStateFlow<State<List<PhotoListEntity>, String>> =
        MutableStateFlow(State.Loading)
    val savedMovie: Flow<State<List<PhotoListEntity>, String>> = _savedMovie

    init {
        viewModelScope.launch {
            photoRepo.getPhotoList()

        }
        showPhoto()
    }

    fun getDetails(currentId: String, prevId: String?, nextId: String?) {
        viewModelScope.launch {
            details.clearBase()
            prevId.isNotNullOrEmpty { id ->
                details.getResult(id)
            }
            details.getResult(currentId)
            nextId?.isNotNullOrEmpty {
                details.getResult(it)
            }
        }
    }

    private fun showPhoto() {
        viewModelScope.launch {
            photoRepo.allPhoto.flowOn(workDispatcher).catch {
                _savedMovie.value = State.Error(it.message.toString())
            }.collect {
                _savedMovie.value = if (it.isEmpty()) {
                    State.Empty
                } else {
                    State.Content(it)
                }
            }
        }
    }
}