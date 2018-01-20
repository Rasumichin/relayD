package com.relayd.web.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   06.04.2016
 *
 */
@Path("persons")
public class PersonsResource {

	@Path("ping")
	@GET
	@Produces("text/plain")
	public String ping() {
		return "a great Ping response from class " + getClass().getSimpleName() + ".";
	}
}