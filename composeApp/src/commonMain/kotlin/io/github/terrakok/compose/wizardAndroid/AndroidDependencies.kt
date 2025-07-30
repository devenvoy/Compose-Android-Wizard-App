package io.github.terrakok.compose.wizardAndroid


val androidVersionPresets = listOf(
    AndroidVersionPreset(
        name = "Latest Stable (Narwhal)",
        kotlinVersion = "2.2.0",
        composeVersion = "1.8.2",
        composeBomVersion = "2024.10.00",
        gradleVersion = "8.13.0",
        agpVersion = "8.11.1",
        kspVersion = "2.2.0-2.0.2"
    ),
    AndroidVersionPreset(
        name = "Android Studio Meerkat Compatible",
        kotlinVersion = "2.0.0",
        composeVersion = "1.5.4",
        gradleVersion = "8.9",
        composeBomVersion = "2024.10.00",
        agpVersion = "8.7.3",
        kspVersion = "2.0.10-1.0.24"
    ),
    AndroidVersionPreset(
        name = "Android Studio LadyBug Compatible",
        kotlinVersion = "1.9.20",
        composeVersion = "1.5.5",
        composeBomVersion = "2024.10.00",
        gradleVersion = "6.8.3",
        agpVersion = "8.6.2",
        kspVersion = "1.9.20‑1.0.14"
    )
)


val androidxCoreKtx = AndroidDependency(
    title = "AndroidX Core KTX",
    description = "Kotlin extensions for Android core libraries",
    url = "https://developer.android.com/jetpack/androidx/releases/core",
    group = "androidx.core",
    id = "core-ktx",
    version = "1.16.0",
    catalogVersionName = "coreKtx",
    catalogName = "androidx-core-ktx"
)

val junit = AndroidDependency(
    title = "JUnit",
    description = "JUnit 4 testing framework",
    url = "https://junit.org/junit4/",
    group = "junit",
    id = "junit",
    version = "4.13.2",
    catalogVersionName = "junit",
    catalogName = "junit"
)

val androidxJunit = AndroidDependency(
    title = "AndroidX JUnit",
    description = "AndroidX extension for JUnit",
    url = "https://developer.android.com/jetpack/androidx/releases/test",
    group = "androidx.test.ext",
    id = "junit",
    version = "1.2.1",
    catalogVersionName = "junitVersion",
    catalogName = "androidx-junit"
)

val espressoCore = AndroidDependency(
    title = "Espresso Core",
    description = "UI testing with Espresso",
    url = "https://developer.android.com/training/testing/espresso",
    group = "androidx.test.espresso",
    id = "espresso-core",
    version = "3.6.1",
    catalogVersionName = "espressoCore",
    catalogName = "androidx-espresso-core"
)

val lifecycleRuntimeKtx = AndroidDependency(
    title = "Lifecycle Runtime KTX",
    description = "KTX extensions for lifecycle-runtime",
    url = "https://developer.android.com/jetpack/androidx/releases/lifecycle",
    group = "androidx.lifecycle",
    id = "lifecycle-runtime-ktx",
    version = "2.9.2",
    catalogVersionName = "lifecycleRuntimeKtx",
    catalogName = "androidx-lifecycle-runtime-ktx"
)

val activityCompose = AndroidDependency(
    title = "Activity Compose",
    description = "Jetpack Compose support in activities",
    url = "https://developer.android.com/jetpack/androidx/releases/activity",
    group = "androidx.activity",
    id = "activity-compose",
    version = "1.10.1",
    catalogVersionName = "activityCompose",
    catalogName = "androidx-activity-compose"
)

val composeBom = AndroidDependency(
    title = "Compose BOM",
    description = "Bill of Materials for Compose",
    url = "https://developer.android.com/jetpack/compose/bom",
    group = "androidx.compose",
    id = "compose-bom",
    version = "2025.07.00",
    catalogVersionName = "composeBom",
    isPackage = true,
    catalogName = "androidx-compose-bom"
)

