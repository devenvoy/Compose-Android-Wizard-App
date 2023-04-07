package io.github.terrakok.compose.wizard.files

import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo

class GradleWrapperProperties(info: ProjectInfo) : ProjectFile {
    override val path = "gradle/wrapper/gradle-wrapper.properties"
    override val content = """
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-${info.gradleVersion}-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
"""
}

class GradleWrapperJar : ProjectFile {
    override val path = "gradle/wrapper/gradle-wrapper.jar"
    override val content = "/* binary file */"
}