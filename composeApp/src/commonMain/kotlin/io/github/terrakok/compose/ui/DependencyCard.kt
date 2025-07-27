package io.github.terrakok.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import compose_multiplatform_wizard.composeapp.generated.resources.Res
import compose_multiplatform_wizard.composeapp.generated.resources.circle_checked
import compose_multiplatform_wizard.composeapp.generated.resources.circle_unchecked
import io.github.terrakok.compose.wizard.Dependency
import io.github.terrakok.compose.wizardAndroid.GroupedAndroidDependency
import org.jetbrains.compose.resources.painterResource

@Composable
fun DependencyCard(
    modifier: Modifier = Modifier,
    dependency: Dependency,
    selected: MutableState<Boolean>
) {
    var isSelected by selected
    Card(
        modifier = modifier.padding(8.dp).fillMaxWidth().height(150.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme
                .surfaceColorAtElevation(4.dp)
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().clickable { isSelected = !isSelected }
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(end = 40.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    text = dependency.title
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    text = dependency.description
                )
            }
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(
                    if (isSelected) Res.drawable.circle_checked else Res.drawable.circle_unchecked
                ),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = null
            )
            TextButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 8.dp),
                onClick = { openUrl(dependency.url) }
            ) {
                Text("MORE INFO")
            }

            val isShowVersions by LocalShowVersions.current
            if (isShowVersions) {
                Text(
                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
                    style = MaterialTheme.typography.bodySmall,
                    text = dependency.version
                )
            }
        }
    }
}

@Composable
fun DependencyCard(
    modifier: Modifier = Modifier,
    dependency: GroupedAndroidDependency,
    selected: MutableState<Boolean>
) {
    var isSelected by selected
    Card(
        modifier = modifier.padding(8.dp).fillMaxWidth().height(150.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme
                .surfaceColorAtElevation(4.dp)
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().clickable { isSelected = !isSelected }
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(end = 40.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    text = dependency.title
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    text = dependency.items.first().description
                )
            }
            Image(
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(
                    if (isSelected) Res.drawable.circle_checked else Res.drawable.circle_unchecked
                ),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                contentDescription = null
            )
            TextButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(end = 8.dp),
                onClick = { openUrl(dependency.items.first().url) }
            ) {
                Text("MORE INFO")
            }

            val isShowVersions by LocalShowVersions.current
            if (isShowVersions) {
                Text(
                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
                    style = MaterialTheme.typography.bodySmall,
                    text = dependency.items.first().version
                )
            }
        }
    }
}