val composeUi = AndroidDependency(
    title = "Compose UI",
    description = "Core UI components for Compose",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-ui",
    group = "androidx.compose.ui",
    id = "ui",
    version = "1.8.3",
    catalogVersionName = "",
    catalogName = "androidx-ui"
)

val composeUiGraphics = AndroidDependency(
    title = "Compose UI Graphics",
    description = "Graphics API for Compose",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-ui",
    group = "androidx.compose.ui",
    id = "ui-graphics",
    version = "1.8.3",
    catalogVersionName = "",
    catalogName = "androidx-ui-graphics"
)

val composeUiTooling = AndroidDependency(
    title = "Compose UI Tooling",
    description = "Tooling support for Compose preview",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-ui",
    group = "androidx.compose.ui",
    id = "ui-tooling",
    version = "1.8.3",
    catalogVersionName = "",
    catalogName = "androidx-ui-tooling"
)

val composeUiToolingPreview = AndroidDependency(
    title = "Compose UI Tooling Preview",
    description = "Preview composables during design time",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-ui",
    group = "androidx.compose.ui",
    id = "ui-tooling-preview",
    version = "1.8.3",
    catalogVersionName = "",
    catalogName = "androidx-ui-tooling-preview"
)

val composeUiTestManifest = AndroidDependency(
    title = "Compose UI Test Manifest",
    description = "Test manifest support for Compose UI testing",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-ui",
    group = "androidx.compose.ui",
    id = "ui-test-manifest",
    version = "1.8.3",
    catalogVersionName = "",
    catalogName = "androidx-ui-test-manifest"
)

val composeUiTestJunit4 = AndroidDependency(
    title = "Compose UI Test JUnit4",
    description = "JUnit4 support for Compose UI testing",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-ui",
    group = "androidx.compose.ui",
    id = "ui-test-junit4",
    version = "1.8.3",
    catalogVersionName = "",
    catalogName = "androidx-ui-test-junit4"
)

val material3 = AndroidDependency(
    title = "Material3",
    description = "Jetpack Compose Material Design 3 UI components",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-material3",
    group = "androidx.compose.material3",
    id = "material3",
    version = "1.3.2",
    catalogVersionName = "material",
    catalogName = "androidx-material3"
)

val materialIconsExtended = AndroidDependency(
    title = "Material Icons Extended",
    description = "Extended Material icons for Compose",
    url = "https://developer.android.com/jetpack/androidx/releases/compose-material",
    group = "androidx.compose.material",
    id = "material-icons-extended",
    version = "1.7.8",
    catalogVersionName = "material-icons-extended",
    catalogName = "androidx-material-icons-extended"
)

val splashscreen = AndroidDependency(
    title = "Splashscreen API",
    description = "Modern splash screen for Android 12+",
    url = "https://developer.android.com/jetpack/androidx/releases/core",
    group = "androidx.core",
    id = "core-splashscreen",
    version = "1.0.1",
    catalogVersionName = "splashscreen",
    catalogName = "androidx-core-splashscreen"
)

val constraintLayoutCompose = AndroidDependency(
    title = "ConstraintLayout for Compose",
    description = "ConstraintLayout support in Compose",
    url = "https://developer.android.com/jetpack/androidx/releases/constraintlayout",
    group = "androidx.constraintlayout",
    id = "constraintlayout-compose",
    version = "1.1.1",
    catalogVersionName = "constraint-layout-compose",
    catalogName = "androidx-constraintlayout-compose"
)

val multidex = AndroidDependency(
    title = "Multidex",
    description = "Support for multidex apps",
    url = "https://developer.android.com/studio/build/multidex",
    group = "androidx.multidex",
    id = "multidex",
    version = "2.0.1",
    catalogVersionName = "multidex",
    catalogName = "androidx-multidex"
)

