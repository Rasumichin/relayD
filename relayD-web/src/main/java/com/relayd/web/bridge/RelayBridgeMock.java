package com.relayd.web.bridge;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;

/**
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

		TreeNode relayOne = new DefaultTreeNode(new TreeNodeRelay("Die 4 ????", "-"), root);

		TreeNode trackOne = new DefaultTreeNode("Etappe 1", new TreeNodeRelay("", "Justus Jonas"), relayOne);
		TreeNode trackTwo = new DefaultTreeNode("Etappe 2", new TreeNodeRelay("", "Peter Shaw"), relayOne);
		TreeNode trackThree = new DefaultTreeNode("Etappe 3", new TreeNodeRelay("", "Bob Andrews"), relayOne);
		TreeNode trackFour = new DefaultTreeNode("Etappe 4", new TreeNodeRelay("", " "), relayOne);

		TreeNode relayTwo = new DefaultTreeNode(new TreeNodeRelay("TKKG", "-"), root);

		trackOne = new DefaultTreeNode("Etappe 1", new TreeNodeRelay("", "Tarzan"), relayTwo);
		trackTwo = new DefaultTreeNode("Etappe 2", new TreeNodeRelay("", "Karl"), relayTwo);
		trackThree = new DefaultTreeNode("Etappe 3", new TreeNodeRelay("", "Klößchen"), relayTwo);
		trackFour = new DefaultTreeNode("Etappe 4", new TreeNodeRelay("", "die fesche Gabi"), relayTwo);

		return root;
	}
}
