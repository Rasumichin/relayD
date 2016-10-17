package com.relayd.web.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   06.04.2016
 *
 */
@Path("person")
public class PersonResource {

	@Path("ping")
	@GET
	@Produces("text/plain")
	public String ping() {
		return "a great Ping response from class " + getClass().getSimpleName() + ".";
	}
}