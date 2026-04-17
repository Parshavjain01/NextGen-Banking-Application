package com.example.bank_app.ui.screens.wallet

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.R
import com.example.bank_app.data.cards

@Composable
fun CardDetailsScreen(
    cardIndex: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val card = cards.getOrNull(cardIndex) ?: cards[0]

    val image = if (card.cardType == "MASTER CARD") {
        painterResource(id = R.drawable.ic_mastercard)
    } else {
        painterResource(id = R.drawable.ic_visa)
    }

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
                text = "Card Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Share */ }) {
                Icon(Icons.Rounded.Settings, "Settings")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Large Card Display
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .shadow(12.dp, RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = image, contentDescription = null, modifier = Modifier.width(60.dp))
                    Icon(Icons.Rounded.Contactless, null, tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(32.dp))
                }
                Column {
                    Text(text = card.cardName, color = Color.White.copy(alpha = 0.9f), fontSize = 16.sp)
                    Text(text = "₹${String.format("%,.2f", card.balance)}", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = card.cardNumber, color = Color.White.copy(alpha = 0.9f), fontSize = 14.sp, letterSpacing = 2.sp)
                    Text(text = "EXP 12/28", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Card Settings
        Text(text = "Card Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        CardSettingItem(Icons.Rounded.Lock, "Lock Card", "Temporary disable this card")
        CardSettingItem(Icons.Rounded.Payments, "Change Pin", "Change your security pin")
        CardSettingItem(Icons.Rounded.Visibility, "Show Details", "Show full card number and CVV")
        CardSettingItem(Icons.Rounded.Error, "Report Lost", "Report card as stolen or lost", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun CardSettingItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, subtitle: String, color: Color = MaterialTheme.colorScheme.onSurface) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { /* action */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, null, tint = color)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontWeight = FontWeight.SemiBold, color = color)
            Text(text = subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
        }
        Icon(Icons.Rounded.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f))
    }
}

