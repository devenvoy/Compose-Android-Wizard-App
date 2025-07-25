package io.github.terrakok.compose.ui

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal actual fun openUrl(url: String?) {
    val nsUrl = url?.let { NSURL.URLWithString(it) } ?: return
    UIApplication.sharedApplication.openURL(nsUrl)
}

actual fun generateAndroidZip(projectInfo: AndroidProjectInfo): ByteArray {
    return ByteArray(10)
}

actual fun saveZipFile(name: String, bytes: ByteArray) {
}