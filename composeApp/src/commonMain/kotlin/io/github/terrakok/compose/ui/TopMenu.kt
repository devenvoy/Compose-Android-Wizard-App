package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.android
import compose_multiplatform_wizard.composeapp.generated.resources.arrow_circle_down
import compose_multiplatform_wizard.composeapp.generated.resources.code
import compose_multiplatform_wizard.composeapp.generated.resources.code_off
import compose_multiplatform_wizard.composeapp.generated.resources.dark_mode
import compose_multiplatform_wizard.composeapp.generated.resources.github
import compose_multiplatform_wizard.composeapp.generated.resources.light_mode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BoxScope.TopMenu(
    modifier: Modifier = Modifier,
    isDark: MutableState<Boolean>,
    isMultiplatform: MutableState<Boolean>,
    downloadButtonState: ButtonState
) {
    var isShowVersions by LocalShowVersions.current
    val windowInfo = LocalWindowInfo.current.containerSize.height
    val density = LocalDensity.current
    val offset =
        with(density) { windowInfo.toDp() - 100.dp }
    println(offset)
    val scope = rememberCoroutineScope()
    HorizontalFloatingToolbar(
        modifier = modifier
            .align(Alignment.TopCenter)
            .offset(y = offset),
        expanded = true,
        leadingContent = {
            Button(
                modifier = Modifier.height(60.dp),
                enabled = downloadButtonState.enabled,
                shapes = ButtonDefaults.shapesFor(60.dp),
                onClick = {
                    scope.launch(Dispatchers.Default) { downloadButtonState.onClick() }
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
        },
        trailingContent = {
            AppBarRow(
//                maxItemCount = 2,
                overflowIndicator = { menuState ->
                    IconButton(
                        onClick = {
                            if (menuState.isExpanded) {
                                menuState.dismiss()
                            } else {
                                menuState.show()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Localized description",
                        )
                    }
                },
            ) {
                toggleableItem(
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.android),
                            contentDescription = "Localized description",
                        )
                    },
                    label = if (isMultiplatform.value) "Switch to Android Project" else "Switch to Multiplatform Project",
                    checked = !isMultiplatform.value,
                    onCheckedChange = { isMultiplatform.value = !isMultiplatform.value }
                )
                toggleableItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                if (isShowVersions) Res.drawable.code else Res.drawable.code_off
                            ),
                            contentDescription = "Localized description",
                        )
                    },
                    label = if (isShowVersions) "Hide versions" else "Show Versions",
                    checked = isShowVersions,
                    onCheckedChange = { isShowVersions = it }
                )
                clickableItem(
                    onClick = { openUrl("https://github.com/devenvoy/Compose-Android-Wizard-App") },
                    icon = {
                        Icon(
                            painter = painterResource(Res.drawable.github),
                            contentDescription = "Localized description",
                        )
                    },
                    label = "Github",
                )
                toggleableItem(
                    checked = isDark.value,
                    icon = {
                        Icon(
                            painter = painterResource(
                                if (isDark.value) Res.drawable.light_mode else Res.drawable.dark_mode
                            ),
                            contentDescription = "Localized description",
                        )
                    },
                    onCheckedChange = { isDark.value = it },
                    label = if (isDark.value) "Light theme" else "Dark theme",
                )
            }
        },
        content = {}
    )
}