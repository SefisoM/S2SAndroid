package com.example.s2sandroid.ui.home_dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.s2sandroid.R
import com.example.s2sandroid.ui.home_dashboard.default
import com.example.s2sandroid.ui.home_dashboard.primary
import com.example.s2sandroid.ui.home_dashboard.secondary

@Composable
fun TransactionHistoryView(
    transactionDescription: String,
    transactionStatus: String,
    transactionDate: String,
    transactionAmount: String?,
    onTransactionClicked: () -> Unit
) {

    val normalizedAmount = transactionAmount
        ?.trim()
        ?.replace(",", "")
        ?.replace(" ", "")
        ?.replace("₦", "")
        ?.replace("R", "")
        ?: "0"

    val numericAmount = normalizedAmount.toDoubleOrNull() ?: 0.0
    val isDebitTransaction = normalizedAmount.startsWith("-") || numericAmount < 0

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = default)
            .clickable { onTransactionClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = default)
                .size(40.dp)
                .background(Color(0xFFF4F6F9), shape = shapes.small)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_s2s_transfer),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                tint = Color(0xFF005199)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = transactionDescription,
                modifier = Modifier
                    .padding(
                        start = default,
                    ),
                maxLines = 1,
                style = typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = primary
            )
            Text(
                text = "$transactionStatus •  $transactionDate",
                modifier = Modifier
                    .padding(
                        start = default,
                    ),
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = typography.labelSmall,
                color = secondary
            )
        }

        Text(
            text = "₦$normalizedAmount",
            modifier = Modifier.padding(
                start = default,
                end = default
            ),
            textAlign = TextAlign.End,
            style = typography.bodyLarge,
            color = if (isDebitTransaction) Color.Red else primary
        )
    }
}