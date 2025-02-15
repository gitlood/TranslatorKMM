import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqlDelightGradlePlugin)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktorCore)
            implementation(libs.ktorSerialization)
            implementation(libs.ktorSerializationJson)
            implementation(libs.sqlDelightRuntime)
            implementation(libs.sqlDelightCoroutinesExtensions)
            implementation(libs.kotlinDateTime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.assertK)
            implementation(libs.turbine)
        }
        androidMain.dependencies {
            implementation(libs.ktorAndroid)
            implementation(libs.sqlDelightAndroidDriver)
        }
        iosMain.dependencies {
            implementation(libs.ktorIOS)
            implementation(libs.sqlDelightNativeDriver)
        }
    }
}

android {
    namespace = "com.example.translatorkmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