val navigationCompose = AndroidDependency(
    title = "Navigation Compose",
    description = "Navigation component for Compose",
    url = "https://developer.android.com/jetpack/compose/navigation",
    group = "androidx.navigation",
    id = "navigation-compose",
    version = "2.9.1",
    catalogVersionName = "navigation-compose",
    catalogName = "androidx-navigation-compose"
)

val navigationRuntimeKtx = AndroidDependency(
    title = "Navigation Runtime KTX",
    description = "Core runtime for Jetpack Navigation",
    url = "https://developer.android.com/jetpack/androidx/releases/navigation",
    group = "androidx.navigation",
    id = "navigation-runtime-ktx",
    version = "2.9.1",
    catalogVersionName = "navigation-runtime-ktx",
    catalogName = "androidx-navigation-runtime-ktx"
)

val hiltNavigationCompose = AndroidDependency(
    title = "Hilt Navigation Compose",
    description = "Hilt support for Compose Navigation",
    url = "https://developer.android.com/jetpack/androidx/releases/hilt",
    group = "androidx.hilt",
    id = "hilt-navigation-compose",
    version = "1.2.0",
    catalogVersionName = "hilt-navigation-compose",
    catalogName = "androidx-hilt-navigation-compose"
)

val pagingCompose = AndroidDependency(
    title = "Paging Compose",
    description = "Compose integration with Paging 3",
    url = "https://developer.android.com/jetpack/androidx/releases/paging",
    group = "androidx.paging",
    id = "paging-compose",
    version = "3.3.6",
    catalogVersionName = "paging-compose",
    catalogName = "androidx-paging-compose"
)

val retrofit = AndroidDependency(
    title = "Retrofit",
    description = "Type-safe HTTP client for Android",
    url = "https://square.github.io/retrofit/",
    group = "com.squareup.retrofit2",
    id = "retrofit",
    version = "3.0.0",
    catalogVersionName = "retrofit",
    catalogName = "retrofit"
)

val gsonConverter = AndroidDependency(
    title = "Gson Converter",
    description = "Gson converter for Retrofit",
    url = "https://github.com/square/retrofit",
    group = "com.squareup.retrofit2",
    id = "converter-gson",
    version = "3.0.0",
    catalogVersionName = "retrofit",
    catalogName = "converter-gson"
)

val loggingInterceptor = AndroidDependency(
    title = "Logging Interceptor",
    description = "OkHttp logging",
    url = "https://square.github.io/okhttp/",
    group = "com.squareup.okhttp3",
    id = "logging-interceptor",
    version = "5.1.0",
    catalogVersionName = "logging-interceptor",
    catalogName = "logging-interceptor"
)

val gson = AndroidDependency(
    title = "Gson",
    description = "JSON serialization/deserialization",
    url = "https://github.com/google/gson",
    group = "com.google.code.gson",
    id = "gson",
    version = "2.13.1",
    catalogVersionName = "gson",
    catalogName = "gson"
)

val landscapistCoil = AndroidDependency(
    title = "Landscapist Coil",
    description = "Image loading via Coil",
    url = "https://github.com/skydoves/landscapist",
    group = "com.github.skydoves",
    id = "landscapist-coil",
    version = "2.5.1",
    catalogVersionName = "landscapist",
    catalogName = "landscapist-coil"
)

val landscapistPlaceholder = AndroidDependency(
    title = "Landscapist Placeholder",
    description = "Image placeholder support",
    url = "https://github.com/skydoves/landscapist",
    group = "com.github.skydoves",
    id = "landscapist-placeholder",
    version = "2.5.1",
    catalogVersionName = "landscapist",
    catalogName = "landscapist-placeholder"
)

val landscapistAnimation = AndroidDependency(
    title = "Landscapist Animation",
    description = "Image animation support",
    url = "https://github.com/skydoves/landscapist",
    group = "com.github.skydoves",
    id = "landscapist-animation",
    version = "2.5.1",
    catalogVersionName = "landscapist",
    catalogName = "landscapist-animation"
)

