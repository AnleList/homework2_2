package ru.netology.hwKotlin

data class Comment(
    val id: Int,
    val fromID: Int,
    val targetId: Int,
    val date: Int,
    val text: String,
    val donut: Donut,
    val replyToUser: Int,
    val replyToComment: Int,
    val attachments: Any?,
    val parentsStack: Array<Comment>?,
    val thread: Any?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Comment

        if (parentsStack != null) {
            if (other.parentsStack == null) return false
            if (!parentsStack.contentEquals(other.parentsStack)) return false
        } else if (other.parentsStack != null) return false

        return true
    }

    override fun hashCode(): Int {
        return parentsStack?.contentHashCode() ?: 0
    }
}
