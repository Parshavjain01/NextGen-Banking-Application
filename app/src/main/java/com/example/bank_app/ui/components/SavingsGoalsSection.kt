package com.example.bank_app.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Flight
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.ui.theme.PurpleStart

data class SavingsGoal(
    val title: String,
    val currentAmount: Float,
    val targetAmount: Float,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun SavingsGoalsSection(modifier: Modifier = Modifier) {
    val goals = remember {
        listOf(
            SavingsGoal("Dream Car", 15000f, 45000f, Icons.Rounded.DirectionsCar, BlueStart),
            SavingsGoal("Vacation", 3200f, 5000f, Icons.Rounded.Flight, OrangeStart),
            SavingsGoal("New Home", 50000f, 150000f, Icons.Rounded.Home, PurpleStart)
        )
    }

    Column(modifier = modifier) {
        SectionHeader(
            title = "Savings Goals",
            actionText = "See All",
            onActionClick = { /* See all goals */ }
        )

        LazyRow(
            modifier = Modifier.padding(top = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(goals.size) { index ->
                SavingsGoalItem(goal = goals[index])
            }
        }
    }
}

@Composable
fun SavingsGoalItem(goal: SavingsGoal) {
    val progress = goal.currentAmount / goal.targetAmount
    
    var visible by remember { mutableStateOf(false) }
    val animatedProgress by animateFloatAsState(
        targetValue = if (visible) progress else 0f,
        animationSpec = tween(durationMillis = 1500),
        label = "progress"
    )
    
    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(goal.color.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = goal.icon,
                        contentDescription = null,
                        tint = goal.color,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Text(
                    text = "${(animatedProgress * 100).toInt()}%",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = goal.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "₹${String.format("%.0f", goal.currentAmount)} / ₹${String.format("%.0f", goal.targetAmount)}",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                fontSize = 12.sp,
                maxLines = 1
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LinearProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = goal.color,
                trackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                strokeCap = StrokeCap.Round
            )
        }
    }
}
