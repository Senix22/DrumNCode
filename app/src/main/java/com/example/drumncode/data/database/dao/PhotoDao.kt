package com.example.drumncode.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.drumncode.data.database.PhotoDatabase.Companion.PHOTO_LIST_TABLE
import com.example.drumncode.data.database.PhotoDto
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {
    @Query("SELECT * FROM $PHOTO_LIST_TABLE")
    fun getAll(): Flow<List<PhotoDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: PhotoDto)

    @Query("DELETE FROM $PHOTO_LIST_TABLE")
    suspend fun deleteAll()

    @Query("DELETE FROM $PHOTO_LIST_TABLE WHERE id == :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM $PHOTO_LIST_TABLE WHERE id == :id")
    suspend fun get(id: Long): PhotoDto?

    @Query("SELECT EXISTS (SELECT 1 FROM $PHOTO_LIST_TABLE WHERE id = :id)")
    suspend fun exists(id: Long): Boolean

    @Transaction
    suspend fun deleteAndCreate(entity: PhotoDto) {
        deleteAll()
        insert(entity)
    }
}