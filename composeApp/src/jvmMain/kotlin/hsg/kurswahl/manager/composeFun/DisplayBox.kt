package hsg.kurswahl.manager.composeFun

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DisplayBox(text: String, index: Int, height: Dp, width: Dp, clickAction: Boolean = false, onClick: () -> Unit = {}) {
    val textStyle = MaterialTheme.typography.labelLarge
    Box(
        Modifier
            .width(width)
            .height(height)
            .background(if (index % 2 == 0) { colorScheme.surfaceVariant } else { colorScheme.surfaceBright })
            .border(1.dp, colorScheme.background)
            .clickable(enabled = clickAction, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            style = textStyle,
            color = colorScheme.onSurfaceVariant
        )
    }
}