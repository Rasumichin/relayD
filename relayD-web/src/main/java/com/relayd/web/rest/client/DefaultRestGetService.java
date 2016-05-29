package com.relayd.web.rest.client;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * The default implementation of a 'RestGetSerive' hides the complexity of the JAX-RS 2.0 spec
 * to call JAX-RS GET services.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 * 
 */
public class DefaultRestGetService implements RestGetService {

	private WebTarget webTarget;

	public DefaultRestGetService(URI resourceUri) {
		setWebTarget(resourceUri);
	}

	private void setWebTarget(URI resourceUri) {
		if (resourceUri == null) {
			throw new IllegalArgumentException("[resourceUri] must not be 'null'.");
		}
		webTarget = ClientBuilder.newClient().target(resourceUri);
	}

	@Override
	public String getResult() {
		Response response = getWebTarget().request().get();
		
		return response.readEntity(String.class);
	}

	private WebTarget getWebTarget() {
		return webTarget;
	}

	@Override
	public URI getResourceUri() {
		return getWebTarget().getUri();
	}
}
