package com.relayd.web.bridge;

import java.util.List;
import java.util.UUID;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Relay;
import com.relayd.Settings;
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

	@SuppressWarnings("unused")
	@Override
	public TreeNode all() {
		TreeNode root = new DefaultTreeNode();

		List<Relay> all = getGateway().getAll();

		for (Relay relay : all) {

			// Methodik übernommen aus dem Primefaces-Beispiel.
			TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay), root);

			// TODO (Christian, Erik, Version 1.4): Aus dem Primefacesbeispiel. Sollte man nochmal verifizieren.
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getMemberFor(Position.FIRST), Position.FIRST), relayTreeNode);
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getMemberFor(Position.SECOND), Position.SECOND), relayTreeNode);
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getMemberFor(Position.THIRD), Position.THIRD), relayTreeNode);
			new DefaultTreeNode(TreeNodeRow.newInstance(relay.getMemberFor(Position.FOURTH), Position.FOURTH), relayTreeNode);
		}

		return root;
	}

	@Override
	public GatewayType getGatewayType() {
		return Settings.getGatewayType();
	}

	@Override
	public void set(Relay relay) {
		getGateway().set(relay);
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
					relay.addMember(participantRow.getMember(), Position.FIRST);
					break;
				case SECOND:
					relay.addMember(participantRow.getMember(), Position.SECOND);
					break;
				case THIRD:
					relay.addMember(participantRow.getMember(), Position.THIRD);
					break;
				case FOURTH:
					relay.addMember(participantRow.getMember(), Position.FOURTH);
					break;
				default:
					relay.addMember(participantRow.getMember(), Position.FIRST);

			}
			getGateway().set(relay);
		}
	}

	@Override
	public Relay get(UUID uuid) {
		return getGateway().get(uuid);
	}

	public RelayGateway getGateway() {
		return RelayGatewayFactory.get(getGatewayType());
	}
}