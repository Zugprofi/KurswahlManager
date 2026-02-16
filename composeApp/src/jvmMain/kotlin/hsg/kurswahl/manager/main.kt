package hsg.kurswahl.manager

import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.key.Key
import hsg.pruefungs.planer.theme.AppTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KurswahlManager",
    ) {
        MenuBar {
            Menu("Datei", mnemonic = 'D') {
                Menu("Öffnen") {
                    Item("xlsx laden", onClick = {})
                    Item("Datenbank laden", onClick = {})
                }
                Item("Schließen", onClick = {})
            }
            Menu("Einstellungen", mnemonic = 'D') {
                Menu("Öffnen") {
                    Item("xlsx laden", onClick = {})
                    Item("Datenbank laden", onClick = {})
                }
                Item("Schließen", onClick = {})
            }
            Menu("Ansicht", mnemonic = 'D') {
                Menu("Öffnen") {
                    Item("xlsx laden", onClick = {})
                    Item("Datenbank laden", onClick = {})
                }
                Item("Schließen", onClick = {})
            }
        }
        AppTheme {
            App()
        }
    }
}