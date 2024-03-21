@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization") version "1.9.22"
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.zntkr.mybookapplication.core.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
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
        aidl = false
        buildConfig = false
        renderScript = false
        shaders = false
    }
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(libs.androidx.compose.material3)

    // serialization
    implementation(libs.kotlinx.serialization.json)

    // okhttp
    implementation(libs.okhttp.logging)

    // retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}