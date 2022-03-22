package ru.netology.hw2_2

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val reply_owner_id: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: Type,
    val postSource: PostSource,
    val geo: Geo,
    val signerId: Int,
    val copyHistory: Array<Post>?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Donut,
    val postponedId: Int
    ) {
    enum class Type {
        POST, COPY, REPLY, POSTPONE, SUGGEST
    }
}
