package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.entity.PersonEntity;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.09.2016
 *
 */
public class PersonToEntityMapper {

	private PersonToEntityMapper() {
	}
	
	public static PersonToEntityMapper newInstance() {
		return new PersonToEntityMapper();
	}

	public PersonEntity mapPersonToEntity(Person person) {
		PersonEntity result = new PersonEntity.Builder()
				.withId(person.getUUID().toString())
				.withForename((person.getForename() == null) ? null : person.getForename().toString())
				.withSurename((person.getSurename() == null) ? null : person.getSurename().toString())
				.withEmail((person.getEmail() == null) ? null : person.getEmail().toString())
				.withYearOfBirth((person.getYearOfBirth() == null) ? null : person.getYearOfBirth().getValue())
				.withComment((person.getComment() == null) ? null : person.getComment().toString())
				.withNationality((person.getNationality() == null) ? null : person.getNationality().toLanguageTag())
				.withRelayname((person.getRelayname() == null) ? null : person.getRelayname().toString())
				.withPos((person.getPosition() == null) ? null : person.getPosition().getValue())
				.withShirtsize((person.getShirtsize() == null) ? null : person.getShirtsize().getSize().intValue())
				.build();
		
		return result;
	}

	public Person mapEntityToPerson(PersonEntity personEntity) {
		Person result = Person.newInstance();
		result.setUUID(UUID.fromString(personEntity.getId()));

		mapForenameFromEntityToPerson(personEntity, result);
		mapSurenameFromEntityToPerson(personEntity, result);
		mapEmailFromEntityToPerson(personEntity, result);
		mapYearOfBirthFromEntityToPerson(personEntity, result);
			
		return result;
	}


	private void mapForenameFromEntityToPerson(PersonEntity personEntity, Person result) {
		if (personEntity.getForename() != null) {
			result.setForename(Forename.newInstance(personEntity.getForename()));
		}
	}

	private void mapSurenameFromEntityToPerson(PersonEntity personEntity, Person result) {
		if (personEntity.getSurename() != null) {
			result.setSurename(Surename.newInstance(personEntity.getSurename()));
		}
	}

	private void mapEmailFromEntityToPerson(PersonEntity personEntity, Person result) {
		if (personEntity.getEmail() != null) {
			result.setEmail(Email.newInstance(personEntity.getEmail()));
		}
	}

	private void mapYearOfBirthFromEntityToPerson(PersonEntity personEntity, Person result) {
		if (personEntity.getYearOfBirth() != null) {
			result.setYearOfBirth(YearOfBirth.newInstance(personEntity.getYearOfBirth()));
		}
	}
}
