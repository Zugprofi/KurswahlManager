package hsg.kurswahl.manager.algo

import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler
import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.lookup.PlanningId
import org.optaplanner.core.api.domain.variable.PlanningVariable

// WpuZuweisung repräsentiert eine einzelne WPU-Zuordnung für einen Schüler.
// Für jeden Schüler der WPU braucht, wird mindestens ein WpuZuweisung-Objekt erstellt:
//   - ZWEI_STUENDIG: 1 WpuZuweisung (slot = 1)
//   - KEINE_FS3:     2 WpuZuweisung (slot = 1 und slot = 2)
//
// @PlanningEntity: OptaPlanner verändert diese Objekte während der Lösungssuche.
@PlanningEntity
class WpuZuweisung(
    @PlanningId
    val id: String = "",
    val schueler: Schueler = Schueler(),
    val slot: Int = 1, // 1 = wpu1, 2 = wpu2

    // @PlanningVariable: Dieses Feld wird von OptaPlanner gesetzt.
    // Kein nullable = true, da jede Zuweisung immer ein Fach bekommen muss.
    // WpuZuweisung-Objekte werden nur für Schüler erstellt die WPU brauchen.
    @PlanningVariable(valueRangeProviderRefs = ["wpuFaecher"])
    var fach: Fach? = null
)
