package com.cleansweep.data.db.dao

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert

@Entity(tableName = "unreadable_file_cache")
data class UnreadableFileCache(
    @PrimaryKey val filePath: String,
    val lastModified: Long,
    val size: Long
)

@Dao
interface UnreadableFileCacheDao {
    @Upsert
    suspend fun upsertAll(files: List<UnreadableFileCache>)

    @Query("SELECT * FROM unreadable_file_cache")
    suspend fun getAll(): List<UnreadableFileCache>

    @Query("DELETE FROM unreadable_file_cache WHERE filePath IN (:paths)")
    suspend fun deleteByPaths(paths: List<String>)

    @Query("DELETE FROM unreadable_file_cache")
    suspend fun clear()
}