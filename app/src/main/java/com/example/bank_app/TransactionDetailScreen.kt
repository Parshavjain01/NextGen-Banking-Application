package com.example.bank_app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.data.Transaction

@Composable
fun TransactionDetailScreen(
    transactionId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Mocking finding the transaction. In a real app, this would come from a database or API.
    val transaction = transactions.getOrNull(transactionId) ?: transactions[0]

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBackClick() },
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Transaction Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Transaction Icon / Status
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(transaction.icon.let { 
                    // This is a simplification; in a real app would use a colored background based on icon/category
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                }),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = transaction.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = transaction.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Completed",
            fontSize = 14.sp,
            color = Color(0xFF4CAF50),
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "₹${String.format("%,.2f", transaction.amount)}",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Details Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp)
        ) {
            DetailRow("Transaction ID", "TRX-${100000 + transactionId}")
            DetailRow("Date", "April 11, 2026")
            DetailRow("Time", "14:25 PM")
            DetailRow("Category", "Finance")
            DetailRow("Payment Method", "Visa (3664)")
            
            Divider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.outlineVariant)
            
            DetailRow("Total Amount", "$${String.format("%,.2f", transaction.amount)}", isBold = true)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Download Statement */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Icon(imageVector = Icons.Rounded.Download, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Download Receipt", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun DetailRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
