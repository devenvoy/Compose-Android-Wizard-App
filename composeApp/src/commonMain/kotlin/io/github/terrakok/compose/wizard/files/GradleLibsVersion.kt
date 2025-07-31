package io.github.terrakok.compose.wizard.files

import io.github.terrakok.compose.wizard.KotlinxSerializationPlugin
import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizard.hasAndroid
import io.github.terrakok.compose.wizard.isPlugin

class GradleLibsVersion(info: ProjectInfo) : ProjectFile {
    override val path = "gradle/libs.versions.toml"
    override val content = buildString {
        // versions
        appendLine("[versions]")
        appendLine()

        appendLine("kotlin = \"${info.kotlinVersion}\"")
        appendLine("agp = \"${info.agpVersion}\"")
        appendLine("compose = \"${info.composeVersion}\"")

        if (info.hasAndroid) {
            appendLine(
                "androidx-appcompat = \"1.7.1\"\n" +
                        "androidx-activityCompose = \"1.10.1\""
            )
        }

        info.dependencies
            .filter { it != KotlinxSerializationPlugin }
            .distinctBy { it.catalogVersionName }
            .forEach {
                appendLine("${it.catalogVersionName} = \"${it.version}\"")
            }
        appendLine()

        // libraries
        val libraries = info.dependencies.filterNot { it.isPlugin() }
        appendLine("[libraries]")
        appendLine()
        if (info.hasAndroid) {
            appendLine(
                "androidx-appcompat = { module = \"androidx.appcompat:appcompat\", version.ref = \"androidx-appcompat\" }\n" +
                        "androidx-activityCompose = { module = \"androidx.activity:activity-compose\", version.ref = \"androidx-activityCompose\" }"
            )
        }
        info.dependencies.filterNot { it.isPlugin() }.forEach {
            appendLine("${it.catalogName} = { module = \"${it.group}:${it.id}\", version.ref = \"${it.catalogVersionName}\" }")
        }
        appendLine()

        // plugins
        appendLine("[plugins]")
        appendLine()

        appendLine("multiplatform = { id = \"org.jetbrains.kotlin.multiplatform\", version.ref = \"kotlin\" }")
        appendLine("cocoapods = { id = \"org.jetbrains.kotlin.native.cocoapods\", version.ref = \"kotlin\" }")
        appendLine("compose = { id = \"org.jetbrains.compose\", version.ref = \"compose\" }")
        appendLine("android-application = { id = \"com.android.application\", version.ref = \"agp\" }")
        appendLine("compose-compiler = { id = \"org.jetbrains.kotlin.plugin.compose\", version.ref = \"kotlin\" }")

        info.dependencies.filter { it.isPlugin() }.forEach {
            appendLine("${it.catalogName} = { id = \"${it.group}\", version.ref = \"${it.catalogVersionName}\" }")
        }
    }
}
