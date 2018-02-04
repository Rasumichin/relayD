package com.relayd.web.api.bridge.mapper;

import com.relayd.Person;
import com.relayd.client.jaxb.PersonDTO;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  04.02.2018
 *
 */
public class PersonToDTOMapper {
	
	private PersonToDTOMapper() {
	}

	public static PersonToDTOMapper newInstance() {
		return new PersonToDTOMapper();
	}

	public PersonDTO mapPersonToDTO(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("'person' must not be 'null'.");
		}
		PersonDTO personDTO = createDTOFromPerson(person);
		
		return personDTO;
	}

	private PersonDTO createDTOFromPerson(Person person) {
		PersonDTO personDTO = PersonDTO.newInstance();
		
		personDTO.setId(person.getUuid().toString());
		personDTO.setForename(person.getForename().toString());
		personDTO.setSurename(person.getSurename().toString());
		personDTO.setEmail(person.getEmail().toString());
		personDTO.setShirtSize(person.getShirtsize().getDescription());
		personDTO.setComment(person.getComment().toString());
		personDTO.setYearOfBirth(person.getYearOfBirth().getValue());
		
		return personDTO;
	}
}
