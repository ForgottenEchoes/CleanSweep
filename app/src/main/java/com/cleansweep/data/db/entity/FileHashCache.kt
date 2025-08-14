package com.cleansweep.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Caches the content hash of a file to avoid re-calculating it on subsequent scans.
 *
 * @param filePath The absolute path to the file, serving as its unique ID.
 * @param lastModified The last modification timestamp of the file when it was hashed.
 * @param size The size of the file in bytes when it was hashed.
 * @param hash The pre-calculated SHA-256 content hash of the file.
 */
@Entity(tableName = "file_hash_cache")
data class FileHashCache(
    @PrimaryKey val filePath: String,
    val lastModified: Long,
    val size: Long,
    val hash: String
)