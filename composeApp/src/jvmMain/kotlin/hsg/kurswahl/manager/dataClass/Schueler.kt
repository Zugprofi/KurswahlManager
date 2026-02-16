package hsg.kurswahl.manager.dataClass

import kurswahlmanager.composeapp.generated.resources.Res

class Schueler (
    val id: String,
    val vorname: String,
    val nachname: String,
    val mail: String,

    // FS3
    val fs3Id: Int? = null,
    val fs3: Fach? = null,
    val fs3Weiterführen: Boolean = true,

    // WPU Wahlen
    val wpuWahl1Ids: List<Int>? = emptyList(), // 3 Prios
    val wpuWahl2Ids: List<Int>? = emptyList(), // 3 Prios

    var wpuWahl1: List<Fach>? = null,
    var wpuWahl2: List<Fach>? = null,
)
