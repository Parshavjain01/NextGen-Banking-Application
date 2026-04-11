
package com.example.bank_app

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Analytics
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Savings
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.bank_app.data.Finance
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.GreenStart
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.ui.theme.PurpleStart
import com.example.bank_app.ui.theme.TealStart

val financeList = listOf(
    Finance(
        icon = Icons.Rounded.Savings,
        name = "My\nSavings",
        background = OrangeStart
    ),

    Finance(
        icon = Icons.Rounded.Wallet,
        name = "My\nWallet",
        background = BlueStart
    ),

    Finance(
        icon = Icons.Rounded.Analytics,
        name = "Finance\nAnalytics",
        background = PurpleStart
    ),

    Finance(
        icon = Icons.Rounded.MonetizationOn,
        name = "My\nTransactions",
        background = GreenStart
    ),
    Finance(
        icon = Icons.Rounded.TrendingUp,
        name = "Invest\nments",
        background = TealStart
    ),
)

@Composable
fun FinanceSection(showSnackbar: (String) -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Finance",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "See All",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { showSnackbar("Finance details coming soon!") }
            )
        }

        LazyRow(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(financeList.size) {
                FinanceItem(it, onClick = { showSnackbar("Opening ${financeList[it].name.replace("\n", "")}...") })
            }
        }
    }
}

@Composable
fun FinanceItem(index: Int, onClick: () -> Unit = {}) {
    val finance = financeList[index]
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "scale"
    )

    val lastPaddingEnd = if (index == financeList.size - 1) 16.dp else 0.dp

    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = lastPaddingEnd)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20.dp),
                    spotColor = finance.background.copy(alpha = 0.3f)
                )
             //  .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                .size(110.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isPressed = true
                    // simulate short press effect
                    onClick()
                    // realistically we'd launch a coroutine to revert isPressed but we can just use the click
                }
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(finance.background)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = finance.icon,
                    contentDescription = finance.name,
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }

            Text(
                text = finance.name,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                lineHeight = 16.sp
            )

        }
    }
}
