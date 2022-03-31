package ru.netology.hw2_2.attachments

interface Link {
    val url: String
    val title: String
    val caption: String
    val description: String
    val photo: Any?
    val product: Any?
    val button: Any?
    val previewPage: String
    val previewUrl: String
}