package com.example.bank_app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bank_app.data.cards

@Composable
fun CardsSection(onCardClick: (Int) -> Unit = {}) {
    Column {
        SectionHeader(
            title = "My Cards",
            actionText = "${cards.size} cards",
            onActionClick = null
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(cards.size) { index ->
                CardItem(
                    card = cards[index],
                    index = index,
                    totalCards = cards.size,
                    onClick = { onCardClick(index) }
                )
            }
        }
    }
}
