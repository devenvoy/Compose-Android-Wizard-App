package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import io.github.skeptick.libres.compose.painterResource
import io.github.terrakok.compose.Res
import io.github.terrakok.compose.wizardAndroid.AndroidProjectInfo
import io.github.terrakok.compose.wizardAndroid.androidxJunit
import io.github.terrakok.compose.wizardAndroid.composeUiTooling
import io.github.terrakok.compose.wizardAndroid.constraintLayoutCompose
import io.github.terrakok.compose.wizardAndroid.espressoCore
import io.github.terrakok.compose.wizardAndroid.gson
import io.github.terrakok.compose.wizardAndroid.gsonConverter
import io.github.terrakok.compose.wizardAndroid.hiltNavigationCompose
import io.github.terrakok.compose.wizardAndroid.junit
import io.github.terrakok.compose.wizardAndroid.landscapistAnimation
import io.github.terrakok.compose.wizardAndroid.landscapistCoil
import io.github.terrakok.compose.wizardAndroid.landscapistPlaceholder
import io.github.terrakok.compose.wizardAndroid.loggingInterceptor
import io.github.terrakok.compose.wizardAndroid.materialIconsExtended
import io.github.terrakok.compose.wizardAndroid.multidex
import io.github.terrakok.compose.wizardAndroid.navigationCompose
import io.github.terrakok.compose.wizardAndroid.navigationRuntimeKtx
import io.github.terrakok.compose.wizardAndroid.pagingCompose
import io.github.terrakok.compose.wizardAndroid.retrofit
import io.github.terrakok.compose.wizardAndroid.roomCompiler
import io.github.terrakok.compose.wizardAndroid.roomKtx
import io.github.terrakok.compose.wizardAndroid.roomRuntime
import io.github.terrakok.compose.wizardAndroid.splashscreen
import io.github.terrakok.compose.wizardAndroid.timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AndroidApp() = AppTheme {
    CompositionLocalProvider(
        LocalShowVersions provides mutableStateOf(false)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.Center)
                    .width(1080.dp)
                    .requiredWidthIn(min = 580.dp)
            ) {
                TopMenu()
                Column(
                    modifier = Modifier.padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Header()
                    Spacer(Modifier.size(20.dp))

                    val default = AndroidProjectInfo()
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

                    VersionsTable(default)

                    val androidDeps = listOf(
                        junit to mutableStateOf(true),
                        androidxJunit to mutableStateOf(true),
                        espressoCore to mutableStateOf(true),
                        composeUiTooling to mutableStateOf(true),
//                        composeUiTestManifest to mutableStateOf(true),
//                        composeUiTestJunit4 to mutableStateOf(true),
//                        material3 to mutableStateOf(true),
                        materialIconsExtended to mutableStateOf(true),
                        splashscreen to mutableStateOf(true),
                        constraintLayoutCompose to mutableStateOf(true),
                        multidex to mutableStateOf(true),
                        navigationCompose to mutableStateOf(true),
                        navigationRuntimeKtx to mutableStateOf(true),
                        hiltNavigationCompose to mutableStateOf(true),
                        pagingCompose to mutableStateOf(true),
                        retrofit to mutableStateOf(true),
                        gsonConverter to mutableStateOf(true),
                        loggingInterceptor to mutableStateOf(true),
                        gson to mutableStateOf(true),
                        landscapistCoil to mutableStateOf(true),
                        landscapistPlaceholder to mutableStateOf(true),
                        landscapistAnimation to mutableStateOf(true),
                        timber to mutableStateOf(true),
                        roomRuntime to mutableStateOf(true),
                        roomKtx to mutableStateOf(true),
                        roomCompiler to mutableStateOf(true),
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
                            scope.launch(Dispatchers.Main) {
                                val zipBytes =
                                    generateAndroidZip(default.copy(dependencies = androidDeps.mapNotNull { if (it.second.value) it.first else null }
                                        .toSet()))
                                saveZipFile(default.name, zipBytes)
                            }
                        }
                    ) {
                        Image(
                            painter = painterResource(Res.image.arrow_circle_down),
                            colorFilter = ColorFilter.tint(getContentColor()),
                            contentDescription = null
                        )
                        Spacer(Modifier.size(10.dp))
                        Text("Download")
                    }
                }
            }
        }
    }
}