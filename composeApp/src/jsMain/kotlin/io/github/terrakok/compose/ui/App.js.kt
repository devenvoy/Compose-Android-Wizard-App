package io.github.terrakok.compose.ui


import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import io.github.terrakok.compose.wizardAndroid.buildFiles
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get


internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}

actual fun saveZipFile(name: String, bytes: ByteArray) {
    // Convert ByteArray to Uint8Array
    val uint8Array = js("new Uint8Array(bytes.length)")
    for (i in bytes.indices) {
        uint8Array[i] = bytes[i]
    }

    val blob = Blob(arrayOf(uint8Array), BlobPropertyBag(type = "application/zip"))
    val url = URL.createObjectURL(blob)

    val a = document.createElement("a")
    a.setAttribute("href", url)
    a.setAttribute("download", "$name.zip")
    document.body?.appendChild(a)
    a.asDynamic().click()
    document.body?.removeChild(a)
    URL.revokeObjectURL(url)
}

@JsModule("fflate")
@JsNonModule
external object Fflate {
    fun zipSync(files: dynamic): Uint8Array
}

actual fun generateAndroidZip(projectInfo: AndroidProjectInfo): ByteArray {
    val files = projectInfo.buildFiles()
    val zipInput = js("{}")

    files.forEach { file ->
        zipInput[file.path] = file.content
    }

    val result: Uint8Array = Fflate.zipSync(zipInput)

    // Convert Uint8Array to ByteArray
    val byteArray = ByteArray(result.length)
    for (i in 0 until result.length) {
        byteArray[i] = result[i]
    }
    return byteArray
}
