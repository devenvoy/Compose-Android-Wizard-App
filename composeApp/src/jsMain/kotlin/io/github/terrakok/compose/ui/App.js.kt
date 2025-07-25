package io.github.terrakok.compose.ui


import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.ProjectFile
import io.github.terrakok.compose.wizardAndroid.buildFiles
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.await
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import kotlin.js.Promise


internal actual fun openUrl(url: String?) {
    url?.let { window.open(it) }
}

actual fun saveZipFile(name: String, bytes: ByteArray) {
    // Convert ByteArray to Uint8Array
    console.log("3")
    val uint8Array = js("new Uint8Array(bytes.length)")
    for (i in bytes.indices) {
        uint8Array[i] = bytes[i]
    }
    console.log("4")
    val blob = Blob(arrayOf(uint8Array), BlobPropertyBag(type = "application/zip"))
    val url = URL.createObjectURL(blob)
    val a = document.createElement("a")
    a.setAttribute("href", url)
    a.setAttribute("download", "$name.zip")
    console.log("5")
    document.body?.appendChild(a)
    a.asDynamic().click()
    document.body?.removeChild(a)
    URL.revokeObjectURL(url)
}

@JsModule("jszip")
@JsNonModule
external class JSZip {
    fun file(path: String, data: String): JSZip
    fun generateAsync(options: dynamic): Promise<Uint8Array>

    companion object {
        operator fun invoke(): JSZip
    }
}

suspend fun generateZip(files: List<ProjectFile>): ByteArray {
    val zip = JSZip()
    files.forEach { zip.file(it.path, it.content) }

    val uint8Array = zip.generateAsync(js("{ type: 'uint8array' }")).await()

    val result = ByteArray(uint8Array.length)
    for (i in result.indices) {
        result[i] = uint8Array[i]
    }
    return result
}

@OptIn(ExperimentalCoroutinesApi::class)
actual suspend fun generateAndroidZip(projectInfo: AndroidProjectInfo): ByteArray {
    return generateZip(projectInfo.buildFiles())
}
