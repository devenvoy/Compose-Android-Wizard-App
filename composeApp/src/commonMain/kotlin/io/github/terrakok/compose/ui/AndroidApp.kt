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
import io.github.terrakok.compose.wizard.ProjectInfo
import io.github.terrakok.compose.wizardAndroid.*
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
                        androidxCoreKtx to mutableStateOf(true),
                        junit to mutableStateOf(true),
                        androidxJunit to mutableStateOf(true),
                        espressoCore to mutableStateOf(true),
                        lifecycleRuntimeKtx to mutableStateOf(true),
                        activityCompose to mutableStateOf(true),
                        composeBom to mutableStateOf(true),
                        composeUi to mutableStateOf(true),
                        composeUiGraphics to mutableStateOf(true),
                        composeUiTooling to mutableStateOf(true),
                        composeUiToolingPreview to mutableStateOf(true),
                        composeUiTestManifest to mutableStateOf(true),
                        composeUiTestJunit4 to mutableStateOf(true),
                        material3 to mutableStateOf(true),
                        materialIconsExtended to mutableStateOf(false),
                        splashscreen to mutableStateOf(false),
                        constraintLayoutCompose to mutableStateOf(false),
                        multidex to mutableStateOf(false),
                        navigationCompose to mutableStateOf(false),
                        navigationRuntimeKtx to mutableStateOf(false),
                        hiltNavigationCompose to mutableStateOf(false),
                        pagingCompose to mutableStateOf(false),
                        retrofit to mutableStateOf(false),
                        gsonConverter to mutableStateOf(false),
                        loggingInterceptor to mutableStateOf(false),
                        gson to mutableStateOf(false),
                        landscapistCoil to mutableStateOf(false),
                        landscapistPlaceholder to mutableStateOf(false),
                        landscapistAnimation to mutableStateOf(false),
                        timber to mutableStateOf(false),
                        roomRuntime to mutableStateOf(false),
                        roomKtx to mutableStateOf(false),
                        roomCompiler to mutableStateOf(false),
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
                            scope.launch(Dispatchers.Default){
                                val zipBytes = generateAndroidZip(default)
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