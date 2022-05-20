package ru.netology.hwKotlin.attachments

import ru.netology.hwKotlin.Attachment
import ru.netology.hwKotlin.Comment

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
    override var commentsList: List<Comment>,
) : Attachment, Note
