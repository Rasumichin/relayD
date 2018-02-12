package com.relayd.web.api.bridge;

import java.util.*;

import com.relayd.Person;
import com.relayd.client.jaxb.PersonDTO;
import com.relayd.ejb.PersonGateway;
import com.relayd.web.api.bridge.mapper.PersonToDTOMapper;

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
		List<PersonDTO> personDTOs = new ArrayList<>();
		List<Person> persons = getPersonGateway().getAll();
		
		PersonToDTOMapper mapper = PersonToDTOMapper.newInstance();
		persons.forEach(eachPerson -> personDTOs.add(mapper.mapPersonToDTO(eachPerson)));
		
		return personDTOs;
	}

	PersonGateway getPersonGateway() {
		return personGateway;
	}
}
