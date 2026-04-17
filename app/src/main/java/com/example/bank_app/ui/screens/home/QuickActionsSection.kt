package com.example.bank_app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.data.QuickAction
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.GreenStart
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.ui.theme.PurpleStart
import com.example.bank_app.data.*
import com.example.bank_app.ui.theme.*
import com.example.bank_app.ui.components.*


val quickActions = listOf(
    QuickAction(
        title = "Send",
        icon = Icons.Rounded.Send,
        backgroundColor = BlueStart
    ),
    QuickAction(
        title = "Scan & Pay",
        icon = Icons.Rounded.QrCodeScanner,
        backgroundColor = GreenStart
    ),
    QuickAction(
        title = "Pay Bills",
        icon = Icons.Rounded.CreditCard,
        backgroundColor = OrangeStart
    ),
    QuickAction(
        title = "Top Up",
        icon = Icons.Rounded.Add,
        backgroundColor = PurpleStart
    )
)

@Composable
fun QuickActionsSection(
    onSendClick: () -> Unit = {},
    onReceiveClick: () -> Unit = {},
    onPayBillsClick: () -> Unit = {},
    onTopUpClick: () -> Unit = {},
    showSnackbar: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "Quick Actions",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            quickActions.forEach { action ->
                QuickActionItem(
                    action = action,
                    onClick = {
                        when (action.title) {
                            "Send" -> onSendClick()
                            "Scan & Pay" -> onReceiveClick()
                            "Pay Bills" -> onPayBillsClick()
                            "Top Up" -> onTopUpClick()
                            else -> showSnackbar("${action.title} coming soon!")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun QuickActionItem(action: QuickAction, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(action.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = action.icon,
                contentDescription = action.title,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = action.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
