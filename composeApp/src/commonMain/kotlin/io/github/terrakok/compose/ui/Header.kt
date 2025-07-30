package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.compose_logo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header(
    modifier: Modifier = Modifier,
    image: DrawableResource = Res.drawable.compose_logo,
    tint: Color? = null,
    text: String = "Compose Multiplatform Wizard"
) {
    val colorFilter = if (tint != null) ColorFilter.tint(color = tint) else tint
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(image),
            colorFilter = colorFilter,
            contentDescription = null
        )
        Spacer(Modifier.size(20.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}