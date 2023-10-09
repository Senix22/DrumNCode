package com.example.drumncode.data.database

import com.example.drumncode.data.models.PhotoDetailsEntity
import com.example.drumncode.data.models.PhotoListEntity
import javax.inject.Inject

class PhotoDataMapper @Inject constructor() {
    operator fun invoke(dto: PhotoDto): PhotoListEntity =
        with(dto) {
            PhotoListEntity(
                id = id,
                owner = owner,
                secret = secret,
                server = server,
                farm = farm,
                title = title,
                isPublic = isPublic,
                isFriend = isFriend,
                isFamily = isFamily
            )
        }

    operator fun invoke(entity: PhotoListEntity): PhotoDto =
        with(entity) {
            PhotoDto(
                id = id.orEmpty(),
                owner = owner,
                secret = secret,
                server = server,
                farm = farm,
                title = title,
                isPublic = isPublic,
                isFriend = isFriend,
                isFamily = isFamily
            )
        }

    operator fun invoke(entity: PhotoDetailsEntity?): PhotoItemDto =
        with(entity) {
            PhotoItemDto(
                id = this?.id.orEmpty(),
                title = this?.title.orEmpty(),
                description = this?.description,
                secret = this?.secret,
                server = this?.server,
                farm = this?.farm
            )
        }

    operator fun invoke(entity: PhotoItemDto): PhotoDetailsEntity =
        with(entity) {
            PhotoDetailsEntity(
                id = id,
                title = title,
                description = description,
                secret = secret,
                server = server,
                farm = farm
            )
        }
}