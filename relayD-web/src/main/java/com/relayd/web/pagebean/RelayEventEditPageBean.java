package com.relayd.web.pagebean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
@SessionScoped
@ManagedBean
public class RelayEventEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String RELAY_EVENT_DIALOG_ID = "relayEventDialog";

	private RelayEventGateway gateway = null;

	// TODO -ALL- Ist das "nur" der primitive Datentyp String oder das Objekt EventName? Macht aber Probleme mit Vorbelegung.
	private String relayEventName = null;
	private Date relayEventDate = null;

	public RelayEventEditPageBean() {
		gateway = RelayEventGatewayFactory.get(GatewayType.MEMORY);
	}

	private RelayEventGateway getGateway() {
		return gateway;
	}

	public Date getDate() {
		return relayEventDate;
	}

	public void setDate(Date aDate) {
		relayEventDate = aDate;
	}

	public String getName() {
		return relayEventName;
	}

	public void setName(String aName) {
		relayEventName = aName;
	}

	public void openDialogForCreateRelayEvent() {
		openDialog();
	}

	public void openDialogFor(UUID uuid) {
		RelayEvent relayEvent = gateway.get(uuid);

		relayEventName = relayEvent.getName().toString();

		relayEventDate = new Date();
		LocalDate localDate = relayEvent.getEventDay().getValue();
		relayEventDate.setDate(localDate.getDayOfMonth());
		relayEventDate.setMonth(localDate.getMonth().getValue());
		relayEventDate.setYear(localDate.getYear());

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
		RelayEvent relayEvent = createRelayEvent();

		save(relayEvent);

		closeDialog();
	}

	RelayEvent createRelayEvent() {
		EventName eventName = new EventName(getName());

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(getDate().getTime());
		LocalDate localDate = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

		EventDay eventDay = new EventDay(localDate);

		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);

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
}