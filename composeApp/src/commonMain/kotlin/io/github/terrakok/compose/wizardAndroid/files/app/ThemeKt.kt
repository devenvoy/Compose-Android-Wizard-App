package io.github.terrakok.compose.wizardAndroid.files.app

import io.github.terrakok.compose.wizardAndroid.ProjectFile
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo

class ThemeKt(info: AndroidProjectInfo) : ProjectFile {
    override val path = "app/src/main/java/${info.packagePath}/ui/theme/Theme.kt"
    override val content = """
        package ${info.packageId}

        import android.os.Build
        import androidx.compose.foundation.isSystemInDarkTheme
        import androidx.compose.material3.MaterialTheme
        import androidx.compose.material3.darkColorScheme
        import androidx.compose.material3.dynamicDarkColorScheme
        import androidx.compose.material3.dynamicLightColorScheme
        import androidx.compose.material3.lightColorScheme
        import androidx.compose.runtime.Composable
        import androidx.compose.ui.platform.LocalContext

        private val LightColors = lightColors(
            primary = md_theme_light_primary,
            onPrimary = md_theme_light_onPrimary,
            secondary = md_theme_light_secondary,
            onSecondary = md_theme_light_onSecondary,
            error = md_theme_light_error,
            onError = md_theme_light_onError,
            background = md_theme_light_background,
            onBackground = md_theme_light_onBackground,
            surface = md_theme_light_surface,
            onSurface = md_theme_light_onSurface,
        )

        private val DarkColors = darkColors(
            primary = md_theme_dark_primary,
            onPrimary = md_theme_dark_onPrimary,
            secondary = md_theme_dark_secondary,
            onSecondary = md_theme_dark_onSecondary,
            error = md_theme_dark_error,
            onError = md_theme_dark_onError,
            background = md_theme_dark_background,
            onBackground = md_theme_dark_onBackground,
            surface = md_theme_dark_surface,
            onSurface = md_theme_dark_onSurface,
        )

        @Composable
        internal fun ${info.safeName}Theme(
            useDarkTheme: Boolean = isSystemInDarkTheme(),
             // Dynamic color is available on Android 12+
            dynamicColor: Boolean = true,
            content: @Composable () -> Unit
        ) {
            val colorScheme = when {
                dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                    val context = LocalContext.current
                    if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
                }   

                darkTheme -> DarkColorScheme
                else -> LightColorScheme
            }
    
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                content = content
            )
        }

    """.trimIndent()
}