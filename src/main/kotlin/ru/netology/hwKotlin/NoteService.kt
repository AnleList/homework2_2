package ru.netology.hwKotlin

import ru.netology.hwKotlin.attachments.NoteAttachment

object NoteService {

    private var lastNoteID = 0
    private val notes = mutableListOf<NoteAttachment>()
    private var lastCommentID = 0
    private val comments = mutableListOf<Comment>()

    fun add(note: NoteAttachment): NoteAttachment {
        lastNoteID += 1
        val identifiedNote = note.copy(id = lastNoteID)
        notes += identifiedNote
        return identifiedNote
    }

    fun createComment(comment: Comment): Comment {
        var commentToReturn: Comment? = null
        for (eachNote in notes)
            if (eachNote.id == comment.targetId) {
                lastCommentID += 1
                comments += comment.copy(id = lastCommentID)
                commentToReturn = comment.copy(id = lastCommentID)
            }
        return commentToReturn ?:
        throw TargetNotFoundException("there is no Note to return or no comment with this targetId")
    }

    fun delete(note: NoteAttachment) {
        var isThereNoteIdInNotes = false
        for ((index, eachNote) in notes.withIndex())
            if (eachNote.id == note.id) {
                notes[index] = eachNote.copy(isNoteDeleted = true)
                isThereNoteIdInNotes = true
            }
        if (!isThereNoteIdInNotes) {
            throw TargetNotFoundException("there is no Note that should be deleted")
        }
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

    fun edit(note: NoteAttachment) {
        var isThereNoteIdInNotes = false
        for ((index, eachNote) in notes.withIndex())
            if (eachNote.id == note.id) {
                notes[index] = note
                isThereNoteIdInNotes = true
            }
        if (!isThereNoteIdInNotes) {
            throw TargetNotFoundException("there is no Note that should be edited")
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

    fun get (): MutableList<NoteAttachment> {
        return notes
    }

    fun getById (id: Int): NoteAttachment {
        var noteToReturn: NoteAttachment? = null
        for ((index, eachNote) in notes.withIndex())
            if (eachNote.id == id) {
                noteToReturn = notes[index]
            }
        return noteToReturn ?: throw TargetNotFoundException("there is no Note to return")
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

}