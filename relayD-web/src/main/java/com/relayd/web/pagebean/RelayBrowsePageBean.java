package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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

	private List<Relay> searchResult = new ArrayList<Relay>();

	private Relay selectedRelay;

	private RelayBridge relayBridge = null;

	public RelayBrowsePageBean() {
		relayBridge = new RelayBridgeMock();
	}

	@PostConstruct
	public void init() {
		refreshRelays();
	}

	public void refreshRelays() {
		searchResult = relayBridge.all();
	}

	public List<Relay> getRelays() {
		return searchResult;
	}

	public Relay getSelectedRelay() {
		return selectedRelay;
	}

	public void setSelectedRelay(Relay aSelectedRelays) {
		selectedRelay = aSelectedRelays;
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}
}