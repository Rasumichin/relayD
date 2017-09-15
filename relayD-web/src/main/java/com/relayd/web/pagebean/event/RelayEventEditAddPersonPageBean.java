package com.relayd.web.pagebean.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.RelayEventBridge;
import com.relayd.web.bridge.RelayEventBridgeImpl;
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
	private RelayEventBridge relayEventBridge = null;

	private List<Person> searchResult = new ArrayList<>();

	public RelayEventEditAddPersonPageBean() {
		personBridge = new PersonBridgeImpl();
		relayEventBridge = new RelayEventBridgeImpl();
	}

	public void openDialogFor(RelayEvent relayEvent) {
		workingRelayEvent = relayEvent;
		init();
		openDialog();
	}

	private void init() {
		searchResult = getPersonBridge().all();
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
		persistRelayEvent();
		closeDialog();
	}

	void persistRelayEvent() {
		getRelayEventBridge().set(workingRelayEvent);
	}

	public void cancel() {
		closeDialog();
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingRelayEvent);
	}

	public List<Person> getPersons() {
		return searchResult;
	}

	public List<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<Person> somePersons) {
		selectedPersons = somePersons;
	}

	private PersonBridge getPersonBridge() {
		return personBridge;
	}

	private RelayEventBridge getRelayEventBridge() {
		return relayEventBridge;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return Forename.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return Surename.sortBySurename(name1, name2);
	}
}