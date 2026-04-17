package com.example.bank_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bank_app.data.transactions

@Composable
fun TransactionsSection(
    onSeeAllClick: () -> Unit = {},
    onTransactionClick: (Int) -> Unit = {},
    showSnackbar: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SectionHeader(
            title = "Recent Transactions",
            actionText = "See All",
            onActionClick = onSeeAllClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        transactions.forEachIndexed { index, transaction ->
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                TransactionItem(
                    transaction = transaction,
                    onClick = { onTransactionClick(index) }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
