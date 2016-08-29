package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.relayd.FormatConstants;
import com.relayd.Person;
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.ValidationResult;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@ManagedBean(name = "personEditPageBean")
@SessionScoped
public class PersonEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonBridge personBridge;
	Person workingPerson = null;
	boolean isNewPerson = false;

	// TODO -schmollc- in eine eigene Klasse stecken!
	private List<Locale> nationalities;
	// TODO -schmollc- in eine eigene Klasse stecken!
	private List<Shirtsize> shirtsizes;
	// TODO -schmollc- in eine eigene Klasse stecken!
	public List<Position> positions;

	public PersonEditPageBean() {
		personBridge = new PersonBridgeImpl();

		initAllNationalities();
		initShirtsizes();
		initPositions();
	}

	// TODO -schmollc- in eine eigene Klasse stecken!
	private void initAllNationalities() {
		nationalities = new ArrayList<Locale>();

		Locale[] locales = Locale.getAvailableLocales();
		for (Locale eachLocale : locales) {
			String code = eachLocale.getCountry();
			String name = eachLocale.getDisplayCountry();

			if (!"".equals(code) && !"".equals(name)) {
				nationalities.add(eachLocale);
			}
		}
	}

	// TODO -schmollc- in eine eigene Klasse stecken!
	private void initShirtsizes() {
		shirtsizes = new ArrayList<Shirtsize>();

		for (Shirtsize eachShirtsize : Shirtsize.values()) {
			shirtsizes.add(eachShirtsize);
		}
	}

	// TODO -schmollc- in eine eigene Klasse stecken!
	void initPositions() {
		positions = new ArrayList<Position>();

		for (Position eachPosition : Position.values()) {
			positions.add(eachPosition);
		}
	}

	public void openDialogForCreatePerson() {
		workingPerson = createNewPerson();
		isNewPerson = true;
		openDialog();
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
		Map<String, Object> options = createDialogOptions();
		RequestContext.getCurrentInstance().openDialog(NavigationConstants.PERSON_DIALOG_ID, options, null);
	}

	Map<String, Object> createDialogOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 400);
		options.put("height", 440);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");

		return options;
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
		workingPerson = createNewPerson();
	}

	public void cancel() {
		closeDialog();
	}
	
	public void nameValueChanged() {
		String currentLocalPart = getCurrentLocalPart();
		Email currentEmail = getEmail();
		currentEmail.setLocalPart(currentLocalPart);
	}

	public String getCurrentLocalPart() {
		String currentLocalPart = null;
		
		Forename currentForename = getForename();
		if (currentForename != null) {
			currentLocalPart = currentForename.toString();
		}
		
		Surename currentSurename = getSurename();
		if (currentSurename != null) {
			if (currentLocalPart == null) {
				currentLocalPart = currentSurename.toString();
			} else {
				currentLocalPart += "." + currentSurename;
			}
		}
		
		return currentLocalPart;
	}
	
	public List<Locale> getNationalities() {
		return Collections.unmodifiableList(nationalities);
	}

	public List<Shirtsize> getShirtsizes() {
		return Collections.unmodifiableList(shirtsizes);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
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

	public Birthday getBirthday() {
		return workingPerson.getBirthday();
	}

	public void setBirthday(Birthday aBirthday) {
		workingPerson.setBirthday(aBirthday);
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

	public String getDatePatttern() {
		return FormatConstants.DATE_FORMAT_ISO;
	}
}