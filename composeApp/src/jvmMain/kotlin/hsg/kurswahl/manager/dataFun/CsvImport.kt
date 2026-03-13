package hsg.kurswahl.manager.dataFun

import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler

// Gibt alle eingelesenen Daten gebündelt zurück
data class KursplanDaten(
    val fremdsprachen: List<Fach>,
    val schueler: List<Schueler>,
    val wpuKurse: List<Fach>
)

object CsvImport {

    // ── Hilfsfunktionen ───────────────────────────────────────────────────────

    private fun String.clean(): String = this.trim('"')

    private fun String.nullableInt(): Int? =
        if (this == "NULL" || this.isBlank()) null else this.clean().toIntOrNull()

    private fun parseLine(line: String): List<String> {
        val result = mutableListOf<String>()
        var current = StringBuilder()
        var inQuotes = false
        for (char in line) {
            when {
                char == '"'              -> inQuotes = !inQuotes
                char == ',' && !inQuotes -> { result.add(current.toString()); current = StringBuilder() }
                else                     -> current.append(char)
            }
        }
        result.add(current.toString())
        return result
    }

    // ── Lesen ─────────────────────────────────────────────────────────────────
    //
    // Statt einem Dateipfad bekommt diese Funktion den Dateiinhalt als String.
    // Beispiel-Aufruf (Desktop/JVM):
    //
    //   import java.io.File
    //   val inhalt = File("ticketsystem.csv").readText()
    //   val daten = CsvImport.lesen(inhalt)

    fun lesen(inhalt: String): KursplanDaten {
        val zeilen = inhalt.lines()

        val fremdsprachen = mutableListOf<Fach>()
        val schueler      = mutableListOf<Schueler>()
        val wpuKurse      = mutableListOf<Fach>()

        var abschnitt = ""

        for (zeile in zeilen) {
            if (zeile.isBlank()) continue
            val f = parseLine(zeile)

            // Header-Zeilen erkennen → Abschnitt wechseln
            when {
                f[0].clean() == "id" && f.size == 2 -> {
                    abschnitt = if (fremdsprachen.isEmpty()) "fremdsprachen" else "wpu"
                    continue
                }
                f[0].clean() == "uuid" -> { abschnitt = "schueler"; continue }
            }

            when (abschnitt) {

                "fremdsprachen" -> fremdsprachen.add(Fach(
                    id          = f[0].clean().toInt(),
                    bezeichnung = f[1].clean()
                ))

                "schueler" -> {
                    val hatFs3  = f[7].clean() == "1"
                    val wpu1Ids = listOfNotNull(f[11].nullableInt(), f[12].nullableInt(), f[13].nullableInt())
                    val wpu2Ids = listOfNotNull(f[14].nullableInt(), f[15].nullableInt(), f[16].nullableInt())

                    schueler.add(Schueler(
                        id               = f[0].clean(),
                        vorname          = f[3].clean().ifBlank { "" },
                        nachname         = f[2].clean(),
                        mail             = "", // nicht in CSV
                        fs3Id            = if (hatFs3) f[9].nullableInt() else null,
                        fs3Weiterfuehren = hatFs3,
                        asb              = f[6].clean() == "1",
                        wpuWahl1Ids      = wpu1Ids.toMutableList(),
                        wpuWahl2Ids      = wpu2Ids.toMutableList()
                    ))
                }

                "wpu" -> wpuKurse.add(Fach(
                    id          = f[0].clean().toInt(),
                    bezeichnung = f[1].clean()
                ))
            }
        }

        return KursplanDaten(fremdsprachen, schueler, wpuKurse)
    }

    // ── Schreiben ─────────────────────────────────────────────────────────────
    //
    // Gibt den fertigen CSV-Inhalt als String zurück.
    // Beispiel-Aufruf (Desktop/JVM):
    //
    //   import java.io.File
    //   val csvText = CsvImport.schreiben(daten)
    //   File("ticketsystem.csv").writeText(csvText)

    fun schreiben(daten: KursplanDaten): String {
        val sb = StringBuilder()

        fun Int?.csv()   = this?.toString() ?: "NULL"
        fun q(s: String) = "\"$s\""

        // Fremdsprachen
        sb.appendLine("\"id\",\"name\"")
        for (k in daten.fremdsprachen) sb.appendLine("${q(k.id.toString())},${q(k.bezeichnung)}")

        // Schüler
        sb.appendLine("\"uuid\",\"done\",\"family_name\",\"given_name\",\"jahrgang\",\"klasse\",\"asb\",\"fs3\",\"fs3_hours\",\"fs3_current\",\"fs3_ifNoWpu\",\"wpu1_1\",\"wpu1_2\",\"wpu1_3\",\"wpu2_1\",\"wpu2_2\",\"wpu2_3\"")
        for (s in daten.schueler) {
            fun List<Int>.prio(i: Int) = q(getOrElse(i) { 0 }.toString())
            sb.appendLine(listOf(
                q(s.id),
                q("0"),
                q(s.nachname),
                q(s.vorname),
                q("99"),
                q("99"),
                q(if (s.asb == true) "1" else "0"),
                q(if (s.fs3Weiterfuehren) "1" else "0"),
                "NULL",
                s.fs3Id.csv(),
                "NULL",
                s.wpuWahl1Ids.prio(0),
                s.wpuWahl1Ids.prio(1),
                s.wpuWahl1Ids.prio(2),
                s.wpuWahl2Ids.prio(0),
                s.wpuWahl2Ids.prio(1),
                s.wpuWahl2Ids.prio(2)
            ).joinToString(","))
        }

        // WPU-Kurse
        sb.appendLine("\"id\",\"name\"")
        for (k in daten.wpuKurse) sb.appendLine("${q(k.id.toString())},${q(k.bezeichnung)}")

        return sb.toString().trimEnd()
    }
}