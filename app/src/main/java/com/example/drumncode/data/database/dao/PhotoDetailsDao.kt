package com.example.drumncode.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drumncode.data.database.PhotoDatabase
import com.example.drumncode.data.database.PhotoDatabase.Companion.PHOTO_ITEM_TABLE
import com.example.drumncode.data.database.PhotoItemDto

import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDetailsDao {
    @Query("SELECT * FROM $PHOTO_ITEM_TABLE")
    fun getAll(): Flow<List<PhotoItemDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhotoItemDto)

    @Query("DELETE FROM $PHOTO_ITEM_TABLE")
    suspend fun deleteAll()

    @Query("DELETE FROM $PHOTO_ITEM_TABLE WHERE id == :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM $PHOTO_ITEM_TABLE WHERE id == :id")
    suspend fun get(id: Long): PhotoItemDto?

    @Query("SELECT EXISTS (SELECT 1 FROM $PHOTO_ITEM_TABLE WHERE id = :id)")
    suspend fun exists(id: Long): Boolean

}