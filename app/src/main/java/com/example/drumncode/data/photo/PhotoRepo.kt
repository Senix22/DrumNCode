package com.example.drumncode.data.photo

import android.util.Log
import com.example.drumncode.common.toFormattedString
import com.example.drumncode.data.api.photolist.PhotoApi
import com.example.drumncode.data.database.dao.PhotoDao
import com.example.drumncode.data.database.PhotoDataMapper
import com.example.drumncode.data.database.PhotoDatabase
import com.example.drumncode.data.models.PhotoListEntity
import com.example.drumncode.data.models.PhotoResult
import com.example.drumncode.data.models.UsualPhotoResult
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoApi: PhotoApiRepository,
    private val database: PhotoDatabase,
    private val mapper: PhotoDataMapper
) {

    private val photoDao: PhotoDao
        get() = database.photoListDao()

    val allPhoto: Flow<List<PhotoListEntity>>
        get() = photoDao.getAll()
            .map { news -> news.map { mapper(it) } }


    suspend fun add(entity: PhotoListEntity) =
        photoDao.insert(
            mapper(entity)
        ).let {
            Log.d("dao", "add")
        }


    suspend fun getPhotoList() = coroutineScope {
        val photoResult = photoApi.requestPopularPhoto()
        when (photoResult) {
            is PhotoResult.Failure -> {
                Log.e("PhotoResult", "addPhoto: ${photoResult.errorText}")
            }

            is PhotoResult.Success -> {
                photoDao.deleteAll()
                photoResult.result?.map {
                    add(it)
                }
            }
        }
    }
}