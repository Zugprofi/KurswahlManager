package hsg.kurswahl.manager.container

import hsg.kurswahl.manager.dataClass.Schueler
import java.util.Collections.emptyMap

class SchuelerContainer (
    var eintraege: MutableMap<String, Schueler> = emptyMap()
)