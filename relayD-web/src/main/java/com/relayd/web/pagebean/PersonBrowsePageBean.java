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
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.ejb.PersonGatewayFactory;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
@ManagedBean
@SessionScoped
public class PersonBrowsePageBean {

	private PersonGateway gateway = null;

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	private List<Person> searchResult = new ArrayList<Person>();

	private Person selected;

	public PersonBrowsePageBean() {
		gateway = PersonGatewayFactory.get(GatewayType.FILE);
	}

	public List<Person> getPersons() {
		searchResult = gateway.getAll();
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
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		gateway.remove(getSelectedPerson().getUUID());
	}

	public void onEditClosed(SelectEvent event) {
		// TODO -Thorsten- Wenn Dialo cancel - keine Message 'saved'
	}

	public boolean isRowSelected() {
		return getSelectedPerson() != null;
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public PersonEditPageBean getPersonEditPageBean() {
		return personEditPageBean;
	}

	public void setPersonEditPageBean(PersonEditPageBean aPersonEditPageBean) {
		personEditPageBean = aPersonEditPageBean;
	}
}