package com.relayd.web.rest.client;

import java.net.URI;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
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
	private String mediaType;

	private DefaultRestGetService(URI resourceUri) {
		setWebTarget(resourceUri);
	}

	public static class Buillder {
		private final URI uri;
		private String mediaType = MediaType.TEXT_PLAIN;

		public Buillder(URI resourceUri) {
			uri = resourceUri;
		}

		public Buillder withMediaType(String aMediaType) {
			mediaType = aMediaType;
			return this;
		}

		public RestGetService build() {
			DefaultRestGetService restGetService = new DefaultRestGetService(uri);
			restGetService.mediaType = mediaType;
			
			return restGetService;
		}
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

	@Override
	public String getMediaType() {
		return mediaType;
	}
}
