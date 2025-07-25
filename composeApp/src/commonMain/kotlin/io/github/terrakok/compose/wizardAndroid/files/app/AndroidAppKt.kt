package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.HiltGroup
import io.github.terrakok.compose.wizardAndroid.ProjectFile

class AndroidAppKt(info: AndroidProjectInfo) : ProjectFile {
    override val path = "app/src/main/java/${info.packagePath}/App.kt"
    override val content = """
        package ${info.packageId}

        import android.app.Application
        ${if (info.dependencies.contains(HiltGroup)) "import dagger.hilt.android.HiltAndroidApp" else ""}
        
        ${if (info.dependencies.contains(HiltGroup)) "@HiltAndroidApp" else ""}
        class ${info.safeName}App : Application() {
            companion object {
                lateinit var INSTANCE: ${info.safeName}App
            }

            override fun onCreate() {
                super.onCreate()
                INSTANCE = this
            }
        }
        
    """.trimIndent()
}

class MainActivityKt(info: AndroidProjectInfo) : ProjectFile {
    override val path = "app/src/main/java/${info.packagePath}/MainActivity.kt"
    override val content = """
        package ${info.packageId}

        import android.os.Bundle
        import androidx.activity.ComponentActivity
        import androidx.activity.compose.setContent
        import androidx.activity.enableEdgeToEdge
        import androidx.compose.foundation.layout.fillMaxSize
        import androidx.compose.foundation.layout.padding
        import androidx.compose.material3.Scaffold
        import androidx.compose.material3.Text
        import androidx.compose.runtime.Composable
        import androidx.compose.ui.Modifier
        import androidx.compose.ui.tooling.preview.Preview
        import ${info.packageId}.ui.theme.AndroidAppTheme
        ${if (info.dependencies.contains(HiltGroup)) "import dagger.hilt.android.AndroidEntryPoint" else ""}
        
        ${if (info.dependencies.contains(HiltGroup)) "@AndroidEntryPoint" else ""}        
        class MainActivity : ComponentActivity() {
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                setContent {
                    ${info.safeName}Theme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Greeting(
                                name = "Android",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
        
        @Composable
        fun Greeting(name: String, modifier: Modifier = Modifier) {
            Text(
                text = "Hello ${'$'}name!",
                modifier = modifier
            )
        }
        
        @Preview(showBackground = true)
        @Composable
        fun GreetingPreview() {
           ${info.safeName}Theme {
                Greeting("Android")
            }
        }
    """.trimIndent()
}
