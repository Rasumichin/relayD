package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.relayd.RelayEvent;
import com.relayd.web.bridge.RelayEventBridge;
import com.relayd.web.bridge.RelayEventBridgeImpl;
import com.relayd.web.pagebean.event.RelayEventEditPageBean;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.05.2016
 *
 */
@ManagedBean
@SessionScoped
public class RelayEventBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	// TODO -medium- Put String in an I18N class! (or minimal on one position for avoid WET!)
	static final String NOT_POSSIBLE = "Not Possible!";

	private RelayEventBridge relayEventBridge;

	private RelayEvent selectedRelayEvent = null;

	private List<RelayEvent> searchResult = new ArrayList<>();

	@ManagedProperty(value = "#{relayEventEditPageBean}")
	private RelayEventEditPageBean relayEventEditPageBean;

	@PostConstruct
	public void init() {
		refreshRelayEvents();
	}

	public RelayEventBrowsePageBean() {
		relayEventBridge = new RelayEventBridgeImpl();
	}

	public List<RelayEvent> getRelayEvents() {
		return searchResult;
	}

	public RelayEvent getSelectedRelayEvent() {
		return selectedRelayEvent;
	}

	public void setSelectedRelayEvent(RelayEvent aRelayEvent) {
		selectedRelayEvent = aRelayEvent;
	}

	public RelayEventEditPageBean getRelayEventEditPageBean() {
		return relayEventEditPageBean;
	}

	public void setRelayEventEditPageBean(RelayEventEditPageBean aRelayEventEditPageBean) {
		relayEventEditPageBean = aRelayEventEditPageBean;
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEventEditPageBean().openDialogForCreateRelayEvent();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		RelayEvent selectedEvent = getSelectedRelayEvent();
		getRelayEventEditPageBean().openDialogFor(selectedEvent.getUuid());
	}

	void showMessageNotImplementedYet() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		refreshRelayEvents();
	}

	private void refreshRelayEvents() {
		searchResult = getBridge().all();

	}

	private RelayEventBridge getBridge() {
		return relayEventBridge;
	}
}