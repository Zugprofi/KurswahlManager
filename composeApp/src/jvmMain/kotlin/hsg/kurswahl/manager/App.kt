package hsg.kurswahl.manager

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
import hsg.kurswahl.manager.composeFun.FachConfigDialog
import hsg.kurswahl.manager.container.*
import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    schuelerContainer: SchuelerContainer,
    fachContainer: FachContainer
) {
    var projectTitle by remember { mutableStateOf("unbenannt") }
    val scrollbarColor = MaterialTheme.colorScheme.secondary
    val scrollbarStyle = ScrollbarStyle(
        minimalHeight = 16.dp,
        thickness = 8.dp,
        shape = MaterialTheme.shapes.small,
        hoverDurationMillis = 300,
        unhoverColor = scrollbarColor.copy(alpha = 0.5f),
        hoverColor = scrollbarColor
    )
    val defaultDropdownColor = colorScheme.onSurfaceVariant
    val unavailableColor = colorScheme.error
    val mainColor = colorScheme.primaryContainer
    val showMissingColor = colorScheme.error
    val dropDownColor = colorScheme.surfaceVariant
    val textStyle = MaterialTheme.typography.labelLarge
    val mainTextColor = colorScheme.onPrimaryContainer
    val colorWarn = Color(0xFFF18F01)

    val testSchueler = listOf(
        Schueler(
            "abc1",
            "Tim",
            "Hofmeister",
            "empty",
            wpuWahl1Ids = listOf(1, 2, 3).toMutableList(),
            fs3Id = 11
        ),
        Schueler(
            "def3",
            "Michael",
            "Morig",
            "empty"
        ),
        Schueler(
            "ckl3",
            "Andre",
            "Arms",
            "empty",
            wpuWahl1Ids = listOf(1, 2, 3).toMutableList(),
            fs3Id = 12
        ),
        Schueler(
            "kmn2",
            "Tobias",
            "Osterkamp",
            "empty",
            wpuWahl1Ids = listOf(1, 2, 3).toMutableList(),
            fs3Id = 13
        )
    )
    val testWpus = listOf(
        Fach(
            1,
            "Mathematik"
        ),
        Fach(
            2,
            "Deutsch"
        ),
        Fach(
            3,
            "Spanisch"
        ),
    )
    val testFs3 = listOf(
        Fach(
            11,
            "Englisch"
        ),
        Fach(
            12,
            "Spanisch"
        ),
        Fach(
            13,
            "Französisch"
        ),
    )

    testSchueler.forEach { schueler ->
        schuelerContainer.add(schueler)
    }
    testWpus.forEach { fach ->
        fachContainer.add(fach)
    }
    testFs3.forEach { fach ->
        fachContainer.add(fach)
    }
    testSchueler.forEach { schueler ->
        schueler.fs3 = fachContainer.get(schueler.fs3Id)
        schueler.wpuWahl1Ids.forEach { fachId ->
            schueler.wpuWahl1.add(fachContainer.get(fachId)!!)
        }
        schueler.wpuWahl2Ids.forEach { fachId ->
            schueler.wpuWahl2.add(fachContainer.get(fachId)!!)
        }
    }
    Box(Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background)) {
        var showSchueler by remember { mutableStateOf(true) }
        var showFaecher by remember { mutableStateOf(false) }
        var showZuweisung by remember { mutableStateOf(false) }
        Column(modifier = Modifier.padding(40.dp)) {
            Row {
                Spacer(modifier = Modifier.padding(5.dp))
                Box(
                    Modifier
                        .width(100.dp)
                        .height(30.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                            )
                        )
                        .background(if (showSchueler) mainColor else colorScheme.surface)
                        .clickable { showSchueler = true; showFaecher = false; showZuweisung = false }, contentAlignment = Alignment.Center
                ) {
                    Text("Schüler",
                        style = textStyle,
                        color = if (showSchueler) mainTextColor else colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Box(
                    Modifier
                        .width(100.dp)
                        .height(30.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                            )
                        )
                        .background(if (showFaecher) mainColor else colorScheme.surface)
                        .clickable { showSchueler = false; showFaecher = true; showZuweisung = false }, contentAlignment = Alignment.Center
                ) {
                    Text("Fächer",
                        style = textStyle,
                        color = if (showFaecher) mainTextColor else colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Box(
                    Modifier
                        .width(100.dp)
                        .height(30.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 10.dp,
                            )
                        )
                        .background(if (showZuweisung) mainColor else colorScheme.surface)
                        .clickable { showSchueler = false; showFaecher = false; showZuweisung = true }, contentAlignment = Alignment.Center
                ) {
                    Text("Zuweisung",
                        style = textStyle,
                        color = if (showZuweisung) mainTextColor else colorScheme.onSurface
                    )
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .clip(
                RoundedCornerShape(10.dp)
                )
                .background(mainColor)
                .padding(20.dp))
            {
                if (showSchueler) {
                    Text("Schüler")
                    Column {
                        schuelerContainer.getAll().forEachIndexed { index, schueler ->
                            Row {
                                DisplayBox(
                                    text = "${schueler.nachname}, ${schueler.vorname}",
                                    index = index,
                                    height = 70.dp,
                                    width = 300.dp
                                )
                                DisplayBox(
                                    text = if (schueler.asb == true) {"AsB"} else {"NsB"},
                                    index = index,
                                    height = 70.dp,
                                    width = 50.dp
                                )
                                DisplayBox(
                                    text = schueler.wpuWahl1.joinToString(separator = "\n") { fach ->
                                    fach.bezeichnung } ?: "",
                                    index = index,
                                    height = 70.dp,
                                    width = 150.dp
                                )
                            }
                        }
                    }
                    //TODO
                    //Daten anzeigen
                    //Boxen editierbar machen, sodass Werte bearbeitet werden können
                } else if (showFaecher) {
                    Text("Fächer")
                    Column {
                        fachContainer.getAll().forEachIndexed { index, fach ->
                            var showThisFachDialog by remember { mutableStateOf(false) }
                            Row {
                                DisplayBox(
                                    text = fach.bezeichnung,
                                    index = index,
                                    height = 30.dp,
                                    width = 150.dp
                                )
                                DisplayBox(
                                    text = "${fach.minSchueler} - ${fach.maxSchueler}",
                                    index = index,
                                    height = 30.dp,
                                    width = 50.dp,
                                    clickAction = true,
                                    onClick = {
                                        showThisFachDialog = true
                                    }
                                )
                                DisplayBox(
                                    text = "${fach.schuelerIds.size}/${fach.maxSchueler}",
                                    index = index,
                                    height = 30.dp,
                                    width = 50.dp
                                )
                            }
                            if (showThisFachDialog) {
                                FachConfigDialog(
                                    fachId = fach.id,
                                    container = fachContainer,
                                    onDismiss = {
                                        showThisFachDialog = false
                                    },
                                )
                            }
                        }
                    }
                    //Boxen editierbar machen, sodass Werte bearbeitet werden können
                } else {
                    Text("Zuweisung")
                    //Boxen editierbar machen, sodass Werte bearbeitet werden können
                    //eigene Fachzuweisungen erlauben
                }
            }
        }
    }
}