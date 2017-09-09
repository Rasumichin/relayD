package com.relayd.web.pagebean.event;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.RelayEvent;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.pagebean.DialogOptionsBuilder;
import com.relayd.web.pagebean.NavigationConstants;

/**
 * @author schmollc
 * @since 27.07.2017
 *
 */
@ManagedBean(name = "relayEventEditAddPersonPageBean")
@SessionScoped
public class RelayEventEditAddPersonPageBean implements Serializable {
	private static final long serialVersionUID = -4345665229617327788L;

	private List<Person> selectedPersons = null;

	private RelayEvent workingRelayEvent = null;

	private PersonBridge personBridge = null;

	public RelayEventEditAddPersonPageBean() {
		personBridge = new PersonBridgeImpl();
	}

	public void openDialogFor(RelayEvent relayEvent) {
		workingRelayEvent = relayEvent;
		openDialog();
	}

	public void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().width(500).height(400).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EVENT_EDIT_ADD_PERSON_DIALOG_ID, options, null);
	}

	public void save() {
		for (Person each : selectedPersons) {
			Participant participant = Participant.newInstance(each);
			workingRelayEvent.addParticipant(participant);
		}
		closeDialog();
	}

	public void cancel() {
		closeDialog();
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public List<Person> getPersons() {
		return personBridge.all();
	}

	public List<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<Person> somePersons) {
		selectedPersons = somePersons;
	}
}