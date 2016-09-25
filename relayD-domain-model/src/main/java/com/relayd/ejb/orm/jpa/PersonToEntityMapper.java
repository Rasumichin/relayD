package com.relayd.ejb.orm.jpa;

import com.relayd.Person;
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
				.build();
		
		return result;
	}
}
