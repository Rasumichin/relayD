package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import com.relayd.Participant;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;
import com.relayd.web.bridge.TreeNodeRow;

/**
 * @author schmollc (Christian@relayd.de)
 * @author thorsten (Thorsten@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean
@SessionScoped
public class RelayBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String NOT_POSSIBLE = "Not Possible!";
	private static final String PLEASE_SELECT_A_ROW_PERSON = "Please select one person row!";
	private static final String PLEASE_SELECT_A_ROW_RELAY = "Please select one relay row!";

	private TreeNode root;

	private TreeNode selectedTreeNode;

	private RelayBridge relayBridge = null;
	private PersonBridge personBridge = null;

	private PersonSort personSort = new PersonSort();
	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;

	private List<Person> selectedPersons;

	private boolean canceled;
	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	@PostConstruct
	public void init() {
		refreshRelays();
		refreshPersons();
	}

	public RelayBrowsePageBean() {
		relayBridge = new RelayBridgeImpl();
		personBridge = new PersonBridgeImpl();
	}

	private void refreshRelays() {
		root = relayBridge.all();
	}

	public RelayEditPageBean getRelayEditPageBean() {
		return relayEditPageBean;
	}

	public void setRelayEditPageBean(RelayEditPageBean aRelayEditPageBean) {
		relayEditPageBean = aRelayEditPageBean;
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

	public void addRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEditPageBean().openDialogForCreateRelay();
	}

	public void addPersonToRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		System.out.println("addPersonToRelay");

		TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();

		if (selectedTreeNode != null) {
			if (selectedRelayNode.isRelay()) {
				if (isOnlyOnePersonRowSelected()) {
					Person selectedPerson = getSelectedPerson();
					System.out.println("Person selected: " + selectedPerson.toString());

				}

				showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Pleasê select a RelayPosition!");

			} else {
				System.out.println("No Relay selected - check if Participant is selected");
				if (selectedRelayNode.getParticipant() != null) {
					System.out.println("Relay Participant selected: " + selectedRelayNode.getParticipant().toString());
					if (isOnlyOnePersonRowSelected()) {
						Person selectedPerson = getSelectedPerson();
						System.out.println("Person selected: " + selectedPerson.toString());

						Participant newRelayParticipant = Participant.newInstance();
						newRelayParticipant.setUuidPerson(selectedPerson.getUuid());
						newRelayParticipant.setForename(selectedPerson.getForename());
						newRelayParticipant.setSurename(selectedPerson.getSurename());

						selectedRelayNode.setParticipant(newRelayParticipant);
						// TODO (Christian, Version 1.3): REMOVE!!!!! ONLY FOR TESTING THE SERVER VERSION!!
						relayBridge.persist(selectedTreeNode);
					}

				} else {
					System.out.println("Unknown Participant selected");

					if (isOnlyOnePersonRowSelected()) {
						Person selectedPerson = getSelectedPerson();
						System.out.println("Person selected: " + selectedPerson.toString());

						Participant newRelayParticipant = Participant.newInstance();
						newRelayParticipant.setUuidPerson(selectedPerson.getUuid());
						newRelayParticipant.setForename(selectedPerson.getForename());
						newRelayParticipant.setSurename(selectedPerson.getSurename());

						selectedRelayNode.setParticipant(newRelayParticipant);

						// TODO (Christian, Version 1.3): REMOVE!!!!! ONLY FOR TESTING THE SERVER VERSION!!
						relayBridge.persist(selectedTreeNode);
					}
				}
			}

		}

	}

	public void editRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRelayRowSelected()) {
			UUID uuid = getSelectedRelay().getUuid();
			getRelayEditPageBean().openDialogFor(uuid);
		} else {
			showMessageErrorNoRowRelaySelected();
		}
	}

	public void removeRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	public void removePersonFromRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		System.out.println("removePersonFromRelay");

		if (selectedTreeNode != null) {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();

			if (selectedRelayNode.getParticipant() != null) {
				System.out.println("Relay Participant selected: " + selectedRelayNode.getParticipant().toString());
				selectedRelayNode.setParticipant(null);
			} else {
				showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Please select a RelayParticipant!");
			}
		}

	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void cancelEditDialog() {
		getRelayEditPageBean().cancel();
	}

	public void onRelayEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshRelays();
	}

	public void onAddPersonToRelayClosed(@SuppressWarnings("unused") SelectEvent event) {
		System.out.println("onAddPersonToRelayClosed");
	}

	public void onRemovePersonFromRelayClosed(@SuppressWarnings("unused") SelectEvent event) {
		System.out.println("onRemovePersonFromRelayClosed");
	}

	public void showAllWithoutRelay() {
	}

	public void showRelaysWithSpace() {
	}

	public void showAll() {
		refreshPersons();
	}

	public void emailExport(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String output = null;
		if (isPersonRowSelected()) {
			output = personBridge.getEmailList(getSelectedPersons());
		} else {
			output = personBridge.getEmailList(getPersons());
		}
		showMessage(FacesMessage.SEVERITY_INFO, "Email", output);
	}

	public void onPersonEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		if (canceled) {
			showMessage(FacesMessage.SEVERITY_INFO, "Canceled!", "");
			canceled = false;
		} else if (isOnlyOnePersonRowSelected()) {
			showMessage(FacesMessage.SEVERITY_INFO, "Saved!", getSelectedPerson().toString());
		} else {
			showMessage(FacesMessage.SEVERITY_INFO, "Added!", "");
		}

		refreshPersons();
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

	public int sortByForename(Forename name1, Forename name2) {
		return personSort.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return personSort.sortBySurename(name1, name2);
	}

	void refreshPersons() {
		searchResult = personBridge.all();
	}

	private boolean isOnlyOnePersonRowSelected() {
		return getSelectedPersons() != null && getSelectedPersons().size() == 1;
	}

	private Person getSelectedPerson() {
		return selectedPersons.get(0);
	}

	private boolean isPersonRowSelected() {
		return getSelectedPersons() != null && !getSelectedPersons().isEmpty();
	}

	private Relay getSelectedRelay() {
		TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
		return selectedRelayNode.getRelay();
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

	public void addPerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogForCreatePerson();
	}

	public void editPerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isOnlyOnePersonRowSelected()) {
			UUID uuid = getSelectedPerson().getUuid();
			getPersonEditPageBean().openDialogFor(uuid);
		} else {
			showMessageErrorNoRowPersonSelected();
		}
	}

	public void removePerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isOnlyOnePersonRowSelected()) {
			personBridge.remove(getSelectedPerson());
			showMessage(FacesMessage.SEVERITY_INFO, "Success", "Remove" + getSelectedPerson().toString());
			refreshPersons();
		} else {
			showMessageErrorNoRowPersonSelected();
		}
	}

	public PersonEditPageBean getPersonEditPageBean() {
		return personEditPageBean;
	}

	public void setPersonEditPageBean(PersonEditPageBean aPersonEditPageBean) {
		personEditPageBean = aPersonEditPageBean;
	}

	void showMessageErrorNoRowPersonSelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, PLEASE_SELECT_A_ROW_PERSON);
	}

	void showMessageErrorNoRowRelaySelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, PLEASE_SELECT_A_ROW_RELAY);
	}

}