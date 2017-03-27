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

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;
import com.relayd.web.bridge.TreeNodeRow;
import com.relayd.web.bridge.TreeNodeRowRelay;

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
	static final String NOT_POSSIBLE = "Not Possible!";
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

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	@PostConstruct
	public void init() {
		relayBridge = new RelayBridgeImpl();
		refreshRelays();
		refreshPersons();
	}

	public RelayBrowsePageBean() {
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

	public void editRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRelayRowSelected()) {
			UUID uuid = getSelectedRelay().getUuid();
			getRelayEditPageBean().openDialogFor(uuid);
		} else {
			showMessageErrorNoRowRelaySelected();
		}
	}

	boolean isRelayRowSelected() {
		if (selectedTreeNode != null) {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			return selectedRelayNode.isRelay();
		}
		return false;
	}

	boolean isRelayTableRowSelected() {
		if (selectedTreeNode != null) {
			return true;
		}
		return false;
	}

	public void addPersonToRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isRelayTableRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Please select a Row!");
		} else if (isRelayRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Only for Member Row possible!");
		} else if (!isPersonRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Please select a Person!");
		} else if (!isOnlyOnePersonRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Please select a single Person!");
		} else {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			Member newRelayMember = Member.newInstance(getSelectedPerson());
			selectedRelayNode.setMember(newRelayMember);
			// TODO (Christian, Version 1.4): REMOVE!!!!! ONLY FOR TESTING THE SERVER VERSION!!
			relayBridge.set(selectedTreeNode);
		}
	}

	public void removePersonFromRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isRelayTableRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Please select a Row!");
		} else if (isRelayRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Only for Member Row possible!");
		} else {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			if (!selectedRelayNode.getMember().isEmpty()) {
				// TODO (Christian, Erik Version 1.4): ein remove im Fachobjekt TreeNodeRow einführen
				selectedRelayNode.setMember(Member.newInstance());
				// TODO (Christian, Version 1.4): REMOVE!!!!! ONLY FOR TESTING THE SERVER VERSION!!
				relayBridge.set(selectedTreeNode);
			}
		}
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage("messages", message);
	}

	void showDialog(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void cancelEditDialog() {
		getRelayEditPageBean().cancel();
	}

	public void onRelayEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshRelays();
	}

	public void showAllRelays() {
		refreshRelays();
	}

	public void showAllPersons() {
		refreshPersons();
	}

	public void showAllWithoutRelay() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	public void emailExportPerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String output;
		if (isPersonRowSelected()) {
			output = personBridge.getEmailList(getSelectedPersons());
		} else {
			output = personBridge.getEmailList(getPersons());
		}
		showDialog(FacesMessage.SEVERITY_INFO, "Email", output);
	}

	public void emailExportRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRelayRowSelected()) {
			String output = getSelectedRelay().getEmailList();
			showDialog(FacesMessage.SEVERITY_INFO, "Email", output);
		} else {
			showMessageErrorNoRowRelaySelected();
		}
	}

	public void onPersonEditClosed(@SuppressWarnings("unused") SelectEvent event) {
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

	public void openAllRelays(@SuppressWarnings("unused") ActionEvent actionEvent) {
		boolean openAllNodes = true;
		collapsingORexpanding(root, openAllNodes);
	}

	public void closeAllRelays(@SuppressWarnings("unused") ActionEvent actionEvent) {
		boolean openAllNodes = false;
		collapsingORexpanding(root, openAllNodes);
	}

	public void collapsingORexpanding(TreeNode treeNode, boolean option) {
		if (treeNode.getChildren().isEmpty()) {
			treeNode.setSelected(false);
		} else {
			for (TreeNode s : treeNode.getChildren()) {
				collapsingORexpanding(s, option);
			}
			treeNode.setExpanded(option);
			treeNode.setSelected(false);
		}
	}

	public String relayCountOf(Person aPerson) {
		int relayCount = belongsToRelay(aPerson);
		return "(" + relayCount + ")";
	}

	public String statusOf(Person aPerson) {
		int relayCount = belongsToRelay(aPerson);
		if (relayCount > 0) {
			return "ui-icon ui-icon-check";
		} else {
			return "ui-icon ui-icon-help";
		}
	}

	private int belongsToRelay(Person aPerson) {
		int relayCount = 0;
		List<TreeNode> tempList = root.getChildren();
		for (TreeNode treeNode : tempList) {
			TreeNodeRow relayNode = (TreeNodeRow) treeNode.getData();
			if (relayNode.isRelay()) {
				Relay relay = ((TreeNodeRowRelay) relayNode).getRelay();
				for (Member participant : relay.getMembers()) {
					if (aPerson.getUuid().equals(participant.getUuidPerson())) {
						relayCount++;
					}
				}
			} else {
				if (relayNode.getMember() != null) {
					if (aPerson.getUuid().equals(relayNode.getMember().getUuidPerson())) {
						relayCount++;
					}
				}

			}
		}
		return relayCount;
	}
}