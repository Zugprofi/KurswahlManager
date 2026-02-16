package hsg.kurswahl.manager.container

import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler
import java.util.Collections.emptyMap

data class FachContainer (
    var eintraege: MutableMap<Int, Fach> = emptyMap()
) {
    fun add(itemToAdd: Fach) {
        val logs = mutableListOf<String>()
        this.eintraege[itemToAdd.id] = itemToAdd
    }

    fun get(id: Int): Fach? {
        val itemToGet = this.eintraege[id]
        return itemToGet
    }

    fun getAll(): List<Fach> {
        return this.eintraege.values.sortedBy { it.beizeichnung }
    }

    fun getSize(): Int {
        return this.eintraege.size
    }
}
