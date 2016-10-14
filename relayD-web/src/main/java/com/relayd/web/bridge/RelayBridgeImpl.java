package com.relayd.web.bridge;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.PersonRelay;
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
	private GatewayType gatewayType = GatewayType.MEMORY;

	public RelayBridgeImpl() {
		super();
		gateway = RelayGatewayFactory.get(getGatewayType());
	}

	@Override
	public TreeNode all() {
		TreeNode root = new DefaultTreeNode(new TreeNodeRelay(PersonRelay.newInstance()), null);

		for (Relay relay : gateway.getAll()) {

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

	@Override
	public GatewayType getGatewayType() {
		return gatewayType;
	}

	@Override
	public void create(Relay relay) {
		gateway.set(relay);
	}
}