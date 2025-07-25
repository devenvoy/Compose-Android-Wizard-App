import io.github.terrakok.compose.ui.AndroidApp
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("Android Compose App Wizard") {
            AndroidApp()
        }
    }
}
