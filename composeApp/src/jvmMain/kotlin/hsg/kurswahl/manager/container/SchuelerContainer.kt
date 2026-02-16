package hsg.kurswahl.manager.container

import hsg.kurswahl.manager.dataClass.Schueler

data class SchuelerContainer (
    var eintraege: MutableMap<String, Schueler>
)