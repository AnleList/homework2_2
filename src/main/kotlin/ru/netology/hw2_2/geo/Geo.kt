package ru.netology.hw2_2.geo

import ru.netology.hw2_2.geo.place.Place

data class Geo(
    val type: String,
    val coordinates: String,
    val place: Place?
)
