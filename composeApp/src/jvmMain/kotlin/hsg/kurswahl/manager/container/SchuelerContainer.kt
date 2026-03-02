package hsg.kurswahl.manager.container

import hsg.kurswahl.manager.dataClass.Schueler
import java.util.Collections

class SchuelerContainer (
    var eintraege: MutableMap<String, Schueler> = mutableMapOf()
) {
    fun add(itemToAdd: Schueler) {
        val logs = mutableListOf<String>()
        if (itemToAdd.id.isBlank()) {
            return
        }
        this.eintraege[itemToAdd.id] = itemToAdd
    }

    fun get(id: String?): Schueler? {
        val itemToGet = this.eintraege[id]
        return itemToGet
    }

    fun getAll(): List<Schueler> {
        return this.eintraege.values.sortedBy { it.nachname }
    }

    fun getSize(): Int {
        return this.eintraege.size
    }
}