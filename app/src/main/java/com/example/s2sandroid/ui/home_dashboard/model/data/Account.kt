package com.example.s2sandroid.ui.home_dashboard.model.data

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("shop_name")
    val shopName: String,
    val owner: String,
    val balance: String,
    val currency: String,
    @SerializedName("last_updated")
    val lastUpdated: String
)