package ru.netology.hw2_2

import ru.netology.hw2_2.enumClasses.Donut_editMode

data class Donut(
    val isDonut: Boolean,
    val paidDuration: Int,
    val placeholder: Any,
    val canPublishFreeCopy: Boolean,
    val editMode: Donut_editMode, //all, duration
)