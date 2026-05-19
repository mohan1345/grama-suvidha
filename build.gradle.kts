buildscript {
    repositories {
        google()
        mavenCentral()
    }
    
    extra.set("compose_ui_version", "1.6.2")
    extra.set("room_version", "2.6.1")
    extra.set("hilt_version", "2.50")
    extra.set("nav_version", "2.7.7")
    extra.set("kotlin_version", "1.9.22")

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}
plugins {
    id("com.android.application") version "8.13.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}
