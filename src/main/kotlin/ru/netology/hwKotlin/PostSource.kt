package ru.netology.hwKotlin

data class PostSource(
    val type: Type,
    val platform: Platform,
    val data: Data,
    val url: String,
) {
    enum class Type {
        VK, WIDGET, API ,RSS, SMS
    }
    enum class Platform {
        ANDROID, IPHONE, WPHONE
    }
    enum class Data {
        PROFILEACTIVITY, PROFILEPHOTO, COMMENTS, LIKE, POLL
    }

}