val timber = AndroidDependency(
    title = "Timber",
    description = "Lightweight logger for Android",
    url = "https://github.com/JakeWharton/timber",
    group = "com.jakewharton.timber",
    id = "timber",
    version = "5.0.1",
    catalogVersionName = "timber",
    catalogName = "timber"
)

val roomRuntime = AndroidDependency(
    title = "Room Runtime",
    description = "Room persistence library",
    url = "https://developer.android.com/jetpack/androidx/releases/room",
    group = "androidx.room",
    id = "room-runtime",
    version = "2.7.2",
    catalogVersionName = "room",
    catalogName = "room-runtime"
)

val roomKtx = AndroidDependency(
    title = "Room KTX",
    description = "KTX extensions for Room",
    url = "https://developer.android.com/jetpack/androidx/releases/room",
    group = "androidx.room",
    id = "room-ktx",
    version = "2.7.2",
    catalogVersionName = "room",
    catalogName = "room-ktx"
)

val roomCompiler = AndroidDependency(
    title = "Room Compiler",
    description = "Annotation processor for Room",
    url = "https://developer.android.com/jetpack/androidx/releases/room",
    group = "androidx.room",
    id = "room-compiler",
    version = "2.7.2",
    catalogVersionName = "room",
    isCompiler = true,
    catalogName = "room-compiler"
)

val hiltAndroid = AndroidDependency(
    title = "Hilt Android",
    description = "Dependency injection library for Android by Google.",
    url = "https://dagger.dev/hilt/",
    group = "com.google.dagger",
    id = "hilt-android",
    version = "2.56",
    catalogVersionName = "hilt",
    catalogName = "hilt-android",
    isPlugin = false
)

val hiltCompiler = AndroidDependency(
    title = "Hilt Compiler (Google)",
    description = "Annotation processor for Hilt by Google.",
    url = "https://dagger.dev/hilt/compiler.html",
    group = "com.google.dagger",
    id = "hilt-compiler",
    version = "2.56",
    catalogVersionName = "hilt",
    catalogName = "hilt-compiler",
    isCompiler = true
)

val hiltPlugin = AndroidDependency(
    title = "Hilt Gradle Plugin",
    description = "Gradle plugin for integrating Hilt into your Android project.",
    url = "https://dagger.dev/hilt/gradle-setup.html",
    group = "com.google.dagger",
    id = "com.google.dagger.hilt.android",
    version = "2.56",
    catalogVersionName = "hilt",
    catalogName = "hilt-android",
    isPlugin = true
)

val hiltWork = AndroidDependency(
    title = "Hilt WorkManager Integration",
    description = "Hilt integration with WorkManager.",
    url = "https://developer.android.com/jetpack/androidx/releases/hilt",
    group = "androidx.hilt",
    id = "hilt-work",
    version = "1.2.0",
    catalogVersionName = "androidxHilt",
    catalogName = "androidx-hilt-work"
)

val hiltCompilerAndroidX = AndroidDependency(
    title = "Hilt Compiler (AndroidX)",
    description = "Annotation processor for Hilt AndroidX components.",
    url = "https://developer.android.com/jetpack/androidx/releases/hilt",
    group = "androidx.hilt",
    id = "hilt-compiler",
    version = "1.2.0",
    catalogVersionName = "androidxHilt",
    catalogName = "androidx-hilt-compiler",
    isCompiler = true
)

val navigationDependency = AndroidDependency(
    title = "Navigation Compose",
    description = "Jetpack Navigation for Compose",
    url = "https://developer.android.com/jetpack/compose/navigation",
    catalogName = "androidx-navigation",
    catalogVersionName = "navVersion",
    version = "2.8.3",
    group = "androidx.navigation",
    id = "navigation-compose"
)

