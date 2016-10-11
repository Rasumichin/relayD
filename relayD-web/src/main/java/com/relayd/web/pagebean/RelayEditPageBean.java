package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.relayd.Relay;
import com.relayd.attributes.Relayname;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 *
 */
@ManagedBean(name = "relayEditPageBean")
@SessionScoped
public class RelayEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Relay workingRelay;

	public RelayEditPageBean() {
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingRelay);
	}

	public void cancel() {
		closeDialog();
	}

	public void setRelayname(Relayname aRelayname) {
		workingRelay.setRelayname(aRelayname);
	}

	public Relayname getRelayname() {
		return workingRelay.getRelayname();
	}

	public void openDialogForCreateRelay() {
		prepareNewRelay();
		openDialog();
	}

	private void prepareNewRelay() {
		workingRelay = Relay.newInstance();
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.RELAY_EDIT_DIALOG_ID, options, null);
	}

}