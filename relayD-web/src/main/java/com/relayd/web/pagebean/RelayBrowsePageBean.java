package com.relayd.web.pagebean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.TreeNode;

import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeMock;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean
@SessionScoped
public class RelayBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String NOT_POSSIBLE = "Not Possible!";

	private TreeNode root;

	private TreeNode selectedTreeNode;

	private RelayBridge relayBridge = null;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	public RelayBrowsePageBean() {
		relayBridge = new RelayBridgeMock();
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

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEditPageBean().openDialogForCreateRelay();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void cancelEditDialog() {
		getRelayEditPageBean().cancel();
	}
}