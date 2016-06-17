package com.relayd.web.pagebean;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.relayd.RelayEvent;
import com.relayd.client.jaxb.EventDTO;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayFactory;
import com.relayd.web.rest.client.DefaultRestGetService;
import com.relayd.web.rest.client.RestGetService;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.05.2016
 * status initial
 */
@SessionScoped
@ManagedBean
public class RelayEventBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private RelayEventGateway gateway = null;

	private RelayEvent selectedRelayEvent = null;

	private RelayEventEditPageBean relayEventEditPageBean = new RelayEventEditPageBean();

	public RelayEventBrowsePageBean() {
		gateway = RelayEventGatewayFactory.get(GatewayType.MEMORY);
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

	public void refresh() {
		// TODO -schmollc- Mmm.. Erstmal eine Lösung zum refresh...
	}

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		relayEventEditPageBean.openDialogForCreateRelayEvent();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		relayEventEditPageBean.openDialogFor(selectedRelayEvent.getUuid());
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