package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.Track;
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
	public List<Relay> all() {
		List<Relay> result = new ArrayList<Relay>();

		for (int i = 0; i < 5; i++) {
			Relay relay = Relay.newInstance();
			relay.setRelayname(Relayname.newInstance("Relay nummer:" + (i + 1)));
			result.add(relay);
		}
		return result;
	}

	@Override
	public TreeNode allRelays() {
		TreeNode root = new DefaultTreeNode(new TreeNodeRelay("Files", "-"), null);

		for (Relay relay : getAllRelays()) {

			TreeNode relayTreeNode = new DefaultTreeNode(new TreeNodeRelay(relay.getRelayname().toString(), "-"), root);
			TreeNode trackOne = new DefaultTreeNode("Etappe 1", new TreeNodeRelay("", relay.getTrackFor(Position.FIRST).getPerson().toString()), relayTreeNode);
			TreeNode trackTwo = new DefaultTreeNode("Etappe 2", new TreeNodeRelay("", relay.getTrackFor(Position.SECOND).getPerson().toString()), relayTreeNode);
			TreeNode trackThree = new DefaultTreeNode("Etappe 3", new TreeNodeRelay("", relay.getTrackFor(Position.THIRD).getPerson().toString()), relayTreeNode);
			TreeNode trackFour = new DefaultTreeNode("Etappe 4", new TreeNodeRelay("", relay.getTrackFor(Position.FOURTH).getPerson().toString()), relayTreeNode);

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

		// TODO -schmollc- Tell, don't ask!
		Track track = relay.getTrackFor(Position.FIRST);
		//		Person person = new PersonBuilder().withForename(Forename.newInstance("Justus")).build();
		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Justus"));
		person.setSurename(Surename.newInstance("Jonas"));
		track.setPerson(person);

		track = relay.getTrackFor(Position.SECOND);
		person = Person.newInstance();
		person.setForename(Forename.newInstance("Peter"));
		person.setSurename(Surename.newInstance("Shaw"));
		track.setPerson(person);

		track = relay.getTrackFor(Position.THIRD);
		person = Person.newInstance();
		person.setForename(Forename.newInstance("Bob"));
		person.setSurename(Surename.newInstance("Andrews"));
		track.setPerson(person);

		track = relay.getTrackFor(Position.FOURTH);
		person = Person.newInstance();
		track.setPerson(person);

		return relay;
	}

	private Relay createDieFanta4() {
		Relay relay = Relay.newInstance();

		relay.setRelayname(Relayname.newInstance("Die Fanta 4"));

		// TODO -schmollc- Tell, don't ask!
		Track track = relay.getTrackFor(Position.FIRST);
		//		Person person = new PersonBuilder().withForename(Forename.newInstance("Justus")).build();
		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Smudo"));
		track.setPerson(person);

		track = relay.getTrackFor(Position.SECOND);
		person = Person.newInstance();
		person.setForename(Forename.newInstance("Michi"));
		person.setSurename(Surename.newInstance("Beck"));
		track.setPerson(person);

		track = relay.getTrackFor(Position.THIRD);
		person = Person.newInstance();
		person.setForename(Forename.newInstance("Thomas"));
		person.setSurename(Surename.newInstance("D."));
		track.setPerson(person);

		track = relay.getTrackFor(Position.FOURTH);
		person = Person.newInstance();
		person.setForename(Forename.newInstance("Andy"));
		person.setSurename(Surename.newInstance("Epsilon"));
		track.setPerson(person);

		return relay;
	}

}
