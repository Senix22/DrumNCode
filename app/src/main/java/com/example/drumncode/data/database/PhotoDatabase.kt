package com.example.drumncode.data.database

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.example.drumncode.data.database.PhotoDatabase.Companion.PHOTO_LIST_TABLE
import com.example.drumncode.data.database.PhotoDatabase.Companion.DATABASE_VERSION
import com.example.drumncode.data.database.PhotoDatabase.Companion.PHOTO_ITEM_TABLE
import com.example.drumncode.data.database.dao.PhotoDao
import com.example.drumncode.data.database.dao.PhotoDetailsDao

@Database(
    entities = [PhotoDto::class, PhotoItemDto::class],
    version = DATABASE_VERSION,
    exportSchema = true
)
abstract class PhotoDatabase : RoomDatabase() {

    abstract fun photoListDao(): PhotoDao
    abstract fun photoItemDao(): PhotoDetailsDao

    companion object {
        const val DATABASE_NAMESPACE = "PhotoDatabase"
        const val PHOTO_LIST_TABLE = "PhotoList"
        const val PHOTO_ITEM_TABLE = "PhotoItem"
        const val DATABASE_VERSION = 1
    }
}

@Entity(tableName = PHOTO_LIST_TABLE)
data class PhotoDto(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "owner") val owner: String?,
    @ColumnInfo(name = "secret") val secret: String?,
    @ColumnInfo(name = "server") val server: String?,
    @ColumnInfo(name = "farm") val farm: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "ispublic") val isPublic: Int?,
    @ColumnInfo(name = "isfriend") val isFriend: Int?,
    @ColumnInfo(name = "isfamily") val isFamily: Int?,
)

@Entity(tableName = PHOTO_ITEM_TABLE)
data class PhotoItemDto(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "secret") val secret: String?,
    @ColumnInfo(name = "server") val server: String?,
    @ColumnInfo(name = "farm") val farm: Int?,
)