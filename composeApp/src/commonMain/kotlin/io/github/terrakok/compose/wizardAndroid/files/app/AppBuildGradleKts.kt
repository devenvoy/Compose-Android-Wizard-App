package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizardAndroid.AndroidDependency
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizardAndroid.compilerNotation
import io.github.terrakok.compose.wizardAndroid.libraryNotation
import io.github.terrakok.compose.wizardAndroid.packageNotation
import io.github.terrakok.compose.wizardAndroid.pluginNotation

class AppBuildGradleKts(info: AndroidProjectInfo) : ProjectFile {
    override val path = "app/build.gradle.kts"
    override val content = buildString {
        val plugins = mutableSetOf<AndroidDependency>()
        val otherDeps = mutableSetOf<AndroidDependency>()

        val dependencies = info.dependencies
            .flatMap { it.items }

        dependencies.forEach { dep ->
            when {
                dep.isPlugin -> plugins.add(dep)
                else -> otherDeps.add(dep)
            }
        }

        appendLine("plugins {")
        appendLine("    alias(libs.plugins.android.application)")
        appendLine("    alias(libs.plugins.kotlin.android)")
        appendLine("    alias(libs.plugins.kotlin.compose)")
        appendLine("    id(\"com.google.devtools.ksp\")")

        plugins.forEach { dep ->
            appendLine("    ${dep.pluginNotation}")
        }
        appendLine("}")
        appendLine("")

        appendLine("android {")
        appendLine("    namespace = \"${info.packageId}\"")
        appendLine("    compileSdk = ${info.targetSdk}")
        appendLine("")
        appendLine("    defaultConfig {")
        appendLine("        minSdk = ${info.minSdk}")
        appendLine("        targetSdk = ${info.targetSdk}")
        appendLine("")
        appendLine("        applicationId = \"${info.packageId}\"")
        appendLine("        versionCode = 1")
        appendLine("        versionName = \"1.0.0\"")
        appendLine("")
        appendLine("        testInstrumentationRunner = \"androidx.test.runner.AndroidJUnitRunner\"")
        appendLine("    }")
        appendLine("    buildTypes {")
        appendLine("        release {")
        appendLine("            isMinifyEnabled = false")
        appendLine("            proguardFiles(")
        appendLine("                getDefaultProguardFile(\"proguard-android-optimize.txt\"),")
        appendLine("                \"proguard-rules.pro\"")
        appendLine("            )")
        appendLine("        }")
        appendLine("    }")
        appendLine("    compileOptions {")
        appendLine("        sourceCompatibility = JavaVersion.VERSION_17")
        appendLine("        targetCompatibility = JavaVersion.VERSION_17")
        appendLine("    }")
        appendLine("    packagingOptions {")
        appendLine("        resources.excludes.add(\"META-INF/**\")")
        appendLine("    }")
        appendLine("    kotlinOptions {")
        appendLine("        jvmTarget = \"17\"")
        appendLine("    }")
        appendLine("")
        appendLine("    buildFeatures {")
        appendLine("        compose = true")
        appendLine("        buildConfig = true")
        appendLine("    }")
        appendLine("}")
        appendLine("")
        appendLine("dependencies {")

        appendLine("    implementation(\"com.google.android.material:material:1.12.0\")")
        appendLine("    implementation(\"androidx.appcompat:appcompat:1.7.1\")")

        otherDeps.forEach { dep ->
            when {
                dep.isCompiler ->  appendLine("    ${dep.compilerNotation}")
                dep.isPackage ->  appendLine("    ${dep.packageNotation}")
                else ->  appendLine("    ${dep.libraryNotation}")
            }
        }

        appendLine("}")
    }
}