package hsg.kurswahl.manager.dataClass

import kurswahlmanager.composeapp.generated.resources.Res

class Schueler (
    val id: String,
    val vorname: String,
    val nachname: String,
    val mail: String,
    val fs3Id: Int? = null,
    val fs3: Fach? = null,
    val wpuWahl1Id: List<Int>? = null,
    var wpuWahl1: List<Fach>? = null,
    val wpuWahl2Id: List<Int>? = null,
    var wpuWahl2: List<Fach>? = null,
)

// Ich gebe dir jetzt die Fälle die eintreten können anhand der Parameter: 1. Der Schüler kann sein fs3 Fach nur zweistündig weiterführen, dann muss er aber ein wpu Fach belegen Die wpuWahl verläuft nach einer Gewichtung, der Schüler kann
// Ich habe ein Informatikprojekt bei dem Ich aus einer Datenbank die Informationen über Schüler und Fächer gibt den Schülern anhand von ihrer Wahl Kurse zuordnet.