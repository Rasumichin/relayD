package com.relayd.web.pagebean;

import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Empty PageBean with needed Methods for Workflow
 *
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
public class PersonEditPageBean {

	public void openDialogForCreatePerson() {
		String notImplementedYet = "[openDialogForCreatePerson] not implemented yet.";
		System.out.println(notImplementedYet);
		addMessage(notImplementedYet);
	}

	public void openDialogFor(UUID aUuid) {
		String notImplementedYet = "[openDialogFor] not implemented yet.";
		System.out.println(notImplementedYet);
		addMessage(notImplementedYet);
	}

	public void save() {
		String notImplementedYet = "[save] not implemented yet.";
		System.out.println(notImplementedYet);
		addMessage(notImplementedYet);
	}

	public void close() {
		String notImplementedYet = "[close] not implemented yet.";
		System.out.println(notImplementedYet);
		addMessage(notImplementedYet);
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}