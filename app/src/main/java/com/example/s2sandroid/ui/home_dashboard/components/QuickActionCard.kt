package com.example.s2sandroid.ui.home_dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.s2sandroid.ui.home_dashboard.default
import com.example.s2sandroid.ui.home_dashboard.primary
import com.example.s2sandroid.ui.home_dashboard.tiny

@Composable
fun QuickActionCard(
    title: String,
    iconRes: Int,
    onQuickActionClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(tiny)
            .clickable {
                onQuickActionClicked()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(80.dp)
                .padding(top = default),
            shape = shapes.small,
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE6E9EF)),
            elevation = CardDefaults.cardElevation(defaultElevation = tiny)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp),
                    tint = Color(0xFF005199)
                )
            }
        }

        Text(
            text = title,
            modifier = Modifier.padding(top = tiny),
            style = typography.bodyLarge,
            color = primary,
            textAlign = TextAlign.Center
        )
    }
}
