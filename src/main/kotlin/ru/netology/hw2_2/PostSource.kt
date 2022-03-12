package ru.netology.hw2_2

data class PostSource(
    val type: PostSource_type,
    val platform: PostSource_platform,
    val data: PostSource_data,
    val url: String,
) {
    enum class PostSource_type {
        VK, WIDGET, API ,RSS, SMS
    }
    enum class PostSource_platform {
        ANDROID, IPHONE, WPHONE
    }
    enum class PostSource_data {
        PROFILE_ACTIVITY, PROFILE_PHOTO, COMMENTS, LIKE, POLL
    }

}
