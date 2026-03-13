package hsg.kurswahl.manager.algo

import hsg.kurswahl.manager.container.FachContainer
import hsg.kurswahl.manager.container.SchuelerContainer
import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Fs3Art
import org.optaplanner.core.config.solver.SolverConfig
import org.optaplanner.core.config.solver.termination.TerminationConfig
import org.optaplanner.core.api.solver.SolverFactory
import java.time.Duration

// loeseProblem ist die Hauptfunktion die den Algorithmus startet.
// Sie nimmt alle Schüler und die verfügbaren WPU-Fächer entgegen
// und gibt die fertige Lösung zurück.
fun loeseProblem(schuelerContainer: SchuelerContainer, wpuFaecher: List<Fach>): KurswahlLoesung {

    // Schritt 1: WpuZuweisung-Objekte erstellen.
    // Für jeden Schüler der WPU braucht, wird ein Objekt pro WPU-Slot erstellt.
    val zuweisungen = mutableListOf<WpuZuweisung>()
    var idZaehler = 0
    schuelerContainer.getAll().forEach { schueler ->
        when (schueler.fs3Art) {
            Fs3Art.ZWEI_STUENDIG -> {
                // 1 WPU-Fach nötig
                zuweisungen.add(WpuZuweisung(id = "${idZaehler++}", schueler = schueler, slot = 1))
            }
            Fs3Art.KEINE_FS3 -> {
                // 2 WPU-Fächer nötig
                zuweisungen.add(WpuZuweisung(id = "${idZaehler++}", schueler = schueler, slot = 1))
                zuweisungen.add(WpuZuweisung(id = "${idZaehler++}", schueler = schueler, slot = 2))
            }
            Fs3Art.VIER_STUENDIG -> {
                // Kein WPU nötig – kein WpuZuweisung-Objekt wird erstellt
            }
        }
    }

    // Schritt 2: Das Problem als KurswahlLoesung-Objekt verpacken.
    val problem = KurswahlLoesung(
        zuweisungen = zuweisungen,
        wpuFaecher = wpuFaecher,
        schueler = schuelerContainer.getAll()
    )

    // Schritt 3: OptaPlanner konfigurieren.
    // withTerminationSpentLimit legt fest wie lange OptaPlanner nach einer besseren Lösung sucht.
    val solverConfig = SolverConfig()
        .withSolutionClass(KurswahlLoesung::class.java)
        .withEntityClasses(WpuZuweisung::class.java)
        .withConstraintProviderClass(KurswahlConstraintProvider::class.java)
        .withTerminationSpentLimit(Duration.ofSeconds(30))

    // Schritt 4: Solver starten und Lösung berechnen.
    val solverFactory = SolverFactory.create<KurswahlLoesung>(solverConfig)
    val solver = solverFactory.buildSolver()
    val loesung = solver.solve(problem)

    // Schritt 5: Ergebnisse zurück in die originalen Schueler-Objekte schreiben.
    // Wichtig: OptaPlanner klont Objekte während der Lösungssuche.
    // Deshalb suchen wir den originalen Schüler über die id aus dem Container.
    loesung.zuweisungen.forEach { zuweisung ->
        val originalSchueler = schuelerContainer.get(zuweisung.schueler.id) ?: return@forEach
        when (zuweisung.slot) {
            1 -> originalSchueler.wpu1 = zuweisung.fach
            2 -> originalSchueler.wpu2 = zuweisung.fach
        }
    }

    return loesung
}
