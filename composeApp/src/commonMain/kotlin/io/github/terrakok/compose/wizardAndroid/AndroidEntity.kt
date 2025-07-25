package io.github.terrakok.compose.wizardAndroid

import io.github.terrakok.compose.wizard.Dependency

// Basic Android project information
data class AndroidProjectInfo(
    val packageId: String = "org.company.app",
    val name: String = "Android App",
    val gradleVersion: String = "8.11.1",
    val kotlinVersion: String = "2.2.0",
    val agpVersion: String = "8.11.1",
    val minSdk: Int = 21,
    val targetSdk: Int = 36,
    val compileSdk: Int = 36,
    val composeVersion: String = "1.8.3",
    val dependencies: Set<AndroidDependency> = defaultAndroidDependencies
) {
    val packagePath get() = packageId.replace(".", "/")
    val safeName get() = name.replace(" ", "")
    val kotlinVersionRef get() = "kotlin"
    val agpVersionRef get() = "agp"
    val composeVersionRef get() = "compose"
}

val defaultAndroidDependencies = setOf(
    activityCompose,
    composeUiTooling,
    material3,
    lifecycleRuntimeKtx,
    androidxCoreKtx,
    junit,
    espressoCore,
    composeBom
)

// Represents a library or plugin used in the Android project
data class AndroidDependency(
    val title: String,
    val description: String,
    val url: String,
    val group: String,
    val id: String,
    val version: String,
    val catalogVersionName: String,
    val catalogName: String,
    val isPlugin: Boolean = false,
)

// Accessors for Gradle notation
val AndroidDependency.catalogAccessor get() = catalogName.replace("-", ".")
val AndroidDependency.libraryNotation get() = "implementation(libs.$catalogAccessor)"
val AndroidDependency.pluginNotation get() = "alias(libs.plugins.$catalogAccessor)"

interface ProjectFile {
    val path: String
    val content: String
}
