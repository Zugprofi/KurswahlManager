import kotlin.test.*
import hsg.kurswahl.manager.container.SchuelerContainer
import hsg.kurswahl.manager.container.FachContainer
import hsg.kurswahl.manager.dataClass.Schueler
import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.verteileSchueler

class VerteilServiceTest {

    @Test
    fun `verteileSchueler verteilt korrekt nach FS3 Status`() {

        // --- Fächer anlegen ---
        val fach1 = Fach(
            id = 1,
            bezeichnung = "Informatik",
            minSchueler = 0,
            maxSchueler = 10

        )

        val fach2 = Fach(
            id = 2,
            bezeichnung = "Technik",
            minSchueler = 0,
            maxSchueler = 10
        )

        val fach3 = Fach(
            id = 3,
            bezeichnung = "Kunst",
            minSchueler = 0,
            maxSchueler = 10
        )

        val fachContainer = FachContainer(
            mutableMapOf(
                1 to fach1,
                2 to fach2,
                3 to fach3
            )
        )

        // --- Schüler mit FS3 (braucht nur 1 WPU) ---
        val schueler1 = Schueler(
            id = "S1",
            vorname = "Max",
            nachname = "Mustermann",
            mail = "max@test.de",
            fs3Weiterfuehren = true,
            wpuWahl1Ids = listOf(1, 2, 3)
        )

        // --- Schüler ohne FS3 (braucht 2 WPU) ---
        val schueler2 = Schueler(
            id = "S2",
            vorname = "Anna",
            nachname = "Musterfrau",
            mail = "anna@test.de",
            fs3Weiterfuehren = false,
            wpuWahl1Ids = listOf(1, 2, 3),
            wpuWahl2Ids = listOf(2, 3, 1)
        )

        val schuelerContainer = SchuelerContainer(
            mutableMapOf(
                "S1" to schueler1,
                "S2" to schueler2
            )
        )

        // --- Hauptfunktion aufrufen ---
        verteileSchueler(schuelerContainer, fachContainer)

        // --- Assertions ---

        // Schüler 1 (mit FS3) bekommt nur WPU1
        assertNotNull(schueler1.wpu1)
        assertNull(schueler1.wpu2)

        // Schüler 2 (ohne FS3) bekommt zwei WPUs
        assertNotNull(schueler2.wpu1)
        assertNotNull(schueler2.wpu2)

        // Prüfen ob Schüler wirklich im Fach eingetragen wurden
        assertTrue(fach1.schuelerIds.contains("S1"))
        assertTrue(fach1.schuelerIds.contains("S2"))
    }
}
