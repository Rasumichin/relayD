package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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
		TreeNode root = new DefaultTreeNode();

		List<Relay> all = gateway.getAll();

		for (Relay relay : all) {

			// Methodik übernommen aus dem Primefaces-Beispiel.
			TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay), root);

			// TODO (Christian, Erik, Version 1.4): Aus dem Primefacesbeispiel. Sollte man nochmal verifizieren.
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getParticipantFor(Position.FIRST), Position.FIRST), relayTreeNode);
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getParticipantFor(Position.SECOND), Position.SECOND), relayTreeNode);
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getParticipantFor(Position.THIRD), Position.THIRD), relayTreeNode);
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getParticipantFor(Position.FOURTH), Position.FOURTH), relayTreeNode);
		}

		return root;
	}

	@Override
	public GatewayType getGatewayType() {
		return gatewayType;
	}

	@Override
	public void set(Relay relay) {
		gateway.set(relay);
	}

	/**
	 * @deprecated Nur zu testzwecken eingebaut um das persitieren zu checken!
	 * Muss dringend umgeschrieben werden -> Das treeNodeModel sollte besser oder anders werden.
	 */
	@Override
	@Deprecated
	public void set(TreeNode treeNode) {
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
			gateway.set(relay);
		}
	}

	@Override
	public Relay get(UUID uuid) {
		return gateway.get(uuid);
	}
}