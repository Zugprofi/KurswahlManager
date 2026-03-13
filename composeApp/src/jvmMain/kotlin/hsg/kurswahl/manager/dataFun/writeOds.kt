package hsg.kurswahl.manager.dataFun

import hsg.kurswahl.manager.container.SchuelerContainer
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument
import org.odftoolkit.odfdom.doc.table.OdfTable

/**
 * Exportiert alle Schüler aus dem Container in eine ODS Datei.
 */
fun writeOds(filePath: String, schuelerContainer: SchuelerContainer) {

    // Neue ODS Datei erstellen
    val ods = OdfSpreadsheetDocument.newSpreadsheetDocument()
    val sheet: OdfTable = OdfTable.newTable(ods)

    // Überschriften
    sheet.getCellByPosition(0,0).setStringValue("UUID")
    sheet.getCellByPosition(1,0).setStringValue("Vorname")
    sheet.getCellByPosition(2,0).setStringValue("Nachname")
    sheet.getCellByPosition(3,0).setStringValue("FS3")
    sheet.getCellByPosition(4,0).setStringValue("WPU1")
    sheet.getCellByPosition(5,0).setStringValue("WPU2")

    val schuelerListe = schuelerContainer.getAll().sortedBy { it.nachname }

    for ((index, schueler) in schuelerListe.withIndex()) {
        val row = index + 1
        sheet.getCellByPosition(0,row).setStringValue(schueler.id)
        sheet.getCellByPosition(1,row).setStringValue(schueler.vorname)
        sheet.getCellByPosition(2,row).setStringValue(schueler.nachname)
        sheet.getCellByPosition(3,row).setStringValue(schueler.fs3Id?.toString() ?: "")
        sheet.getCellByPosition(4,row).setStringValue(schueler.wpuWahl1Ids.joinToString(","))
        sheet.getCellByPosition(5,row).setStringValue(schueler.wpuWahl2Ids.joinToString(","))
    }

    ods.save(filePath)
    println("Export abgeschlossen: $filePath")
}