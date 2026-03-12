package hsg.kurswahl.manager

import kotlin.test.*
import hsg.kurswahl.manager.algo.loeseProblem
import hsg.kurswahl.manager.container.SchuelerContainer
import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Fs3Art
import hsg.kurswahl.manager.dataClass.Schueler

class AlgoTest {

    // Hilfsfunktion: gibt alle Zuweisungen eines Containers als Tabelle aus
    private fun druckeErgebnisse(container: SchuelerContainer, testName: String) {
        println("\n=== $testName ===")
        println("%-25s %-15s %-15s %-15s".format("Name", "fs3Art", "WPU1", "WPU2"))
        println("-".repeat(70))
        container.getAll().forEach { schueler ->
            println("%-25s %-15s %-15s %-15s".format(
                "${schueler.nachname}, ${schueler.vorname}",
                schueler.fs3Art.name,
                schueler.wpu1?.bezeichnung ?: "-",
                schueler.wpu2?.bezeichnung ?: "-"
            ))
        }
        println()
    }

    // Test 1: Normaler Fall – ZWEI_STUENDIG Schüler bekommen wpu1 zugewiesen.
    @Test
    fun zweiStaendigSchuelerBekommenWpu1() {
        val fach1 = Fach(1, "Informatik", minSchueler = 0, maxSchueler = 10)
        val fach2 = Fach(2, "Kunst", minSchueler = 0, maxSchueler = 10)

        val schueler1 = Schueler("s1", "Anna", "Müller",
            fs3Art = Fs3Art.ZWEI_STUENDIG,
            wpuWahl1 = mutableListOf(fach1, fach2)
        )
        val schueler2 = Schueler("s2", "Ben", "Schmidt",
            fs3Art = Fs3Art.ZWEI_STUENDIG,
            wpuWahl1 = mutableListOf(fach1, fach2)
        )

        val container = SchuelerContainer()
        container.add(schueler1)
        container.add(schueler2)

        val loesung = loeseProblem(container, listOf(fach1, fach2))
        druckeErgebnisse(container, "Test 1: ZWEI_STUENDIG Schüler")
        println("Score: ${loesung.score}")

        assertNotNull(schueler1.wpu1, "schueler1 sollte wpu1 zugewiesen bekommen")
        assertNotNull(schueler2.wpu1, "schueler2 sollte wpu1 zugewiesen bekommen")
    }

    // Test 2: VIER_STUENDIG Schüler dürfen kein WPU haben.
    @Test
    fun vierStaendigSchuelerBekommenKeinWpu() {
        val fach1 = Fach(1, "Informatik", minSchueler = 0, maxSchueler = 10)

        val schueler = Schueler("s1", "Anna", "Müller",
            fs3Art = Fs3Art.VIER_STUENDIG
        )

        val container = SchuelerContainer()
        container.add(schueler)

        val loesung = loeseProblem(container, listOf(fach1))
        druckeErgebnisse(container, "Test 2: VIER_STUENDIG Schüler")
        println("Score: ${loesung.score}")

        assertNull(schueler.wpu1, "VIER_STUENDIG Schüler sollte kein wpu1 haben")
        assertNull(schueler.wpu2, "VIER_STUENDIG Schüler sollte kein wpu2 haben")
    }

    // Test 3: KEINE_FS3 Schüler bekommen zwei verschiedene WPU-Fächer.
    @Test
    fun keineFs3SchuelerBekommenZweiWpuFaecher() {
        val fach1 = Fach(1, "Informatik", minSchueler = 0, maxSchueler = 10)
        val fach2 = Fach(2, "Kunst", minSchueler = 0, maxSchueler = 10)

        val schueler = Schueler("s1", "Anna", "Müller",
            fs3Art = Fs3Art.KEINE_FS3,
            wpuWahl1 = mutableListOf(fach1, fach2),
            wpuWahl2 = mutableListOf(fach1, fach2)
        )

        val container = SchuelerContainer()
        container.add(schueler)

        val loesung = loeseProblem(container, listOf(fach1, fach2))
        druckeErgebnisse(container, "Test 3: KEINE_FS3 Schüler")
        println("Score: ${loesung.score}")

        assertNotNull(schueler.wpu1, "KEINE_FS3 Schüler sollte wpu1 zugewiesen bekommen")
        assertNotNull(schueler.wpu2, "KEINE_FS3 Schüler sollte wpu2 zugewiesen bekommen")
        assertNotEquals(schueler.wpu1, schueler.wpu2, "wpu1 und wpu2 dürfen nicht dasselbe Fach sein")
    }

    // Test 4: Wenn ein Fach überfüllt wäre, ist der Score negativ (Hard Constraint verletzt).
    @Test
    fun ueberfuellteFaecherFuehrenZuNegativemScore() {
        val fach1 = Fach(1, "Informatik", minSchueler = 0, maxSchueler = 1)

        val schueler1 = Schueler("s1", "Anna", "Müller",
            fs3Art = Fs3Art.ZWEI_STUENDIG,
            wpuWahl1 = mutableListOf(fach1)
        )
        val schueler2 = Schueler("s2", "Ben", "Schmidt",
            fs3Art = Fs3Art.ZWEI_STUENDIG,
            wpuWahl1 = mutableListOf(fach1)
        )

        val container = SchuelerContainer()
        container.add(schueler1)
        container.add(schueler2)

        val loesung = loeseProblem(container, listOf(fach1))
        druckeErgebnisse(container, "Test 4: Überfülltes Fach")
        println("Score: ${loesung.score}")

        assertTrue(loesung.score.hardScore() < 0,
            "Score sollte negativ sein wenn maxSchueler überschritten wird")
    }

    // Test 5: Schüler bekommt idealerweise ein Fach aus seinen Wahlen (Soft Constraint).
    @Test
    fun schuelerBekommtFachAusWahl() {
        val gewuenscht = Fach(1, "Informatik", minSchueler = 0, maxSchueler = 10)
        val ungewuenscht = Fach(2, "Kunst", minSchueler = 0, maxSchueler = 10)

        val schueler = Schueler("s1", "Anna", "Müller",
            fs3Art = Fs3Art.ZWEI_STUENDIG,
            wpuWahl1 = mutableListOf(gewuenscht)
        )

        val container = SchuelerContainer()
        container.add(schueler)

        val loesung = loeseProblem(container, listOf(gewuenscht, ungewuenscht))
        druckeErgebnisse(container, "Test 5: Wahl des Schülers")
        println("Score: ${loesung.score}")

        assertEquals(gewuenscht, schueler.wpu1,
            "Schüler sollte das gewünschte Fach bekommen wenn es möglich ist")
    }
}
