package hsg.kurswahl.manager.dataClass

data class Fach (
    val id: Int,
    val bezeichnung: String,
    var minSchueler: Int = 0,
    var maxSchueler: Int = 0,
    var schuelerIds: MutableList<String> = mutableListOf(),
    var schueler: MutableList<Schueler> = mutableListOf()
)
    {
        fun hatPlatz(): Boolean = schuelerIds.size < maxSchueler
        fun fuegeSchuelerHinzu(s: Schueler) {
            schuelerIds.add(s.id)
        }

        fun aktuelleAnzahl(): Int = schuelerIds.size
    }