package hsg.kurswahl.manager.container

import hsg.kurswahl.manager.dataClass.Fach
import hsg.kurswahl.manager.dataClass.Schueler

data class FachContainer (
    var eintraege: MutableMap<Int, Fach>,
)
