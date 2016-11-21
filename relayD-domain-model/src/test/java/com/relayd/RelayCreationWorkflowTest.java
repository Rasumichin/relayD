package com.relayd;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * Langweilige Aktivitäten sind perverserweise längst nicht so langweilig, wenn man sich intensiv auf sie konzentriert.
 *  - David Foster Wallace
 *
 * @author schmollc (Christian@relayd.de)
 * @since 18.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayCreationWorkflowTest {

	@Test
	public void testCreateRelayAndAddToEvent() {
		// Event wird "angelegt" bzw ist vorhanden.
		RelayEvent duesseldorf = RelayEvent.duesseldorf();

		// Macht das Anlegen einer Relay ohne ein Event überhaupt Sinn?
		// Sollte die newInstance nicht als Parameter ein Event besitzen?
		Relay staubwolke = Relay.newInstance(duesseldorf);

		Relayname relayname = Relayname.newInstance("Staubwolke");
		staubwolke.setRelayname(relayname);

		// Wenn im Constructor das Event angeben wurde ist folgendes nicht mehr nötig/möglich
		//		duesseldorf.addRelay(staubwolke);

		// Nun kann schon ein persistieren erfolgen?
		// Oder sollte jede Relay immer min. eine Person besitzen?

		PersonRelay justusJonas = PersonRelay.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));

		staubwolke.addPersonRelay(justusJonas, Position.FIRST);

		// Prüfungen
		PersonRelay peterShaw = PersonRelay.newInstance();
		peterShaw.setForename(Forename.newInstance("Peter"));
		peterShaw.setSurename(Surename.newInstance("Shaw"));
		// Sollte ein Fehler verursachen, da Position 1 schon vergeben!
		staubwolke.addPersonRelay(peterShaw, Position.FIRST);

		Relay dieVierFragezeichen = Relay.newInstance(duesseldorf);
		dieVierFragezeichen.setRelayname(Relayname.newInstance("Die 4 ????"));

		// KEIN Fehler! Aber vielleicht eine Warnung?
		// (Theoretisch kann EINE Person ALLE 4 Strecken für ALLE Staffeln laufen! -> Shirtsize XXL!)
		dieVierFragezeichen.addPersonRelay(justusJonas, Position.FIRST);

		// Bedenkliche Konstellation
		PersonRelay bobAndrews = PersonRelay.newInstance();
		bobAndrews.setForename(Forename.newInstance("Bob"));
		bobAndrews.setSurename(Surename.newInstance("Andrews"));

		// Bob springt ein in Staffel Staubwolke die 1. Strecke mit z.B. 56:33min
		// und möchte in Staffel 4???? die zweite Strecke laufen.
		// Peter Shaw läuft aber in Staffel 4???? die erste Etappe und zwar mit 44:12
		// D.h. Bob ist noch gar nicht in der Wechselzone für die 2. Etappe.
		// Bei untigem Code sollte dann ggf eine Warnung erscheinen?
		// Die Konstellation würde nur "sicher" sein wenn Bob auch die ERSTE Position in
		// der Staffel 4???? ist.
		staubwolke.addPersonRelay(bobAndrews, Position.FIRST);
		dieVierFragezeichen.addPersonRelay(bobAndrews, Position.SECOND);

		// Justus jonas fällt aus
		//		staubwolke.remove(Position.FIRST);
		// oder
		//		staubwolke.remove(justusJonas);

		// Staubwolke fällt komplett aus....
		// -> löschen aus Event?
		//		duesseldorf.remove(staubwolke);
		// oder recyclen?
		//		staubwolke.setRelayname(Relayname.newInstance("Anderer Relay Name"));

		//Statistiken
		duesseldorf.getMaxNumberOfRelays();
		duesseldorf.getNumberOfRelays();
		//		duesseldorf.getEmptyRelays();
		// Name der Methode noch etwas schwammig
		//		duesseldorf.getRelaysWithMissingData();

		// Workflow
		// Justus Jonas sagt komplett ab -> krank (Beispiel: Felix Woertelboer)
		//		justusJonas.setAvailableForEvent(duesseldorf, false);
		// oder
		//		justusJonas.setNotAvailableForEvent(duesseldorf);
		// dann sollte er automatisch aus den Relays entfernt werden in welchen er teilnimmt

	}
}