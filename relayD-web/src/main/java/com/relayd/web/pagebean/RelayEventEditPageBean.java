package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.EventName;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayFactory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 14.06.2016
 * status initial
 */
@ManagedBean(name = "relayEventEditPageBean")
@SessionScoped
public class RelayEventEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String RELAY_EVENT_DIALOG_ID = "relayEventDialog";

	private RelayEventGateway gateway = null;

	private EventName eventName = null;
	private EventDay eventDate = null;

	public RelayEventEditPageBean() {
		gateway = RelayEventGatewayFactory.get(GatewayType.FILE);
	}

	public void openDialogForCreateRelayEvent() {
		openDialog();
	}

	public void openDialogFor(UUID uuid) {
		RelayEvent relayEvent = gateway.get(uuid);

		setName(relayEvent.getName());
		setDate(relayEvent.getEventDay());

		openDialog();
	}

	private void openDialog() {
		Map<String, Object> options = createOptions();

		RequestContext.getCurrentInstance().openDialog(RELAY_EVENT_DIALOG_ID, options, null);
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
		// TODO checken ob relay event schon existiert. ... Dann kein create!
		RelayEvent relayEvent = createRelayEvent();

		save(relayEvent);

		closeDialog();
	}

	RelayEvent createRelayEvent() {
		RelayEvent relayEvent = new RelayEvent(getName(), getDate());

		return relayEvent;
	}

	private void save(RelayEvent relayEvent) {
		getGateway().set(relayEvent);
	}

	public void cancel() {
		closeDialog();
	}

	private void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(RELAY_EVENT_DIALOG_ID);
	}

	public EventName getName() {
		return eventName;
	}

	public void setName(EventName aEventName) {
		eventName = aEventName;
	}

	private RelayEventGateway getGateway() {
		return gateway;
	}

	public EventDay getDate() {
		return eventDate;
	}

	public void setDate(EventDay aDate) {
		eventDate = aDate;
	}

}