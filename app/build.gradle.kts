plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-android-extensions")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
    id ("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("29.0.3")

    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.example.catsimage"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    dependencies {

        implementation("androidx.core:core-ktx:1.3.2")
        implementation("androidx.appcompat:appcompat:1.2.0")
        implementation("com.google.android.material:material:1.3.0")
        implementation("androidx.constraintlayout:constraintlayout:2.0.4")
        testImplementation("junit:junit:4.+")
        androidTestImplementation("androidx.test.ext:junit:1.1.2")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

        //Coil
        implementation("io.coil-kt:coil:1.1.1")

        // Navigation Component
        implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
        implementation("androidx.navigation:navigation-ui-ktx:2.3.5")

        // Dagger Hilt
        implementation("com.google.dagger:hilt-android:2.36")
        kapt("com.google.dagger:hilt-android-compiler:2.36")
        implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
        kapt("androidx.hilt:hilt-compiler:1.0.0")

        // Retrofit + GSON
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("com.squareup.okhttp3:okhttp:4.9.1")
        implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

        // Glide
        implementation("com.github.bumptech.glide:glide:3.7.0")

        // Paging 3
        implementation("androidx.paging:paging-runtime-ktx:3.0.1")
    }
}