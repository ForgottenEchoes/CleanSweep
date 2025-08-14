package com.cleansweep.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cleansweep.data.db.dao.FileHashDao
import com.cleansweep.data.db.dao.FolderDetailsDao
import com.cleansweep.data.db.dao.PHashDao
import com.cleansweep.data.db.dao.SimilarGroupDao
import com.cleansweep.data.db.dao.UnreadableFileCache
import com.cleansweep.data.db.dao.UnreadableFileCacheDao
import com.cleansweep.data.db.entity.FileHashCache
import com.cleansweep.data.db.entity.FolderDetailsCache
import com.cleansweep.data.db.entity.PHashCache
import com.cleansweep.data.db.entity.SimilarGroupCache

@Database(
    entities = [
        FileHashCache::class,
        FolderDetailsCache::class,
        PHashCache::class,
        SimilarGroupCache::class,
        UnreadableFileCache::class
    ],
    version = 5,
    exportSchema = true
)
abstract class CleanSweepDatabase : RoomDatabase() {
    abstract fun fileHashDao(): FileHashDao
    abstract fun folderDetailsDao(): FolderDetailsDao
    abstract fun pHashDao(): PHashDao
    abstract fun similarGroupDao(): SimilarGroupDao
    abstract fun unreadableFileCacheDao(): UnreadableFileCacheDao

    companion object {
        const val DATABASE_NAME = "cleansweep_db"
    }
}