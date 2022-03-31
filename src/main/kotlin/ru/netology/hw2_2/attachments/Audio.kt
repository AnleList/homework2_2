package ru.netology.hw2_2.attachments

interface Audio {
    val id: Int
    val ownerID: Int
    val artist: String
    val title: String
    val duration: Int
    val url: String
    val lyricsID: Int?
    val albumID: Int?
    val genreID: Int
    val date: Int
    val noSearch: Boolean?
    val isHQ: Boolean
}