package com.example.bank_app.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Payment
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.GreenStart
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.data.*
import com.example.bank_app.ui.theme.*
import com.example.bank_app.ui.components.*


data class NotificationItem(val title: String, val description: String, val icon: ImageVector, val color: androidx.compose.ui.graphics.Color)

val notificationsList = listOf(
    NotificationItem("Payment Successful", "You have successfully sent $50.00 to Alex.", Icons.Rounded.Payment, GreenStart),
    NotificationItem("Security Alert", "New login from Mac OS.", Icons.Rounded.Security, OrangeStart),
    NotificationItem("System Update", "Your bank app has been updated to version 2.0.", Icons.Rounded.Notifications, BlueStart)
)

@Composable
fun NotificationsScreen(showSnackbar: (String) -> Unit = {}, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Notifications",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        
        LazyColumn(
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(notificationsList.size) { index ->
                NotificationRow(
                    item = notificationsList[index],
                    onClick = { showSnackbar("Opening notification: ${notificationsList[index].title}") }
                )
            }
        }
    }
}

@Composable
fun NotificationRow(item: NotificationItem, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(item.color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.color,
                modifier = Modifier.size(24.dp)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column {
            Text(
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
