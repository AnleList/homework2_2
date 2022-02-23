package ru.netology.hw2_2

import ru.netology.hw2_2.enumClasses.PostSource_data
import ru.netology.hw2_2.enumClasses.PostSource_type
import ru.netology.hw2_2.enumClasses.PostSource_platform

data class PostSource(
    val type: PostSource_type, //vk, widget, api ,rss ,sms
    val platform: PostSource_platform, //android, iphone, wphone
    val data: PostSource_data, //profile_activity, profile_photo, comments, like, poll
    val url: String,
)
