package io.github.terrakok.compose.wizard.files.app

import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizard.ProjectInfo

class DesktopMainKt(info: ProjectInfo) : ProjectFile {
    override val path = "composeApp/src/desktopMain/kotlin/main.kt"
    override val content = """
        import androidx.compose.ui.unit.dp
        import androidx.compose.ui.window.Window
        import androidx.compose.ui.window.application
        import androidx.compose.ui.window.rememberWindowState
        import ${info.packageId}.App

        fun main() = application {
            Window(
                title = "${info.name}",
                state = rememberWindowState(width = 800.dp, height = 600.dp),
                onCloseRequest = ::exitApplication,
            ) { App() }
        }
    """.trimIndent()
}

class IosMainKt(info: ProjectInfo) : ProjectFile {
    override val path = "composeApp/src/iosMain/kotlin/main.kt"
    override val content = """
        import androidx.compose.ui.window.ComposeUIViewController
        import ${info.packageId}.App
        import platform.UIKit.UIViewController

        fun MainViewController(): UIViewController {
            return ComposeUIViewController { App() }
        }
        
    """.trimIndent()
}

class BrowserMainKt(info: ProjectInfo) : ProjectFile {
    override val path = "composeApp/src/jsMain/kotlin/main.kt"
    override val content = """
        import ${info.packageId}.App
        import org.jetbrains.skiko.wasm.onWasmReady

        fun main() {
            onWasmReady {
                BrowserViewportWindow("${info.name}") {
                    App()
                }
            }
        }

    """.trimIndent()
}