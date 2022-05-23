package ru.netology.hwKotlin

import ru.netology.hwKotlin.exceptions.TargetDeletedException
import ru.netology.hwKotlin.exceptions.TargetNotFoundException

object CommentService: CrudService<Comment>  {
    private var lastCommentID = 0L
    private val comments = mutableListOf<Comment>()

    override fun add(comment: Comment): Long {
        lateinit var identifiedComment: Comment
        var isCommentTargetIdInNotes = false
        for (eachNote in NoteService.read())
            if (eachNote.id == comment.targetId) {
                lastCommentID += 1L
                identifiedComment = comment.copy(id = lastCommentID)
                comments += identifiedComment
                isCommentTargetIdInNotes = true
            }
        if (!isCommentTargetIdInNotes) throw TargetNotFoundException("There is no Note for this Comment")
        return identifiedComment.id
    }

    override fun delete(id: Long) {
        var isThereCommentIdInComments = false
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == id) {
                comments[index] = eachComment.copy(isCommentDeleted = true)
                isThereCommentIdInComments = true
            }
        if (!isThereCommentIdInComments) {
            throw TargetNotFoundException("There is no Comment that should be deleted.")
        }
    }

    override fun edit(comment: Comment) {
        var isThereCommentIdInComments = false
        var isCommentDeleted = true
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == comment.id) {
                isThereCommentIdInComments = true
                if (!eachComment.isCommentDeleted) {
                    isCommentDeleted = false
                    comments[index] = comment
                }
            }
        if (!isThereCommentIdInComments) {
            throw TargetNotFoundException("There is no Comment that should be edited.")
        } else if (isCommentDeleted) throw TargetDeletedException("Comment")
    }

    override fun read(): List<Comment> {
        return comments.toList()
    }

    fun getByTargetId (targetId: Long): List<Comment> {
        val commentsToReturn = mutableListOf<Comment>()
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.targetId == targetId || !eachComment.isCommentDeleted) {
                commentsToReturn.add(comments[index])
            }
        return commentsToReturn.toList()
    }

    override fun getById(id: Long): Comment {
        var commentToReturn: Comment? = null
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == id) {
                commentToReturn = comments[index]
            }
        return commentToReturn ?:
        throw TargetNotFoundException("There is no Comment to return.")
    }

    override fun restore(id: Long) {
        var isCommentToRestore = false
        for ((index, eachComment) in comments.withIndex())
            if (eachComment.id == id) {
                comments[index] = (comments[index]).copy(isCommentDeleted = false)
                isCommentToRestore = true
            }
        if (!isCommentToRestore)
            throw TargetNotFoundException("There is no Comment that should be restore.")
    }
}