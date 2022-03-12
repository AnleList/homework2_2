package ru.netology.hw2_2

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Any,
    val canPublishFreeCopy: Boolean,
    val editMode: Donut_editMode,
) {
    enum class Donut_editMode {
        ALL, DURATION
    }
}