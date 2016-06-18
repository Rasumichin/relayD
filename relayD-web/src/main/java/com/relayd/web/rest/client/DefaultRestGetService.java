package com.relayd.web.rest.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * The default implementation of a 'RestGetSerive' hides the complexity of the JAX-RS 2.0 spec
 * to call JAX-RS GET services.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 29.05.2016
 * status initial
 * 
 */
// TODO (Erik, 2016-06-05): extend behaviour to handle result collections
// TODO (Erik, 2016-06-05): setter for media type (handle 'null' value)
public class DefaultRestGetService implements RestGetService {
	private WebTarget webTarget;
	private String mediaType;
	private String path;
	private Client restClient;

	private DefaultRestGetService(URI resourceUri) {
		restClient = ClientBuilder.newClient();
		setWebTarget(resourceUri);
	}

	public static class Buillder {
		private final URI uri;
		private String mediaType = MediaType.TEXT_PLAIN;
		private String path = "";

		public Buillder(URI resourceUri) {
			uri = resourceUri;
		}

		public Buillder withMediaType(String aMediaType) {
			mediaType = aMediaType;
			return this;
		}

		public Buillder withPath(String aPath) {
			path = aPath;
			return this;
		}
	
		public RestGetService build() {
			DefaultRestGetService restGetService = new DefaultRestGetService(uri);
			restGetService.mediaType = mediaType;
			restGetService.setPath(path);
			
			return restGetService;
		}
	}

	private void setWebTarget(URI resourceUri) {
		if (resourceUri == null) {
			throw new IllegalArgumentException("[resourceUri] must not be 'null'.");
		}
		webTarget = restClient.target(resourceUri);
	}

	@Override
	public <T> T getResult(Class<T> aClass) {
		return getWebTarget()
				.path(getPath())
				.request(getMediaType())
				.get(aClass);
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

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String aPath) {
		if (aPath == null) {
			throw new IllegalArgumentException("[aPath] must not be 'null'.");
		}
		path = aPath;
	}

	// This 'private' method is package protected for testing purposes.
	Client getRestClient() {
		return restClient;
	}
	
	/**
	 * Closes the 'heavyweight' resource 'restClient' on finalization. Although this way is known as a 'smell'
	 * - not 100 percent reliable and might introduce a performance drawback - I decided to go this way to
	 * release the developer from maintaining a kind of life cycle when using this little REST client framework.
	 * 
	 * I measured the performance with and without overriding 'finalize()'. Implementing this method comes with
	 * an extra cost of about 1 millisecond, which is fine.
	 * 
	 * @author Rasumichin (Erik@relayd.de)
	 * 
	 */
	@Override
	protected void finalize() throws Throwable {
		restClient.close();

		super.finalize();
	}
}
