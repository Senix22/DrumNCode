package com.example.drumncode.data.photo

import android.util.Log
import com.example.drumncode.data.api.photolist.PhotoApi
import com.example.drumncode.data.database.PhotoDataMapper
import com.example.drumncode.data.database.PhotoDatabase
import com.example.drumncode.data.database.dao.PhotoDetailsDao
import com.example.drumncode.data.models.PhotoDetailsEntity
import com.example.drumncode.data.models.PhotoDetailsResult
import com.example.drumncode.data.models.UsualPhotoDetailsResult
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PhotoDetailsRepo @Inject constructor(
    private val api: PhotoApi,
    private val database: PhotoDatabase,
    private val mapper: PhotoDataMapper,
    private val apiMapper: PhotoListResponseMapper,
) {

    private val photoItemDao: PhotoDetailsDao
        get() = database.photoItemDao()

    val getCachedItem: Flow<List<PhotoDetailsEntity>>
        get() = photoItemDao.getAll().map { news -> news.map { mapper(it) } }

    private suspend fun requestPopularPhoto(id: String): UsualPhotoDetailsResult {
        return apiMapper.mapPhotoDetails(api.getPhotoDetailsList(photoId = id))
    }

    suspend fun clearBase() {
        photoItemDao.deleteAll()
    }

    suspend fun getResult(id: String) = coroutineScope {
        val result = requestPopularPhoto(id)
        when (result) {
            is PhotoDetailsResult.Failure -> {
                Log.e("PhotoDetailsResult", "addPhoto: ${result.errorText}")
            }

            is PhotoDetailsResult.Success -> {
                photoItemDao.insert(
                    mapper(
                        result.result
                    )
                )
            }
        }
    }
}