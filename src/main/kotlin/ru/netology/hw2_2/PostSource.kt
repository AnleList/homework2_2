package ru.netology.hw2_2

data class PostSource(
    val type: String, //vk, widget, api ,rss ,sms
    val platform: String, //android, iphone, wphone
    val data: String, //profile_activity, profile_photo, comments, like, poll
    val url: String,
)
