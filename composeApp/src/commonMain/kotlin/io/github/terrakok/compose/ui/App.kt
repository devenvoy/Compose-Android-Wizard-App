package io.github.terrakok.compose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SplitButtonDefaults
import androidx.compose.material3.SplitButtonLayout
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.ic_android
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

val LocalShowVersions = compositionLocalOf { mutableStateOf(false) }

@Composable
fun App() {
    val systemTheme = isSystemInDarkTheme()
    val isDark = remember { mutableStateOf(systemTheme) }
    val isMultiplatform = remember { mutableStateOf(true) }
    val downloadButtonState = rememberButtonState()

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
                        ComposeMultiPlatformApp(downloadButtonState)
                    } else {
                        AndroidPlatformApp(downloadButtonState)
                    }
                }

                TextFieldState()

                TopMenu(
                    isDark = isDark,
                    isMultiplatform = isMultiplatform, downloadButtonState = downloadButtonState
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ComposeMultiPlatformApp(downloadButtonState: ButtonState) {
    Column(
        modifier = Modifier
            .width(1080.dp)
            .requiredWidthIn(min = 420.dp)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val default = ProjectInfo()
        val isShowVersions by LocalShowVersions.current
        var projectNameState by remember { mutableStateOf(default.name) }
        var projectIdState by remember { mutableStateOf(default.packageId) }
        val androidState = remember { mutableStateOf(true) }
        val iosState = remember { mutableStateOf(true) }
        val desktopState = remember { mutableStateOf(true) }
        val browserState = remember { mutableStateOf(true) }
        val deps = listOf(
            Napier to mutableStateOf(true),
            ImageLoader to mutableStateOf(true),
            KotlinxCoroutinesCore to mutableStateOf(true),
            BuildConfigPlugin to mutableStateOf(true),
            ComposeIcons to mutableStateOf(false),
            Voyager to mutableStateOf(false),
            KtorCore to mutableStateOf(false),
            KotlinxSerializationJson to mutableStateOf(false),
            KotlinxDateTime to mutableStateOf(false),
            MultiplatformSettings to mutableStateOf(false),
            Koin to mutableStateOf(false),
            KStore to mutableStateOf(false),
            SQLDelightPlugin to mutableStateOf(false),
            ApolloPlugin to mutableStateOf(false),
        )
        val isAndroid by androidState
        val isIos by iosState
        val isDesktop by desktopState
        val isBrowser by browserState

        val scope = rememberCoroutineScope()
        downloadButtonState.enabled = (isAndroid || isIos || isDesktop || isBrowser)
                && projectNameState.isNotBlank() && projectIdState.isNotBlank()

        downloadButtonState.onClick = {
            scope.launch(Dispatchers.Default) {
                val zipBytes = generateZip(
                    default.copy(
                    name = projectNameState,
                    packageId = projectIdState,
                    dependencies = deps.mapNotNull { if (it.second.value) it.first else null }
                        .toSet()))
                saveZipFile(default.name, zipBytes)
            }
        }

        Spacer(Modifier.size(40.dp))
        Header()
        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            shape = RoundedCornerShape(50),
            value = projectNameState,
            onValueChange = { projectNameState = it },
            label = { Text("Project name") })
        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            shape = RoundedCornerShape(50),
            value = projectIdState,
            onValueChange = { projectIdState = it },
            label = { Text("Project ID") })
        Spacer(Modifier.size(20.dp))

        ComposeTargetGroup(androidState, iosState, desktopState, browserState)
        Spacer(Modifier.size(20.dp))

        AnimatedVisibility(isShowVersions) {
            VersionsTable(
                AndroidProjectInfo(
                    default.packageId,
                    default.name,
                    default.gradleVersion,
                    default.kotlinVersion,
                    default.agpVersion,
                    default.androidMinSdk,
                    default.androidTargetSdk
                )
            )
        }

        SimpleGrid(
            modifier = Modifier, columnWidth = 300.dp, itemCount = deps.size
        ) {
            val (dep, state) = deps[it]
            DependencyCard(dependency = dep, selected = state)
        }

        Spacer(Modifier.size(100.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AndroidPlatformApp(downloadButtonState: ButtonState) {
    Column(
        modifier = Modifier
            .width(1080.dp)
            .requiredWidthIn(min = 420.dp)
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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

        val scope = rememberCoroutineScope()
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

        val isShowVersions by LocalShowVersions.current

        downloadButtonState.enabled = projectNameState.isNotBlank() && projectIdState.isNotBlank()
        downloadButtonState.onClick = {
            scope.launch(Dispatchers.Default) {
                val zipBytes = generateAndroidZip(
                    projectInfo.copy(
                        name = projectNameState,
                    packageId = projectIdState,
                    dependencies = androidDeps.mapNotNull { if (it.second.value) it.first else null }
                        .toSet()))
                saveZipFile(projectInfo.name, zipBytes)
            }
        }

        Spacer(Modifier.size(40.dp))
        Header(text = "Android Template Wizard", image = Res.drawable.ic_android)
        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            value = projectNameState, shape = RoundedCornerShape(50),
            onValueChange = { projectNameState = it },
            label = { Text("Project name") }
        )
        Spacer(Modifier.size(20.dp))

        OutlinedTextField(
            modifier = Modifier.width(480.dp),
            singleLine = true,
            value = projectIdState, shape = RoundedCornerShape(50),
            onValueChange = { projectIdState = it },
            label = { Text("Project ID") }
        )
        Spacer(Modifier.size(20.dp))

        AnimatedVisibility(isShowVersions) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SplitButtonLayout(leadingButton = {
                    SplitButtonDefaults.OutlinedLeadingButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Edit,
                            modifier = Modifier.size(SplitButtonDefaults.LeadingIconSize),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = "Localized description",
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text(selectedPreset.name)
                    }
                }, trailingButton = {
                    SplitButtonDefaults.TrailingButton(
                        checked = showVersionBottomSheet,
                        onCheckedChange = { showVersionBottomSheet = true },
                        modifier = Modifier.semantics {
                            stateDescription =
                                if (showVersionBottomSheet) "Expanded" else "Collapsed"
                        },
                    ) {
                        val rotation: Float by animateFloatAsState(
                            targetValue = if (showVersionBottomSheet) 180f else 0f,
                            label = "Trailing Icon Rotation",
                        )
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            modifier = Modifier.size(SplitButtonDefaults.TrailingIconSize)
                                .graphicsLayer {
                                    this.rotationZ = rotation
                                },
                            contentDescription = "Localized description",
                        )
                    }
                })
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

        SimpleGrid(
            modifier = Modifier,
            columnWidth = 300.dp,
            itemCount = androidDeps.size
        ) {
            val (dep, state) = androidDeps[it]
            DependencyCard(dependency = dep, selected = state)
        }

        Spacer(Modifier.size(100.dp))
    }
}

@Composable
internal fun getContentColor() = LocalContentColor.current.copy(alpha = 1f)

internal expect fun openUrl(url: String?)

expect fun saveZipFile(name: String, bytes: ByteArray)

expect suspend fun generateAndroidZip(projectInfo: AndroidProjectInfo): ByteArray

expect suspend fun generateZip(projectInfo: ProjectInfo): ByteArray