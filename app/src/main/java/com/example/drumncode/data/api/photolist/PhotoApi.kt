package com.example.drumncode.data.api.photolist

import com.example.drumncode.data.api.photodetails.PhotoDetailsResponse
import com.example.moviaapp.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query


const val KEY = "531d11b4153d70488de3e69321772c9d"

interface PhotoApi {

    @GET("?method=flickr.interestingness.getList&format=json&nojsoncallback=1")
    suspend fun getPhotoList(
        @Query("api_key") apiKey: String = KEY,
        @Query("date") date: String,
        @Query("page") page: String = "1",
        @Query("per_page") perPage: String = "9",
    ): NetworkResponse<PhotoResponse, Any>

    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1")
    suspend fun getPhotoDetailsList(
        @Query("api_key") apiKey: String = KEY,
        @Query("photo_id") photoId: String,
    ): NetworkResponse<PhotoDetailsResponse, Any>
}