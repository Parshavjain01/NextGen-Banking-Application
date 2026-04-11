package com.example.bank_app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ElectricalServices
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bank_app.ui.theme.BlueStart
import com.example.bank_app.ui.theme.OrangeStart
import com.example.bank_app.ui.theme.PurpleStart

data class BillItem(val title: String, val amount: String, val icon: ImageVector, val color: androidx.compose.ui.graphics.Color)

val bills = listOf(
    BillItem("Electricity", "$120.00", Icons.Rounded.ElectricalServices, OrangeStart),
    BillItem("Water", "$45.50", Icons.Rounded.WaterDrop, BlueStart),
    BillItem("Internet provided", "$89.99", Icons.Rounded.Wifi, PurpleStart)
)

@Composable
fun PayBillsScreen(
    onBackClick: () -> Unit,
    showSnackbar: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
                text = "Pay Bills",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(bills.size) { index ->
                BillRow(bill = bills[index], onPayClick = {
                    showSnackbar("Successfully paid ${bills[index].title} bill!")
                })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BillRow(bill: BillItem, onPayClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(bill.color.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = bill.icon,
                contentDescription = null,
                tint = bill.color,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = bill.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Due in 3 Days",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error
            )
        }

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = bill.amount,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onPayClick,
                modifier = Modifier.height(32.dp),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                Text("Pay", fontSize = 12.sp)
            }
        }
    }
}
