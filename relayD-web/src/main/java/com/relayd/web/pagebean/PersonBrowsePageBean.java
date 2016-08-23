package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
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

	private PersonBridge personBridge = null;

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	private List<Person> searchResult = new ArrayList<Person>();
	private List<Person> filteredPersons;

	private Person selected;
	private boolean canceled;

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
	}

	private void refreshPersons() {
		searchResult = personBridge.all();
	}

	public List<Person> getPersons() {
		if (searchResult == null | searchResult.size() < 1) {
			refreshPersons();
		}
		return searchResult;
	}

	public Person getSelectedPerson() {
		return selected;
	}

	public void setSelectedPerson(Person aSelected) {
		selected = aSelected;
	}

	public int sortByRelayname(Person personOne, Person personTwo) {
		if (personOne.getRelayname() == null) {
			return -1;
		}
		if (personTwo.getRelayname() == null) {
			return 1;
		}
		int position = personOne.getRelayname().toString().compareTo(personTwo.getRelayname().toString());
		if (personOne.getPosition() == null || personTwo.getPosition() == null) {
			return position;
		}
		position = position + personOne.getPosition().toString().compareTo(personTwo.getPosition().toString());
		return position;
	}

	public int sortByForename(Forename name1, Forename name2) {
		//return -1, 0 , 1 if car1 is less than, equal to or greater than car2
		return name1.toString().compareTo(name2.toString());
	}

	public int sortBySurename(Surename name1, Surename name2) {
		//return -1, 0 , 1 if car1 is less than, equal to or greater than car2
		return name1.toString().compareTo(name2.toString());
	}

	public int sortByBirthday(Birthday birthday1, Birthday birthday2) {
		//return -1, 0 , 1 if car1 is less than, equal to or greater than car2
		return birthday1.toString().compareTo(birthday2.toString());
	}

	public int sortByShirtsize(Shirtsize size1, Shirtsize size2) {
		//return -1, 0 , 1 if car1 is less than, equal to or greater than car2
		return size1.toString().compareTo(size2.toString());
	}

	public int sortByEmail(Email email1, Email email2) {
		//return -1, 0 , 1 if car1 is less than, equal to or greater than car2
		return email1.toString().compareTo(email2.toString());
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogForCreatePerson();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		getPersonEditPageBean().openDialogFor(getSelectedPerson().getUUID());
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		System.out.println("Removed");
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		personBridge.remove(getSelectedPerson());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Removed!", getSelectedPerson().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
		refreshPersons();

	}

	public void onEditClosed(SelectEvent event) {
		if (canceled) {
			System.out.println("Cancel Message");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Canceld!", "");
			FacesContext.getCurrentInstance().addMessage(null, message);
			canceled = false;

		} else if (getSelectedPerson() != null) {
			System.out.println("Saved!");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved!", getSelectedPerson().toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			System.out.println("Added!");
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added!", "");
			FacesContext.getCurrentInstance().addMessage(null, message);

		}

		refreshPersons();
	}

	public boolean isRowSelected() {
		return getSelectedPerson() != null;
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
		System.out.println("Canceld Dialog!");
	}

	private void showMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * @return the filteredPersons
	 */
	public List<Person> getFilteredPersons() {
		return filteredPersons;
	}

	/**
	 * @param filteredPersons the filteredPersons to set
	 */
	public void setFilteredPersons(List<Person> filteredPersons) {
		this.filteredPersons = filteredPersons;
	}
}
