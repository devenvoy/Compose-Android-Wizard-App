package io.github.terrakok.compose.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Shape


@Stable
class ButtonState(
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(8),
    onClick: () -> Unit = {}
) {
    var enabled by mutableStateOf(enabled)
    var shape by mutableStateOf(shape)
    var onClick by mutableStateOf(onClick)
}

@Composable
fun rememberButtonState(
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(8),
    onClick: () -> Unit = {}
): ButtonState {
    return remember {
        ButtonState(enabled, shape, onClick)
    }
}