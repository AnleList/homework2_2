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
        id = 0,
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
        id = 0,
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
    fun add(){
        val testNote = testNote

        val addTestResult = NoteService.add(testNote)

        assertNotEquals(0, addTestResult.id)
    }

    @Test
    fun createComment(){
        NoteService.add(testNote)
        val testComment = testComment

        val createCommentResult = NoteService.createComment(testComment)

        assertNotEquals(0, createCommentResult.id)
    }
    @Test(expected = TargetNotFoundException::class)
    fun createCommentShouldThrow() {
        NoteService.add(testNote)
        val testComment = testComment

        NoteService.createComment(testComment.copy(targetId = 0))
    }

    @Test
    fun delete(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.delete(addResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun deleteShouldThrow() {
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.delete(addResult.copy(id = 0))
    }

    @Test
    fun deleteComment(){
        NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.deleteComment(createCommentResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun deleteCommentShouldThrow() {
        NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.deleteComment(createCommentResult.copy(id = 0))
    }

    @Test
    fun edit(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.edit(addResult.copy(text = "some edit text of note"))
    }
    @Test(expected = TargetNotFoundException::class)
    fun editShouldThrow() {
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        NoteService.edit(addResult.copy(id = 0))
    }

    @Test
    fun editComment(){
        NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.editComment(createCommentResult.copy(text = "some edit text of Comment"))
    }
    @Test(expected = TargetNotFoundException::class)
    fun editCommentShouldThrow() {
        NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)

        NoteService.editComment(createCommentResult.copy(id = 0))
    }

    @Test
    fun get(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        val getResult = NoteService.get()

        assertEquals(addResult, getResult.last{it.id == addResult.id})
    }

    @Test
    fun getById(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        val getByIdResult = NoteService.getById(addResult.id)

        assertEquals(addResult, getByIdResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun getByIdShouldThrow() {
        val testNote = testNote
        NoteService.add(testNote)

        NoteService.getById(0)
    }

    @Test
    fun getComments(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)

        val getCommentsResult = NoteService.getComments(addResult)

        assertEquals(createCommentResult,
            getCommentsResult.last{it.id == createCommentResult.id})
    }

    @Test
    fun restoreComment(){
        NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)
        NoteService.deleteComment(createCommentResult)

        val restoreCommentResult = NoteService.restoreComment(createCommentResult.id)

        assertEquals(createCommentResult, restoreCommentResult)
    }
    @Test(expected = TargetNotFoundException::class)
    fun restoreCommentShouldThrow() {
        NoteService.add(testNote)
        val testComment = testComment
        val createCommentResult = NoteService.createComment(testComment)
        NoteService.deleteComment(createCommentResult)

        NoteService.restoreComment(0)
    }
}