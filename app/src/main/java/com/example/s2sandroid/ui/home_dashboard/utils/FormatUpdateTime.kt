package com.example.s2sandroid.ui.home_dashboard.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun FormatUpdateTime(timestamp: String?): String {
    if (timestamp.isNullOrBlank()) return "Unavailable"

    return try {
        val instant = Instant.parse(timestamp)
        val formatter = DateTimeFormatter
            .ofPattern("dd MMM yyyy, HH:mm")
            .withZone(ZoneId.systemDefault())

        formatter.format(instant)
    } catch (_: Exception) {
        "Unavailable"
    }
}