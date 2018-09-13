package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.relayd.web.bridge.RelayEventBridge;
import com.relayd.web.bridge.RelayEventBridgeImpl;

/**
 * @author  schmollc (Christian@relayd.com)
 * @since   13.09.2018
 *
 */
@ManagedBean
@SessionScoped
public class DockPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private RelayEventBridge relayEventBridge;

	@PostConstruct
	public void init() {
		relayEventBridge = new RelayEventBridgeImpl();
	}

	public boolean noEventExist() {
		List<RelayEventDisplay> relayEvents = relayEventBridge.allDisplays();
		boolean isEmpty = relayEvents.isEmpty();
		return isEmpty;
	}
}