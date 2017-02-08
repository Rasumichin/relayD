package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.relayd.RelayEvent;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayFactory;
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

	private RelayEventGateway gateway = null;

	private RelayEvent selectedRelayEvent = null;

	@ManagedProperty(value = "#{relayEventEditPageBean}")
	private RelayEventEditPageBean relayEventEditPageBean;

	public RelayEventBrowsePageBean() {
		gateway = RelayEventGatewayFactory.get(GatewayType.MEMORY);
	}

	public List<RelayEvent> getRelayEvents() {
		return gateway.getAll();
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
		getRelayEventEditPageBean().openDialog();
	}

	void showMessageNotImplementedYet() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}