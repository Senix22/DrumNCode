package com.example.drumncode.data.api.photodetails

import com.google.gson.annotations.SerializedName


data class PhotoDetailsResponse(
    @SerializedName("photo") val photo : PhotoResponse
)
data class PhotoResponse(
    @SerializedName("id") val id: String,
    @SerializedName("secret") val secret: String,
    @SerializedName("server") val server: String,
    @SerializedName("farm") val farm: Int,
    @SerializedName("dateuploaded") val dateuploaded: String,
    @SerializedName("isfavorite") val isfavorite: Int,
    @SerializedName("license") val license: String,
    @SerializedName("safety_level") val safety_level: String,
    @SerializedName("rotation") val rotation: Int,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("title") val title: Title?,
    @SerializedName("description") val description: Description,
    @SerializedName("visibility") val visibility: Visibility,
    @SerializedName("dates") val dates: Dates,
    @SerializedName("views") val views: String,
    @SerializedName("editability") val editability: Editability,
    @SerializedName("publiceditability") val publiceditability: Editability,
    @SerializedName("usage") val usage: Usage,
    @SerializedName("comments") val comments: Comments,
    @SerializedName("notes") val notes: Notes,
    @SerializedName("people") val people: People,
    @SerializedName("tags") val tags: Tags,
    @SerializedName("urls") val urls: Urls,
    @SerializedName("media") val media: String,
    @SerializedName("stat") val stat: String
)

data class Owner(
    @SerializedName("nsid") val nsid: String,
    @SerializedName("username") val username: String,
    @SerializedName("realname") val realname: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("iconserver") val iconserver: String,
    @SerializedName("iconfarm") val iconfarm: Int,
    @SerializedName("path_alias") val path_alias: String,
    @SerializedName("gift") val gift: Gift
)

data class Gift(
    @SerializedName("gift_eligible") val gift_eligible: Boolean,
    @SerializedName("eligible_durations") val eligible_durations: List<String>,
    @SerializedName("new_flow") val new_flow: Boolean
)

data class Title(
    @SerializedName("_content") val content: String?
)

data class Description(
    @SerializedName("_content") val _content: String
)

data class Visibility(
    @SerializedName("ispublic") val ispublic: Int,
    @SerializedName("isfriend") val isfriend: Int,
    @SerializedName("isfamily") val isfamily: Int
)

data class Dates(
    @SerializedName("posted") val posted: String,
    @SerializedName("taken") val taken: String,
    @SerializedName("takengranularity") val takengranularity: Int,
    @SerializedName("takenunknown") val takenunknown: String,
    @SerializedName("lastupdate") val lastupdate: String
)

data class Editability(
    @SerializedName("cancomment") val cancomment: Int,
    @SerializedName("canaddmeta") val canaddmeta: Int
)

data class Usage(
    @SerializedName("candownload") val candownload: Int,
    @SerializedName("canblog") val canblog: Int,
    @SerializedName("canprint") val canprint: Int,
    @SerializedName("canshare") val canshare: Int
)

data class Comments(
    @SerializedName("_content") val _content: String
)

data class Notes(
    @SerializedName("note") val note: List<Any> // При необхідності змініть тип даних для `note`
)

data class People(
    @SerializedName("haspeople") val haspeople: Int
)

data class Tags(
    @SerializedName("tag") val tag: List<Tag>
)

data class Tag(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("authorname") val authorname: String,
    @SerializedName("raw") val raw: String,
    @SerializedName("_content") val _content: String,
    @SerializedName("machine_tag") val machine_tag: Int
)

data class Urls(
    @SerializedName("url") val url: List<Url>
)

data class Url(
    @SerializedName("type") val type: String,
    @SerializedName("_content") val _content: String
)