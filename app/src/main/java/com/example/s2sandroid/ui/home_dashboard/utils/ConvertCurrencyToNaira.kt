package com.example.s2sandroid.ui.home_dashboard.utils

import java.util.Locale

fun  ConvertCurrencyToNaira (
    zarAmount: String?,
    exchangeRate: Double
): String {
    val normalizedAmount = zarAmount
        ?.trim()
        ?.replace("\u00A0", "")
        ?.replace(" ", "")
        ?.replace("₦", "")
        ?.replace("R", "")
        ?.replace(",", ".")

    val parsedAmount = normalizedAmount
        ?.toDoubleOrNull()
        ?: 0.0

    val nairaAmount = parsedAmount * exchangeRate
    return "₦${String.format(Locale.US, "%,.2f", nairaAmount)}"
}