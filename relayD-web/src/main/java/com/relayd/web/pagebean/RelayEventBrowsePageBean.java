package com.relayd.web.pagebean;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.relayd.RelayEvent;
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
 *
 */
@ManagedBean
@SessionScoped
public class RelayEventBrowsePageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private RelayEventGateway gateway = null;

	private RelayEvent selectedRelayEvent = null;

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