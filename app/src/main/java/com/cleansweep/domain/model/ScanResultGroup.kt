package com.cleansweep.domain.model

import com.cleansweep.data.model.MediaItem

/**
 * A sealed interface representing a group of scan results.
 * This can be either a group of exact duplicates or a group of visually similar media.
 */
sealed interface ScanResultGroup {
    val items: List<MediaItem>
    val uniqueId: String
}

/**
 * Represents a group of files that are byte-for-byte identical.
 *
 * @property hash The SHA-256 hash common to all files in this group.
 * @property items The list of identical media items. These are pre-sorted by date.
 * @property sizePerFile The size of each individual file in the group.
 */
data class ExactDuplicateGroup(
    val hash: String,
    override val items: List<MediaItem>,
    val sizePerFile: Long
) : ScanResultGroup {
    override val uniqueId: String get() = hash
}

/**
 * Represents a group of visually similar media items (images or videos).
 *
 * @property pHash The perceptual hash common to all media items in this group.
 * @property items The list of visually similar media items.
 */
data class SimilarMediaGroup(
    val pHash: String,
    override val items: List<MediaItem>
) : ScanResultGroup {
    override val uniqueId: String get() = pHash
}