package ru.netology.hw2_2.attachments

import ru.netology.hw2_2.Attachment

data class AudioAttachment(override val type: String,
                      override val id: Int,
                      override val ownerID: Int,
                      override val artist: String,
                      override val title: String,
                      override val duration: Int,
                      override val url: String,
                      override val lyricsID: Int?,
                      override val albumID: Int?,
                      override val genreID: Int,
                      override val date: Int,
                      override val noSearch: Boolean?,
                      override val isHQ: Boolean
) : Attachment, Audio {
}