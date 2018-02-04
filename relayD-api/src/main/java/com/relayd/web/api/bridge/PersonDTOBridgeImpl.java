package com.relayd.web.api.bridge;

import java.util.List;

import com.relayd.client.jaxb.PersonDTO;
import com.relayd.ejb.PersonGateway;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  28.01.2018
 *
 */
public class PersonDTOBridgeImpl implements PersonDTOBridge {
	private PersonGateway personGateway;

	private PersonDTOBridgeImpl(PersonGateway gateway) {
		personGateway = gateway;
	}

	public static PersonDTOBridgeImpl newInstance(PersonGateway gateway) {
		if (gateway == null) {
			throw new IllegalArgumentException("'gateway' must not be 'null'.");
		}
		
		return new PersonDTOBridgeImpl(gateway);
	}

	@Override
	public List<PersonDTO> all() {
		return null;
	}

	PersonGateway getPersonGateway() {
		return personGateway;
	}
}
