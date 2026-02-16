package hsg.kurswahl.manager.dataClass

data class Fach (
    val id: Int,
    val beizeichnung: String,
    var minSchueler: Int,
    var maxSchueler: Int,

    val schuelerIds: MutableList<String> = mutableListOf(),
    ){
    fun hatPlatz(): Boolean = schuelerIds.size < maxSchueler
    fun fuegeSchuelerHinzu(s: Schueler) {
        schuelerIds.add(s.id)
    }
    fun aktuelleAnzahl(): Int = schuelerIds.size
}

