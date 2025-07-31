package io.github.terrakok.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.terrakok.compose.wizardAndroid.AndroidVersionPreset
import io.github.terrakok.compose.wizardAndroid.androidVersionPresets

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun VersionSelectorBottomSheet(
    selectedPreset: AndroidVersionPreset,
    onSelectPreset: (AndroidVersionPreset) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("Select Version Set", style = MaterialTheme.typography.titleLargeEmphasized, modifier = Modifier.padding(12.dp))

        HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

        androidVersionPresets.forEach { preset ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectPreset(preset) }
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = preset == selectedPreset,
                    onClick = { onSelectPreset(preset) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = preset.name)
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
    }
}
