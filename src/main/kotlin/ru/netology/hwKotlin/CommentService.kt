package ru.netology.hwKotlin

import ru.netology.hwKotlin.attachments.NoteAttachment

object CommentService: CredService<Comment>  {
    private var lastCommentID = 0L
    private val comments = mutableListOf<Comment>()

    fun createComment(comment: Comment): Comment {

    }

    fun deleteComment(comment: Comment) {
        var isThereCommentIdInComments = false
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == comment.id) {
                comments[index] = eachComment.copy(isCommentDeleted = true)
                isThereCommentIdInComments = true
            }
        if (!isThereCommentIdInComments) {
            throw TargetNotFoundException("there is no Comment that should be deleted")
        }
    }

    fun editComment(comment: Comment) {
        var isThereCommentIdInComments = false
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == comment.id) {
                comments[index] = comment
                isThereCommentIdInComments = true
            }
        if (!isThereCommentIdInComments) {
            throw TargetNotFoundException("there is no Comment that should be edited")
        }
    }

    fun getComments (note: NoteAttachment): MutableList<Comment> {
        val commentsToReturn = mutableListOf<Comment>()
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.targetId == note.id) {
                commentsToReturn.add(comments[index])
            }
        return commentsToReturn
    }

    fun restoreComment (commentId: Int): Comment {
        var commentToReturn: Comment? = null
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == commentId) {
                comments[index] = (comments[index]).copy(isCommentDeleted = false)
                commentToReturn = (comments[index]).copy(isCommentDeleted = false)
            }
        return commentToReturn ?:
        throw TargetNotFoundException("there is no Comment that should be restore")
    }



    override fun add(comment: Comment): Long {
        lateinit var identifiedComment: Comment
        for (eachNote in NoteService.read())
            if (eachNote.id == comment.targetId) {
                lastCommentID += 1L
                identifiedComment = comment.copy(id = lastCommentID)
                comments += identifiedComment
            } else
                throw TargetNotFoundException("there is no comment to return or no comment with this targetId")
        return identifiedComment.id
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun edit(entity: Comment) {
        TODO("Not yet implemented")
    }

    override fun read(): List<Comment> {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): Comment {
        TODO("Not yet implemented")
    }

    override fun restore(id: Long) {
        TODO("Not yet implemented")
    }
}