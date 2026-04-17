package com.example.bank_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bank_app.data.financeList

@Composable
fun FinanceSection(
    showSnackbar: (String) -> Unit = {},
    onAnalyticsClick: () -> Unit = {}
) {
    Column {
        SectionHeader(
            title = "Finance",
            actionText = "See All",
            onActionClick = { showSnackbar("Finance details coming soon!") }
        )

        LazyRow(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(financeList.size) { index ->
                FinanceItem(
                    finance = financeList[index],
                    index = index,
                    totalItems = financeList.size,
                    onClick = {
                        if (financeList[index].name.contains("Analytics")) {
                            onAnalyticsClick()
                        } else {
                            showSnackbar("Opening ${financeList[index].name.replace("\n", "")}...")
                        }
                    }
                )
            }
        }
    }
}
