package com.example.bank_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bank_app.data.BottomNavigation
import com.example.bank_app.ui.theme.BankningAppUITheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.animation.*
import androidx.compose.animation.core.tween

import androidx.compose.foundation.isSystemInDarkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemTheme = isSystemInDarkTheme()
            var isDarkTheme by remember { mutableStateOf(systemTheme) }
            
            BankningAppUITheme(darkTheme = isDarkTheme) {
                SetBarColor(MaterialTheme.colorScheme.background)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = { isDarkTheme = it }
                    )
                }
            }
        }
    }
}

@Composable
fun SetBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color = color)
    }
}

val bottomNavItems = listOf(
    BottomNavigation("Home", Icons.Rounded.Home),
    BottomNavigation("Wallet", Icons.Rounded.Wallet),
    BottomNavigation("Notifications", Icons.Rounded.Notifications),
    BottomNavigation("Account", Icons.Rounded.AccountCircle)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(isDarkTheme: Boolean, onThemeChange: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "Login"

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    
    val showSnackbar: (String) -> Unit = { message ->
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            // Hide bottom bar on deep screens like SendMoney, AllTransactions
            if (currentRoute in listOf("Home", "Wallet", "Notifications", "Account")) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp
                ) {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.title,
                            onClick = { 
                                navController.navigate(item.title) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(text = item.title)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "Login",
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(500)) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = {
                slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
            }
        ) {
            composable("Login") {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate("Home") {
                            popUpTo("Login") { inclusive = true }
                        }
                    },
                    onSignUpClick = { navController.navigate("SignUp") },
                    showSnackbar = showSnackbar
                )
            }
            composable("SignUp") {
                SignUpScreen(
                    onSignUpClick = {
                        navController.navigate("Home") {
                            popUpTo("Login") { inclusive = true }
                        }
                    },
                    onLoginClick = {
                        navController.navigate("Login") {
                            popUpTo("SignUp") { inclusive = true }
                        }
                    },
                    showSnackbar = showSnackbar
                )
            }
            composable("Home") {
                HomeContentScreen(
                    onSendMoneyClick = { navController.navigate("SendMoney") },
                    onReceiveClick = { navController.navigate("Receive") },
                    onPayBillsClick = { navController.navigate("PayBills") },
                    onTopUpClick = { navController.navigate("TopUp") },
                    onCardClick = { index -> navController.navigate("CardDetails/$index") },
                    onSeeAllTransactionsClick = { navController.navigate("AllTransactions") },
                    onTransactionClick = { index -> 
                        navController.navigate("TransactionDetail/$index")
                    },
                    showSnackbar = showSnackbar
                )
            }
            composable("Wallet") {
                WalletScreen(onCardClick = { index -> navController.navigate("CardDetails/$index") }, showSnackbar = showSnackbar)
            }
            composable("Notifications") {
                NotificationsScreen(showSnackbar = showSnackbar)
            }
            composable("Account") {
                AccountScreen(
                    onLogoutClick = {
                        navController.navigate("Login") {
                            popUpTo(0) { inclusive = true } // Clear entire backstack
                        }
                    },
                    onSettingsClick = {
                        navController.navigate("Settings")
                    },
                    onHelpCenterClick = {
                        navController.navigate("HelpCenter")
                    },
                    showSnackbar = showSnackbar
                )
            }
            composable("HelpCenter") {
                HelpCenterScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("SendMoney") {
                SendMoneyScreen(
                    onBackClick = { navController.popBackStack() },
                    showSnackbar = showSnackbar
                )
            }
            composable("AllTransactions") {
                AllTransactionsScreen(
                    onBackClick = { navController.popBackStack() },
                    onTransactionClick = { index -> 
                        navController.navigate("TransactionDetail/$index")
                    },
                    showSnackbar = showSnackbar
                )
            }
            composable(
                route = "TransactionDetail/{id}",
                arguments = listOf(androidx.navigation.navArgument("id") { type = androidx.navigation.NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                TransactionDetailScreen(
                    transactionId = id,
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("Settings") {
                SettingsScreen(
                    isDarkMode = isDarkTheme,
                    onThemeChange = onThemeChange,
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "CardDetails/{index}",
                arguments = listOf(androidx.navigation.navArgument("index") { type = androidx.navigation.NavType.IntType })
            ) { backStackEntry ->
                val index = backStackEntry.arguments?.getInt("index") ?: 0
                CardDetailsScreen(
                    cardIndex = index,
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("Receive") {
                ReceiveScreen(
                    onBackClick = { navController.popBackStack() },
                    showSnackbar = showSnackbar
                )
            }
            composable("PayBills") {
                PayBillsScreen(
                    onBackClick = { navController.popBackStack() },
                    showSnackbar = showSnackbar
                )
            }
            composable("TopUp") {
                TopUpScreen(
                    onBackClick = { navController.popBackStack() },
                    showSnackbar = showSnackbar
                )
            }
        }
    }
}
