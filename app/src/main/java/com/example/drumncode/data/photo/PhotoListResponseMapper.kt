package com.example.drumncode.data.photo

import com.example.drumncode.common.asPhoto
import com.example.drumncode.data.api.photodetails.PhotoDetailsResponse
import com.example.drumncode.data.api.photodetails.Title
import com.example.drumncode.data.api.photolist.PhotoResponse
import com.example.drumncode.data.models.PhotoDetailsEntity
import com.example.drumncode.data.models.PhotoDetailsResult
import com.example.drumncode.data.models.PhotoResult
import com.example.drumncode.data.models.UsualPhotoDetailsResult
import com.example.drumncode.data.models.UsualPhotoResult
import com.example.moviaapp.network.NetworkResponse

import javax.inject.Inject

class PhotoListResponseMapper @Inject constructor() {


    fun mapPhotoResponse(resultResponse: NetworkResponse<PhotoResponse, Any>): UsualPhotoResult {
        return when (resultResponse) {
            is NetworkResponse.Success -> {
                with(resultResponse.body) {
                    PhotoResult.Success(result = photos?.photos?.map {
                        it.asPhoto()
                    } ?: emptyList())
                }
            }

            is NetworkResponse.NetworkError -> PhotoResult.Failure(
                resultResponse.error.message.orEmpty(), null
            )

            is NetworkResponse.ApiError -> (PhotoResult.Failure(
                resultResponse.body.toString(), resultResponse.code
            ))

            is NetworkResponse.UnknownError -> (PhotoResult.Failure(
                resultResponse.error.message.orEmpty(), null
            ))
        }
    }

    fun mapPhotoDetails(resultResponse: NetworkResponse<PhotoDetailsResponse, Any>): UsualPhotoDetailsResult {
        return when (resultResponse) {
            is NetworkResponse.Success -> {
                with(resultResponse.body.photo) {
                    PhotoDetailsResult.Success(
                        result = PhotoDetailsEntity(
                            id = id,
                            title = title?.content.orEmpty(),
                            description = description._content,
                            secret = secret,
                            server = server,
                            farm = farm

                        )
                    )

                }
            }

            is NetworkResponse.NetworkError -> PhotoDetailsResult.Failure(
                resultResponse.error.message.orEmpty(), null
            )

            is NetworkResponse.ApiError -> (PhotoDetailsResult.Failure(
                resultResponse.body.toString(), resultResponse.code
            ))

            is NetworkResponse.UnknownError -> (PhotoDetailsResult.Failure(
                resultResponse.error.message.orEmpty(), null
            ))
        }
    }

}