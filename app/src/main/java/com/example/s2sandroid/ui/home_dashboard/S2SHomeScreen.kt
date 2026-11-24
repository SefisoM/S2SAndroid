package com.example.s2sandroid.ui.home_dashboard

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.ScreenScaffold
import com.example.s2sandroid.ui.theme.S2SAndroidTheme
import com.example.s2sandroid.R
import com.example.s2sandroid.ui.home_dashboard.components.QuickActionCard
import com.example.s2sandroid.ui.home_dashboard.components.TransactionHistoryTile
import com.example.s2sandroid.ui.home_dashboard.components.UserBalanceView
import com.example.s2sandroid.ui.home_dashboard.model.data.Account
import com.example.s2sandroid.ui.home_dashboard.model.data.Transaction
import com.example.s2sandroid.ui.home_dashboard.utils.FormatUpdateTime
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//Margin
//Will be moved to dimens.kt in the future
val bigRommy = 28.dp
val default = 16.dp
val tiny: Dp = 8.dp
val roomy: Dp = 20.dp
val bigger: Dp = 32.dp
val tiniest: Dp = 2.dp
val none: Dp = 0.dp
val extraTiny: Dp = 4.dp
val small: Dp = 12.dp

//Colors
//Will be moved to colors.kt in the future
val primary = Color(0xFF000000)
val secondary = Color(0xFF808080)
val white = Color(0xFFFFFFFF)

@Composable
fun S2SHomeScreen() {

    val context = LocalContext.current
    var transactions by remember { mutableStateOf<List<Transaction>>(emptyList()) }
    var account by remember { mutableStateOf<Account?>(null) }

    LaunchedEffect(Unit) {
        transactions = runCatching {
            context.resources
                .openRawResource(R.raw.transactions)
                .bufferedReader()
                .use { reader ->
                    val type = object : TypeToken<List<Transaction>>() {}.type
                    Gson().fromJson<List<Transaction>>(reader, type).orEmpty()
                }
        }.getOrElse {
            emptyList()
        }

        account = runCatching {
            context.resources
                .openRawResource(R.raw.account)
                .bufferedReader()
                .use { reader ->
                    val type = object : TypeToken<Account>() {}.type
                    Gson().fromJson<Account>(reader, type)
                }
        }.getOrElse {
            null
        }
    }

    ScreenScaffold(
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6F9))
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(bottom = bigRommy + roomy)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(
                                top = default,
                                start =default,
                                end = default,
                                bottom = bigger
                            ),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = account?.shopName ?: "Shop Name",
                            style = typography.titleLarge,
                            color = primary
                        )

                        Box(
                            modifier = Modifier.size(40.dp)
                                .clickable{
                                    Toast.makeText(context, "Open notification screen", Toast.LENGTH_SHORT).show()
                                }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_s2s_notification),
                                contentDescription = "Notifications",
                                modifier = Modifier
                                    .align(Alignment.Center),
                                tint = Color(0xFF005199)
                            )

                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .align(Alignment.TopEnd)
                                    .offset(x = (-6).dp, y = 6.dp)
                                    .background(Color.Red, shape = CircleShape)
                            )
                        }
                    }

                    UserBalanceView(account?.balance ?: "0", FormatUpdateTime(account?.lastUpdated))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        QuickActionCard(stringResource(R.string.pay_bill), R.drawable.ic_s2s_transfer, onQuickActionClicked = { Toast.makeText(context, "Move to Pay bill screen", Toast.LENGTH_SHORT).show()  })
                        QuickActionCard(stringResource(R.string.top_up), R.drawable.ic_s2s_pay_bills, onQuickActionClicked = {Toast.makeText(context, "Move to TopUp screen", Toast.LENGTH_SHORT).show()} )
                        QuickActionCard(stringResource(R.string.more), R.drawable.ic_s2s_more, onQuickActionClicked = {Toast.makeText(context, "Move to More screen", Toast.LENGTH_SHORT).show()})
                    }

                    Text(
                        text = stringResource(R.string.payment_history),
                        modifier = Modifier.padding(
                            top = default,
                            start = default + tiniest
                        ),
                        style = typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                        color = primary
                    )

                    TransactionHistoryTile(
                        transactions = transactions,
                        onTransactionClicked = { transaction ->
                            Toast.makeText(context, "Navigate to $transaction transaction details screen", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun S2SHomeScreenPreview() {
    S2SAndroidTheme {
        S2SHomeScreen()
    }
}