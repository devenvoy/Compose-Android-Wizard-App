package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class Readme(info: AndroidProjectInfo) : ProjectFile {
    override val path = "README.MD"
    override val content = buildString {
        appendLine("# ${info.name}")
        appendLine("")
        appendLine("")
        appendLine("`sh gradle wrapper`")
        appendLine("`sh chmod +x ./gradlew`")
        appendLine("`sh ./gradlew app:dependencies`")
        appendLine("`sh git init`")
        appendLine("`sh git add .`")
        appendLine("`sh git commit -m \"initial commit\"`")
        appendLine("`sh git branch -M main")
        appendLine("`sh git remote add origin https://github.com/your-username/your-repo-name.git`")
        appendLine("`sh git push -u origin main")

        appendLine()
        appendLine()
        appendLine()
        appendLine()
        appendLine()
        appendLine("- Project made by -> @devenvoy")
        appendLine("- X -")
    }
}