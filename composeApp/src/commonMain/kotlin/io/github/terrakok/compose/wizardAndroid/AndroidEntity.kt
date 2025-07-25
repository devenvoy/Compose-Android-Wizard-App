package io.github.terrakok.compose.wizardAndroid

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
    val dependencies: Set<GroupedAndroidDependency> = defaultAndroidDependencies
) {
    val packagePath get() = packageId.replace(".", "/")
    val safeName get() = name.replace(" ", "")
    val kotlinVersionRef get() = "kotlin"
    val agpVersionRef get() = "agp"
    val composeVersionRef get() = "compose"
}

val defaultAndroidDependencies = setOf(
   CoreGroup,ComposeUIGroup,TestGroup,MaterialAndSplashGroup
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
    val isCompiler: Boolean = false,
    val isPackage: Boolean = false
)

// Accessors for Gradle notation
val AndroidDependency.catalogAccessor get() = catalogName.replace("-", ".")
val AndroidDependency.libraryNotation get() = "implementation(libs.$catalogAccessor)"
val AndroidDependency.pluginNotation get() = "alias(libs.plugins.$catalogAccessor)"
val AndroidDependency.compilerNotation get() = "ksp(libs.$catalogAccessor)"
val AndroidDependency.packageNotation get() = "implementation(platform(libs.$catalogAccessor))"


data class GroupedAndroidDependency(
    val title: String,
    val items: List<AndroidDependency>
)
