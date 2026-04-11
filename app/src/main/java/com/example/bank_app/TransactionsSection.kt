package com.example.bank_app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material.icons.rounded.LocalGroceryStore
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Payments
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.data.Transaction
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.GreenStart
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.ui.theme.PurpleStart
import com.example.bank_app.ui.theme.SuccessGreen
import com.example.bank_app.ui.theme.ErrorRed

val transactions = listOf(
    Transaction(
        title = "Salary Deposit",
        description = "Monthly Salary - HDFC Bank",
        amount = "+₹85,000.00",
        isIncome = true,
        icon = Icons.Rounded.Payments,
        iconBackground = GreenStart,
        date = "Today"
    ),
    Transaction(
        title = "Zomato",
        description = "Food Delivery",
        amount = "-₹456.99",
        isIncome = false,
        icon = Icons.Rounded.Fastfood,
        iconBackground = OrangeStart,
        date = "Yesterday"
    ),
    Transaction(
        title = "Jio Prepaid",
        description = "Mobile Recharge",
        amount = "-₹749.00",
        isIncome = false,
        icon = Icons.Rounded.Payments,
        iconBackground = BlueStart,
        date = "Yesterday"
    ),
    Transaction(
        title = "Swiggy",
        description = "Dinner",
        amount = "-₹812.40",
        isIncome = false,
        icon = Icons.Rounded.Fastfood,
        iconBackground = PurpleStart,
        date = "Jun 12"
    ),
    Transaction(
        title = "Netflix",
        description = "Monthly Subscription",
        amount = "-₹499.00",
        isIncome = false,
        icon = Icons.Rounded.Movie,
        iconBackground = OrangeStart,
        date = "Jun 10"
    )
)

@Composable
fun TransactionsSection(
    onSeeAllClick: () -> Unit = {},
    onTransactionClick: (Int) -> Unit = {},
    showSnackbar: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transactions",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            TextButton(onClick = onSeeAllClick) {
                Text(
                    text = "See All",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        transactions.forEachIndexed { index, transaction ->
            TransactionItem(
                transaction = transaction,
                onClick = { onTransactionClick(index) }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(transaction.iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = transaction.icon,
                contentDescription = transaction.title,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = transaction.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = transaction.description,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = transaction.amount,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isIncome) SuccessGreen else ErrorRed
            )
            Text(
                text = transaction.date,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}
