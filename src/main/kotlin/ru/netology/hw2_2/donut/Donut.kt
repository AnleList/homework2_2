package ru.netology.hw2_2.donut

import ru.netology.hw2_2.donut.placeholder.Placeholder

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Placeholder,
    val canPublishFreeCopy: Boolean,
    val editMode: String, //all, duration
)