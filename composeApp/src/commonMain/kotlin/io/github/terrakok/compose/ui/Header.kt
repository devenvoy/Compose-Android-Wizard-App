package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.skeptick.libres.compose.painterResource
import io.github.terrakok.compose.Res

@Composable
fun Header(modifier: Modifier = Modifier, text: String = "Compose Multiplatform Wizard") {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(Res.image.compose_logo),
            contentDescription = null
        )
        Spacer(Modifier.size(20.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}