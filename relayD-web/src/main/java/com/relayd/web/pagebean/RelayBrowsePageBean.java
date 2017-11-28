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
import com.relayd.Participant;
import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.ParticipantBridge;
import com.relayd.web.bridge.ParticipantBridgeImpl;
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
	private RelayEventBridge relayEventBridge;
	private ParticipantBridge participantBridge;

	private List<Participant> participants = new ArrayList<>();
	private List<Participant> filteredParticipants;
	private List<Participant> selectedParticipants;

	private RelayEvent selectedRelayEvent;
	private RelayEventDisplay relayEventDisplay;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	@ManagedProperty(value = "#{memberEditPageBean}")
	private MemberEditPageBean memberEditPageBean;

	@ManagedProperty(value = "#{participantEditPageBean}")
	private ParticipantEditPageBean participantEditPageBean;

	@PostConstruct
	public void init() {
		relayBridge = new RelayBridgeImpl();
		relayEventBridge = new RelayEventBridgeImpl();
		participantBridge = new ParticipantBridgeImpl();

		initRelays();
		refreshParticipants();
	}

	private void initRelays() {
		for (RelayEvent eachRelayEvent : relayEventBridge.all()) {
			setSelectedRelayEvent(eachRelayEvent);
			RelayEventDisplay relayEventDisplayInit = RelayEventDisplay.newInstance(eachRelayEvent.getUuid(), eachRelayEvent.toString());
			setRelayEventDisplay(relayEventDisplayInit);
			break;
		}
		refreshRelays();
	}

	void refreshParticipants() {
		for (RelayEvent eachRelayEvent : relayEventBridge.all()) {
			if (selectedRelayEvent.equals(eachRelayEvent)) {
				selectedRelayEvent = eachRelayEvent;
			}
		}
		participants = new ArrayList<>(getSelectedRelayEvent().getParticipants());
		refreshRelays();
	}

	private void refreshRelays() {
		root = relayBridge.convertToTreeNode(getSelectedRelayEvent().getRelays());
	}

	private RelayEvent getSelectedRelayEvent() {
		return selectedRelayEvent;
	}

	private void setSelectedRelayEvent(RelayEvent aSelectedRelayEvent) {
		selectedRelayEvent = aSelectedRelayEvent;
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
		getRelayEditPageBean().openDialogForCreateRelay(getSelectedRelayEvent());
	}

	public boolean addRelayPossible() {
		if (selectedRelayEvent.getMaxNumberOfRelays().intValue() == selectedRelayEvent.getNumberOfRelays()) {
			return true;
		}
		return false;
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

	public void editParticipant(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isParticipantRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_PERSON);
		} else if (!isOnlyOneParticipantRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_SINGLE_PERSON);
		} else {
			UUID uuid = getSelectedParticipant().getUuid();
			getParticipantEditPageBean().openDialogFor(uuid);
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

	public void addParticipantToRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (!isRelayTableRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW_2);
		} else if (isRelayRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.ONLY_FOR_MEMBER_ROW_POSSIBLE);
		} else if (!isParticipantRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_PERSON);
		} else if (!isOnlyOneParticipantRowSelected()) {
			showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_SINGLE_PERSON);
		} else {
			TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
			Member newRelayMember = Member.newInstance(getSelectedParticipant());
			selectedRelayNode.setMember(newRelayMember);
			relayBridge.set(selectedTreeNode);
		}
	}

	public void removeParticipantFromRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
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

	public void onParticpantEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshParticipants();
	}

	public void showAllRelays() {
		refreshRelays();
	}

	public void showAllParticipants() {
		refreshParticipants();
	}

	public void showAllWithoutRelay() {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.NOT_IMPLEMENTD_YET);
	}

	public void emailExportParticipant(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.NOT_IMPLEMENTD_YET);
		String output;
		if (isParticipantRowSelected()) {
			output = participantBridge.getEmailList(getSelectedParticipants());
		} else {
			output = participantBridge.getEmailList(getParticipants());
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

	public List<Participant> getFilteredParticipants() {
		return filteredParticipants;
	}

	public void setFilteredParticipants(List<Participant> someFilteredParticipants) {
		filteredParticipants = someFilteredParticipants;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public List<Participant> getSelectedParticipants() {
		return selectedParticipants;
	}

	public void setSelectedParticipants(List<Participant> someSelectedParticipants) {
		selectedParticipants = someSelectedParticipants;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return Forename.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return Surename.sortBySurename(name1, name2);
	}

	private boolean isOnlyOneParticipantRowSelected() {
		return getSelectedParticipants() != null && getSelectedParticipants().size() == 1;
	}

	private Participant getSelectedParticipant() {
		return selectedParticipants.get(0);
	}

	private boolean isParticipantRowSelected() {
		return getSelectedParticipants() != null && !getSelectedParticipants().isEmpty();
	}

	private Relay getSelectedRelay() {
		TreeNodeRow selectedRelayNode = (TreeNodeRow) selectedTreeNode.getData();
		return selectedRelayNode.getRelay();
	}

	private Member getSelectedMember() {
		TreeNodeRow selectedMemberNode = (TreeNodeRow) selectedTreeNode.getData();
		return selectedMemberNode.getMember();
	}

	void showMessageErrorNoRowParticipantSelected() {
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

	public String relayCountOf(Participant aParticipant) {
		int relayCount = belongsToRelay(aParticipant);
		return "(" + relayCount + ")";
	}

	public String statusOf(Participant aParticipant) {
		int relayCount = belongsToRelay(aParticipant);
		if (relayCount > 0) {
			return "ui-icon ui-icon-check";
		} else {
			return "ui-icon ui-icon-help";
		}
	}

	private int belongsToRelay(Participant aParticipant) {
		int relayCount = 0;
		List<TreeNode> tempList = root.getChildren();
		for (TreeNode treeNode : tempList) {
			TreeNodeRow relayNode = (TreeNodeRow) treeNode.getData();
			if (relayNode.isRelay()) {
				Relay relay = ((TreeNodeRowRelay) relayNode).getRelay();
				for (Member participant : relay.getMembers()) {
					if (aParticipant.getUuidPerson().equals(participant.getUuidPerson())) {
						relayCount++;
					}
				}
			} else {
				if (relayNode.getMember() != null) {
					if (aParticipant.getUuidPerson().equals(relayNode.getMember().getUuidPerson())) {
						relayCount++;
					}
				}
			}
		}
		return relayCount;
	}

	public RelayEventDisplay getRelayEventDisplay() {
		return relayEventDisplay;
	}

	public void setRelayEventDisplay(RelayEventDisplay aRelayEventDisplay) {
		relayEventDisplay = aRelayEventDisplay;
	}

	public List<RelayEventDisplay> getRelayEventDisplays() {
		List<RelayEventDisplay> someDisplays = new ArrayList<>();
		for (RelayEvent eachRelayEvent : relayEventBridge.all()) {
			RelayEventDisplay display = RelayEventDisplay.newInstance(eachRelayEvent.getUuid(), eachRelayEvent.toString());
			someDisplays.add(display);
		}
		return someDisplays;

	}

	public void switchRelayEventDisplay(AjaxBehaviorEvent ajax) {
		SelectOneMenu selectOneMenu = (SelectOneMenu) ajax.getSource();
		RelayEventDisplay selectedRelayEventDisplay = (RelayEventDisplay) selectOneMenu.getValue();
		for (RelayEvent eachRelayEvent : relayEventBridge.all()) {
			if (eachRelayEvent.getUuid().equals(selectedRelayEventDisplay.getUuid())) {
				setSelectedRelayEvent(eachRelayEvent);
			}
		}
		refreshRelays();
		refreshParticipants();
	}

	public MemberEditPageBean getMemberEditPageBean() {
		return memberEditPageBean;
	}

	public void setMemberEditPageBean(MemberEditPageBean aMemberEditPageBean) {
		memberEditPageBean = aMemberEditPageBean;
	}

	public ParticipantEditPageBean getParticipantEditPageBean() {
		return participantEditPageBean;
	}

	public void setParticipantEditPageBean(ParticipantEditPageBean aParticipantEditPageBean) {
		participantEditPageBean = aParticipantEditPageBean;
	}

	public Integer getParticipantSum() {
		return selectedRelayEvent.getNumberOfParticipants();
	}

	public Integer getRelaysSum() {
		return selectedRelayEvent.getNumberOfRelays();
	}
}