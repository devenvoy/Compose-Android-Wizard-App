package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class Readme(info: AndroidProjectInfo) : ProjectFile {
    override val path = "README.MD"
    override val content = buildString {
        appendLine("# Android Compose Application")
        appendLine("")
    }
}