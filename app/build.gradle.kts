plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.navigation.safeargs)
}

android {
    namespace = "com.eniskaner.moviesseriestrackerinwolrdaround"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.eniskaner.moviesseriestrackerinwolrdaround"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    kotlin {
        sourceSets.main {
            kotlin.srcDir("build/generated/ksp/main/kotlin")
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
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    kapt {
        useBuildCache = false
        correctErrorTypes = false
        generateStubs = true
    }
    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // hilt
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    annotationProcessor (libs.hilt.android.compiler)
    implementation (libs.hilt.android.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material.material3)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodelKtx)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    //OkHttp
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging.interceptor)
    implementation(platform(libs.okHttp.bom))
    //Gson
    implementation(libs.gson)
    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Glide
    implementation(libs.glide)
    kapt(libs.glide.compiler)
    // Swipe Refresh Layout
    implementation(libs.swiperefreshlayout)
    // Multidex
    implementation(libs.multidex)
    //ksp
    implementation(libs.ksp)
    implementation(libs.ksp.api)
    implementation(libs.ksp.gradlePlugin)
    //Room Database
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    implementation(kotlin("reflect"))

    //youtube extractor
    implementation (libs.pierfrancescosoffritti.chromecast.sender)
    implementation (libs.pierfrancescosoffritti.core)
    implementation (libs.pierfrancescosoffritti.custom.ui)
}

/*
def coroutines_version = "1.6.4"
def nav_version = "2.5.3"
def retrofitVersion = '2.9.0'
def rxJavaVersion = '2.1.1'
def fragment_version = "1.6.0"
def work_version = "2.8.1"
def okhttp_version = "4.11.0"
def room_version = "2.5.1"
def lifecycle_version = "2.2.0"


dependencies {

    //youtube extractor
    //implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28'
    //implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:12.0.0'
    //implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:custom-ui:12.0.0'

}*/
