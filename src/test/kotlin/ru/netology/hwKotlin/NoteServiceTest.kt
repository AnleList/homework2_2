package ru.netology.hwKotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import ru.netology.hwKotlin.attachments.NoteAttachment
import ru.netology.hwKotlin.exceptions.TargetDeletedException
import ru.netology.hwKotlin.exceptions.TargetNotFoundException
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

        assert(NoteService.getById(addResult).isNoteDeleted)
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
    fun editNoteShouldThrowTargetNotFoundException() {
        val testNote = testNote
        NoteService.add(testNote)

        NoteService.edit(testNote.copy(id = 0L))
    }
    @Test(expected = TargetDeletedException::class)
    fun editNoteShouldThrowTargetDeletedException() {
        val testNote = testNote
        val addResult = NoteService.add(testNote)
        NoteService.delete(addResult)

        NoteService.edit(testNote.copy(id = addResult))
    }

    @Test
    fun readNote(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        val readResult = NoteService.read()

        assertEquals(testNote.copy(id = addResult), readResult.last{it.id == addResult})
    }

    @Test
    fun getNoteById(){
        val testNote = testNote
        val addResult = NoteService.add(testNote)

        val getNoteByIdResult = NoteService.getById(addResult)

        assertEquals(addResult, getNoteByIdResult.id)
    }
    @Test(expected = TargetNotFoundException::class)
    fun getNoteByIdShouldThrowTargetNotFoundException() {
        val testNote = testNote
        NoteService.add(testNote)

        NoteService.getById(0L)
    }

    @Test
    fun restoreNote(){
        val addResult = NoteService.add(testNote)
        NoteService.delete(addResult)

        NoteService.restore(addResult)

        assert(!NoteService.getById(addResult).isNoteDeleted)
    }
    @Test(expected = TargetNotFoundException::class)
    fun restoreNoteShouldThrow() {
        val addResult = NoteService.add(testNote)
        NoteService.delete(addResult)

        NoteService.restore(0L)
    }


    @Test
    fun addComment(){
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)

        val addCommentTestResult = CommentService.add(testComment)

        assertNotEquals(0L, addCommentTestResult)
        assert(CommentService.read().contains(testComment.copy(id = addCommentTestResult)))
    }
    @Test(expected = TargetNotFoundException::class)
    fun addCommentShouldThrow() {
        NoteService.add(testNote)
        val testComment = testComment.copy(targetId = 0)

        CommentService.add(testComment)
    }

    @Test
    fun deleteComment(){
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        val addCommentResult = CommentService.add(testComment)

        CommentService.delete(addCommentResult)

        assert(CommentService.getById(addCommentResult).isCommentDeleted)
    }
    @Test(expected = TargetNotFoundException::class)
    fun deleteCommentShouldThrow() {
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        CommentService.add(testComment)

        CommentService.delete(0L)
    }

    @Test
    fun editComment(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult)
        val addCommentResult = CommentService.add(testComment)
        val commentToEdit = CommentService.getById(addCommentResult).copy(
            text = "some edit text of Comment"
        )

        CommentService.edit(commentToEdit)
    }
    @Test(expected = TargetNotFoundException::class)
    fun editCommentShouldThrowTargetNotFoundException() {
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult)
        CommentService.add(testComment)

        CommentService.edit(testComment.copy(id = 0L))
    }
    @Test(expected = TargetDeletedException::class)
    fun editCommentShouldThrowTargetDeletedException() {
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        val addCommentResult = CommentService.add(testComment)
        CommentService.delete(addCommentResult)

        CommentService.edit(testComment.copy(id = addCommentResult))
    }

    @Test
    fun readComments(){
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        val addCommentResult = CommentService.add(testComment)

        val readCommentsResult = CommentService.read()

        assertEquals(testComment.copy(id = addCommentResult),
            readCommentsResult.last{it.id == addCommentResult})
    }

    @Test
    fun getCommentsByTargetId(){
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        val addCommentResult = CommentService.add(testComment)

        val getByTargetIdResult = CommentService.getByTargetId(addNoteResult)

        assertEquals(testComment.copy(id = addCommentResult),
            getByTargetIdResult.first{it.id == addCommentResult})
        assertEquals(1, getByTargetIdResult.size)
    }

    @Test
    fun getCommentById(){
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        val addCommentResult = CommentService.add(testComment)

        val getCommentByIdResult = CommentService.getById(addCommentResult)

        assertEquals(addCommentResult, getCommentByIdResult.id)
        assertEquals(getCommentByIdResult, testComment.copy(id = addCommentResult))
    }
    @Test(expected = TargetNotFoundException::class)
    fun getCommentByIdShouldThrowTargetNotFoundException() {
        val addNoteResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addNoteResult)
        CommentService.add(testComment)

        CommentService.getById(0L)
    }

    @Test
    fun restoreComment(){
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult)
        val addCommentResult = CommentService.add(testComment)
        CommentService.delete(addCommentResult)

        CommentService.restore(addCommentResult)

        assert(!CommentService.getById(addCommentResult).isCommentDeleted)
    }
    @Test(expected = TargetNotFoundException::class)
    fun restoreCommentShouldThrow() {
        val addResult = NoteService.add(testNote)
        val testComment = testComment.copy(targetId = addResult)
        val addCommentResult = CommentService.add(testComment)
        CommentService.delete(addCommentResult)

        CommentService.restore(0)
    }
}