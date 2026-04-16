package com.example.bank_app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeContentScreen(
    onSendMoneyClick: () -> Unit,
    onReceiveClick: () -> Unit,
    onPayBillsClick: () -> Unit,
    onTopUpClick: () -> Unit,
    onCardClick: (Int) -> Unit = {},
    onSeeAllTransactionsClick: () -> Unit,
    onAnalyticsClick: () -> Unit,
    onTransactionClick: (Int) -> Unit = {},
    showSnackbar: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        WalletSection()
        Spacer(modifier = Modifier.height(16.dp))
        QuickActionsSection(
            onSendClick = onSendMoneyClick,
            onReceiveClick = onReceiveClick,
            onPayBillsClick = onPayBillsClick,
            onTopUpClick = onTopUpClick,
            showSnackbar = showSnackbar
        )
        Spacer(modifier = Modifier.height(20.dp))
        CardsSection(onCardClick = onCardClick)
        Spacer(modifier = Modifier.height(20.dp))
        SavingsGoalsSection()
        Spacer(modifier = Modifier.height(20.dp))
        FinanceSection(
            showSnackbar = showSnackbar,
            onAnalyticsClick = onAnalyticsClick
        )
        Spacer(modifier = Modifier.height(20.dp))
        TransactionsSection(
            onSeeAllClick = onSeeAllTransactionsClick,
            onTransactionClick = onTransactionClick,
            showSnackbar = showSnackbar
        )
        Spacer(modifier = Modifier.height(20.dp))
        CurrenciesSection()
        Spacer(modifier = Modifier.height(100.dp))
    }
}
