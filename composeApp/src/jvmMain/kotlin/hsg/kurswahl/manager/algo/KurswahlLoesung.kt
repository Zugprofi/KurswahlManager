package hsg.kurswahl.manager.algo

import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

// @PlanningSolution: Diese Klasse beschreibt das gesamte Problem für OptaPlanner.
// Sie enthält alle Daten: was verändert werden darf (Zuweisungen) und was feststeht (Fächer, Schüler).
@PlanningSolution
class KurswahlLoesung(

    // Die Zuweisungen die OptaPlanner verändern darf (Planning Entities).
    @PlanningEntityCollectionProperty
    var zuweisungen: List<WpuZuweisung> = emptyList(),

    // Die WPU-Fächer die als mögliche Werte für fach in WpuZuweisung in Frage kommen.
    // id = "wpuFaecher" muss mit valueRangeProviderRefs in WpuZuweisung.kt übereinstimmen.
    @ValueRangeProvider(id = "wpuFaecher")
    @ProblemFactCollectionProperty
    var wpuFaecher: List<Fach> = emptyList(),

    // Die Schüler als unveränderliche Daten (Problem Facts).
    @ProblemFactCollectionProperty
    var schueler: List<Schueler> = emptyList(),

    // Der Score zeigt wie gut die aktuelle Lösung ist.
    // HardSoftScore: Hard = muss erfüllt sein, Soft = sollte erfüllt sein.
    @PlanningScore
    var score: HardSoftScore = HardSoftScore.ZERO
)
