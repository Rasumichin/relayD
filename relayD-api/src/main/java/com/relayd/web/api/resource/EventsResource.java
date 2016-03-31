package com.relayd.web.api.resource;

import com.relayd.web.api.jaxb.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

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

    @POST
    @Consumes("application/json")
    public Response addEvent(EventDTO anEvent, @Context UriInfo uriInfo) {
        // TODO Erik, 2016-03-31: discuss the level of input validation here
        URI newEventUri = null;
        try {
            // TODO Erik, 2016-03-31: Find out whether there is away to explicitely avoid the path separator.
            newEventUri = new URI(uriInfo.getAbsolutePath().toString() + "/" + anEvent.getId());
        } catch (URISyntaxException ex) {
            Logger.getLogger(EventsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Response.status(201)
                .location(newEventUri)
                .build();
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
