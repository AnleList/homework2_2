package ru.netology.hw2_2

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Any,
    val canPublishFreeCopy: Boolean,
    val editMode: Mode,
) {
    enum class Mode {
        ALL, DURATION
    }
}