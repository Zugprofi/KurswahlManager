package hsg.kurswahl.manager.dataFun

import hsg.kurswahl.manager.container.SchuelerContainer
import hsg.kurswahl.manager.dataClass.Schueler
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument
import org.odftoolkit.odfdom.doc.table.OdfTable

/**
 * Liest die ODS-Datei "wpu_wahl.ods" aus resources ein
 * und legt die Schüler im Container ab.
 */
fun readOdsFromResources(schuelerContainer: SchuelerContainer) {

    val inputStream = ClassLoader.getSystemResourceAsStream("files/wpu_wahl.ods")
        ?: throw RuntimeException("Datei wpu_wahl.ods nicht gefunden")

    // ODS-Dokument öffnen
    val ods: OdfSpreadsheetDocument = OdfSpreadsheetDocument.loadDocument(inputStream)

    // Erstes Tabellenblatt auswählen
    val sheet: OdfTable = ods.getTableList().first()

    // Anzahl der Zeilen
    val rowCount = sheet.rowCount

    // Erste Zeile = Header → Schleife ab Zeile 1
    for (row in 1 until rowCount) {

        val id = sheet.getCellByPosition(0, row).stringValue
        if (id.isBlank()) continue

        val nachname = sheet.getCellByPosition(2, row).stringValue
        val vorname = sheet.getCellByPosition(3, row).stringValue

        // fs3_current (ID des FS3-Fachs)
        val fs3IdString = sheet.getCellByPosition(9, row).stringValue
        val fs3Id = fs3IdString.toIntOrNull()

        // WPU1 Prioritäten
        val wpu1 = mutableListOf<Int>()
        sheet.getCellByPosition(11, row).stringValue.toIntOrNull()?.let { wpu1.add(it) }
        sheet.getCellByPosition(12, row).stringValue.toIntOrNull()?.let { wpu1.add(it) }
        sheet.getCellByPosition(13, row).stringValue.toIntOrNull()?.let { wpu1.add(it) }

        // WPU2 Prioritäten
        val wpu2 = mutableListOf<Int>()
        sheet.getCellByPosition(14, row).stringValue.toIntOrNull()?.let { wpu2.add(it) }
        sheet.getCellByPosition(15, row).stringValue.toIntOrNull()?.let { wpu2.add(it) }
        sheet.getCellByPosition(16, row).stringValue.toIntOrNull()?.let { wpu2.add(it) }

        // Schueler-Objekt erstellen
        if (vorname != null && nachname != null && id != null) {
            val schueler = Schueler(
                id = id,
                vorname = vorname,
                nachname = nachname,
                mail = "", // Mail steht nicht in Tabelle
                fs3Id = fs3Id,
                wpuWahl1Ids = wpu1,
                wpuWahl2Ids = wpu2
            )
            schuelerContainer.add(schueler)
        } else {
            break
        }
    }

    println("Import abgeschlossen: ${schuelerContainer.getSize()} Schüler geladen.")
}