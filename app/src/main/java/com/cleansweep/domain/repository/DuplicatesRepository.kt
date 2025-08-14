package com.cleansweep.domain.repository

import android.content.Context
import android.content.SharedPreferences
import com.cleansweep.data.db.dao.UnreadableFileCache
import com.cleansweep.data.db.dao.UnreadableFileCacheDao
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class DuplicatesRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val unreadableFileCacheDao: UnreadableFileCacheDao
) {
    companion object {
        private const val PREFS_NAME = "duplicates_prefs"
        private const val KEY_HIDDEN_GROUP_IDS = "hidden_group_ids"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    suspend fun getHiddenGroupIds(): Set<String> = withContext(Dispatchers.IO) {
        prefs.getStringSet(KEY_HIDDEN_GROUP_IDS, emptySet()) ?: emptySet()
    }

    suspend fun hideGroupId(groupId: String) = withContext(Dispatchers.IO) {
        val currentHiddenIds = getHiddenGroupIds().toMutableSet()
        currentHiddenIds.add(groupId)
        prefs.edit { putStringSet(KEY_HIDDEN_GROUP_IDS, currentHiddenIds) }
    }

    suspend fun getUnreadableFileCache(): List<UnreadableFileCache> = withContext(Dispatchers.IO) {
        return@withContext unreadableFileCacheDao.getAll()
    }

    suspend fun updateUnreadableFileCache(newlyUnreadable: List<File>) = withContext(Dispatchers.IO) {
        if (newlyUnreadable.isEmpty()) return@withContext

        val cacheEntries = newlyUnreadable.map {
            UnreadableFileCache(
                filePath = it.absolutePath,
                lastModified = it.lastModified(),
                size = it.length()
            )
        }
        unreadableFileCacheDao.upsertAll(cacheEntries)
    }

    suspend fun clearUnreadableFileCache() = withContext(Dispatchers.IO) {
        unreadableFileCacheDao.clear()
    }
}