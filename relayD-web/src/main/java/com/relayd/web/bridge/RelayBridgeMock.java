package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.PersonRelay;
import com.relayd.Relay;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;

/**
 * Ugly, ugly! :)
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
public class RelayBridgeMock implements RelayBridge {

	@Override
	public TreeNode all() {
		TreeNode root = new DefaultTreeNode(new TreeNodeRelay(PersonRelay.newInstance()), null);

		for (Relay relay : getAllRelays()) {

			// Methodik übernommen aus dem Primefaces-Beispiel.
			TreeNode relayTreeNode = new DefaultTreeNode(new TreeNodeRelay(relay.getRelayname()), root);

			// TODO -schmollc/lotz- Sieht nach Trainwreck aus. Aber wenn man direkt auf Person geht... Dann "verschwindet" der Track...
			// Moment.. mmm... dann würde die toString von Track halt sagen: "8.3km, Justus, Jonas, usw.."... mmmmm.....
			TreeNode trackOne = new DefaultTreeNode("Etappe 1", new TreeNodeRelay(relay.getTrackFor(Position.FIRST).getPersonRelay()), relayTreeNode);
			TreeNode trackTwo = new DefaultTreeNode("Etappe 2", new TreeNodeRelay(relay.getTrackFor(Position.SECOND).getPersonRelay()), relayTreeNode);
			TreeNode trackThree = new DefaultTreeNode("Etappe 3", new TreeNodeRelay(relay.getTrackFor(Position.THIRD).getPersonRelay()), relayTreeNode);
			TreeNode trackFour = new DefaultTreeNode("Etappe 4", new TreeNodeRelay(relay.getTrackFor(Position.FOURTH).getPersonRelay()), relayTreeNode);

		}

		return root;
	}

	private List<Relay> getAllRelays() {
		List<Relay> result = new ArrayList<Relay>();

		result.add(createDie4());
		result.add(createDieFanta4());

		return result;
	}

	private Relay createDie4() {
		Relay relay = Relay.newInstance();

		relay.setRelayname(Relayname.newInstance("Die 4 ????"));

		//		Person person = new PersonBuilder().withForename(Forename.newInstance("Justus")).build();
		PersonRelay person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		relay.addPersonRelay(person, Position.FIRST);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Peter"));
		person.setSurename(Surename.newInstance("Shaw"));
		relay.addPersonRelay(person, Position.SECOND);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Bob"));
		person.setSurename(Surename.newInstance("Andrews"));
		relay.addPersonRelay(person, Position.THIRD);

		person = PersonRelay.newInstance();
		relay.addPersonRelay(person, Position.FOURTH);

		return relay;
	}

	private Relay createDieFanta4() {
		Relay relay = Relay.newInstance();

		relay.setRelayname(Relayname.newInstance("Die Fanta 4"));

		//		PersonRelay person = new PersonRelayBuilder().withForename(Forename.newInstance("Justus")).build();
		PersonRelay person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Smudo"));
		relay.addPersonRelay(person, Position.FIRST);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Michi"));
		person.setSurename(Surename.newInstance("Beck"));
		relay.addPersonRelay(person, Position.SECOND);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Thomas"));
		person.setSurename(Surename.newInstance("D."));
		relay.addPersonRelay(person, Position.THIRD);

		person = PersonRelay.newInstance();
		person.setForename(Forename.newInstance("Andy"));
		person.setSurename(Surename.newInstance("Epsilon"));
		relay.addPersonRelay(person, Position.FOURTH);

		return relay;
	}
}