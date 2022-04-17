package ru.netology.hwKotlin.attachments

interface Note {
    val id: Int
    val ownerID: Int
    val title: String
    val text: String
    val date: Int
    val comments: Int
    val readComments: Int
    val viewUrl: String
}