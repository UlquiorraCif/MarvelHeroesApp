plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.leary.marvelheroesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.leary.marvelheroesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.0")
    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation (platform("org.jetbrains.kotlin:kotlin-bom:1.9.20"))
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.activity:activity-compose:1.8.2")
    implementation ( platform("androidx.compose:compose-bom:2024.02.02"))
    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.ui:ui-graphics")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.7")


    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation (libs.androidx.espresso.core)
    androidTestImplementation (platform("androidx.compose:compose-bom:2024.02.02"))
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}