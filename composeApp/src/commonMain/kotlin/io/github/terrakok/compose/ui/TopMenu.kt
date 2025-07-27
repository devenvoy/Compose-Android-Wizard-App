package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.arrow_circle_down
import compose_multiplatform_wizard.composeapp.generated.resources.code
import compose_multiplatform_wizard.composeapp.generated.resources.code_off
import compose_multiplatform_wizard.composeapp.generated.resources.dark_mode
import compose_multiplatform_wizard.composeapp.generated.resources.github
import compose_multiplatform_wizard.composeapp.generated.resources.light_mode
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopMenu(
    modifier: Modifier = Modifier,
    isDark: MutableState<Boolean>,
    isMultiplatform: MutableState<Boolean>
) {
    Row(
        modifier = modifier.fillMaxWidth().padding( horizontal = 10.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isShowVersions by LocalShowVersions.current
        Switch(
            modifier = Modifier
                .padding(4.dp),
            checked = isMultiplatform.value,
            onCheckedChange = { isMultiplatform.value = it }
        )
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable { isShowVersions = !isShowVersions }
                .padding(8.dp),
            painter = painterResource(
                if (isShowVersions) Res.drawable.code else Res.drawable.code_off
            ),
            colorFilter = ColorFilter.tint(getContentColor()),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable { openUrl("https://github.com/devenvoy/Compose-Android-Wizard-App") }
                .padding(8.dp),
            painter = painterResource(Res.drawable.github),
            colorFilter = ColorFilter.tint(getContentColor()),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable { isDark.value = !isDark.value }
                .padding(8.dp),
            painter = painterResource(
                if (isDark.value) Res.drawable.light_mode else Res.drawable.dark_mode
            ),
            colorFilter = ColorFilter.tint(getContentColor()),
            contentDescription = null
        )
    }
}