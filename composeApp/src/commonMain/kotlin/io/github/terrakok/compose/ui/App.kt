package io.github.terrakok.compose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.android
import compose_multiplatform_wizard.composeapp.generated.resources.arrow_circle_down
import io.github.terrakok.compose.wizard.ApolloPlugin
import io.github.terrakok.compose.wizard.BuildConfigPlugin
import io.github.terrakok.compose.wizard.ComposeIcons
import io.github.terrakok.compose.wizard.ImageLoader
import io.github.terrakok.compose.wizard.KStore
import io.github.terrakok.compose.wizard.Koin
import io.github.terrakok.compose.wizard.KotlinxCoroutinesCore
import io.github.terrakok.compose.wizard.KotlinxDateTime
import io.github.terrakok.compose.wizard.KotlinxSerializationJson
import io.github.terrakok.compose.wizard.KtorCore
import io.github.terrakok.compose.wizard.LibresCompose
import io.github.terrakok.compose.wizard.MultiplatformSettings
import io.github.terrakok.compose.wizard.Napier
import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizard.SQLDelightPlugin
import io.github.terrakok.compose.wizard.Voyager
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.CoilGroup
import io.github.terrakok.compose.wizardAndroid.ComposeUIGroup
import io.github.terrakok.compose.wizardAndroid.CoreGroup
import io.github.terrakok.compose.wizardAndroid.HiltGroup
import io.github.terrakok.compose.wizardAndroid.LandscapistGroup
import io.github.terrakok.compose.wizardAndroid.LoggingGroup
import io.github.terrakok.compose.wizardAndroid.MaterialAndSplashGroup
import io.github.terrakok.compose.wizardAndroid.MultidexConstraintGroup
import io.github.terrakok.compose.wizardAndroid.NavigationGroup
import io.github.terrakok.compose.wizardAndroid.PagingGroup
import io.github.terrakok.compose.wizardAndroid.RetrofitGroup
import io.github.terrakok.compose.wizardAndroid.RoomDBGroup
import io.github.terrakok.compose.wizardAndroid.TestGroup
import io.github.terrakok.compose.wizardAndroid.TypeSafeNavGroup
import io.github.terrakok.compose.wizardAndroid.androidVersionPresets
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

val LocalShowVersions = compositionLocalOf { mutableStateOf(false) }

