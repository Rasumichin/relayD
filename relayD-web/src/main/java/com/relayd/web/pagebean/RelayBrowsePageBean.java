package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.relayd.Relay;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.07.2016
 * status initial
 */
@ManagedBean
@SessionScoped
public class RelayBrowsePageBean {

	private RelayBridge relayBridge;

	private List<Relay> searchResult = new ArrayList<Relay>();

	private Relay selected;

	public RelayBrowsePageBean() {
		relayBridge = new RelayBridgeImpl();
	}

	public List<Relay> getRelays() {
		searchResult = relayBridge.all();
		return searchResult;
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public boolean isRowSelected() {
		return getSelectedRelay() != null;
	}

	public Relay getSelectedRelay() {
		return selected;
	}

	public void setSelectedRelay(Relay aSelected) {
		selected = aSelected;
	}

}