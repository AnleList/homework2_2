package ru.netology.hwKotlin.attachments

import ru.netology.hwKotlin.Comment

interface Note {
    val id: Int
    val ownerID: Int
    val title: String
    val text: String
    val date: Int
    val comments: Int
    var commentsList: List<Comment>
    val readComments: Int
    val viewUrl: String
    var isNoteDeleted: Boolean
}