@Composable
fun App() {
    val systemTheme = isSystemInDarkTheme()
    val isDark = remember { mutableStateOf(systemTheme) }
    val isMultiplatform = remember { mutableStateOf(true) }

    AppTheme(isDark) {
        CompositionLocalProvider(
            LocalShowVersions provides mutableStateOf(false)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .verticalScroll(rememberScrollState()),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                ) {
                    if (isMultiplatform.value) {
                        ComposeMultiPlatformApp()
                    } else {
                        AndroidPlatformApp()
                    }
                }

                TopMenu(
                    isDark = isDark,
                    isMultiplatform = isMultiplatform,
                    onDownloadClick = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeMultiPlatformApp() {
    Column(
        modifier = Modifier
            .width(1080.dp)
            .requiredWidthIn(min = 420.dp)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(Modifier.size(20.dp))

        val default = ProjectInfo()
        var projectNameState by remember { mutableStateOf(default.name) }
        var projectIdState by remember { mutableStateOf(default.packageId) }

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            value = projectNameState,
            onValueChange = { projectNameState = it },
            label = { Text("Project name") }
        )
        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            value = projectIdState,
            onValueChange = { projectIdState = it },
            label = { Text("Project ID") }
        )
        Spacer(Modifier.size(20.dp))

        val androidState = remember { mutableStateOf(true) }
        val iosState = remember { mutableStateOf(true) }
        val desktopState = remember { mutableStateOf(true) }
        val browserState = remember { mutableStateOf(true) }
        ComposeTargetGroup(androidState, iosState, desktopState, browserState)
        Spacer(Modifier.size(20.dp))

//                    VersionsTable(default)

        val deps = listOf(
            Napier to mutableStateOf(true),
            LibresCompose to mutableStateOf(true),
            Voyager to mutableStateOf(true),
            ImageLoader to mutableStateOf(true),
            KotlinxCoroutinesCore to mutableStateOf(true),
            BuildConfigPlugin to mutableStateOf(true),
            KtorCore to mutableStateOf(false),
            ComposeIcons to mutableStateOf(false),
            KotlinxSerializationJson to mutableStateOf(false),
            KotlinxDateTime to mutableStateOf(false),
            MultiplatformSettings to mutableStateOf(false),
            Koin to mutableStateOf(false),
            KStore to mutableStateOf(false),
            SQLDelightPlugin to mutableStateOf(false),
            ApolloPlugin to mutableStateOf(false),
        )

        SimpleGrid(
            modifier = Modifier,
            columnWidth = 300.dp,
            itemCount = deps.size
        ) {
            val (dep, state) = deps[it]
            DependencyCard(dependency = dep, selected = state)
        }
        Spacer(Modifier.size(20.dp))

        val isAndroid by androidState
        val isIos by iosState
        val isDesktop by desktopState
        val isBrowser by browserState

        val scope = rememberCoroutineScope()
        val isReady = (isAndroid || isIos || isDesktop || isBrowser)
                && projectNameState.isNotBlank() && projectIdState.isNotBlank()
        Button(
            enabled = isReady,
            onClick = {
                scope.launch(Dispatchers.Default) {
                    val zipBytes =
                        generateZip(
                            default.copy(
                                name = projectNameState,
                                packageId = projectIdState,
                                dependencies = deps.mapNotNull { if (it.second.value) it.first else null }
                            .toSet()))
                    saveZipFile(default.name, zipBytes)
                }
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.arrow_circle_down),
                colorFilter = ColorFilter.tint(getContentColor()),
                contentDescription = null
            )
            Spacer(Modifier.size(10.dp))
            Text("Download")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidPlatformApp() {
    Column(
        modifier = Modifier
            .width(1080.dp)
            .requiredWidthIn(min = 420.dp)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(text = "Android Template Wizard", image = Res.drawable.android, tint = Color.Green)
        Spacer(Modifier.size(20.dp))

        var showVersionBottomSheet by remember { mutableStateOf(false) }
        var selectedPreset by remember { mutableStateOf(androidVersionPresets.first()) }

        val projectInfo = AndroidProjectInfo(
            kotlinVersion = selectedPreset.kotlinVersion,
            composeVersion = selectedPreset.composeVersion,
            gradleVersion = selectedPreset.gradleVersion,
            agpVersion = selectedPreset.agpVersion
        )
        var projectNameState by remember { mutableStateOf(projectInfo.name) }
        var projectIdState by remember { mutableStateOf(projectInfo.packageId) }

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            value = projectNameState,
            onValueChange = { projectNameState = it },
            label = { Text("Project name") }
        )
        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            value = projectIdState,
            onValueChange = { projectIdState = it },
            label = { Text("Project ID") }
        )
        Spacer(Modifier.size(20.dp))
        val isShowVersions by LocalShowVersions.current
        AnimatedVisibility(isShowVersions) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedButton(onClick = { showVersionBottomSheet = true }) {
                    Text(selectedPreset.name)
                    Spacer(Modifier.size(10.dp))
                    Image(
                        imageVector = Icons.Default.ArrowDropDown,
                        colorFilter = ColorFilter.tint(getContentColor()),
                        contentDescription = null
                    )
                }
                VersionsTable(projectInfo)
            }
        }

        if (showVersionBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showVersionBottomSheet = false },
                content = {
                    VersionSelectorBottomSheet(
                        selectedPreset = selectedPreset,
                        {
                            selectedPreset = it
                            showVersionBottomSheet = false
                        }
                    )
                }
            )
        }

        val androidDeps = listOf(
            CoreGroup to mutableStateOf(true),
            ComposeUIGroup to mutableStateOf(true),
            MaterialAndSplashGroup to mutableStateOf(true),
            NavigationGroup to mutableStateOf(true),
            TypeSafeNavGroup to mutableStateOf(true),
            RoomDBGroup to mutableStateOf(false),
            RetrofitGroup to mutableStateOf(false),
            HiltGroup to mutableStateOf(false),
            PagingGroup to mutableStateOf(false),
            CoilGroup to mutableStateOf(false),
            LoggingGroup to mutableStateOf(false),
            MultidexConstraintGroup to mutableStateOf(false),
            LandscapistGroup to mutableStateOf(false),
            TestGroup to mutableStateOf(true)
        )


        SimpleGrid(
            modifier = Modifier,
            columnWidth = 300.dp,
            itemCount = androidDeps.size
        ) {
            val (dep, state) = androidDeps[it]
            DependencyCard(dependency = dep, selected = state)
        }
        Spacer(Modifier.size(20.dp))


        val scope = rememberCoroutineScope()
        val isReady = projectNameState.isNotBlank() && projectIdState.isNotBlank()
        Button(
            enabled = isReady,
            onClick = {
                scope.launch(Dispatchers.Default) {
                    val zipBytes =
                        generateAndroidZip(
                            projectInfo.copy(
                                name = projectNameState,
                                packageId = projectIdState,
                                dependencies = androidDeps.mapNotNull { if (it.second.value) it.first else null }
                                    .toSet()))
                    saveZipFile(projectInfo.name, zipBytes)
                }
            }
        ) {
            Image(
                painter = painterResource(Res.drawable.arrow_circle_down),
                colorFilter = ColorFilter.tint(getContentColor()),
                contentDescription = null
            )
            Spacer(Modifier.size(10.dp))
            Text("Download")
        }
    }
}

@Composable
internal fun getContentColor() = LocalContentColor.current.copy(alpha = 1f)

internal expect fun openUrl(url: String?)

expect fun saveZipFile(name: String, bytes: ByteArray)

expect suspend fun generateAndroidZip(projectInfo: AndroidProjectInfo): ByteArray

expect suspend fun generateZip(projectInfo: ProjectInfo): ByteArray