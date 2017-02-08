package com.relayd.web.pagebean.event;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.web.pagebean.DialogOptionsBuilder;
import com.relayd.web.pagebean.NavigationConstants;

/**
 * @author schmollc
 * @since 08.02.2017
 *
 */
@ManagedBean(name = "relayEventEditPageBean")
@SessionScoped
public class RelayEventEditPageBean implements Serializable {
	private static final long serialVersionUID = 453304395884163605L;
	// TODO -medium- Put String in an I18N class! (or minimal on one position for avoid WET!)
	static final String NOT_POSSIBLE = "Not Possible!";

	RelayEvent workingRelayEvent = null;

	public void openDialogForCreateRelayEvent() {
		prepareNewRelayEvent();
		openDialog();
	}

	void prepareNewRelayEvent() {
		workingRelayEvent = RelayEvent.newInstance();
	}

	public void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(400).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EVENT_EDIT_DIALOG_ID, options, null);
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(null);
	}

	public void cancel() {
		closeDialog();
	}

	public void save() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	void showMessageNotImplementedYet() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, "Not implemented yet!");
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public EventDay getEventDay() {
		return workingRelayEvent.getEventDay();
	}

	public void setEventDay(EventDay aEventDay) {
		workingRelayEvent.setEventDay(aEventDay);
	}

}