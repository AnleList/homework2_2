package ru.netology.hw2_2

data class Likes(
    val count: Int,
    val userLikes: Boolean,
    val canLike: Boolean,
    val canPublish: Boolean,
)
