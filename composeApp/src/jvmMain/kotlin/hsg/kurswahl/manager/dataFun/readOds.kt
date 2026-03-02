package hsg.kurswahl.manager.dataFun

import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument
import org.odftoolkit.odfdom.doc.table.OdfTable

fun readOds() {
    val inputFile = "input.ods"
    // ODS-Dokument laden
    val ods: OdfSpreadsheetDocument = OdfSpreadsheetDocument.loadDocument(inputFile)
    // Erstes Tabellenblatt auswählen
    val sheet: OdfTable = ods.getTableList().first()
    // Wert aus Zelle B2 lesen
    val valueB2: String = sheet.getCellByPosition("B2").stringValue
    println("Alter Wert in B2: $valueB2")
    // Neuer Wert in Zelle C3 schreiben
    sheet.getCellByPosition("C3").setStringValue("Neuer Wert")
    // Dokument unter neuem Namen speichern
    ods.save("output.ods")
}