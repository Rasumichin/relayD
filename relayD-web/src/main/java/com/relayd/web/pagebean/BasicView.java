package com.relayd.web.pagebean;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventName;
import com.relayd.client.jaxb.EventDTO;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.orm.memory.RelayEventGatewayMemory;
import com.relayd.web.rest.client.DefaultRestGetService;
import com.relayd.web.rest.client.RestGetService;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.05.2016
 * status initial
 */
@ManagedBean
public class BasicView implements Serializable {
	private static final long serialVersionUID = 1L;

	// This will be later set through Inject, Factory or something else....
	private RelayEventGateway gateway = null;

	private Date relayEventDate = null;
	private String relayEventName = null;
	
	private RelayEvent selectedRelayEvent = null;

	public BasicView() {
		// This will be later set through Inject, Factory or something else....
		gateway = new RelayEventGatewayMemory();
		// Use Gateway you need for your test e.g. File for working without Network
		//		gateway = new RelayEventGatewaySql();
		//		gateway = new RelayEventGatewayFile();
		// etc...
	}

	public List<RelayEvent> getRelayEvents() {
		return gateway.getAll();
	}
	
	public RelayEvent getSelectedRelayEvent() {
		return selectedRelayEvent;
	}
	
	public void setSelectedRelayEvent(RelayEvent aRelayEvent) {
		selectedRelayEvent = aRelayEvent;
	}


	public List<EventDTO> getEvents() {
		return EventDTO.getRandomEvents();
	}

	public String getEventsPingRequest(String uriAuthority) throws URISyntaxException {
		String pathToResource = "/relayD-api/resources/events/ping";
		URI resourceUri = getResourceUri(uriAuthority, pathToResource);

		RestGetService restService = createRestGetService(resourceUri);

		return restService.getResult(String.class);
	}

	RestGetService createRestGetService(URI resourceUri) {
		return new DefaultRestGetService.Buillder(resourceUri).build();
	}

	// TODO (Erik, 2016-05-29): This method DOES NOT belong here.
	private URI getResourceUri(String uriAuthority, String pathToResource) throws URISyntaxException {
		// Build URI string as defined in https://en.wikipedia.org/wiki/Uniform_Resource_Identifier#Syntax
		String scheme = "http:";
		String uriString = scheme + "//" + uriAuthority + "/" + pathToResource;
		URI resourceUri = new URI(uriString);

		return resourceUri;
	}

	public Date getDate() {
		return relayEventDate;
	}

	public void setDate(Date aDate) {
		relayEventDate = aDate;
	}

	public String getName() {
		return relayEventName;
	}

	public void setName(String aName) {
		relayEventName = aName;
	}

	public void refresh() {
		// TODO -schmollc- Mmm.. Erstmal eine Lösung zum refresh...
	}

	// TODO -schmollc- Diese Methode gehört in eine eigene Klasse die sich um den Dialog kümmert!
	public void save() {

		EventName eventName = new EventName(getName());
		Date eventDay = getDate();

		RelayEvent relayEvent = new RelayEvent(eventName, eventDay);
		gateway.set(relayEvent);
		closeDialog();
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		openDialog();
	}

	// TODO -schmollc- Diese Methode gehört in eine eigene Klasse die sich um den Dialog kümmert!
	public void cancel() {
		closeDialog();
	}

	// TODO -schmollc- Diese Methode gehört in eine eigene Klasse die sich um den Dialog kümmert!
	private void closeDialog() {
		RequestContext.getCurrentInstance().closeDialog("relayEventDialog");
	}

	// TODO -schmollc- Diese Methode gehört in eine eigene Klasse die sich um den Dialog kümmert!
	private void openDialog() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 640);
		options.put("height", 340);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");

		RequestContext.getCurrentInstance().openDialog("relayEventDialog", options, null);
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String notImplementedYet = "Edit not implemented yet.";
		System.out.println(notImplementedYet);
		addMessage(notImplementedYet);

	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		String notImplementedYet = "Remove not implemented yet.";
		System.out.println(notImplementedYet);
		addMessage(notImplementedYet);
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Please use Submit, Dude!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}