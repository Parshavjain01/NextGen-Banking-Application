plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") // ✅ No version here
}


android {
    namespace = "com.example.bank_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.bank_app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // ✅ Compatible with Compose BOM 2023.10.01
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    lint {
        disable.add("MutableCollectionMutableState")
        disable.add("AutoboxingStateCreation")
    }
}

dependencies {
    // Core & Lifecycle
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))

    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Google Play Services Wallet API
    implementation("com.google.android.gms:play-services-wallet:19.2.1")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Accompanist for System UI customization
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    // Material icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // Optional testing and debugging (commented)
    // testImplementation("junit:junit:4.13.2")
    // androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    // androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // debugImplementation("androidx.compose.ui:ui-tooling")
    // debugImplementation("androidx.compose.ui:ui-test-manifest")
}
