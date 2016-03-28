package com.relayd.web.api.resource;

import com.relayd.web.api.jaxb.*;
import java.util.*;
import javax.ws.rs.*;

/**
 * @author Rasumichin (Erik@cloud.franke-net.com)
 * @since 27.03.2016
 * status initial
 */
@Path("events")
public class EventsResource {

    @GET
    @Produces("application/json")
    public List<EventDTO> getEvents() {
        return EventDTO.getRandomEvents();
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
        EventDTO event = EventDTO.getRandomEvents().get(0);
        event.setId(id);
        
        return event;
    }
}
