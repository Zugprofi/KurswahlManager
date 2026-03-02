package hsg.kurswahl.manager.dataFun

import org.odftoolkit.odfdom.SpreadsheetDocument
import java.io.File

fun main() {
    val file = File("beispiel.ods")

    val document = SpreadsheetDocument.loadDocument(file)
    val sheet = document.getSheetByIndex(0) // erstes Tabellenblatt

    val cell = sheet.getCellByPosition(0, 0) // Spalte 0, Zeile 0 (A1)
    val cellValue = cell.displayText

    println("Zelleninhalt: $cellValue")

    document.close()
}