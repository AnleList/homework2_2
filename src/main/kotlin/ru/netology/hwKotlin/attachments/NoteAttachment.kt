package ru.netology.hwKotlin.attachments

import ru.netology.hwKotlin.Attachment

data class NoteAttachment(
    override val type: String,
    override val id: Int,
    override val ownerID: Int,
    override val title: String,
    override val text: String,
    override val date: Int,
    override val comments: Int,
    override val readComments: Int,
    override val viewUrl: String,
    override var isNoteDeleted: Boolean = false,
) : Attachment, Note
