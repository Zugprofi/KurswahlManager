package hsg.kurswahl.manager.dataClass

class Schueler (
    val id: String,
    val vorname: String,
    val nachname: String,

    // FS3
    val fs3Id: Int? = null,
    var fs3: Fach? = null,
    val fs3Art: Fs3Art = Fs3Art.VIER_STUENDIG,
    val asb: Boolean? = null,

    // WPU Wahlen
    val wpuWahl1Ids: MutableList<Int> = mutableListOf(),
    val wpuWahl2Ids: MutableList<Int> = mutableListOf(),

    var wpuWahl1: MutableList<Fach> = mutableListOf(),
    var wpuWahl2: MutableList<Fach> = mutableListOf(),

    var wpu1: Fach? = null,
    var wpu2: Fach? = null
)
