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
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.PersonBridgeImpl;
import com.relayd.web.bridge.RelayBridge;
import com.relayd.web.bridge.RelayBridgeImpl;
import com.relayd.web.bridge.RelayRow;

/**
 * @author schmollc (Christian@relayD.de)
 * @since 20.06.2016
 * status initial
 */
@ManagedBean
@SessionScoped
public class RelayMapPageBean {

	private PersonBridge personBridge = null;
	private RelayBridge relayBridge = null;

	@ManagedProperty(value = "#{personEditPageBean}")
	private PersonEditPageBean personEditPageBean;

	@ManagedProperty(value = "#{relayEditPageBean}")
	private RelayEditPageBean relayEditPageBean;

	private List<Person> searchResultPersons = new ArrayList<Person>();
	private List<RelayRow> searchResultRelayRows = new ArrayList<RelayRow>();

	private Person selectedPerson;
	private RelayRow selectedRelayRow;

	public RelayMapPageBean() {
		personBridge = new PersonBridgeImpl();
		relayBridge = new RelayBridgeImpl();
	}

	public List<Person> getPersons() {
		searchResultPersons = personBridge.all();
		return searchResultPersons;
	}

	public List<RelayRow> getRelayRows() {
		searchResultRelayRows = relayBridge.all();
		return searchResultRelayRows;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person aSelected) {
		selectedPerson = aSelected;
	}

	public Integer getNumberOfPersonResults() {
		return searchResultPersons == null ? 0 : searchResultPersons.size();
	}

	public void addPerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogForCreatePerson();
	}

	public void editPerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getPersonEditPageBean().openDialogFor(getSelectedPerson().getUUID());
	}

	public void removePerson(@SuppressWarnings("unused") ActionEvent actionEvent) {
		personBridge.remove(getSelectedPerson());
	}

	public void onEditClosed(SelectEvent event) {

	}

	public boolean isRowPersonSelected() {
		return getSelectedPerson() != null;
	}

	public PersonEditPageBean getPersonEditPageBean() {
		return personEditPageBean;
	}

	public void setPersonEditPageBean(PersonEditPageBean aPersonEditPageBean) {
		personEditPageBean = aPersonEditPageBean;
	}

	public RelayEditPageBean getRelayEditPageBean() {
		return relayEditPageBean;
	}

	public void setRelayEditPageBean(RelayEditPageBean aRelayEditPageBean) {
		relayEditPageBean = aRelayEditPageBean;
	}

	public Integer getNumberOfRelayRowResults() {
		return searchResultRelayRows == null ? 0 : searchResultRelayRows.size();
	}

	public boolean isRowRelaySelected() {
		return getSelectedRelayRow() != null;
	}

	public RelayRow getSelectedRelayRow() {
		return selectedRelayRow;
	}

	public void setSelectedRelayRow(RelayRow aSelected) {
		selectedRelayRow = aSelected;
	}

	public void addRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEditPageBean().openDialogForCreateRelay();
	}

	public void editRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		getRelayEditPageBean().openDialogFor(getSelectedRelayRow().getUUID());
	}

	public void removeRelay(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// relayBridge.remove(getSelectedRelay());
		showMessage("remove not implemented yet!");
	}

	public void move(@SuppressWarnings("unused") ActionEvent actionEvent) {
		relayBridge.add(getSelectedRelayRow(), getSelectedPerson());

		showMessage("> not implemented yet!");
	}

	private void showMessage(String text) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}