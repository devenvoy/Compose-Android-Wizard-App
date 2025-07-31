package io.github.terrakok.compose.wizard.files

import io.github.terrakok.compose.wizard.ProjectFile

class GradleProperties : ProjectFile {
    override val path = "gradle.properties"
    override val content = """
#Gradle
org.gradle.jvmargs=-Xmx4096M -Dfile.encoding=UTF-8 -Dkotlin.daemon.jvm.options\="-Xmx4096M"

#Kotlin
kotlin.code.style=official
kotlin.js.compiler=ir

#MPP
kotlin.mpp.enableCInteropCommonization=true
kotlin.mpp.androidSourceSetLayoutVersion=2

#Compose
org.jetbrains.compose.experimental.uikit.enabled=true
org.jetbrains.compose.experimental.jscanvas.enabled=true
kotlin.native.cacheKind=none

#Android
android.useAndroidX=true
android.nonTransitiveRClass=true
"""
}