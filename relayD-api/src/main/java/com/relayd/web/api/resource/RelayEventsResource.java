package com.relayd.web.api.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.relayd.Settings;
import com.relayd.client.jaxb.RelayEventDTO;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.RelayEventGatewayFactory;
import com.relayd.web.api.bridge.RelayEventDTOBridge;
import com.relayd.web.api.bridge.RelayEventDTOBridgeImpl;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  27.03.2016
 *
 */
@Path("relayEvents")
public class RelayEventsResource {
	private RelayEventDTOBridge relayEventDTOBridge;

	// Public constructor is required for JAX-RS.
	public RelayEventsResource() {
	}

	private RelayEventsResource(RelayEventDTOBridge bridge) {
		relayEventDTOBridge = bridge;
	}

	public static RelayEventsResource newInstance(RelayEventDTOBridge bridge) {
		if (bridge == null) {
			throw new IllegalArgumentException("[bridge] must not be 'null'.");
		}
		return new RelayEventsResource(bridge);
	}

	public RelayEventDTOBridge getRelayEventDTOBridge() {
		if (relayEventDTOBridge == null) {
			GatewayType gatewayType = Settings.getGatewayType();
			relayEventDTOBridge = RelayEventDTOBridgeImpl.newInstance(RelayEventGatewayFactory.get(gatewayType));
		}

		return relayEventDTOBridge;
	}

	@GET
	@Produces("application/json")
	public List<RelayEventDTO> getRelayEvents() {
		List<RelayEventDTO> result = getRelayEventDTOBridge().all();

		return result;
	}

	@POST
	@Consumes("application/json")
	public Response addEvent(RelayEventDTO anEvent, @Context UriInfo uriInfo) {
		// Action here:
		// Validate and persist new resource.

		// TODO - REL-297 - discuss the level of input validation here
		URI newEventUri = null;
		try {
			// TODO - REL-284 - Find out whether there is away to explicitly avoid the path separator.
			newEventUri = new URI(uriInfo.getAbsolutePath().toString() + "/" + anEvent.getId());
		} catch (URISyntaxException ex) {
			Logger.getLogger(RelayEventsResource.class.getName()).log(Level.SEVERE, null, ex);
		}

		return Response.status(201)
				.location(newEventUri)
				.build();
	}

	@Path("{id}")
	@PUT
	@Consumes("application/json")
	public Response updateEvent(@PathParam("id") String id, RelayEventDTO anEvent) {
		// Action here:
		// Determine existing resource and persist new state of resource.

		// Other possible responses:
		// - 404 not found
		// - 304 not modified (state has not been changed)
		return Response.status(200).build();
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
	public RelayEventDTO getEvent(@PathParam("id") String id) {
		// Action here:
		// Obtain corresponding resource ('id') and return.
		RelayEventDTO event = RelayEventDTO.getRandomEvents().get(0);
		event.setId(id);

		return event;
	}

	@Path("{id}")
	@DELETE
	public Response deleteEvent(@PathParam("id") String id) {
		// Action here:
		// Determine whether 'id' exists and delete the corresponding resource.
		Response.ResponseBuilder responseBuilder;
		if (RelayEventDTO.isEventExistingFor(id)) {
			responseBuilder = Response.status(204);
		} else {
			responseBuilder = Response.status(404);
		}

		return responseBuilder.build();
	}
}
