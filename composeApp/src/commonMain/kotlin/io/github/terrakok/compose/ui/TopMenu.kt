package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import io.github.skeptick.libres.compose.painterResource
import io.github.terrakok.compose.Res

@Composable
fun TopMenu(
    modifier: Modifier = Modifier,
    isDark: MutableState<Boolean>,
    isMultiplatform: MutableState<Boolean>
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        var isShowVersions by LocalShowVersions.current

        Switch(checked = isMultiplatform.value, onCheckedChange = { isMultiplatform.value = it })

        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable { isShowVersions = !isShowVersions }
                .padding(8.dp),
            painter = painterResource(
                if (isShowVersions) Res.image.code else Res.image.code_off
            ),
            colorFilter = ColorFilter.tint(getContentColor()),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable { openUrl("https://developer.android.com/jetpack/compose/documentation") }
                .padding(8.dp),
            painter = painterResource(Res.image.menu_book),
            colorFilter = ColorFilter.tint(getContentColor()),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape)
                .clickable { openUrl("https://github.com/terrakok/Compose-Multiplatform-Wizard") }
                .padding(8.dp),
            painter = painterResource(Res.image.github),
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
                if (isDark.value) Res.image.light_mode else Res.image.dark_mode
            ),
            colorFilter = ColorFilter.tint(getContentColor()),
            contentDescription = null
        )
    }
}