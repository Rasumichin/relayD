package com.relayd.web.api.resource;

import com.relayd.web.api.jaxb.*;
import javax.ws.rs.*;

/**
 * @author Rasumichin (Erik@cloud.franke-net.com)
 * @since 27.03.2016
 * status initial
 */
@Path("events")
public class EventsResource {

    @GET
    @Produces("text/plain")
    public String getEvents() {
        return "list of events";
    }

    @Path("ping")
    @GET
    @Produces("text/plain")
    public String ping() {
        return "Pong response from class " + getClass().getSimpleName() + ".";
    }

    @Path("{id}")
    @GET
    @Produces("application/json")
    public EventDTO getEvent(@PathParam("id") String id) {
        EventDTO event = new EventDTO(id);
        event.setTitle("Duesseldorf Metro Marathon");
        event.setYear(2017);
        event.setNumberOfParticipants(24);
        
        return event;
    }
}
