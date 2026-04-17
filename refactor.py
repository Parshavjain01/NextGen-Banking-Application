import os
import shutil

base_path = "app/src/main/java/com/example/bank_app"

moves = {
    "LoginScreen.kt": "ui/screens/auth",
    "SignUpScreen.kt": "ui/screens/auth",
    "LockScreen.kt": "ui/screens/auth",
    
    "HomeContentScreen.kt": "ui/screens/home",
    "CardsSection.kt": "ui/screens/home",
    "CurrenciesSection.kt": "ui/screens/home",
    "FinanceSection.kt": "ui/screens/home",
    "QuickActionsSection.kt": "ui/screens/home",
    "SavingsGoalsSection.kt": "ui/screens/home",
    "TransactionsSection.kt": "ui/screens/home",
    "WalletSection.kt": "ui/screens/home",
    
    "SendMoneyScreen.kt": "ui/screens/transfer",
    "ReceiveScreen.kt": "ui/screens/transfer",
    "TopUpScreen.kt": "ui/screens/transfer",
    "PayBillsScreen.kt": "ui/screens/transfer",
    
    "AnalyticsScreen.kt": "ui/screens/analytics",
    
    "AllTransactionsScreen.kt": "ui/screens/transactions",
    "TransactionDetailScreen.kt": "ui/screens/transactions",
    
    "WalletScreen.kt": "ui/screens/wallet",
    "CardDetailsScreen.kt": "ui/screens/wallet",
    
    "AccountScreen.kt": "ui/screens/account",
    "SettingsScreen.kt": "ui/screens/account",
    "SecurityScreen.kt": "ui/screens/account",
    "HelpCenterScreen.kt": "ui/screens/account",
    "NotificationsScreen.kt": "ui/screens/account",
    
    "BottomNavigationBar.kt": "ui/components",
}

for file, dest in moves.items():
    dest_dir = os.path.join(base_path, dest)
    os.makedirs(dest_dir, exist_ok=True)
    src = os.path.join(base_path, file)
    if os.path.exists(src):
        dst = os.path.join(dest_dir, file)
        shutil.move(src, dst)
        
        with open(dst, "r") as f:
            content = f.read()
            
        pkg_prefix = dest.replace("/", ".")
        content = content.replace("package com.example.bank_app\n", f"package com.example.bank_app.{pkg_prefix}\n")
        
        # Add wildcard imports for data and theme just in case
        imports_to_add = """
import com.example.bank_app.data.*
import com.example.bank_app.ui.theme.*
import com.example.bank_app.ui.components.*
"""
        # find last import and insert
        idx = content.rfind("import ")
        if idx != -1:
            end_of_line = content.find("\n", idx)
            content = content[:end_of_line] + imports_to_add + content[end_of_line:]
            
        with open(dst, "w") as f:
            f.write(content)
print("Done")
