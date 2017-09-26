package com.relayd.web.pagebean;

import java.io.Serializable;
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

import org.primefaces.context.RequestContext;
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
import com.relayd.web.local.I18N;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 15.06.2016
 *
 */
@ManagedBean
@SessionScoped
public class PersonBrowsePageBean implements Serializable {
	private static final long serialVersionUID = -7684007777613912395L;

	private List<Boolean> visibleColumns;

	private PersonBridge personBridge = null;

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;

	private List<Person> selectedPersons;
	private boolean canceled;

	@PostConstruct
	public void init() {
		// TODO - REL-305 - Clarify with CS what this list is needed for.
		visibleColumns = Arrays.asList(true, true, true, true, true, true, true, true);
		refreshPersons();
	}

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
	}

	void refreshPersons() {
		searchResult = personBridge.all();
	}

	public List<Person> getPersons() {
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

	public int sortByForename(Forename name1, Forename name2) {
		return Forename.sortByForename(name1, name2);
	}

	public int sortBySurename(Surename name1, Surename name2) {
		return Surename.sortBySurename(name1, name2);
	}

	public int sortByYearOfBirth(YearOfBirth yearOfBirth1, YearOfBirth yearOfBirth2) {
		return YearOfBirth.sortByYearOfBirth(yearOfBirth1, yearOfBirth2);
	}

	public int sortByShirtsize(Shirtsize size1, Shirtsize size2) {
		return Shirtsize.sortByShirtsize(size1, size2);
	}

	public int sortByEmail(Email email1, Email email2) {
		return Email.sortByEmail(email1, email2);
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogForCreatePerson();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRowSelectedForOneRow()) {
			UUID uuid = getSelectedPerson().getUuid();
			getPersonEditPageBean().openDialogFor(uuid);
		} else {
			showMessageErrorNoRowSelected();
		}
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		if (isRowSelectedForOneRow()) {
			personBridge.remove(getSelectedPerson());
			showMessage(FacesMessage.SEVERITY_INFO, I18N.SUCCESS, I18N.REMOVE + getSelectedPerson().toString());
			refreshPersons();
		} else {
			showMessageErrorNoRowSelected();
		}
	}

	public void emailExport(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String output;
		if (personsSelected()) {
			output = personBridge.getEmailList(getSelectedPersons());
		} else {
			output = personBridge.getEmailList(getPersons());
		}
		showDialog(FacesMessage.SEVERITY_INFO, I18N.EMAIL, output);
	}

	public void onEditClosed(@SuppressWarnings("unused") SelectEvent event) {
		if (canceled) {
			showMessage(FacesMessage.SEVERITY_INFO, I18N.CANCELED, "");
			canceled = false;
		} else if (isRowSelectedForOneRow()) {
			showMessage(FacesMessage.SEVERITY_INFO, I18N.SAVED, getSelectedPerson().toString());
		} else {
			showMessage(FacesMessage.SEVERITY_INFO, I18N.ADDED, "");
		}

		refreshPersons();
	}

	private boolean personsSelected() {
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
		showMessage(FacesMessage.SEVERITY_ERROR, I18N.NOT_POSSIBLE, I18N.SELECT_A_ROW);
	}

	void showMessage(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	void showDialog(Severity severity, String summary, String textMessage) {
		FacesMessage message = new FacesMessage(severity, summary, textMessage);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void showAll() {
		refreshPersons();
	}

	public List<Boolean> getVisibleColumns() {
		return visibleColumns;
	}

	public void onToggle(ToggleEvent e) {
		visibleColumns.set((Integer) e.getData(), e.getVisibility() == Visibility.VISIBLE);
	}

	public void info(@SuppressWarnings("unused") ActionEvent actionEvent) {
		showMessage(FacesMessage.SEVERITY_INFO, I18N.USED_GATEWAY, "" + personBridge.getGatewayType());
	}
}