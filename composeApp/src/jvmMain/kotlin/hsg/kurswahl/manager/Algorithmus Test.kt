package hsg.kurswahl.manager

import hsg.kurswahl.manager.container.SchuelerContainer
import hsg.kurswahl.manager.container.FachContainer
import hsg.kurswahl.manager.dataClass.Schueler


fun verteileSchueler(
    schuelerContainer: SchuelerContainer,
    fachContainer: FachContainer
) {
    val schuelerListe = schuelerContainer.eintraege.values.toList()


}


fun verteileWpu(
    schuelerListe: List<Schueler>,
    fachContainer: FachContainer,
    Schueler : Schueler,
    wpuIndex: Int
) {
    for (prioritaet in 0..2)

        for (schueler in schuelerListe) {

            val bereitsZugewiesen = when (wpuIndex) {
                1 -> schueler.wpu1 != null
                2 -> schueler.wpu2 != null
                else -> true
            }
        if(bereitsZugewiesen) continue

        val wahlen = when (wpuIndex) {
            1 -> schueler.wpuWahl1Ids
            2 -> schueler.wpuWahl2Ids
            else -> emptyList()
        }
            if(wahlen.size <= prioritaet) continue

            val fachId = wahlen[prioritaet]
            val fach = fachContainer.eintraege[fachId] ?: continue

            if (fach.hatPlatz()){

                fach.fuegeSchuelerHinzu(schueler)


            }


    }
}
