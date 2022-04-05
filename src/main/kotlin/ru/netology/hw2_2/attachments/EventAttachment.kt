package ru.netology.hw2_2.attachments

import ru.netology.hw2_2.Attachment

data class EventAttachment(
    override val type: String,
    override val id: Int,
    override val time: Int,
    override val memberStatus: Int,
    override val isFavorite: Boolean,
    override val address: String,
    override val text: String,
    override val buttonText: String,
    override val friends: Array<Int>?
): Attachment, Event
