package hsg.kurswahl.manager.dataClass

import kurswahlmanager.composeapp.generated.resources.Res

class Schueler (
    val id: String,
    val vorname: String,
    val nachname: String,
    val mail: String,

    // FS3
    val fs3Id: Int? = null,
    var fs3: Fach? = null,
    val fs3Weiterfuehren: Boolean = true,
    val asb: Boolean? = null,

    // WPU Wahlen
    val wpuWahl1Ids: MutableList<Int> = mutableListOf(), // 3 Prios
    val wpuWahl2Ids: MutableList<Int> = mutableListOf(), // 3 Prios

    var wpuWahl1: MutableList<Fach> = mutableListOf(),
    var wpuWahl2: MutableList<Fach> = mutableListOf(),

    var wpu1: Fach? = null,
    var wpu2: Fach? = null
)