val serializationDependency = AndroidDependency(
    title = "Kotlinx Serialization JSON",
    description = "Kotlinx JSON serialization",
    url = "https://github.com/Kotlin/kotlinx.serialization",
    catalogName = "kotlinx-serialization",
    catalogVersionName = "kotlinSerialization",
    version = "1.7.3",
    group = "org.jetbrains.kotlinx",
    id = "kotlinx-serialization-json"
)

val CoilComposeDependency = AndroidDependency(
    title = "Coil Compose",
    description = "Image loading support for Jetpack Compose using Coil",
    url = "https://github.com/coil-kt/coil",
    group = "io.coil-kt.coil3",
    id = "coil-compose",
    version = "3.3.0",
    catalogVersionName = "coil3Version",
    catalogName = "coil-compose"
)

val CoilNetworkOkHttpDependency = AndroidDependency(
    title = "Coil Network OkHttp",
    description = "Coil network support using OkHttp",
    url = "https://github.com/coil-kt/coil",
    group = "io.coil-kt.coil3",
    id = "coil-network-okhttp",
    version = "3.3.0",
    catalogVersionName = "coil3Version",
    catalogName = "coil-network-okhttp"
)

val CoilCoreDependency = AndroidDependency(
    title = "Coil Core",
    description = "Core image loading library using Coil",
    url = "https://github.com/coil-kt/coil",
    group = "io.coil-kt.coil3",
    id = "coil",
    version = "3.3.0",
    catalogVersionName = "coil3Version",
    catalogName = "coil"
)


//------------------------------------------------


val CoreGroup = GroupedAndroidDependency(
    title = "Core",
    items = listOf(
        androidxCoreKtx,
        lifecycleRuntimeKtx,
        activityCompose
    )
)

val ComposeUIGroup = GroupedAndroidDependency(
    title = "Compose UI",
    items = listOf(
        composeBom,
        composeUi,
        composeUiGraphics,
        composeUiTooling,
        composeUiToolingPreview
    )
)

val MaterialAndSplashGroup = GroupedAndroidDependency(
    title = "Material & Splash",
    items = listOf(material3,splashscreen, materialIconsExtended)
)

val MultidexConstraintGroup = GroupedAndroidDependency(
    title = "Constraint & MultiDex",
    items = listOf(constraintLayoutCompose,multidex)
)

val NavigationGroup = GroupedAndroidDependency(
    title = "Navigation",
    items = listOf(navigationCompose, navigationRuntimeKtx)
)

val TypeSafeNavGroup = GroupedAndroidDependency(
    title = "Navigation (type-safe) & Serialization",
    items = listOf(navigationDependency, serializationDependency)
)

val PagingGroup = GroupedAndroidDependency(
    title = "Pagination",
    items = listOf(pagingCompose)
)

val LoggingGroup = GroupedAndroidDependency(
    title = "Logging",
    items = listOf(loggingInterceptor,timber)
)

val TestGroup = GroupedAndroidDependency(
    title = "Testing",
    items = listOf(
        junit, androidxJunit, espressoCore,
        composeUiTestManifest,
        composeUiTestJunit4
    )
)

val RoomDBGroup = GroupedAndroidDependency(
    title = "Room",
    items = listOf(roomRuntime, roomKtx, roomCompiler)
)

val RetrofitGroup = GroupedAndroidDependency(
    title = "Retrofit",
    items = listOf(retrofit, gsonConverter)
)

val LandscapistGroup = GroupedAndroidDependency(
    title = "Landscapist",
    items = listOf(landscapistCoil, landscapistPlaceholder, landscapistAnimation)
)

val HiltGroup = GroupedAndroidDependency(
    title = "Hilt (DI)",
    items = listOf(
        hiltAndroid,
        hiltCompiler,
        hiltPlugin,
        hiltNavigationCompose
//        hiltWork,
//        hiltCompilerAndroidX,
    )
)

val CoilGroup = GroupedAndroidDependency(
    title = "Coil Image Loader",
    items = listOf(
        CoilComposeDependency,
        CoilNetworkOkHttpDependency,
        CoilCoreDependency
    )
)
