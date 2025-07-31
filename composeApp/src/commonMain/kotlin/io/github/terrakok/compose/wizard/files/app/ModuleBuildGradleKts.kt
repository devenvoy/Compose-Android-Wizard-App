package io.github.terrakok.compose.wizard.files.app

import io.github.terrakok.compose.wizard.ApolloPlugin
import io.github.terrakok.compose.wizard.BuildConfigPlugin
import io.github.terrakok.compose.wizard.ComposePlatform
import io.github.terrakok.compose.wizard.Dependency
import io.github.terrakok.compose.wizard.KStore
import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizard.SQLDelightPlugin
import io.github.terrakok.compose.wizard.hasAndroid
import io.github.terrakok.compose.wizard.hasBrowser
import io.github.terrakok.compose.wizard.hasDesktop
import io.github.terrakok.compose.wizard.hasIos
import io.github.terrakok.compose.wizard.isCommon
import io.github.terrakok.compose.wizard.isPlugin
import io.github.terrakok.compose.wizard.libraryNotation
import io.github.terrakok.compose.wizard.pluginNotation

class ModuleBuildGradleKts(info: ProjectInfo) : ProjectFile {
    override val path = "composeApp/build.gradle.kts"
    override val content = buildString {
        val plugins = mutableSetOf<Dependency>()
        val commonDeps = mutableSetOf<Dependency>()
        val otherDeps = mutableSetOf<Dependency>()
        info.dependencies.forEach { dep ->
                when  {
                    dep.isPlugin() -> plugins.add(dep)
                    dep.isCommon() -> commonDeps.add(dep)
                    else -> otherDeps.add(dep)
                }
            }


        if (info.hasDesktop) {
            appendLine("import org.jetbrains.compose.desktop.application.dsl.TargetFormat")
        }
        if(info.hasDesktop) {
            appendLine("import org.jetbrains.kotlin.gradle.dsl.JvmTarget")
        }
        appendLine("import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag")
        appendLine("")

        appendLine("plugins {")
        appendLine("    alias(libs.plugins.multiplatform)")
        appendLine("    alias(libs.plugins.compose)")
        appendLine("    alias(libs.plugins.compose.compiler)")
        if (info.hasIos) {
            appendLine("    alias(libs.plugins.cocoapods)")
        }
        if (info.hasAndroid) {
            appendLine("    alias(libs.plugins.android.application)")
        }
        plugins.forEach { dep ->
            appendLine("    ${dep.pluginNotation}")
        }
        appendLine("}")
        appendLine("")
        appendLine("kotlin {")
        if (info.hasAndroid) {
            appendLine("    androidTarget {")
            appendLine("        compilerOptions {")
            appendLine("            jvmTarget.set(JvmTarget.JVM_17)")
            appendLine("        }")
            appendLine("    }")
            appendLine("")
        }
        if (info.hasDesktop) {
            appendLine("    jvm()")
            appendLine("")
        }
        if (info.hasBrowser) {
            appendLine("    js {")
            appendLine("        browser()")
            appendLine("        binaries.executable()")
            appendLine("    }")
            appendLine("")
        }
        if (info.hasIos) {
            appendLine(
                """
                    listOf(
                        iosX64(),
                        iosArm64(),
                        iosSimulatorArm64()
                    ).forEach { iosTarget ->
                        iosTarget.binaries.framework {
                            baseName = "ComposeApp"
                            isStatic = true
                        }
                    }
                    applyDefaultHierarchyTemplate()
            """.trimIndent()
            )
            appendLine("")
            appendLine("    cocoapods {")
            appendLine("        version = \"1.0.0\"")
            appendLine("        summary = \"Compose application framework\"")
            appendLine("        homepage = \"empty\"")
            appendLine("        ios.deploymentTarget = \"11.0\"")
            appendLine("        podfile = project.file(\"../iosApp/Podfile\")")
            appendLine("        framework {")
            appendLine("            baseName = \"ComposeApp\"")
            appendLine("            isStatic = true")
            appendLine("        }")
            appendLine("    }")
            appendLine("")
        }
        appendLine("    sourceSets {")
        appendLine("        commonMain.dependencies {")
        appendLine("                implementation(compose.runtime)")
        appendLine("                implementation(compose.foundation)")
        appendLine("                implementation(compose.material3)")

        commonDeps.forEach { dep ->
            appendLine("                ${dep.libraryNotation}")
        }
        appendLine("        }")
        appendLine("")
        appendLine("        commonTest.dependencies {")
        appendLine("//            implementation(libs.kotlin.test)")
        appendLine("        }")
        appendLine("")
        if (info.hasAndroid) {
            appendLine("        androidMain.dependencies{")
            appendLine("            implementation(compose.preview)")
            appendLine("            implementation(libs.androidx.activityCompose)")
            otherDeps.forEach { dep ->
                if (dep.platforms.contains(ComposePlatform.Android)) {
                    appendLine("                ${dep.libraryNotation}")
                }
            }
            appendLine("        }")
            appendLine("")
        }
        if (info.hasDesktop) {
            appendLine("        jvmMain.dependencies {")
            appendLine("                implementation(compose.desktop.common)")
            appendLine("                implementation(compose.desktop.currentOs)")

            otherDeps.forEach { dep ->
                if (dep.platforms.contains(ComposePlatform.Desktop)) {
                    appendLine("                ${dep.libraryNotation}")
                }
            }
            appendLine("        }")
            appendLine("")
        }
        if (info.hasBrowser) {
            appendLine("        jsMain.dependencies {")
            appendLine("                implementation(compose.html.core)")

            otherDeps.forEach { dep ->
                if (dep.platforms.contains(ComposePlatform.Browser)) {
                    appendLine("                ${dep.libraryNotation}")
                }
            }
            appendLine("        }")
            appendLine("")
        }
        appendLine("    }")
        appendLine("}")
        appendLine("")
        if (info.hasAndroid) {
            appendLine("android {")
            appendLine("    namespace = \"${info.packageId}\"")
            appendLine("    compileSdk = ${info.androidTargetSdk}")
            appendLine("")
            appendLine("    defaultConfig {")
            appendLine("        minSdk = ${info.androidMinSdk}")
            appendLine("        targetSdk = ${info.androidTargetSdk}")
            appendLine("")
            appendLine("        applicationId = \"${info.packageId}.androidApp\"")
            appendLine("        versionCode = 1")
            appendLine("        versionName = \"1.0.0\"")
            appendLine("    }")
            appendLine("    compileOptions {")
            appendLine("        sourceCompatibility = JavaVersion.VERSION_1_8")
            appendLine("        targetCompatibility = JavaVersion.VERSION_1_8")
            appendLine("    }")
            appendLine("    buildTypes {")
            appendLine("        getByName(\"release\") {")
            appendLine("            isMinifyEnabled = false")
            appendLine("        }")
            appendLine("    }")
            appendLine("    packaging {")
            appendLine("        resources {")
            appendLine("            excludes += \"/META-INF/{AL2.0,LGPL2.1}\"")
            appendLine("        }")
            appendLine("    }")
            appendLine("}")
            appendLine("")
        }
        appendLine("")
        appendLine("dependencies {")
        appendLine("    debugImplementation(compose.uiTooling)")
        appendLine("}")
        appendLine("")

        if (info.hasDesktop) {
            appendLine("compose.desktop {")
            appendLine("    application {")
            appendLine("        mainClass = \"MainKt\"")
            appendLine("")
            appendLine("        nativeDistributions {")
            appendLine("            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)")
            appendLine("            packageName = \"${info.packageId}.desktopApp\"")
            appendLine("            packageVersion = \"1.0.0\"")
            appendLine("            description = \"Compose Multiplatform App\"")
            appendLine("            copyright = \"© 2024 My Name. All rights reserved.\"")
            appendLine("            windows{")
            appendLine("                shortcut = true")
            appendLine("                dirChooser = true")
            appendLine("            }")
            appendLine("            modules(")
            if (info.dependencies.contains(SQLDelightPlugin)) {
                appendLine("                \"java.sql\",")
            }
            if (info.dependencies.contains(KStore)) {
                appendLine("                \"java.prefs\",")
            }
            appendLine("                \"java.net.http\",")
            appendLine("                \"java.management\",")
            appendLine("                \"jdk.unsupported\",")
            appendLine("                \"java.instrument\",")
            appendLine("                \"jdk.security.auth\",")
            appendLine("            )")
            appendLine("        }")
            appendLine("        buildTypes.release.proguard {")
            appendLine("            obfuscate.set(false)")
            appendLine("            configurationFiles.from(project.file(\"assemble/proguard-rules.pro\"))")
            appendLine("        }")
            appendLine("    }")
            appendLine("}")
        }
        if (plugins.contains(BuildConfigPlugin)) {
            appendLine("")
            appendLine("buildConfig {")
            appendLine("  // BuildConfig configuration here.")
            appendLine("  // https://github.com/gmazzo/gradle-buildconfig-plugin#usage-in-kts")
            appendLine("}")
        }

        appendLine("composeCompiler {")
        appendLine("    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)")
        appendLine("}")

        if (plugins.contains(SQLDelightPlugin)) {
            appendLine("")
            appendLine("sqldelight {")
            appendLine("  databases {")
            appendLine("    create(\"MyDatabase\") {")
            appendLine("      // Database configuration here.")
            appendLine("      // https://cashapp.github.io/sqldelight")
            appendLine("      packageName.set(\"${info.packageId}.db\")")
            appendLine("    }")
            appendLine("  }")
            appendLine("}")
        }

        if (plugins.contains(ApolloPlugin)) {
            appendLine("")
            appendLine("apollo {")
            appendLine("  service(\"api\") {")
            appendLine("    // GraphQL configuration here.")
            appendLine("    // https://www.apollographql.com/docs/kotlin/advanced/plugin-configuration/")
            appendLine("    packageName.set(\"${info.packageId}.graphql\")")
            appendLine("  }")
            appendLine("}")
        }
    }
}