package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayGateway;
import com.relayd.ejb.RelayGatewayFactory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 14.10.2016
 *
 */
public class RelayBridgeImpl implements RelayBridge {

	private RelayGateway gateway = null;
	private GatewayType gatewayType = GatewayType.JPA;

	public RelayBridgeImpl() {
		super();
		gateway = RelayGatewayFactory.get(getGatewayType());
	}

	@Override
	public TreeNode all() {
		TreeNode root = new DefaultTreeNode(new TreeNodeRow(Participant.newInstance()), null);

		List<Relay> all = gateway.getAll();
		// TODO - schmollc- entfernen wenn alles rund läuft!
		//		all.addAll(getAllMock());

		for (Relay relay : all) {

			// Methodik übernommen aus dem Primefaces-Beispiel.
			TreeNode relayTreeNode = new DefaultTreeNode(new TreeNodeRow(relay), root);

			// TODO -schmollc/lotz- Sieht nach Trainwreck aus. Aber wenn man direkt auf Person geht... Dann "verschwindet" der Track...
			// Moment.. mmm... dann würde die toString von Track halt sagen: "8.3km, Justus, Jonas, usw.."... mmmmm.....
			@SuppressWarnings("unused")
			TreeNode trackOne = new DefaultTreeNode("Etappe 1", new TreeNodeRow(relay.getParticipantFor(Position.FIRST)), relayTreeNode);
			@SuppressWarnings("unused")
			TreeNode trackTwo = new DefaultTreeNode("Etappe 2", new TreeNodeRow(relay.getParticipantFor(Position.SECOND)), relayTreeNode);
			@SuppressWarnings("unused")
			TreeNode trackThree = new DefaultTreeNode("Etappe 3", new TreeNodeRow(relay.getParticipantFor(Position.THIRD)), relayTreeNode);
			@SuppressWarnings("unused")
			TreeNode trackFour = new DefaultTreeNode("Etappe 4", new TreeNodeRow(relay.getParticipantFor(Position.FOURTH)), relayTreeNode);

		}

		return root;
	}

	@Override
	public GatewayType getGatewayType() {
		return gatewayType;
	}

	@Override
	public void create(Relay relay) {
		gateway.set(relay);
	}

	private List<Relay> getAllMock() {
		List<Relay> result = new ArrayList<Relay>();

		result.add(createDie4());
		result.add(createDieFanta4());

		return result;
	}

	private Relay createDie4() {
		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());

		relay.setRelayname(Relayname.newInstance("Die 4 ????"));

		Participant participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Justus"));
		participant.setSurename(Surename.newInstance("Jonas"));
		relay.addParticipant(participant, Position.FIRST);

		participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Peter"));
		participant.setSurename(Surename.newInstance("Shaw"));
		relay.addParticipant(participant, Position.SECOND);

		participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Bob"));
		participant.setSurename(Surename.newInstance("Andrews"));
		relay.addParticipant(participant, Position.THIRD);

		participant = Participant.newInstance();
		relay.addParticipant(participant, Position.FOURTH);

		return relay;
	}

	private Relay createDieFanta4() {
		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());

		relay.setRelayname(Relayname.newInstance("Die Fanta 4"));

		Participant participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Smudo"));
		relay.addParticipant(participant, Position.FIRST);

		participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Michi"));
		participant.setSurename(Surename.newInstance("Beck"));
		relay.addParticipant(participant, Position.SECOND);

		participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Thomas"));
		participant.setSurename(Surename.newInstance("D."));
		relay.addParticipant(participant, Position.THIRD);

		participant = Participant.newInstance();
		participant.setForename(Forename.newInstance("Andy"));
		participant.setSurename(Surename.newInstance("Epsilon"));
		relay.addParticipant(participant, Position.FOURTH);

		return relay;
	}

	/**
	 * @deprecated Nur zu testzwecken eingebaut um das persitieren zu checken!
	 * Muss dringend umgeschrieben werden -> Das treeNodeModel sollte besser oder anders werden.
	 */
	@Override
	@Deprecated
	public void persist(TreeNode treeNode) {
		if (treeNode.getParent() != null) {
			TreeNodeRow relayNode = (TreeNodeRow) treeNode.getParent().getData();
			TreeNodeRow participantRow = (TreeNodeRow) treeNode.getData();

			Relay relay = relayNode.getRelay();
			relay.addParticipant(participantRow.getParticipant(), Position.FIRST);

			RelayGateway jpaGateway = RelayGatewayFactory.get(GatewayType.JPA);

			jpaGateway.set(relay);
		}
	}
}