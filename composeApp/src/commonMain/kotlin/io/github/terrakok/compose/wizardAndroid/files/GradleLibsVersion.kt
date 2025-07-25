package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class GradleLibsVersion(info: AndroidProjectInfo) : ProjectFile {
    override val path = "gradle/libs.versions.toml"
    override val content = buildString {
        // === [versions] ===
        appendLine("[versions]")
        appendLine()

        appendLine("kotlin = \"${info.kotlinVersion}\"")
        appendLine("agp = \"${info.agpVersion}\"")
        appendLine("compose = \"${info.composeVersion}\"")

        val predefined = setOf("kotlin", "agp", "compose")

        info.dependencies
            .filter { it.catalogVersionName.isNotBlank() && it.catalogVersionName !in predefined }
            .distinctBy { it.catalogVersionName }
            .forEach {
                appendLine("${it.catalogVersionName} = \"${it.version}\"")
            }
        appendLine()

        // === [libraries] ===
        appendLine("[libraries]")
        appendLine()

        info.dependencies
            .filterNot { it.isPlugin }
            .forEach {
                val versionPart = if (it.catalogVersionName.isNotBlank())
                    "version.ref = \"${it.catalogVersionName}\""
                else
                    "version = \"${it.version}\""

                appendLine("${it.catalogName} = { module = \"${it.group}:${it.id}\", $versionPart }")
            }
        appendLine()

        // === [plugins] ===
        appendLine("[plugins]")
        appendLine()

        appendLine("android-application = { id = \"com.android.application\", version.ref = \"agp\" }")
        appendLine("android-library = { id = \"com.android.library\", version.ref = \"agp\" }")
        appendLine("kotlin-android = { id = \"org.jetbrains.kotlin.android\", version.ref = \"kotlin\" }")
        appendLine("kotlin-compose = { id = \"org.jetbrains.kotlin.plugin.compose\", version.ref = \"kotlin\" }")

        info.dependencies
            .filter { it.isPlugin }
            .forEach {
                val versionPart = if (it.catalogVersionName.isNotBlank())
                    "version.ref = \"${it.catalogVersionName}\""
                else
                    "version = \"${it.version}\""

                appendLine("${it.catalogName} = { id = \"${it.group}\", $versionPart }")
            }
    }
}
