package ru.netology.hwKotlin

import ru.netology.hwKotlin.attachments.NoteAttachment

object NoteService: CredService<NoteAttachment> {

    private var lastNoteID = 0L
    private val notes = mutableListOf<NoteAttachment>()

    override fun add(note: NoteAttachment): Long {
        lastNoteID += 1
        val identifiedNote = note.copy(id = lastNoteID)
        notes += identifiedNote
        return identifiedNote.id
    }

    override fun delete(id: Long) {
        var isThereNoteIdInNotes = false
        for ((index, eachNote) in notes.withIndex())
            if (eachNote.id == id) {
                notes[index] = eachNote.copy(isNoteDeleted = true)
                isThereNoteIdInNotes = true
            }
        if (!isThereNoteIdInNotes) {
            throw TargetNotFoundException("there is no Note that should be deleted")
        }
    }

    override fun edit(note: NoteAttachment) {
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

    override fun read(): List<NoteAttachment> {
        return notes.toList()
    }

    override fun getById(id: Long): NoteAttachment {
        var noteToReturn: NoteAttachment? = null
        for ((index, eachNote) in notes.withIndex())
            if (eachNote.id == id) {
                noteToReturn = notes[index]
            }
        return noteToReturn ?: throw TargetNotFoundException("there is no Note to return")
    }

    override fun restore(id: Long) {
        for ((index, eachNote) in notes.withIndex())
            if (eachNote.id == id) {
                notes[index] = (notes[index]).copy(isNoteDeleted = false)
            } else
                throw TargetNotFoundException("there is no Note that should be restore")
    }
}