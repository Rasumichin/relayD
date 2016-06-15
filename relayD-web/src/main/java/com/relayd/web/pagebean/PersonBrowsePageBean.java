package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.relayd.Person;
import com.relayd.service.PersonService;

/**
 * Empty PageBean with needed Methods for Workflow
 *
 * @author schmollc (Christian@relayd.de)
 * @since 15.06.2016
 * status initial
 */
public class PersonBrowsePageBean {
	@Inject
	private PersonService personService;

	@Inject
	private PersonEditPageBean personEditPageBean;

	private List<Person> searchResult = new ArrayList<Person>();

	private Person selected;

	public List<Person> getSearchResult() {
		return searchResult;
	}

	public void search() {
		searchResult = personService.get();
	}

	public Person getSelected() {
		return selected;
	}

	public void setSelected(Person aSelected) {
		selected = aSelected;
	}

	public Integer getNumberOfResults() {
		return searchResult == null ? 0 : searchResult.size();
	}

	public void createAction() {
		personEditPageBean.openDialogForCreatePerson();
	}

	public void editAction() {
		// TODO -schmollc- Wie handelt man dieses Problem am schönsten?
		if (!isRowSelected()) {
			addMessage("Please select a valid row.");
			return;
		}
		UUID uuid = getSelected().getUUID();
		personEditPageBean.openDialogFor(uuid);
	}

	public boolean isRowSelected() {
		return getSelected() != null;
	}

	private void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Bekackte Amateure, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}