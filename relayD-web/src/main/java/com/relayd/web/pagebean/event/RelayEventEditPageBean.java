package com.relayd.web.pagebean.event;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.relayd.Participant;
import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.web.bridge.RelayEventBridge;
import com.relayd.web.bridge.RelayEventBridgeImpl;
import com.relayd.web.local.I18N;
import com.relayd.web.pagebean.DialogOptionsBuilder;
import com.relayd.web.pagebean.NavigationConstants;

/**
 * @author schmollc
 * @since 08.02.2017
 *
 */
@ManagedBean(name = "relayEventEditPageBean")
@SessionScoped
public class RelayEventEditPageBean implements Serializable {
	private static final long serialVersionUID = 453304395884163605L;

	private List<Participant> selectedParticipants = null;

	private RelayEventBridge relayEventBridge;

	RelayEvent workingRelayEvent = null;

	@ManagedProperty(value = "#{relayEventEditAddPersonPageBean}")
	private RelayEventEditAddPersonPageBean relayEventEditAddPersonPageBean;

	public RelayEventEditPageBean() {
		relayEventBridge = new RelayEventBridgeImpl();
	}

	public void openDialogFor(UUID uuid) {
		workingRelayEvent = getRelayEvent(uuid);
		openDialog();
	}

	private RelayEvent getRelayEvent(UUID uuid) {
		return getBridge().get(uuid);
	}

	public void openDialogForCreateRelayEvent() {
		prepareNewRelayEvent();
		openDialog();
	}

	void prepareNewRelayEvent() {
		workingRelayEvent = RelayEvent.newInstance();
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().width(500).height(400).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EVENT_EDIT_DIALOG_ID, options, null);
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void cancel() {
		closeDialog();
	}

	public void save() {
		persistRelayEvent();
		closeDialog();
	}

	void showMessageNotImplementedYet() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.NOT_IMPLEMENTD_YET);
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public EventDay getEventDay() {
		return workingRelayEvent.getEventDay();
	}

	public void setEventDay(EventDay aEventDay) {
		workingRelayEvent.setEventDay(aEventDay);
	}

	public Eventname getEventname() {
		return workingRelayEvent.getName();
	}

	public void setEventname(Eventname anEventname) {
		workingRelayEvent.setName(anEventname);
	}

	public Collection<Participant> getParticipants() {
		return workingRelayEvent.getParticipants();
	}

	public List<Participant> getSelectedParticipants() {
		return selectedParticipants;
	}

	public void setSelectedParticipants(List<Participant> someParticipants) {
		selectedParticipants = someParticipants;
	}

	public Integer getNumberOfRelays() {
		return workingRelayEvent.getMaxNumberOfRelays();
	}

	void persistRelayEvent() {
		getBridge().set(workingRelayEvent);
	}

	private RelayEventBridge getBridge() {
		return relayEventBridge;
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEventEditAddPersonPageBean().openDialogFor(workingRelayEvent);
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		for (Participant each : selectedParticipants) {
			workingRelayEvent.removeParticipant(each);
		}
	}

	public void emailExport(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessageNotImplementedYet();
	}

	public RelayEventEditAddPersonPageBean getRelayEventEditAddPersonPageBean() {
		return relayEventEditAddPersonPageBean;
	}

	public void setRelayEventEditAddPersonPageBean(RelayEventEditAddPersonPageBean aRelayEventEditAddPersonPageBean) {
		relayEventEditAddPersonPageBean = aRelayEventEditAddPersonPageBean;
	}
}