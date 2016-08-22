package com.relayd.web.pagebean;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventName;
import com.relayd.client.jaxb.EventDTO;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGateway;
import com.relayd.ejb.RelayEventGatewayFactory;
import com.relayd.web.rest.client.DefaultRestGetService;
import com.relayd.web.rest.client.RestGetService;
import com.relayd.web.theme.Theme;
import com.relayd.web.theme.ThemeService;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.05.2016
 * status initial
 */
@ManagedBean
@SessionScoped
public class RelayEventBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private RelayEventGateway gateway = null;

	private RelayEvent selectedRelayEvent = null;

	@ManagedProperty(value = "#{relayEventEditPageBean}")
	private RelayEventEditPageBean relayEventEditPageBean;

	public RelayEventEditPageBean getRelayEventEditPageBean() {
		return relayEventEditPageBean;
	}

	public void setRelayEventEditPageBean(RelayEventEditPageBean aRelayEventEditPageBean) {
		relayEventEditPageBean = aRelayEventEditPageBean;
	}

	public RelayEventBrowsePageBean() {
		gateway = RelayEventGatewayFactory.get(GatewayType.FILE);
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

	public void add(@SuppressWarnings("unused") ActionEvent actionEvent) {
		relayEventEditPageBean.openDialogForCreateRelayEvent();
	}

	public void edit(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		relayEventEditPageBean.openDialogFor(selectedRelayEvent.getUUID());
	}

	public void remove(@SuppressWarnings("unused") ActionEvent actionEvent) {
		// TODO -ALL- Abprüfung auf selektion passiert... wie?
		EventName removeRelay = getSelectedRelayEvent().getName();
		gateway.remove(getSelectedRelayEvent().getUUID());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Remove!", "Relay Event:" + removeRelay.toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void onEditClosed(SelectEvent event) {
		// TODO -Thorsten- Wenn Dialo cancel - keine Message 'saved'
		RelayEvent editedEvent = (RelayEvent) event.getObject();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved!", "Relay Event:" + editedEvent.getName().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private List<Theme> themes;

	@ManagedProperty("#{themeService}")
	private ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setService(ThemeService service) {
		this.service = service;
	}
}