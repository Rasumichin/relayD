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
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import com.relayd.Member;
import com.relayd.Person;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;
import com.relayd.web.bridge.RelayEventBridge;
import com.relayd.web.bridge.RelayEventBridgeImpl;
import com.relayd.web.bridge.TreeNodeRow;
import com.relayd.web.bridge.TreeNodeRowRelay;
import com.relayd.web.local.I18N;

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

	private TreeNode root;

	private TreeNode selectedTreeNode;

	private RelayBridge relayBridge;
	private PersonBridge personBridge;
	private RelayEventBridge relayEventBridge;

	private PersonSort personSort = new PersonSort();
	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;
	private List<Person> selectedPersons;

	private EventYear eventYear = EventYear.YEAR_2017;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	@ManagedProperty(value = "#{memberEditPageBean}")
	private MemberEditPageBean memberEditPageBean;

	@PostConstruct
	public void init() {
		relayBridge = new RelayBridgeImpl();
		relayEventBridge = new RelayEventBridgeImpl();
		intiRelays();
		refreshPersons();
	}

	public RelayBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
	}

	private void intiRelays() {
		List<RelayEvent> someRelayEvents = relayEventBridge.all();
		relayEvent = someRelayEvents.get(0);
		root = relayBridge.convertToTreeNode(relayEvent.getRelays());
	}

	private void refreshRelays() {
		root = relayBridge.convertToTreeNode(relayEvent.getRelays());
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
		getRelayEditPageBean().openDialogForCreateRelay(relayEvent);
	}

	public void editRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isRelayTableRowSelected()) {
			showMessageErrorNoRowRelaySelected();
		} else if (isRelayRowSelected()) {
			UUID uuid = getSelectedRelay().getUuid();
			getRelayEditPageBean().openDialogFor(uuid);
		} else {
			UUID uuid = getSelectedMember().getUuid();
			getMemberEditPageBean().openDialogFor(uuid);
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
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW_2);
		} else if (isRelayRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.ONLY_FOR_MEMBER_ROW_POSSIBLE);
		} else if (!isPersonRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_PERSON);
		} else if (!isOnlyOnePersonRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_SINGLE_PERSON);
		} else {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			Member newRelayMember = Member.newInstance(getSelectedPerson());
			selectedRelayNode.setMember(newRelayMember);
			relayBridge.set(selectedTreeNode);
		}
	}

	public void removePersonFromRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isRelayTableRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW_2);
		} else if (isRelayRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.ONLY_FOR_MEMBER_ROW_POSSIBLE);
		} else {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			if (!selectedRelayNode.getMember().isEmpty()) {
				// TODO - REL-277 - Ein remove im Fachobjekt TreeNodeRow einführen
				selectedRelayNode.setMember(Member.newInstance());
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
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.NOT_IMPLEMENTD_YET);
	}

	public void emailExportPerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String output;
		if (isPersonRowSelected()) {
			output = personBridge.getEmailList(getSelectedPersons());
		} else {
			output = personBridge.getEmailList(getPersons());
		}
		showDialog(FacesMessage.SEVERITY_INFO, I18N.EMAIL, output);
	}

	public void emailExportRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRelayRowSelected()) {
			String output = getSelectedRelay().getEmailList();
			showDialog(FacesMessage.SEVERITY_INFO, I18N.EMAIL, output);
		} else {
			showMessageErrorNoRowRelaySelected();
		}
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

	private Member getSelectedMember() {
		TreeNodeRow selectedMemberNode = (TreeNodeRow) selectedTreeNode.getData();
		return selectedMemberNode.getMember();
	}

	void showMessageErrorNoRowPersonSelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW_PERSON);
	}

	void showMessageErrorNoRowRelaySelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW_RELAY);
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

	public EventYear getEventYear() {
		return eventYear;
	}

	public void setEventYear(EventYear aEventYear) {
		eventYear = aEventYear;
	}

	public void switchEventYear(AjaxBehaviorEvent ajax) {
		SelectOneMenu selectOneMenu = (SelectOneMenu) ajax.getSource();
		EventYear selectedEventYear = (EventYear) selectOneMenu.getValue();
		for (RelayEvent eachRelayEvent : relayEventBridge.all()) {
			if (eachRelayEvent.getEventDay().toString().contains(selectedEventYear.getDescription())) {
				setRelayEvent(eachRelayEvent);
			}
		}
		refreshRelays();
	}

	// ***********************************************************************************
	// TODO - REL-278 - Mit dem Domain Objekt funktioniert die Combobox seltsamerweise nicht.
	private List<RelayEvent> relayEvents;
	private RelayEvent relayEvent;

	public RelayEvent getRelayEvent() {
		return relayEvent;
	}

	public void setRelayEvent(RelayEvent aRelayEvent) {
		relayEvent = aRelayEvent;
	}

	public List<RelayEvent> getRelayEvents() {
		return relayEvents;
	}

	public void switchRelayEvent(AjaxBehaviorEvent ajax) {
		SelectOneMenu selectOneMenu = (SelectOneMenu) ajax.getSource();
		RelayEvent selectedRelayEvent = (RelayEvent) selectOneMenu.getValue();
		//		converter="com.relayd.web.converter.RelayEventValueObjectConverter"
	}

	/**
		<p:selectOneMenu
			id="relayEvent"
			value="#{relayBrowsePageBean.relayEvent}"
			var="entry"
			converter="com.relayd.web.converter.RelayEventValueObjectConverter"
			style="width:70%">
			<f:selectItem
				itemLabel="RelayEvent"
				itemValue="" />
			<f:selectItems
				value="#{relayBrowsePageBean.relayEvents}"
				var="relayEvent"
				itemLabel="#{relayEvent.eventDay}"
				itemValue="#{relayEvent.uuid}" />
			<p:column>
				#{entry}
			</p:column>
			<p:ajax
				global="false"
				listener="#{relayBrowsePageBean.switchRelayEvent}" />
		</p:selectOneMenu>
	 */

	public MemberEditPageBean getMemberEditPageBean() {
		return memberEditPageBean;
	}

	public void setMemberEditPageBean(MemberEditPageBean aMemberEditPageBean) {
		memberEditPageBean = aMemberEditPageBean;
	}
}