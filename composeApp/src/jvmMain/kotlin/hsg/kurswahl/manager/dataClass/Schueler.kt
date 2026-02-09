package hsg.kurswahl.manager.dataClass

import kurswahlmanager.composeapp.generated.resources.Res

class Schueler (
    val Id: String,
    val vorName: String,
    val nachName: String,
    val mail: String,
    val fs3Id: Int? = null,
    val fs3: Fach? = null,
    val wpuWahl1Id: List<Int>? = null,
    var wpuWahl1: List<Fach>? = null,
    val wpuWahl2Id: List<Int>? = null,
    var wpuWahl2: List<Fach>? = null,
)