plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.contacttuto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.contacttuto"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // ZXing core library for QR code encoding/decoding
    implementation("com.google.zxing:core:3.5.1")
    // ZXing Android Embedded library for barcode scanning and QR code generation
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
}