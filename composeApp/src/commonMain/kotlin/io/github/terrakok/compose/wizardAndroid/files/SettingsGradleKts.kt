package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizard.safeName
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo

class SettingsGradleKts(info: AndroidProjectInfo) : ProjectFile {
    override val path = "settings.gradle.kts"
    override val content = buildString {
        appendLine("rootProject.name = \"${info.safeName}\"")
        appendLine("include(\":app\")")
        appendLine("")
        appendLine("pluginManagement {")
        appendLine("    repositories {")
        appendLine("        google {")
        appendLine("            content {")
        appendLine("                includeGroupByRegex(\"com\\\\.android.*\")")
        appendLine("                includeGroupByRegex(\"com\\\\.google.*\")")
        appendLine("                includeGroupByRegex(\"androidx.*\")")
        appendLine("            }")
        appendLine("        }")
        appendLine("        mavenCentral()")
        appendLine("        gradlePluginPortal()")
        appendLine("    }")
        appendLine("}")
        appendLine("")
        appendLine("dependencyResolutionManagement {")
        appendLine("    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)")
        appendLine("    repositories {")
        appendLine("        google()")
        appendLine("        mavenCentral()")
        appendLine("    }")
        appendLine("}")
    }
}