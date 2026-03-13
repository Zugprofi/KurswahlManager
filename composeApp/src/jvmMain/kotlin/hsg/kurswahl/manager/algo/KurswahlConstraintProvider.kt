package hsg.kurswahl.manager.algo

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintCollectors.count
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider
import org.optaplanner.core.api.score.stream.Joiners

class KurswahlConstraintProvider : ConstraintProvider {

    override fun defineConstraints(factory: ConstraintFactory): Array<Constraint> {
        return arrayOf(
            // Hard Constraints (müssen erfüllt sein)
            maxSchuelerProFach(factory),
            minSchuelerProFach(factory),
            keinDoppeltesFach(factory),
            // Soft Constraints (sollten erfüllt sein, können aber verletzt werden)
            wahlDesSchuelerBeachten(factory),
        )
    }

    // Hard: Ein Fach darf nicht mehr Schüler haben als maxSchueler.
    // groupBy zählt wie viele Zuweisungen auf dasselbe Fach zeigen.
    private fun maxSchuelerProFach(factory: ConstraintFactory): Constraint {
        return factory.forEach(WpuZuweisung::class.java)
            .filter { it.fach != null }
            .groupBy({ z: WpuZuweisung -> z.fach }, count())
            .filter { fach, anzahl -> fach!!.maxSchueler > 0 && anzahl > fach.maxSchueler }
            .penalize(HardSoftScore.ONE_HARD) { _, anzahl -> anzahl }
            .asConstraint("Max Schüler pro Fach überschritten")
    }

    // Hard: Ein Fach das Schüler hat, muss mindestens minSchueler haben.
    // (Fächer mit 0 Schülern sind ok – sie werden einfach nicht angeboten.)
    private fun minSchuelerProFach(factory: ConstraintFactory): Constraint {
        return factory.forEach(WpuZuweisung::class.java)
            .filter { it.fach != null }
            .groupBy({ z: WpuZuweisung -> z.fach }, count())
            .filter { fach, anzahl -> fach!!.minSchueler > 0 && anzahl < fach.minSchueler }
            .penalize(HardSoftScore.ONE_HARD) { fach, anzahl -> fach!!.minSchueler - anzahl }
            .asConstraint("Min Schüler pro Fach nicht erreicht")
    }

    // Hard: Ein Schüler darf nicht zweimal dasselbe Fach belegen.
    // forEachUniquePair findet alle Paare von Zuweisungen desselben Schülers.
    private fun keinDoppeltesFach(factory: ConstraintFactory): Constraint {
        return factory.forEachUniquePair(
            WpuZuweisung::class.java,
            Joiners.equal { it.schueler },
            Joiners.equal { it.fach }
        )
            .filter { z1, _ -> z1.fach != null }
            .penalize(HardSoftScore.ONE_HARD)
            .asConstraint("Schüler darf nicht zweimal dasselbe Fach belegen")
    }

    // Soft: Das zugewiesene Fach sollte aus den Wahlen des Schülers kommen.
    // Soft weil: wenn alle Wahlen eines Schülers überfüllt sind, muss er woanders hin.
    // slot = 1 → Wahl aus wpuWahl1, slot = 2 → Wahl aus wpuWahl2
    private fun wahlDesSchuelerBeachten(factory: ConstraintFactory): Constraint {
        return factory.forEach(WpuZuweisung::class.java)
            .filter { zuweisung ->
                val wahl = if (zuweisung.slot == 1) zuweisung.schueler.wpuWahl1
                           else zuweisung.schueler.wpuWahl2
                zuweisung.fach != null && zuweisung.fach !in wahl
            }
            .penalize(HardSoftScore.ONE_SOFT)
            .asConstraint("WPU nicht aus Wahl des Schülers")
    }
}
