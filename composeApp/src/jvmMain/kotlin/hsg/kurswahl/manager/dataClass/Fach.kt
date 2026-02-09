package hsg.kurswahl.manager.dataClass

data class Fach (
    val id: Int,
    val beizeichnung: String,
    var minSchueler: Int,
    var maxSchueler: Int,
    var schuelerId: List<String>,
    var schueler: List<Schueler>

    )