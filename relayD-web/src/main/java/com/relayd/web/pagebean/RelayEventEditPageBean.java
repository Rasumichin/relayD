package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

	private RelayEvent workingEvent = null;

	public RelayEventEditPageBean() {
		gateway = RelayEventGatewayFactory.get(GatewayType.FILE);
	}

	public void openDialogForCreateRelayEvent() {
		workingEvent = new RelayEvent(null, null);
		openDialog();
	}

	public void openDialogFor(UUID uuid) {
		workingEvent = gateway.get(uuid);
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
		getGateway().set(workingEvent);
		closeDialog();
	}

	public void cancel() {
		closeDialog();
	}

	private void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingEvent);
	}

	public EventName getName() {
		return workingEvent.getName();
	}

	public void setName(EventName anEventName) {
		workingEvent.setName(anEventName);
	}

	public EventDay getDate() {
		return workingEvent.getEventDay();
	}

	public void setDate(EventDay aDay) {
		workingEvent.setEventDay(aDay);
	}

	private RelayEventGateway getGateway() {
		return gateway;
	}
}