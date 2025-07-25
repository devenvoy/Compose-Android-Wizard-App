package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizardAndroid.AndroidDependency
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile
import io.github.terrakok.compose.wizardAndroid.libraryNotation
import io.github.terrakok.compose.wizardAndroid.pluginNotation
import io.github.terrakok.compose.wizardAndroid.roomCompiler
import io.github.terrakok.compose.wizardAndroid.roomKtx
import io.github.terrakok.compose.wizardAndroid.roomRuntime

class AppBuildGradleKts(info: AndroidProjectInfo) : ProjectFile {
    override val path = "app/build.gradle.kts"
    override val content = buildString {
        val plugins = mutableSetOf<AndroidDependency>()
        val otherDeps = mutableSetOf<AndroidDependency>()
        info.dependencies.forEach { dep ->
            when {
                dep.isPlugin -> plugins.add(dep)
                else -> otherDeps.add(dep)
            }
        }

        appendLine("plugins {")
        appendLine("    alias(libs.plugins.android.application)")
        appendLine("    alias(libs.plugins.kotlin.android)")
        appendLine("    alias(libs.plugins.kotlin.compose)")
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
        if (info.dependencies.containsAll(listOf(roomKtx, roomCompiler, roomRuntime))) {
            appendLine("")
            appendLine("    room {")
            appendLine("        schemaDirectory(\"${'$'}projectDir/schemas\")")
            appendLine("    }")
        }
        appendLine("}")
        appendLine("")
        appendLine("dependencies {")
        appendLine("    implementation(libs.androidx.core.ktx)")
        appendLine("    implementation(libs.androidx.lifecycle.runtime.ktx)")
        appendLine("    implementation(libs.androidx.activity.compose)")
        appendLine("    implementation(platform(libs.androidx.compose.bom))")
        appendLine("    implementation(libs.androidx.ui)")
        appendLine("    implementation(libs.androidx.ui.graphics)")
        appendLine("    implementation(libs.androidx.ui.tooling.preview)")
        appendLine("    implementation(libs.androidx.material3)")

        otherDeps.forEach { dep ->
            appendLine("    ${dep.libraryNotation}")
        }

        appendLine("}")

        /*if (plugins.contains()) {
            appendLine("")
            appendLine("apollo {")
            appendLine("  service(\"api\") {")
            appendLine("    // GraphQL configuration here.")
            appendLine("    // https://www.apollographql.com/docs/kotlin/advanced/plugin-configuration/")
            appendLine("    packageName.set(\"${info.packageId}.graphql\")")
            appendLine("  }")
            appendLine("}")
        }*/
    }
}