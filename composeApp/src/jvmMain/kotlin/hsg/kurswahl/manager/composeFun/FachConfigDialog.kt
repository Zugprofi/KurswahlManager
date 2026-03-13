package hsg.kurswahl.manager.composeFun

import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.TextButton
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import hsg.kurswahl.manager.composeFun.DisplayBox
import hsg.kurswahl.manager.container.*
import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler

@Composable
fun FachConfigDialog(
    fachId: Int,
    container: FachContainer,
    onDismiss: () -> Unit
) {
    val fach = container.get(fachId)!!
    var newMinSchueler by remember { mutableStateOf(fach.minSchueler.toString()) }
    var newMaxSchueler by remember { mutableStateOf(fach.maxSchueler.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(16.dp),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,

        title = {
            Text(
                text = "Fach konfigurieren",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },

        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Lege die minimale und maximale Anzahl an Schülern für dieses Fach fest.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                OutlinedTextField(
                    value = newMinSchueler,
                    onValueChange = { value ->
                        if (value.all { it.isDigit() }) {
                            newMinSchueler = value
                        }
                    },
                    label = { Text("Minimale Schülerzahl") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )

                OutlinedTextField(
                    value = newMaxSchueler,
                    onValueChange = { value ->
                        if (value.all { it.isDigit() }) {
                            newMaxSchueler = value
                        }
                    },
                    label = { Text("Maximale Schülerzahl") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = {

                    val min = newMinSchueler.toIntOrNull()
                    val max = newMaxSchueler.toIntOrNull()

                    if (min != null && max != null) {
                        fach.minSchueler = min
                        fach.maxSchueler = max
                    }
                    println(fach.minSchueler)
                    println(fach.maxSchueler)
                    onDismiss()
                }
            ) {
                Text(
                    text = "Speichern",
                    fontWeight = FontWeight.SemiBold
                )
            }
        },

        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "Abbrechen",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}