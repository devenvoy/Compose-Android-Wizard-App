package io.github.terrakok.compose.wizardAndroid

import io.github.terrakok.compose.wizard.ProjectFile
import io.github.terrakok.compose.wizardAndroid.files.Gitignore
import io.github.terrakok.compose.wizardAndroid.files.GradleBat
import io.github.terrakok.compose.wizardAndroid.files.GradleLibsVersion
import io.github.terrakok.compose.wizardAndroid.files.GradleProperties
import io.github.terrakok.compose.wizardAndroid.files.GradleWrapperJar
import io.github.terrakok.compose.wizardAndroid.files.GradleWrapperProperties
import io.github.terrakok.compose.wizardAndroid.files.Gradlew
import io.github.terrakok.compose.wizardAndroid.files.Readme
import io.github.terrakok.compose.wizardAndroid.files.RootBuildGradleKts
import io.github.terrakok.compose.wizardAndroid.files.SettingsGradleKts
import io.github.terrakok.compose.wizardAndroid.files.app.AndroidAppKt
import io.github.terrakok.compose.wizardAndroid.files.app.AndroidManifest
import io.github.terrakok.compose.wizardAndroid.files.app.AppBuildGradleKts
import io.github.terrakok.compose.wizardAndroid.files.app.ColorKt
import io.github.terrakok.compose.wizardAndroid.files.app.ColorResource
import io.github.terrakok.compose.wizardAndroid.files.app.DimenResource
import io.github.terrakok.compose.wizardAndroid.files.app.DrawableResource
import io.github.terrakok.compose.wizardAndroid.files.app.MainActivityKt
import io.github.terrakok.compose.wizardAndroid.files.app.StringResource
import io.github.terrakok.compose.wizardAndroid.files.app.ThemeKt
import io.github.terrakok.compose.wizardAndroid.files.app.ThemeNightResource
import io.github.terrakok.compose.wizardAndroid.files.app.ThemeResource


fun AndroidProjectInfo.buildFiles(): List<ProjectFile> = buildList {
    // General project files
    add(Gitignore())
    add(Readme(this@buildFiles))
    add(GradleBat())
    add(Gradlew())
    add(GradleWrapperProperties(this@buildFiles))
    add(GradleWrapperJar())
    add(GradleLibsVersion(this@buildFiles))
    add(GradleProperties())

    // Root Gradle build setup
    add(RootBuildGradleKts(this@buildFiles))
    add(SettingsGradleKts(this@buildFiles))

    // App module files
    add(AppBuildGradleKts(this@buildFiles))
    add(ColorKt(this@buildFiles))
    add(ThemeKt(this@buildFiles))
    add(AndroidAppKt(this@buildFiles))

    add(DrawableResource())
    add(ColorResource())
    add(DimenResource())
    add(StringResource(this@buildFiles))
    add(ThemeResource(this@buildFiles))
    add(ThemeNightResource(this@buildFiles))

    /*    if (dependencies.contains(ApolloPlugin)) {
            add(GraphQLSchema())
            add(GraphQLQuery())
        }
    */

    add(AndroidManifest(this@buildFiles))
    add(MainActivityKt(this@buildFiles))
}
