package ru.netology.hwKotlin.attachments

import ru.netology.hwKotlin.Attachment

data class GraffitiAttachment(
    override val type: String,
    override val id: Int,
    override val ownerID: Int,
    override val photo130: String,
    override val photo604: String
): Attachment, Graffiti
