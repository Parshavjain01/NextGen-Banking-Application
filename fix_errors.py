import re

# Fix AppNavigation.kt
path = "app/src/main/java/com/example/bank_app/navigation/AppNavigation.kt"
with open(path, "r") as f:
    nav = f.read()
    
fix_colors = """val navColors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                    bottomNavItems.forEach { item ->"""
                    
nav = nav.replace("bottomNavItems.forEach { item ->", fix_colors)
nav = re.sub(r"colors = NavigationBarItemDefaults.colors\([^)]+\)", "colors = navColors", nav)
with open(path, "w") as f:
    f.write(nav)


# Fix CardsSection R reference
path = "app/src/main/java/com/example/bank_app/ui/screens/home/CardsSection.kt"
with open(path, "r") as f:
    cards = f.read()
if "import com.example.bank_app.R" not in cards:
    cards = cards.replace("import com.example.bank_app.data.*", "import com.example.bank_app.data.*\nimport com.example.bank_app.R")
with open(path, "w") as f:
    f.write(cards)

# Fix AllTransactionsScreen
path = "app/src/main/java/com/example/bank_app/ui/screens/transactions/AllTransactionsScreen.kt"
with open(path, "r") as f:
    all_txn = f.read()
if "import com.example.bank_app.ui.screens.home.*" not in all_txn:
    all_txn = all_txn.replace("import com.example.bank_app.ui.components.*", "import com.example.bank_app.ui.components.*\nimport com.example.bank_app.ui.screens.home.*")
with open(path, "w") as f:
    f.write(all_txn)

# Fix TransactionDetailScreen
path = "app/src/main/java/com/example/bank_app/ui/screens/transactions/TransactionDetailScreen.kt"
with open(path, "r") as f:
    txn_det = f.read()
if "import com.example.bank_app.ui.screens.home.*" not in txn_det:
    txn_det = txn_det.replace("import com.example.bank_app.ui.components.*", "import com.example.bank_app.ui.components.*\nimport com.example.bank_app.ui.screens.home.*")
with open(path, "w") as f:
    f.write(txn_det)

# Fix CardDetailsScreen
path = "app/src/main/java/com/example/bank_app/ui/screens/wallet/CardDetailsScreen.kt"
with open(path, "r") as f:
    card_det = f.read()
if "import com.example.bank_app.ui.screens.home.*" not in card_det:
    card_det = card_det.replace("import com.example.bank_app.ui.components.*", "import com.example.bank_app.ui.components.*\nimport com.example.bank_app.ui.screens.home.*\nimport com.example.bank_app.R")
with open(path, "w") as f:
    f.write(card_det)
    
# Fix WalletScreen
path = "app/src/main/java/com/example/bank_app/ui/screens/wallet/WalletScreen.kt"
with open(path, "r") as f:
    wallet = f.read()
if "import com.example.bank_app.ui.screens.home.*" not in wallet:
    wallet = wallet.replace("import com.example.bank_app.ui.components.*", "import com.example.bank_app.ui.components.*\nimport com.example.bank_app.ui.screens.home.*")
with open(path, "w") as f:
    f.write(wallet)
print("Done")
