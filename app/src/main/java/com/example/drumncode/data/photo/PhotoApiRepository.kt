package com.example.drumncode.data.photo

import com.example.drumncode.common.toFormattedString
import com.example.drumncode.data.api.photolist.PhotoApi
import com.example.drumncode.data.models.UsualPhotoResult
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class PhotoApiRepository  @Inject constructor(
    private val api: PhotoApi,
    private val apiMapper: PhotoListResponseMapper,
) {

    // CREATE THIS CAUSE U CAN GET EMPTY LIST FOR CURRENT DAY
    private fun getCurrentDateMinusOneDayFormatted(): String {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val formattedDate = calendar.time.toFormattedString()
        return formattedDate
    }

     suspend fun requestPopularPhoto(): UsualPhotoResult {
        return apiMapper.mapPhotoResponse(api.getPhotoList(date = getCurrentDateMinusOneDayFormatted()))
    }
}