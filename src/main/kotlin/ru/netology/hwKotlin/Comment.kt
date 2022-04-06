package ru.netology.hwKotlin

data class Comment(
    val id: Int,
    val fromID: Int,
    val postId: Int,
    val date: Int,
    val text: String,
    val donut: Donut,
    val replyToUser: Int,
    val replyToComment: Int,
    val attachments: Any,
    val parentsStack: Array<Comment>?,
    val thread: Any
)
