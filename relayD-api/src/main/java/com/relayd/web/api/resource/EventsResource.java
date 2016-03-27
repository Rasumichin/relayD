package com.relayd.web.api.resource;

import javax.ws.rs.*;

/**
 * @author  Rasumichin (Erik@cloud.franke-net.com)
 * @since   27.03.2016
 * status   initial
 */
@Path("events")
public class EventsResource {

    @Path("ping")
    @GET
    @Produces("text/plain")
    public String ping() {
        return "Pong response from class " + getClass().getSimpleName() + ".";
    }
}
