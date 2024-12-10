package com.example.spacexlauches.extensions

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

val String.asDate: Date get() {
    val formatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
    val offsetDateTime: OffsetDateTime = OffsetDateTime.parse(this, formatter)
    val instant = offsetDateTime.toInstant()
    return Date.from(instant)
}