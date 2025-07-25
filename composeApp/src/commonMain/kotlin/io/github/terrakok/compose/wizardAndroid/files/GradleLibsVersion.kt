package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class GradleLibsVersion(info: AndroidProjectInfo) : ProjectFile {
    override val path = "gradle/libs.versions.toml"
    override val content = buildString {
        // versions
        appendLine("[versions]")
        appendLine()

        appendLine("agp = \"${info.agpVersion}\"")
        appendLine("kotlin = \"${info.kotlinVersion}\"")
        appendLine("compose = \"${info.composeVersion}\"")

        /*
                info.dependencies
                    .filter { it != KotlinxSerializationPlugin }
                    .distinctBy { it.catalogVersionName }
                    .forEach {
                        appendLine("${it.catalogVersionName} = \"${it.version}\"")
                    }
                appendLine()
         */

        // libraries
        val libraries = info.dependencies.filterNot { it.isPlugin }
        appendLine("[libraries]")
        appendLine()

        libraries.forEach {
            appendLine("${it.catalogName} = { module = \"${it.group}:${it.id}\", version.ref = \"${it.catalogVersionName}\" }")
        }
        appendLine()

        // plugins
        appendLine("[plugins]")
        appendLine()

        appendLine("android-application = { id = \"com.android.application\", version.ref = \"agp\" }")
        appendLine("android-library = { id = \"com.android.library\", version.ref = \"agp\" }")
        appendLine("kotlin-android = { id = \"org.jetbrains.kotlin.android\", version.ref = \"kotlin\" }")
        appendLine("kotlin-compose = { id = \"org.jetbrains.kotlin.plugin.compose\", version.ref = \"kotlin\" }")

        info.dependencies.filter { it.isPlugin }.forEach {
            appendLine("${it.catalogName} = { id = \"${it.group}\", version.ref = \"${it.catalogVersionName}\" }")
        }
    }
}
