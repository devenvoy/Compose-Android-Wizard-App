package io.github.terrakok.compose.wizardAndroid.files

import io.github.terrakok.compose.wizardAndroid.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo

class GradleWrapperProperties(info: AndroidProjectInfo) : ProjectFile {
    override val path = "gradle/wrapper/gradle-wrapper.properties"
    override val content = """
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.13-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
""".trimIndent()
}

class GradleWrapperJar : ProjectFile {
    override val path = "gradle/wrapper/gradle-wrapper.jar"
    override val content = "/* binary file */"
}