package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.ValidationResult;
import com.relayd.web.local.I18N;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 15.06.2016
 *
 */
@ManagedBean(name = "personEditPageBean")
@SessionScoped
public class PersonEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonBridge personBridge;

	Person workingPerson = null;

	public PersonEditPageBean() {
		personBridge = new PersonBridgeImpl();
	}

	public void openDialogForCreatePerson() {
		prepareNewPerson();
		openDialog();
	}

	void prepareNewPerson() {
		workingPerson = createNewPerson();
	}

	Person createNewPerson() {
		Person person = Person.newInstance();
		return person;
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().height(400).build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.PERSON_PERSON_DIALOG_ID, options, null);
	}

	public void openDialogFor(UUID uuid) {
		workingPerson = getPerson(uuid);
		openDialog();
	}

	Person getPerson(UUID uuid) {
		return getBridge().get(uuid);
	}

	public void save() {
		// TODO (Christian, Version 1.4): Sollte die Validierung lieber in der Bridge passieren?
		// Denn wenn die Validierung ok war wieso sollte ich dann das Object nochmal der Bridge geben?
		// Es könnte direkt weitermachen....

		ValidationResult validateResult = getBridge().validateEMail(workingPerson);
		if (validateResult.isEmpty()) {
			persistPerson();
			closeDialog();
		} else {
			showError();
		}
	}

	void persistPerson() {
		getBridge().persistPerson(workingPerson);
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingPerson);
	}

	void showError() {
		// TODO (Christian, Version 1.4): wie im ObjectConverter sollte die Nachricht aus dem ValidationResult Object kommen!
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, I18N.EMAIL_NOT_UNIQUE, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void saveAndNext() {
		persistPerson();
		prepareNewPerson();
	}

	public void cancel() {
		closeDialog();
	}

	public void nameValueChanged() {
		workingPerson.nameValueChanged();
	}

	public Forename getForename() {
		return workingPerson.getForename();
	}

	public void setForename(Forename aForename) {
		workingPerson.setForename(aForename);
	}

	public Surename getSurename() {
		return workingPerson.getSurename();
	}

	public void setSurename(Surename aSurename) {
		workingPerson.setSurename(aSurename);
	}

	public YearOfBirth getYearOfBirth() {
		return workingPerson.getYearOfBirth();
	}

	public void setYearOfBirth(YearOfBirth aYearOfBirth) {
		workingPerson.setYearOfBirth(aYearOfBirth);
	}

	public Shirtsize getShirtsize() {
		return workingPerson.getShirtsize();
	}

	public void setShirtsize(Shirtsize aShirtsize) {
		workingPerson.setShirtsize(aShirtsize);
	}

	public Email getEmail() {
		return workingPerson.getEmail();
	}

	public void setEmail(Email anEmail) {
		workingPerson.setEmail(anEmail);
	}

	public void setComment(Comment aComment) {
		workingPerson.setComment(aComment);
	}

	public Comment getComment() {
		return workingPerson.getComment();
	}

	private PersonBridge getBridge() {
		return personBridge;
	}

	public Integer getMaxLengthForComment() {
		return Comment.MAX_LENGTH;
	}
}