package hsg.kurswahl.manager.dataClass

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class Fach (
    val id: Int,
    val bezeichnung: String,
    var schuelerIds: MutableList<String> = mutableListOf(),
    var schueler: MutableList<Schueler> = mutableListOf(),
    var minSchueler: Int = 0,
    var maxSchueler: Int = 0
)