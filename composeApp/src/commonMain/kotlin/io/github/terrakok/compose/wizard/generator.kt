package io.github.terrakok.compose.wizard

import io.github.terrakok.compose.wizard.files.*
import io.github.terrakok.compose.wizard.files.app.*

fun ProjectInfo.buildFiles() = buildList {
    add(Gitignore())
    add(Readme(this@buildFiles))

    add(GradleBat())
    add(Gradlew())
    add(GradleWrapperProperties(this@buildFiles))
    add(GradleWrapperJar())
    add(GradleLibsVersion(this@buildFiles))

    add(GradleProperties())
    add(RootBuildGradleKts(this@buildFiles))
    add(SettingsGradleKts(this@buildFiles))

    add(ModuleBuildGradleKts(this@buildFiles))
    add(ColorKt(this@buildFiles))
    add(ThemeKt(this@buildFiles))
    add(AppKt(this@buildFiles))

    if (this@buildFiles.dependencies.contains(ApolloPlugin)) {
        add(GraphQLSchema())
        add(GraphQLQuery())
    }

    if (this@buildFiles.hasAndroid) {
        add(AndroidManifest(this@buildFiles))
        add(AndroidAppKt(this@buildFiles))
    }

    if (this@buildFiles.hasDesktop) {
        add(DesktopAppKt(this@buildFiles))
        add(DesktopMainKt(this@buildFiles))
    }

    if (this@buildFiles.hasIos) {
        add(Podspec(this@buildFiles))
        add(IosAppKt(this@buildFiles))
        add(IosMainKt(this@buildFiles))

        add(Podfile())
        add(IosAppIcon())
        add(IosAccentColor())
        add(IosAssets())
        add(IosPreviewAssets())
        add(IosAppSwift())
        add(IosXcworkspace())
        add(IosPbxproj(this@buildFiles))
    }

    if (this@buildFiles.hasBrowser) {
        add(BrowserAppKt(this@buildFiles))
        add(IndexHtml(this@buildFiles))
        add(BrowserMainKt(this@buildFiles))
        add(BrowserViewportWindowKt())
    }
}