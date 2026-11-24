package com.example.s2sandroid.ui.home_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.s2sandroid.ui.home_dashboard.default
import com.example.s2sandroid.ui.home_dashboard.extraTiny
import com.example.s2sandroid.ui.home_dashboard.model.data.Transaction
import com.example.s2sandroid.ui.home_dashboard.tiniest
import com.example.s2sandroid.ui.home_dashboard.tiny
import com.example.s2sandroid.ui.home_dashboard.utils.ConvertCurrencyToNaira
import com.example.s2sandroid.ui.home_dashboard.utils.normalizeAmount
import kotlin.collections.forEach

@Composable
fun TransactionHistoryTile(
    transactions: List<Transaction>,
    onTransactionClicked:(transactionNumber: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = extraTiny,
                end = default,
                start = default,
                bottom = default,
            ),
        shape = shapes.large ,
        CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        elevation = CardDefaults.cardElevation(defaultElevation = tiniest)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFFFF))
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(
                    bottom = default
                ),
            verticalArrangement = Arrangement.spacedBy(tiny)
        ) {

            transactions.forEach { transaction ->

                val normalizedAmount = normalizeAmount(
                    transaction.amountMinor?.let { minor -> (minor / 100.0).toString() }
                        ?: transaction.amount
                )

                val currency = transaction.currency
                val exchangeRate = when (currency.uppercase()) {
                    "USD" -> 800.0
                    "EUR" -> 900.0
                    "ZAR" -> 105.0
                    else -> 1.0
                }
                TransactionHistoryView(
                    transactionDescription = transaction.description ?: "Transaction",
                    transactionStatus = transaction.status,
                    transactionDate = transaction.createdAt.toString(),
                    transactionAmount = ConvertCurrencyToNaira(normalizedAmount, exchangeRate),
                    onTransactionClicked = {onTransactionClicked(transaction.id)}
                )
            }
        }
    }
}