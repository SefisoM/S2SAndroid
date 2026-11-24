package com.example.s2sandroid.ui.home_dashboard.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.s2sandroid.R
import com.example.s2sandroid.ui.home_dashboard.default
import com.example.s2sandroid.ui.home_dashboard.extraTiny
import com.example.s2sandroid.ui.home_dashboard.secondary
import com.example.s2sandroid.ui.home_dashboard.small
import com.example.s2sandroid.ui.home_dashboard.tiny
import com.example.s2sandroid.ui.home_dashboard.utils.ConvertCurrencyToNaira
import com.example.s2sandroid.ui.home_dashboard.white

@Composable
fun UserBalanceView(
    userBalance: String?,
    lastUpdated: String?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            }
            .padding(
                top = extraTiny,
                end = default,
                start = default
            ),
        shape = shapes.large,
        CardDefaults.cardColors(containerColor = Color(0xFF005199)),
        elevation = CardDefaults.cardElevation(defaultElevation = tiny)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = default
                ),
            verticalArrangement = Arrangement.spacedBy(tiny)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = default),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = default,
                            end = default
                        )
                ) {

                    Text(
                        text = "Available balance",
                        modifier = Modifier.padding(
                            top = tiny,
                            bottom = default
                        ),
                        style = typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                        color = white
                    )

                    Text(
                        text = ConvertCurrencyToNaira(userBalance, 105.0),
                        modifier = Modifier.padding(
                            end = small,
                            bottom = tiny
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.headlineLarge.copy(fontWeight = FontWeight.SemiBold),
                        color = white
                    )

                    Text(
                        text = "Last update: $lastUpdated",
                        modifier = Modifier.padding(
                            end = small
                        ),
                        style = typography.bodySmall,
                        color = secondary
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.ic_s2s_wallet),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterVertically)
                        .padding(end = small),
                    tint = white,
                )
            }
        }
    }
}