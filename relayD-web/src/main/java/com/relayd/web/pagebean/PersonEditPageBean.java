package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.Locale;
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
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.ValidationResult;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 15.06.2016
 * status initial
 *
 */
@ManagedBean(name = "personEditPageBean")
@SessionScoped
public class PersonEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonBridge personBridge;

	Person workingPerson = null;
	Email lastCalculatedEmail = null;
	boolean isNewPerson = false;

	public PersonEditPageBean() {
		personBridge = new PersonBridgeImpl();
	}

	public void openDialogForCreatePerson() {
		prepareNewPerson();
		openDialog();
	}

	void prepareNewPerson() {
		workingPerson = createNewPerson();
		isNewPerson = true;
		lastCalculatedEmail = workingPerson.getEmail().clone();
	}

	Person createNewPerson() {
		Person person = Person.newInstance();
		Email defaultEmail = getDefaultEmail();
		person.setEmail(defaultEmail);

		return person;
	}

	Email getDefaultEmail() {
		return Email.newInstance("forename.surename@canda.com");
	}

	void openDialog() {
		Map<String, Object> options = new DialogOptionsBuilder().build();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.PERSON_PERSON_DIALOG_ID, options, null);
	}

	public void openDialogFor(UUID uuid) {
		workingPerson = getPerson(uuid);
		isNewPerson = false;
		openDialog();
	}

	Person getPerson(UUID uuid) {
		return getBridge().get(uuid);
	}

	public void save() {
		// TODO -schmollc- Sollte die Validierung lieber in der Bridge passieren?
		// Denn wenn die Validierung ok war wieso sollte ich dann das Object nochmal der Bridge geben?
		// Es könnte direkt weitermachen....

		ValidationResult validateResult = getBridge().validateEMail(workingPerson);
		// TODO Also ne! Schmoll!! Tell, don't ask!!!!
		if (validateResult.getMessage().isEmpty()) {
			persistPerson();
			closeDialog();
		} else {
			showError();
		}
	}

	void persistPerson() {
		if (isNewPerson) {
			getBridge().create(workingPerson);
		} else {
			getBridge().update(workingPerson);
		}
	}

	void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingPerson);
	}

	void showError() {
		// TODO -schmollc- wie im ObjectConverter sollte die Nachricht aus dem ValidationResult Object kommen!
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email not uniqe!", null);
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
		Email currentEmail = getEmail();
		// TODO Mit erik drüber reden. Für mich liest sich das: Wenn A gleich B, dann setz doch A bitte auf B...Das getCurrentLocalPart sorgt aber "scheinbar" für neuere Daten?
		// Jedenfalls nicht intuitiv verstehbar. Viellecht den if umschreiben? if(mustUpdateLastCalcMail) oder so... denk man weniger nach..
		if (currentEmail.equals(lastCalculatedEmail)) {
			String currentLocalPart = getCurrentLocalPart();
			currentEmail.setLocalPart(currentLocalPart);
			lastCalculatedEmail = Email.newInstance(currentEmail.toString());
		}
	}

	public String getCurrentLocalPart() {
		Forename currentForename = getForename();
		Surename currentSurename = getSurename();

		String currentLocalPart = (currentForename == null) ? null : currentForename.toString();
		if (currentSurename != null) {
			currentLocalPart = (currentLocalPart == null) ? currentSurename.toString() : currentLocalPart + '.' + currentSurename;
		}

		if (currentLocalPart != null) {
			currentLocalPart = currentLocalPart.replaceAll("\\s+", "");
		}

		return currentLocalPart;
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

	public Locale getNationality() {
		return workingPerson.getNationality();
	}

	public void setNationality(Locale aLocale) {
		workingPerson.setNationality(aLocale);
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

	public void setRelayname(Relayname aRelayname) {
		workingPerson.setRelayname(aRelayname);
	}

	public Relayname getRelayname() {
		return workingPerson.getRelayname();
	}

	public void setPosition(Position aPosition) {
		workingPerson.setPosition(aPosition);
	}

	public Position getPosition() {
		return workingPerson.getPosition();
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