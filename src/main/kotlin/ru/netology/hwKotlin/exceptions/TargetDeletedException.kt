package ru.netology.hwKotlin.exceptions

class TargetDeletedException (target: String): RuntimeException("$target deleted.")