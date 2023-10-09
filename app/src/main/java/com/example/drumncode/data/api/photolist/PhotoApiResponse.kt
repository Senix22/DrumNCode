package com.example.drumncode.data.api.photolist

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("photos") val photos: Additional?,
    @SerializedName("extra") val extra: Extra,
    @SerializedName("stat") val stat: String
)

data class Additional(
    @SerializedName("page") val page: Int?,
    @SerializedName("pages") val pages: Int?,
    @SerializedName("perpage") val perpage: Int?,
    @SerializedName("total") val total: Int?,
    @SerializedName("photo") val photos: List<ResultResponse>?
)

data class ResultResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("owner") val owner: String?,
    @SerializedName("secret") val secret: String?,
    @SerializedName("server") val server: String?,
    @SerializedName("farm") val farm: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("ispublic") val isPublic: Int?,
    @SerializedName("isfriend") val isFriend: Int?,
    @SerializedName("isfamily") val isFamily: Int?,
)

data class Extra(
    @SerializedName("explore_date") val explore_date: String?,
    @SerializedName("next_prelude_interval") val next_prelude_interval: Int?
)

