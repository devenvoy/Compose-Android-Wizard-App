package io.github.terrakok.compose.ui

import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizard.buildFiles
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.buildFiles
import java.awt.Desktop
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

actual fun saveZipFile(name: String, bytes: ByteArray) {
    val downloadsPath = File(System.getProperty("user.home"), "Downloads")
    val file = File(downloadsPath, "$name.zip")
    file.writeBytes(bytes)
    Desktop.getDesktop().open(file.parentFile)
}


actual suspend fun generateAndroidZip(projectInfo: AndroidProjectInfo): ByteArray {
    val files = projectInfo.buildFiles()
    val baos = ByteArrayOutputStream()
    val zipStream = ZipOutputStream(baos)

    files.forEach { file ->
        val entry = ZipEntry(file.path)
        zipStream.putNextEntry(entry)
        zipStream.write(file.content.toByteArray())
        zipStream.closeEntry()
    }

    zipStream.close()
    return baos.toByteArray()
}

actual suspend fun generateZip(projectInfo: ProjectInfo): ByteArray {
    val files = projectInfo.buildFiles()
    val baos = ByteArrayOutputStream()
    val zipStream = ZipOutputStream(baos)

    files.forEach { file ->
        val entry = ZipEntry(file.path)
        zipStream.putNextEntry(entry)
        zipStream.write(file.content.toByteArray())
        zipStream.closeEntry()
    }

    zipStream.close()
    return baos.toByteArray()
}