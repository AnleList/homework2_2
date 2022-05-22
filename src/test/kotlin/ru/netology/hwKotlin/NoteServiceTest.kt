package ru.netology.hwKotlin

import org.junit.Assert.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import ru.netology.hwKotlin.attachments.NoteAttachment
import java.util.*

class NoteServiceTest {
    private val testNote = NoteAttachment(
        type = "note",
        id = 0L,
        ownerID = 1,
        title = "some title of note",
        text = "some text of note",
        date = (Date().time / 1000).toInt(),
        comments = 0,
        readComments = 0,
        viewUrl = null,
        isNoteDeleted = false
    )
    private val testPlaceholder = 0
    private val testDonut = Donut(
        false,
        0,
        testPlaceholder,
        false,
        Donut.Mode.ALL
    )
    private val testComment = Comment(
        id = 0L,
        fromID = 1,
        targetId = 1,
        date = (Date().time / 1000).toInt(),
        text = "some text of Comment of note 1",
        donut = testDonut,
        replyToUser = 1,
        replyToComment = 1,
        attachments = null,
        parentsStack = null,
        thread = null,
        isCommentDeleted = false
    )

    @Test
    fun addNote(){
        val testNote = testNote

        val addTestResult = NoteService.add(testNote)

        assertNotEquals(0L, addTestResult)
    }

    @Test
    fun deleteNote(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.delete(addResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun deleteNoteShouldThrow() {
        val testNote = testNote
        NoteService.add(testNote)

        NoteService.delete(0L)
    }

    @Test
    fun editNote(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.edit(NoteService.getById(addResult).copy(text = "some edit text of note"))
    }
    @Test(expected = TargetNotFoundException::class)
    fun editNoteShouldThrow() {
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.edit(NoteService.getById(addResult).copy(id = 0L))
    }

    @Test
    fun readNote(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)
        var typeIsRight = false

        val readResult = NoteService.read()
        if (readResult is List) typeIsRight = true

        assertEquals(addResult, readResult.last{it.id == addResult})
        assertEquals(true, typeIsRight)
    }

    @Test
    fun getNoteById(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        val getByIdResult = NoteService.getById(addResult)

        assertEquals(addResult, getByIdResult.id)
    }
    @Test(expected = TargetNotFoundException::class)
    fun getNoteByIdShouldThrow() {
        val testNote = testNote
        NoteService.add(testNote)

        NoteService.getById(0L)
    }

    @Test
    fun restoreNote(){
        val addResult = NoteService.add(testNote)
        NoteService.delete(addResult)

        NoteService.restore(addResult)

        assertEquals(false, (NoteService.getById(addResult)).isNoteDeleted)
    }
    @Test(expected = TargetNotFoundException::class)
    fun restoreNoteShouldThrow() {
        val addResult = NoteService.add(testNote)
        NoteService.delete(addResult)

        NoteService.restore(0L)
    }





    @Test
    fun createComment(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)

        val createCommentResult = NoteService.createComment(testComment)

        assertNotEquals(0, createCommentResult.id)
    }
    @Test(expected = TargetNotFoundException::class)
    fun createCommentShouldThrow() {
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)

        NoteService.createComment(testComment.copy(targetId = 0))
    }

    @Test
    fun deleteComment(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.deleteComment(createCommentResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun deleteCommentShouldThrow() {
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.deleteComment(createCommentResult.copy(id = 0))
    }

    @Test
    fun editComment(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.editComment(createCommentResult.copy(text = "some edit text of Comment"))
    }
    @Test(expected = TargetNotFoundException::class)
    fun editCommentShouldThrow() {
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.editComment(createCommentResult.copy(id = 0))
    }

    @Test
    fun getComments(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)

        val getCommentsResult = NoteService.getComments(addResult)

        assertEquals(createCommentResult,
            getCommentsResult.last{it.id == createCommentResult.id})
        assertEquals(1, getCommentsResult.size)
    }

    @Test
    fun restoreComment(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)
        NoteService.deleteComment(createCommentResult)

        val restoreCommentResult = NoteService.restoreComment(createCommentResult.id)

        assertEquals(createCommentResult, restoreCommentResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun restoreCommentShouldThrow() {
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult.id)
        val createCommentResult = NoteService.createComment(testComment)
        NoteService.deleteComment(createCommentResult)

        NoteService.restoreComment(0)
    }
}