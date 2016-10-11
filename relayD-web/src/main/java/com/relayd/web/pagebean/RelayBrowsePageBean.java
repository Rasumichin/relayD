package com.relayd.web.pagebean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.TreeNode;

import com.relayd.Relay;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeMock;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean
@SessionScoped
public class RelayBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private TreeNode root;

	private Relay selectedRelay;

	private RelayBridge relayBridge = null;

	public RelayBrowsePageBean() {
		relayBridge = new RelayBridgeMock();
		root = relayBridge.allRelays();
	}

	public Relay getSelectedRelay() {
		return selectedRelay;
	}

	public void setSelectedRelay(Relay aSelectedRelays) {
		selectedRelay = aSelectedRelays;
	}

	public TreeNode getRoot() {
		return root;
	}
}