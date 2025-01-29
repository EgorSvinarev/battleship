package ru.csit.battleship.domain.date

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.format() = DATE_FORMATTER.format(this)

const val DATE_FORMAT = "yyyy-MM-dd"
val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault())