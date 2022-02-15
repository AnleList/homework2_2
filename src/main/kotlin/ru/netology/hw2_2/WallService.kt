package ru.netology.hw2_2

import ru.netology.hw2_2.donut.Donut

object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        val postWithID: Post = post.copy(id = posts.size)
        posts += postWithID
        return posts.last()
    }

    fun update (post: Post): Boolean {
        var isPostUpdated = false
        for ((index, postWithID) in posts.withIndex())
            if (postWithID.id == post.id) {
                posts[index] = postWithID.copy(
                    fromId = post.fromId,
                    createdBy = post.createdBy,
                    text = post.text,
                    replyPostId = post.replyPostId,
                    friendsOnly = post.friendsOnly,
                    comments = post.comments,
                    copyright = post.copyright,
                    likes = post.likes,
                    reposts = post.reposts,
                    views = post.views,
                    postType = post.postType,
                    signerId = post.signerId,
                    canPin = post.canPin,
                    canDelete = post.canDelete,
                    canEdit = post.canEdit,
                    isPinned = post.isPinned,
                    markedAsAds = post.markedAsAds,
                    isFavorite = post.isFavorite,
                    donut = post.donut,
                    postponedId = post.postponedId
                )
                isPostUpdated = true
            }
        return isPostUpdated
    }
}