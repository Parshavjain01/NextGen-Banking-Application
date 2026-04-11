package com.example.bank_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Contactless
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardDetailsScreen(
    cardIndex: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Fallback safely to 0 if out of bounds
    val safeIndex = if (cardIndex in cards.indices) cardIndex else 0
    val card = cards[safeIndex]

    val image = if (card.cardType == "MASTER CARD") {
        painterResource(id = R.drawable.ic_mastercard)
    } else {
        painterResource(id = R.drawable.ic_visa)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
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
                text = "Card Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Large Card Display
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(25.dp),
                    spotColor = Color.Black.copy(alpha = 0.4f)
                )
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .fillMaxWidth()
                .height(220.dp)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image,
                    contentDescription = card.cardName,
                    modifier = Modifier.width(60.dp)
                )

                Icon(
                    imageVector = Icons.Rounded.Contactless,
                    contentDescription = "Contactless",
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(32.dp)
                )
            }

            Text(
                text = card.cardName,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "₹${String.format("%,.2f", card.balance)}",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = card.cardNumber, // Un-obscured as requested
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Detailed Metadata List
        CardDetailRow("Account Holder", "Parshav Jain")
        CardDetailRow("Card Type", card.cardType)
        CardDetailRow("Status", "Active", Color(0xFF4CAF50))
        CardDetailRow("IFSC Code", "HDFC0001234")
        CardDetailRow("PAN Linked", "ABCDE1234F", Color(0xFF4CAF50))
        CardDetailRow("Credit Limit", "₹${String.format("%,.2f", card.balance + 10000.0)}")
    }
}

@Composable
fun CardDetailRow(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onBackground) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor
        )
    }
}
