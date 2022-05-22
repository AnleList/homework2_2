package ru.netology.hwKotlin.attachments

import ru.netology.hwKotlin.Comment

interface Note {
    val id: Long
    val ownerID: Int
    val title: String
    val text: String
    val date: Int
    val comments: Int
    val readComments: Int
    val viewUrl: String?
    var isNoteDeleted: Boolean
}