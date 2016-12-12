package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.TreeNodeRow;

/**
 * @author schmollc (Christian@relayd.de)
 * @author thorsten (Thorsten@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean
@SessionScoped
public class RelayBrowseMapPageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String NOT_POSSIBLE = "Not Possible!";
	private static final String PLEASE_SELECT_A_ROW_PERSON = "Please select one person row!";
	private static final String PLEASE_SELECT_A_ROW_RELAY = "Please select one relay row!";

	private TreeNode root;

	private TreeNode selectedTreeNode;

	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;

	private List<Person> selectedPersons;

	@PostConstruct
	public void init() {
		refreshRelays();
		refreshPersons();
	}

	public RelayBrowseMapPageBean() {
	}

	private void refreshRelays() {
		root = allRelays();
	}

	private List<Person> allPersons() {
		List<Person> somePersons = new ArrayList<Person>();
		Person justusJonas = Person.newInstance();
		justusJonas.setForename(Forename.newInstance("Justus"));
		justusJonas.setSurename(Surename.newInstance("Jonas"));
		somePersons.add(justusJonas);

		Person peterShaw = Person.newInstance();
		peterShaw.setForename(Forename.newInstance("Peter"));
		peterShaw.setSurename(Surename.newInstance("Shaw"));
		somePersons.add(peterShaw);

		Person bobAndrews = Person.newInstance();
		bobAndrews.setForename(Forename.newInstance("Bob"));
		bobAndrews.setSurename(Surename.newInstance("Andrews"));
		somePersons.add(bobAndrews);

		return somePersons;
	}

	private TreeNode allRelays() {
		TreeNode root = new DefaultTreeNode(TreeNodeRow.newInstance(Participant.newInstance(), Position.UNKNOWN), null);

		Relay relay = Relay.newInstance(RelayEvent.duesseldorf());
		relay.setRelayname(Relayname.newInstance("Staubwolke"));

		TreeNode relayTreeNode = new DefaultTreeNode(TreeNodeRow.newInstance(relay), root);

		@SuppressWarnings("unused")
		TreeNode trackOne = new DefaultTreeNode("Etappe 1", TreeNodeRow.newInstance(relay.getParticipantFor(Position.FIRST), Position.FIRST), relayTreeNode);
		@SuppressWarnings("unused")
		TreeNode trackTwo = new DefaultTreeNode("Etappe 2", TreeNodeRow.newInstance(relay.getParticipantFor(Position.SECOND), Position.SECOND), relayTreeNode);
		@SuppressWarnings("unused")
		TreeNode trackThree = new DefaultTreeNode("Etappe 3", TreeNodeRow.newInstance(relay.getParticipantFor(Position.THIRD), Position.THIRD), relayTreeNode);
		@SuppressWarnings("unused")
		TreeNode trackFour = new DefaultTreeNode("Etappe 4", TreeNodeRow.newInstance(relay.getParticipantFor(Position.FOURTH), Position.FOURTH), relayTreeNode);
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedTreeNode;
	}

	public void setSelectedNode(TreeNode aSelectedRelays) {
		selectedTreeNode = aSelectedRelays;
	}

	public TreeNode getRoot() {
		return root;
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Person> getFilteredPersons() {
		return filteredPersons;
	}

	public void setFilteredPersons(List<Person> someFilteredPersons) {
		filteredPersons = someFilteredPersons;
	}

	public List<Person> getPersons() {
		return searchResult;
	}

	public List<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<Person> someSelectedPersons) {
		selectedPersons = someSelectedPersons;
	}

	void refreshPersons() {
		searchResult = allPersons();
	}

	boolean isRelayRowSelected() {
		if (selectedTreeNode != null) {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			return selectedRelayNode.isRelay();
		}
		return false;
	}

	public boolean isRemovingPersonFromRelayAllowed() {
		return false;
	}

	void showMessageErrorNoRowPersonSelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, PLEASE_SELECT_A_ROW_PERSON);
	}

	void showMessageErrorNoRowRelaySelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, PLEASE_SELECT_A_ROW_RELAY);
	}

	public void onPersonDrop(DragDropEvent ddEvent) {
		System.out.println("Im onPersonDrop");

		Person person = (Person) ddEvent.getData();

		Participant participant = createParticipant(person);
		TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();

		Relay relay = selectedRelayNode.getRelay();
		relay.addParticipant(participant);
	}

	private Participant createParticipant(Person person) {
		Participant newRelayParticipant = Participant.newInstance(person.getForename(), person.getSurename(), person.getUuid());
		return newRelayParticipant;
	}
}