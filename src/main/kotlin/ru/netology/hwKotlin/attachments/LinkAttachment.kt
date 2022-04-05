package ru.netology.hwKotlin.attachments

import ru.netology.hwKotlin.Attachment

data class LinkAttachment(
    override val type: String,
    override val url: String,
    override val title: String,
    override val caption: String,
    override val description: String,
    override val photo: Any?,
    override val product: Any?,
    override val button: Any?,
    override val previewPage: String,
    override val previewUrl: String
) : Attachment, Link
