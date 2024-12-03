// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
    }
    dependencies {
        val navVersion = "2.8.4"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        classpath ("com.android.tools.build:gradle:8.7.3")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}