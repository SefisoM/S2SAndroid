package com.example.s2sandroid.ui.home_dashboard.utils

fun normalizeAmount(rawAmount: String?): String {
    val value = rawAmount
        ?.trim()
        ?.replace("\u00A0", "")
        ?.replace(" ", "")
        ?.replace("R", "")
        ?.replace("₦", "")
        ?: return "0"

    val normalized = if (value.contains(".") && value.contains(",")) {
        // Dot is decimal separator; comma is the thousands separator
        value.replace(",", "")
    } else if (value.count { it == ',' } == 1 && !value.contains(".")) {
        // A single comma is decimal separator
        value.replace(",", ".")
    } else {
        value.replace(",", "")
    }

    return normalized
}