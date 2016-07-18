package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.relayd.Person;
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

	private Person selected;

	public PersonBrowsePageBean() {
		personBridge = new PersonBridgeImpl();
	}

	public List<Person> getPersons() {
		searchResult = personBridge.all();
		return searchResult;
	}

	public Person getSelectedPerson() {
		return selected;
	}

	public void setSelectedPerson(Person aSelected) {
		selected = aSelected;
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
		System.out.println("removed");
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		// TODO -schmollc- Die Gui refresht nach dem remove nicht.
		personBridge.remove(getSelectedPerson());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Removed!", getSelectedPerson().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onEditClosed(SelectEvent event) {
		System.out.println("Saved!");
		if (getSelectedPerson() != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved!", getSelectedPerson().toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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
		System.out.println("Canceld Dialog!");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Canceld!", "");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private void showMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}