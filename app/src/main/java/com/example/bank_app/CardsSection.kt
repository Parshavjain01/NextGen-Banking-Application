package com.example.bank_app

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Contactless
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.data.Card
import com.example.bank_app.ui.theme.BlueEnd
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.GreenEnd
import com.example.bank_app.ui.theme.GreenStart
import com.example.bank_app.ui.theme.OrangeEnd
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.ui.theme.PurpleEnd
import com.example.bank_app.ui.theme.PurpleStart

val cards = listOf(

    Card(
        cardType = "VISA",
        cardNumber = "3664 7865 3786 3976",
        cardName = "Business",
        balance = 46467.00,
        color = getGradient(PurpleStart, PurpleEnd),
    ),

    Card(
        cardType = "MASTER CARD",
        cardNumber = "2347 5837 8992 2234",
        cardName = "Savings",
        balance = 6467.50,
        color = getGradient(BlueStart, BlueEnd),
    ),

    Card(
        cardType = "VISA",
        cardNumber = "0078 3467 3446 7899",
        cardName = "Personal",
        balance = 3467.25,
        color = getGradient(OrangeStart, OrangeEnd),
    ),

    Card(
        cardType = "MASTER CARD",
        cardNumber = "3567 7865 3786 3976",
        cardName = "Travel",
        balance = 2647.80,
        color = getGradient(GreenStart, GreenEnd),
    ),
)

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

@Composable
fun CardsSection(onCardClick: (Int) -> Unit = {}) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Cards",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${cards.size} cards",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow {
            items(cards.size) { index ->
                CardItem(index = index, onClick = { onCardClick(index) })
            }
        }
    }
}

@Composable
fun CardItem(index: Int, onClick: () -> Unit = {}) {
    val card = cards[index]
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "scale"
    )

    val lastItemPaddingEnd = if (index == cards.size - 1) 16.dp else 0.dp

    val image = if (card.cardType == "MASTER CARD") {
        painterResource(id = R.drawable.ic_mastercard)
    } else {
        painterResource(id = R.drawable.ic_visa)
    }

    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = lastItemPaddingEnd)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        Column(
            modifier = Modifier
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(25.dp),
                    spotColor = Color.Black.copy(alpha = 0.4f)
                )
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .width(280.dp)
                .height(170.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    isPressed = true
                    onClick()
                }
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Row - Card Type Logo and Contactless Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = image,
                    contentDescription = card.cardName,
                    modifier = Modifier.width(50.dp)
                )

                Icon(
                    imageVector = Icons.Rounded.Contactless,
                    contentDescription = "Contactless",
                    tint = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.size(28.dp)
                )
            }

            Column {
                Text(
                    text = card.cardName,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "₹${String.format("%,.2f", card.balance)}",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Card Number Section with a hint of glassmorphism
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(vertical = 6.dp, horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = card.cardNumber,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 1.5.sp
                    )

                    Text(
                        text = "EXP 12/28",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}