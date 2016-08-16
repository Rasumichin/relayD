package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
@ManagedBean(name = "relayEditPageBean")
@SessionScoped
public class RelayEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private RelayBridge relayBridge;

	private Relay workingRelay = null;

	private boolean isNewRelay = false;

	public RelayEditPageBean() {
		relayBridge = new RelayBridgeImpl();
	}

	public void openDialogForCreateRelay() {
		workingRelay = Relay.newInstance();
		isNewRelay = true;
		openDialog();
	}

	public void openDialogFor(UUID uuid) {
		workingRelay = getBridge().get(uuid);
		isNewRelay = false;
		openDialog();
	}

	private void openDialog() {
		Map<String, Object> options = createOptions();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EDIT_DIALOG_ID, options, null);
	}

	private Map<String, Object> createOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 640);
		options.put("height", 340);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		return options;
	}

	public void save() {
		if (isNewRelay) {
			getBridge().create(workingRelay);
		} else {
			getBridge().update(workingRelay);
		}
		closeDialog();
	}

	public void cancel() {
		closeDialog();
	}

	private void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingRelay);
	}

	public Relayname getRelayname() {
		return workingRelay.getRelayname();
	}

	public void setRelayname(Relayname aRelayname) {
		workingRelay.setRelayname(aRelayname);
	}

	private RelayBridge getBridge() {
		return relayBridge;
	}
}