package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.relayd.Person;
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@ManagedBean
@SessionScoped
public class PersonBrowsePageBean {
	// Should be I18N
	private static final String PLEASE_SELECT_A_ROW = "Please select one row!";
	private static final String NOT_POSSIBLE = "Not Possible!";

	private PersonBridge personBridge = null;

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;

	private List<Person> selectedPersons;
	private boolean canceled;

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
	}

	void refreshPersons() {
		searchResult = personBridge.all();
	}

	public List<Person> getPersons() {
		if (searchResult == null | searchResult.size() < 1) {
			refreshPersons();
		}
		return searchResult;
	}

	private Person getSelectedPerson() {
		return selectedPersons.get(0);
	}

	public List<Person> getSelectedPersons() {
		return selectedPersons;
	}

	public void setSelectedPersons(List<Person> someSelectedPersons) {
		selectedPersons = someSelectedPersons;
	}

	public int sortByRelayname(Person personOne, Person personTwo) {
		if (personOne.getRelayname() == null) {
			return -1;
		}
		if (personTwo.getRelayname() == null) {
			return 1;
		}
		int position = personOne.getRelayname().compareTo(personTwo.getRelayname());
		if (personOne.getPosition() == null || personTwo.getPosition() == null) {
			return position;
		}
		position = position + personOne.getPosition().compareTo(personTwo.getPosition());
		return position;
	}

	public int sortByForename(Forename name1, Forename name2) {
		return name1.toString().compareTo(name2.toString());
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return name1.toString().compareTo(name2.toString());
	}

	public int sortByBirthday(Birthday birthday1, Birthday birthday2) {
		return birthday1.toString().compareTo(birthday2.toString());
	}

	public int sortByShirtsize(Shirtsize size1, Shirtsize size2) {
		return size1.toString().compareTo(size2.toString());
	}

	public int sortByEmail(Email email1, Email email2) {
		return email1.toString().compareTo(email2.toString());
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogForCreatePerson();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRowSelectedForOneRow()) {
			UUID uuid = getSelectedPerson().getUUID();
			getPersonEditPageBean().openDialogFor(uuid);
		} else {
			showMessageErrorNoRowSelected();
		}
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRowSelectedForOneRow()) {
			personBridge.remove(getSelectedPerson());
			showMessage(FacesMessage.SEVERITY_INFO, "Success", "Remove" + getSelectedPerson().toString());
			refreshPersons();
		} else {
			showMessageErrorNoRowSelected();
		}
	}

	public void emailExport(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String output = personBridge.getEmailList();
		showMessage(FacesMessage.SEVERITY_INFO, "Email", output);
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		if (canceled) {
			showMessage(FacesMessage.SEVERITY_INFO, "Canceld!", "");
			canceled = false;
		} else if (getSelectedPerson() != null) {
			showMessage(FacesMessage.SEVERITY_INFO, "Saved!", getSelectedPerson().toString());
		} else {
			showMessage(FacesMessage.SEVERITY_INFO, "Added!", "");
		}

		refreshPersons();
	}

	public boolean isRowSelectedForOneRow() {
		return getSelectedPersons() != null && getSelectedPersons().size() == 1;
	}

	public PersonEditPageBean getPersonEditPageBean() {
		return personEditPageBean;
	}

	public void setPersonEditPageBean(PersonEditPageBean aPersonEditPageBean) {
		personEditPageBean = aPersonEditPageBean;
	}

	public void cancelEditDialog() {
		getPersonEditPageBean().cancel();
		canceled = true;
	}

	public List<Person> getFilteredPersons() {
		return filteredPersons;
	}

	public void setFilteredPersons(List<Person> someFilteredPersons) {
		filteredPersons = someFilteredPersons;
	}

	void showMessageErrorNoRowSelected() {
		showMessage(FacesMessage.SEVERITY_ERROR, NOT_POSSIBLE, PLEASE_SELECT_A_ROW);
	}

	void showMessage(Severity severityInfo, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severityInfo, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}