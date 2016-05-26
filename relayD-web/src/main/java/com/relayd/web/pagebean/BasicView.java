package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.relayd.client.jaxb.EventDTO;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.05.2016
 * status initial
 * 
 */
@ManagedBean
@ViewScoped
public class BasicView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String restTargetRoot;
	private Client restClient = ClientBuilder.newClient();

	Client getRestClient() {
		return restClient;
	}
	
	public List<EventDTO> getEvents() {
		return EventDTO.getRandomEvents();
	}

	public String getEventsPingRequest() {
		String pathToResource = "resources/events/ping";
		WebTarget webTarget = getWebTarget(pathToResource);
		
		return getResultFromRestService(webTarget);
	}

	public void setRestTargetRoot(String targetRoot) {
		restTargetRoot = targetRoot;
	}

	String getRestTargetRoot() {
		return restTargetRoot;
	}

	WebTarget getWebTarget(String pathToResource) {
		return getRestClient().target(getRestTargetRoot() + pathToResource);
	}

	public String getResultFromRestService(WebTarget webTarget) {
		Response response = webTarget.request().get();
		String result = response.readEntity(String.class);
		
		return result;
	}
}