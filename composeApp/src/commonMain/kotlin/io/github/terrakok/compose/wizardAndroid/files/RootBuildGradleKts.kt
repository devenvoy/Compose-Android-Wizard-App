package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile
import io.github.terrakok.compose.wizardAndroid.catalogAccessor

class RootBuildGradleKts(info: AndroidProjectInfo) : ProjectFile {
    override val path = "build.gradle.kts"

    override val content = buildString {
        appendLine("plugins {")

        // Core plugins (these are typically always included)
        appendLine("    alias(libs.plugins.android.application) apply false")
        appendLine("    alias(libs.plugins.kotlin.android) apply false")
        appendLine("    alias(libs.plugins.kotlin.compose) apply false")
        appendLine("    id(\"com.google.devtools.ksp\") version \"2.2.0-2.0.2\" apply false")

        // Dynamically include plugin dependencies
        info.dependencies.flatMap { it.items }
            .filter { it.isPlugin }
            .forEach { dep ->
                appendLine("    alias(libs.plugins.${dep.catalogAccessor}) apply false")
            }

        appendLine("}")
    }
}
