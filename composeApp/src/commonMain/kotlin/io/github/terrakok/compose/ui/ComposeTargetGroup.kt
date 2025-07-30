package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonGroupDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.android
import compose_multiplatform_wizard.composeapp.generated.resources.apple
import compose_multiplatform_wizard.composeapp.generated.resources.language
import compose_multiplatform_wizard.composeapp.generated.resources.laptop
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ComposeTargetGroup(
    android: MutableState<Boolean>,
    ios: MutableState<Boolean>,
    desktop: MutableState<Boolean>,
    browser: MutableState<Boolean>
) {
    val targets = listOf(
        Triple("Android", android, Res.drawable.android),
        Triple("iOS", ios, Res.drawable.apple),
        Triple("Desktop", desktop, Res.drawable.laptop),
        Triple("Browser", browser, Res.drawable.language)
    )

    Row(
        Modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(ButtonGroupDefaults.ConnectedSpaceBetween)
    ) {
        targets.forEachIndexed { index, (label, state, icon) ->
            ToggleButton(
                modifier = Modifier.height(60.dp).shadow(0.dp),
                checked = state.value,
                onCheckedChange = { state.value = it },
                shapes = when (index) {
                    0 -> ButtonGroupDefaults.connectedLeadingButtonShapes()
                    targets.lastIndex -> ButtonGroupDefaults.connectedTrailingButtonShapes()
                    else -> ButtonGroupDefaults.connectedMiddleButtonShapes()
                }
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = label,
                    modifier = Modifier.size(20.dp),
                    tint = if (state.value)
                        MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.size(ToggleButtonDefaults.IconSpacing))
                Text(label.uppercase())
            }
        }
    }
}
