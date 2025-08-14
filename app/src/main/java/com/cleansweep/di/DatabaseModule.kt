package com.cleansweep.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cleansweep.data.db.CleanSweepDatabase
import com.cleansweep.data.db.dao.FileHashDao
import com.cleansweep.data.db.dao.FolderDetailsDao
import com.cleansweep.data.db.dao.PHashDao
import com.cleansweep.data.db.dao.SimilarGroupDao
import com.cleansweep.data.db.dao.UnreadableFileCacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE image_phash_cache RENAME TO phash_cache;")
        }
    }

    private val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "CREATE TABLE IF NOT EXISTS `unreadable_file_cache` (`filePath` TEXT NOT NULL, `lastModified` INTEGER NOT NULL, `size` INTEGER NOT NULL, PRIMARY KEY(`filePath`))"
            )
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CleanSweepDatabase {
        return Room.databaseBuilder(
            context,
            CleanSweepDatabase::class.java,
            CleanSweepDatabase.DATABASE_NAME
        )
            .addMigrations(MIGRATION_3_4, MIGRATION_4_5)
            .build()
    }

    @Provides
    @Singleton
    fun provideFileHashDao(database: CleanSweepDatabase): FileHashDao {
        return database.fileHashDao()
    }

    @Provides
    @Singleton
    fun providePHashDao(database: CleanSweepDatabase): PHashDao {
        return database.pHashDao()
    }

    @Provides
    @Singleton
    fun provideSimilarGroupDao(database: CleanSweepDatabase): SimilarGroupDao {
        return database.similarGroupDao()
    }

    @Provides
    @Singleton
    fun provideFolderDetailsDao(database: CleanSweepDatabase): FolderDetailsDao {
        return database.folderDetailsDao()
    }

    @Provides
    @Singleton
    fun provideUnreadableFileCacheDao(database: CleanSweepDatabase): UnreadableFileCacheDao {
        return database.unreadableFileCacheDao()
    }
}