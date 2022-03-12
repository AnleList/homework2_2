package ru.netology.hw2_2

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostID = 0

    fun add(post: Post): Post {
        if (lastPostID != 0) lastPostID += 1 else lastPostID = 1
        val nextId =  lastPostID
        val identifiedPost = post.copy(id = nextId)
        posts += post
        return identifiedPost
    }

    fun update (post: Post): Boolean {
        for ((index, arrPost) in posts.withIndex())
            if (arrPost.id == post.id) {
                posts[index] = post.copy(ownerId = arrPost.ownerId, date = arrPost.date)
                return true
            }
        return false
    }
}