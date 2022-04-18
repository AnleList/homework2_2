package ru.netology.hwKotlin

object WallService {
    private var posts = emptyArray<Post>()
    private var lastPostID = 0
    private var comments = emptyArray<Comment>()

    fun add(post: Post): Post {
        if (lastPostID != 0) lastPostID += 1 else lastPostID = 1
        val nextId =  lastPostID
        val identifiedPost = post.copy(id = nextId)
        posts += identifiedPost
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

    fun createComment(comment: Comment) {
        var isTherePostIdInPosts = false
        for (arrPost in posts)
            if (arrPost.id == comment.targetId) {
                comments += comment
                isTherePostIdInPosts = true
            }
        if (!isTherePostIdInPosts) {
                throw TargetNotFoundException("there is no post for the comment being added now")
        }
    }
}