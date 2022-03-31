package ru.netology.hw2_2.attachments

import ru.netology.hw2_2.Attachment

data class GraffitiAttachment(
    override val type: String,
    override val id: Int,
    override val ownerID: Int,
    override val photo130: String,
    override val photo604: String
): Attachment, Graffiti
