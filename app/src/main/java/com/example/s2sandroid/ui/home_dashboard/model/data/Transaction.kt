package com.example.s2sandroid.ui.home_dashboard.model.data

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class Transaction(
    val id: String,
    @SerializedName("created_at")
    val createdAt: JsonElement,
    val type: String,
    val amount: String? = null,
    @SerializedName("amount_minor")
    val amountMinor: Long? = null,
    val currency: String,
    val status: String,
    val description: String? = null
)