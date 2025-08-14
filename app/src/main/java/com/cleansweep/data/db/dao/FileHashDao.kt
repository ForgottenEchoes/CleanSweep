package com.cleansweep.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.cleansweep.data.db.entity.FileHashCache

@Dao
interface FileHashDao {
    @Query("SELECT * FROM file_hash_cache")
    suspend fun getAllHashes(): List<FileHashCache>

    @Upsert
    suspend fun upsertHashes(hashes: List<FileHashCache>)

    @Query("DELETE FROM file_hash_cache WHERE filePath IN (:paths)")
    suspend fun deleteHashesByPath(paths: List<String>)

    @Query("DELETE FROM file_hash_cache")
    suspend fun clear()
}