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
                        CoreGroup to mutableStateOf(true) ,
                        ComposeUIGroup to mutableStateOf(true) ,
                        MaterialAndSplashGroup to mutableStateOf(true) ,
                        NavigationGroup to mutableStateOf(true) ,
                        PagingGroup to mutableStateOf(true) ,
                        LoggingGroup to mutableStateOf(true) ,
                        MultidexConstraintGroup to mutableStateOf(true) ,
                        RoomDBGroup to mutableStateOf(true) ,
                        RetrofitGroup to mutableStateOf(true) ,
                        HiltGroup to mutableStateOf(true),
                        LandscapistGroup to mutableStateOf(true) ,
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