package ru.netology.hw2_2

object WallService {
    private var posts = emptyArray<Post>()

    fun add(post: Post): Post {
        posts += if (posts.isNotEmpty())
            post.copy(id = (posts.last()).id + 1)
        else post.copy(id = 1)
        return posts.last()
    }

    fun update (post: Post): Boolean {
        var isPostUpdated = false
        for ((index, arrPost) in posts.withIndex())
            if (arrPost.id == post.id) {
                posts[index] = post
                isPostUpdated = true
            }
        return isPostUpdated
    }
}