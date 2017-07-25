package com.relayd.web.pagebean;

import java.io.Serializable;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Relayname;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean(name = "relayEditPageBean")
@SessionScoped
public class RelayEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	Relay workingRelay;

	private RelayBridge relayBridge;

	public RelayEditPageBean() {
		relayBridge = new RelayBridgeImpl();
	}

	public void openDialogForCreateRelay(RelayEvent activeRelayEvent) {
		workingRelay = createNewRelay(activeRelayEvent);
		openDialog();
	}

	Relay createNewRelay(RelayEvent relayEvent) {
		Relay relay = Relay.newInstance(relayEvent);
		// TODO - REL-261 - Wie bekommt man die Verknüpfung besser hin?
		relayEvent.addRelay(relay);
		return relay;
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(140).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EDIT_DIALOG_ID, options, null);
	}

	public void openDialogFor(UUID uuid) {
		workingRelay = getRelay(uuid);
		openDialog();
	}

	public void setRelayname(Relayname aRelayname) {
		workingRelay.setRelayname(aRelayname);
	}

	public Relayname getRelayname() {
		return workingRelay.getRelayname();
	}

	public void setDuration(Duration aDuration) {
		workingRelay.setDuration(aDuration);
	}

	public Duration getDuration() {
		return workingRelay.getDuration();
	}

	Relay getRelay(UUID uuid) {
		return getBridge().get(uuid);
	}

	private RelayBridge getBridge() {
		return relayBridge;
	}

	public void save() {
		persistRelay();
		closeDialog();
	}

	void persistRelay() {
		getBridge().set(workingRelay);
	}

	public void cancel() {
		closeDialog();
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingRelay);
	}
}