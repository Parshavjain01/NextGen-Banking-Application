package com.example.bank_app.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import com.example.bank_app.ui.theme.*

// Mock Data for the application
val bottomNavItems = listOf(
    BottomNavigation("Home", Icons.Rounded.Home),
    BottomNavigation("Wallet", Icons.Rounded.Wallet),
    BottomNavigation("Notifications", Icons.Rounded.Notifications),
    BottomNavigation("Account", Icons.Rounded.AccountCircle)
)

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

val financeList = listOf(
    Finance(
        icon = Icons.Rounded.Savings,
        name = "My\nSavings",
        background = OrangeStart
    ),
    Finance(
        icon = Icons.Rounded.Wallet,
        name = "My\nWallet",
        background = BlueStart
    ),
    Finance(
        icon = Icons.Rounded.Analytics,
        name = "Finance\nAnalytics",
        background = PurpleStart
    ),
    Finance(
        icon = Icons.Rounded.MonetizationOn,
        name = "My\nTransactions",
        background = GreenStart
    ),
    Finance(
        icon = Icons.Rounded.TrendingUp,
        name = "Invest\nments",
        background = TealStart
    ),
)

val transactions = listOf(
    Transaction(
        title = "Salary Deposit",
        description = "Monthly Salary - HDFC Bank",
        amount = "+₹85,000.00",
        isIncome = true,
        icon = Icons.Rounded.Payments,
        iconBackground = GreenStart,
        date = "Today"
    ),
    Transaction(
        title = "Zomato",
        description = "Food Delivery",
        amount = "-₹456.99",
        isIncome = false,
        icon = Icons.Rounded.Fastfood,
        iconBackground = OrangeStart,
        date = "Yesterday"
    ),
    Transaction(
        title = "Jio Prepaid",
        description = "Mobile Recharge",
        amount = "-₹749.00",
        isIncome = false,
        icon = Icons.Rounded.Payments,
        iconBackground = BlueStart,
        date = "Yesterday"
    ),
    Transaction(
        title = "Swiggy",
        description = "Dinner",
        amount = "-₹812.40",
        isIncome = false,
        icon = Icons.Rounded.Fastfood,
        iconBackground = PurpleStart,
        date = "Jun 12"
    ),
    Transaction(
        title = "Netflix",
        description = "Monthly Subscription",
        amount = "-₹499.00",
        isIncome = false,
        icon = Icons.Rounded.Movie,
        iconBackground = OrangeStart,
        date = "Jun 10"
    )
)

private fun getGradient(
    startColor: androidx.compose.ui.graphics.Color,
    endColor: androidx.compose.ui.graphics.Color,
): androidx.compose.ui.graphics.Brush {
    return androidx.compose.ui.graphics.Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}
