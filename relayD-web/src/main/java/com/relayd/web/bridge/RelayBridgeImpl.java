package com.relayd.web.bridge;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.attributes.Position;
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
		TreeNode root = new DefaultTreeNode(TreeNodeRow.newInstance(Participant.newInstance(), Position.UNKNOWN), null);

		List<Relay> all = gateway.getAll();

		for (Relay relay : all) {

			// Methodik übernommen aus dem Primefaces-Beispiel.
			TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay), root);

			// TODO (Christian, Erik, Version 1.3): Sieht nach Trainwreck aus. Aber wenn man direkt auf Person geht... Dann "verschwindet" der Track...
			// Moment.. mmm... dann würde die toString von Track halt sagen: "8.3km, Justus, Jonas, usw.."... mmmmm.....
			@SuppressWarnings("unused")
			TreeNode trackOne = new DefaultTreeNode("Etappe 1", TreeNodeRow.newInstance(relay.getParticipantFor(Position.FIRST), Position.FIRST), relayTreeNode);
			@SuppressWarnings("unused")
			TreeNode trackTwo = new DefaultTreeNode("Etappe 2", TreeNodeRow.newInstance(relay.getParticipantFor(Position.SECOND), Position.SECOND), relayTreeNode);
			@SuppressWarnings("unused")
			TreeNode trackThree = new DefaultTreeNode("Etappe 3", TreeNodeRow.newInstance(relay.getParticipantFor(Position.THIRD), Position.THIRD), relayTreeNode);
			@SuppressWarnings("unused")
			TreeNode trackFour = new DefaultTreeNode("Etappe 4", TreeNodeRow.newInstance(relay.getParticipantFor(Position.FOURTH), Position.FOURTH), relayTreeNode);

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
			switch (participantRow.getPosition()) {
				case FIRST:
					relay.addParticipant(participantRow.getParticipant(), Position.FIRST);
					break;
				case SECOND:
					relay.addParticipant(participantRow.getParticipant(), Position.SECOND);
					break;
				case THIRD:
					relay.addParticipant(participantRow.getParticipant(), Position.THIRD);
					break;
				case FOURTH:
					relay.addParticipant(participantRow.getParticipant(), Position.FOURTH);
					break;
				default:
					relay.addParticipant(participantRow.getParticipant(), Position.FIRST);

			}
			RelayGateway jpaGateway = RelayGatewayFactory.get(GatewayType.JPA);

			jpaGateway.set(relay);
		}
	}
}