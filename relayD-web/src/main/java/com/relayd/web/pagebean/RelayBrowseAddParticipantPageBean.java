package com.relayd.web.pagebean;

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

/**
 * @author schmollc
 * @since 21.02.2018
 *
 */
@ManagedBean(name = "relayBrowseAddParticipantPageBean")
@SessionScoped
public class RelayBrowseAddParticipantPageBean implements Serializable {
	private static final long serialVersionUID = 4415212951351263072L;

	private List<Person> selectedPersons = null;

	private RelayEvent workingRelayEvent = null;

	private PersonBridge personBridge = null;
	private RelayEventBridge relayEventBridge = null;

	private List<Person> searchResult = new ArrayList<>();

	public RelayBrowseAddParticipantPageBean() {
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
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_BROWSE_ADD_PARTICIPANT_DIALOG_ID, options, null);
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