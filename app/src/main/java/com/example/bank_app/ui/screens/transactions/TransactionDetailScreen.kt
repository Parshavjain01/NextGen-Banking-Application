package com.example.bank_app.ui.screens.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.data.transactions
import com.example.bank_app.ui.theme.SuccessGreen
import com.example.bank_app.ui.theme.ErrorRed

@Composable
fun TransactionDetailScreen(
    transactionId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transaction = transactions.getOrNull(transactionId) ?: transactions[0]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Rounded.ArrowBack, "Back")
            }
            Text(
                text = "Transaction Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Share */ }) {
                Icon(Icons.Rounded.Share, "Share")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(transaction.iconBackground.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = transaction.icon,
                    contentDescription = null,
                    tint = transaction.iconBackground,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = transaction.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = transaction.description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = transaction.amount,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (transaction.isIncome) SuccessGreen else ErrorRed
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Transaction Details List
        DetailItem("Status", "Completed", SuccessGreen)
        DetailItem("Date", transaction.date)
        DetailItem("Transaction ID", "TXN${1000000 + transactionId}")
        DetailItem("Payment Method", "Visa Business (....3976)")
    }
}

@Composable
fun DetailItem(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurface) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        Text(text = value, fontWeight = FontWeight.SemiBold, color = valueColor)
    }
    Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
}
