package com.example.drumncode.common

import android.view.View
import com.example.drumncode.data.api.photolist.ResultResponse
import com.example.drumncode.data.models.PhotoListEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun ResultResponse.asPhoto() = PhotoListEntity(
    id = id.orEmpty(),
    owner = owner.orEmpty(),
    secret = secret.orEmpty(),
    server = server.orEmpty(),
    farm = farm ?: 0,
    title = title.orEmpty(),
    isPublic = isPublic ?: 0,
    isFriend = isFriend ?: 0,
    isFamily = isFamily ?: 0
)

fun Date.toFormattedString(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(this)
}

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}


fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

inline fun <T : Any, R> T?.isNotNullOrEmpty(block: (T) -> R): R? {
    return if (this != null) {
        if (this is String && this.isEmpty()) {
            null
        } else if (this is Collection<*> && this.isEmpty()) {
            null
        } else {
            block(this)
        }
    } else {
        null
    }
}
