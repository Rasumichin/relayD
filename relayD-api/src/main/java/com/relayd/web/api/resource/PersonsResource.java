package com.relayd.web.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.relayd.Settings;
import com.relayd.client.jaxb.PersonsDTO;
import com.relayd.ejb.*;
import com.relayd.web.api.bridge.*;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   06.04.2016
 *
 */
@Path("persons")
public class PersonsResource {
	private PersonDTOBridge personDTOBridge;

	public PersonsResource(PersonDTOBridge aPersonDTOBridge) {
		personDTOBridge = aPersonDTOBridge;
	}

	public static PersonsResource newInstance(PersonDTOBridge aPersonDTOBridge) {
		if (aPersonDTOBridge == null) {
			throw new IllegalArgumentException("'aPersonDTOBridge' must not be 'null'.");
		}
		
		return new PersonsResource(aPersonDTOBridge);
	}

	@Path("ping")
	@GET
	@Produces("text/plain")
	public String ping() {
		return "a great Ping response from class " + getClass().getSimpleName() + ".";
	}

	@GET
	@Produces("application/json")
	public PersonsDTO getPersons() {
		PersonsDTO result = new PersonsDTO();
		
		return result;
	}

	public PersonDTOBridge getPersonDTOBridge() {
		if (personDTOBridge == null) {
			GatewayType gatewayType = Settings.getGatewayType();
			personDTOBridge = PersonDTOBridgeImpl.newInstance(PersonGatewayFactory.get(gatewayType));
		}
		
		return personDTOBridge;
	}
}