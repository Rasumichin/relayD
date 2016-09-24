package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
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

	private List<Boolean> visibleColumns;

	private PersonBridge personBridge = null;

	private PersonSort personSort = new PersonSort();

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;

	private List<Person> selectedPersons;
	private boolean canceled;

	@PostConstruct
	public void init() {
		visibleColumns = Arrays.asList(true, true, true, true, true, true, true, true, true, true);
	}

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
	}

	void refreshPersons() {
		searchResult = personBridge.all();
	}

	public List<Person> getPersons() {
		if (searchResult == null || searchResult.isEmpty()) {
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
		return personSort.sortByRelayname(personOne, personTwo);
	}

	public int sortByForename(Forename name1, Forename name2) {
		return personSort.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return personSort.sortBySurename(name1, name2);
	}

	public int sortByYearOfBirth(YearOfBirth yearOfBirth1, YearOfBirth yearOfBirth2) {
		return personSort.sortByYearOfBirth(yearOfBirth1, yearOfBirth2);
	}

	public int sortByShirtsize(Shirtsize size1, Shirtsize size2) {
		return personSort.sortByShirtsize(size1, size2);
	}

	public int sortByEmail(Email email1, Email email2) {
		return personSort.sortByEmail(email1, email2);
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
		String output = null;
		if (isRowSelected()) {
			output = personBridge.getEmailList(getSelectedPersons());
		} else {
			output = personBridge.getEmailList(getPersons());
		}
		showMessage(FacesMessage.SEVERITY_INFO, "Email", output);
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		if (canceled) {
			showMessage(FacesMessage.SEVERITY_INFO, "Canceld!", "");
			canceled = false;
		} else if (isRowSelectedForOneRow()) {
			showMessage(FacesMessage.SEVERITY_INFO, "Saved!", getSelectedPerson().toString());
		} else {
			showMessage(FacesMessage.SEVERITY_INFO, "Added!", "");
		}

		refreshPersons();
	}

	// TODO -schmollc- Der Name ist schlecht! Mit Lotz absprechen!
	private boolean isRowSelected() {
		return getSelectedPersons() != null && !getSelectedPersons().isEmpty();
	}

	boolean isRowSelectedForOneRow() {
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

	public void showAll() {
		refreshPersons();
	}

	public void showRelaysWithSpace() {
		searchResult = personBridge.relaysWithSpace();
	}

	public void showAllWithoutRelay() {
		searchResult = personBridge.allWithoutRelay();
	}

	public List<Boolean> getVisibleColumns() {
		return visibleColumns;
	}

	public void onToggle(ToggleEvent e) {
		visibleColumns.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
	}
}