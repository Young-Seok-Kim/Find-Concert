plugins {
//    alias(libs.plugins.android.application)
//    alias(libs.plugins.kotlin.android)
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.youngs.findconcert"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.youngs.findconcert"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
    kapt {
        correctErrorTypes = true // 에러 타입 수정 허용 (필수)
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    // other dependencies
    // Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.accompanist:accompanist-themeadapter-material3:0.28.0")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("org.jsoup:jsoup:1.16.1")

    val roomVersion = "2.6.1"

    // Room 라이브러리
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Room + Kotlin Coroutines 지원
    implementation("androidx.room:room-ktx:$roomVersion")

    // 테스트용 Room 라이브러리 (선택)
    testImplementation("androidx.room:room-testing:$roomVersion")
}