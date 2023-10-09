package com.example.drumncode.data.models

typealias UsualPhotoResult = PhotoResult<PhotoListEntity>
typealias UsualPhotoDetailsResult = PhotoDetailsResult<PhotoDetailsEntity>

sealed class PhotoResult<out T> {
    data class Success<T>(
        val result: List<T>?
    ) : PhotoResult<T>()

    data class Failure<T>(
        val errorText: String,
        val code: Int?
    ) : PhotoResult<T>()
}

sealed class PhotoDetailsResult<out T> {
    data class Success<T>(
        val result: T?
    ) : PhotoDetailsResult<T>()

    data class Failure<T>(
        val errorText: String,
        val code: Int?
    ) : PhotoDetailsResult<T>()
}


data class PhotoListEntity(
    val id: String?,
    val owner: String?,
    val secret: String?,
    val server: String?,
    val farm: Int?,
    val title: String?,
    val isPublic: Int?,
    val isFriend: Int?,
    val isFamily: Int?,
)

data class PhotoDetailsEntity(
    val id: String,
    val title: String?,
    val description: String?,
    val secret: String?,
    val server: String?,
    val farm: Int?,
)
