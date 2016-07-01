package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.relayd.Person;
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayFactory;

/**
 * Empty PageBean with needed Methods for Workflow
 *
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@ManagedBean(name = "personEditPageBean")
@SessionScoped
public class PersonEditPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PERSON_DIALOG_ID = "personDialog";

	private PersonGateway gateway = null;

	private Person workingPerson = null;

	private List<Locale> nationalities;

	private List<Shirtsize> shirtsizes;

	public PersonEditPageBean() {
		gateway = PersonGatewayFactory.get(GatewayType.FILE);
		fillAllNationalities();
		fillShirtsizes();
	}

	public void openDialogForCreatePerson() {
		workingPerson = Person.newInstance();
		openDialog();
	}

	public void openDialogFor(UUID uuid) {
		workingPerson = getGateway().get(uuid);
		openDialog();
	}

	private void openDialog() {
		Map<String, Object> options = createOptions();
		RequestContext.getCurrentInstance().openDialog(PERSON_DIALOG_ID, options, null);
	}

	private Map<String, Object> createOptions() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 640);
		options.put("height", 340);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");
		return options;
	}

	public void save() {
		getGateway().set(workingPerson);
		closeDialog();
	}

	public void cancel() {
		closeDialog();
	}

	private void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog(workingPerson);
	}

	public List<Locale> getNationalities() {
		return nationalities;
	}

	public List<Shirtsize> getShirtsizes() {
		return shirtsizes;
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

	private PersonGateway getGateway() {
		return gateway;
	}

	public String getDatePatttern() {
		return FormatConstants.DATE_FORMAT;
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// TODO -schmollc- ab hier in eine eigene Klasse stecken!
	private void fillShirtsizes() {
		shirtsizes = new ArrayList<Shirtsize>();

		for (Shirtsize shirtsize : Shirtsize.values()) {
			shirtsizes.add(shirtsize);
		}
	}

	public void fillAllNationalities() {
		nationalities = new ArrayList<Locale>();

		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			String code = locale.getCountry();
			String name = locale.getDisplayCountry();

			if (!"".equals(code) && !"".equals(name)) {
				nationalities.add(locale);
			}
		}
	}
}