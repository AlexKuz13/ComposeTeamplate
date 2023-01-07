package com.alexkuz.corecommon.date

import com.alexkuz.corecommon.DEFAULT_LOCALE
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date

fun LocalDate?.toCalendar(): Calendar {
    val instant = this?.atStartOfDay(ZoneId.systemDefault())?.toInstant()
    return Calendar.getInstance().apply { time = Date.from(instant) }
}

fun LocalDate?.toString(format: DateTimeFormat): String? = runCatching {
    val formatter = DateTimeFormatter.ofPattern(
        format.code,
        DEFAULT_LOCALE
    )
    this?.format(formatter)
}.getOrNull()

fun LocalDateTime?.toString(format: DateTimeFormat): String? = runCatching {
    val formatter = DateTimeFormatter.ofPattern(
        format.code,
        DEFAULT_LOCALE
    )
    this?.format(formatter)
}.getOrNull()

fun String?.toDate(format: DateTimeFormat): LocalDate? = runCatching {
    val formatter = DateTimeFormatter.ofPattern(
        format.code,
        DEFAULT_LOCALE
    )
    LocalDate.parse(this, formatter)
}.getOrNull()

fun Long.toCalendar(): Calendar {
    return Calendar.getInstance().apply {
        timeInMillis = this@toCalendar
    }
}

fun LocalDate?.calculateDeadline(): Int {
    return ChronoUnit.DAYS.between(LocalDate.now(ZoneId.systemDefault()), this).toInt()
}