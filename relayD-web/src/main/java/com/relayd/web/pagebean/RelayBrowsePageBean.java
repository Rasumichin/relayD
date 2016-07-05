package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;
import com.relayd.web.bridge.RelayRow;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 03.07.2016
 * status initial
 */
@ManagedBean
@SessionScoped
public class RelayBrowsePageBean {

	private RelayBridge relayBridge;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	private List<RelayRow> searchResult = new ArrayList<RelayRow>();

	private RelayRow selected;

	public RelayBrowsePageBean() {
		relayBridge = new RelayBridgeImpl();
	}

	public List<RelayRow> getRelays() {
		searchResult = relayBridge.all();
		return searchResult;
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public boolean isRowSelected() {
		return getSelectedRelayRow() != null;
	}

	public RelayRow getSelectedRelayRow() {
		return selected;
	}

	public void setSelectedRelayRow(RelayRow aSelected) {
		selected = aSelected;
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEditPageBean().openDialogForCreateRelay();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEditPageBean().openDialogFor(getSelectedRelayRow().getUUID());
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// relayBridge.remove(getSelectedRelay());
		showMessage("remove not implemented yet!");
	}

	public void onEditClosed(SelectEvent event) {
		// TODO -schmollc- Ist diese Methode noetig?
	}

	private void showMessage(String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public RelayEditPageBean getRelayEditPageBean() {
		return relayEditPageBean;
	}

	public void setRelayEditPageBean(RelayEditPageBean aRelayEditPageBean) {
		relayEditPageBean = aRelayEditPageBean;
	}
}