package ru.netology.hwKotlin

import ru.netology.hwKotlin.attachments.NoteAttachment

object NoteService {

 private var lastNoteID = 0
 private var notes = mutableListOf<NoteAttachment>()
 private var lastCommentID = 0
 private var comments = emptyList<Comment>()

 fun add(note: NoteAttachment): NoteAttachment {
  lastNoteID += 1
  val identifiedNote = note.copy(id = lastNoteID)
  notes += identifiedNote
  return identifiedNote
 }

 fun createComment(comment: Comment) {
  var isThereNoteIdInNotes = false
  for (eachNote in notes)
   if (eachNote.id == comment.targetId) {
    lastCommentID += 1
    comments += comment.copy(id = lastCommentID)
    isThereNoteIdInNotes = true
   }
  if (!isThereNoteIdInNotes) {
   throw TargetNotFoundException("there is no note for the comment that should be added")
  }
 }

 fun delete (noteId: Int) {
  for (eachNote in notes)
   if (eachNote.id == noteId)
 //    eachNote = eachNote.copy(isNoteDeleted = true)
 }
}