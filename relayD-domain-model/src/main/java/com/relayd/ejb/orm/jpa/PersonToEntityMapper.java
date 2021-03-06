package com.relayd.ejb.orm.jpa;

import com.relayd.Person;
import com.relayd.entity.PersonEntity;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 25.09.2016
 *
 */
public class PersonToEntityMapper {

	private PersonToEntityMapper() {
	}

	public static PersonToEntityMapper newInstance() {
		return new PersonToEntityMapper();
	}

	public void mapPersonToEntity(Person person, PersonEntity personEntity) {
		if (person == null) {
			throw new IllegalArgumentException("[person] must not be 'null'!");
		}
		if (personEntity == null) {
			throw new IllegalArgumentException("[personEntity] must not be 'null'!");
		}

		personEntity.setForename(person.getForename().isEmpty() ? null : person.getForename().toString());
		personEntity.setSurename((person.getSurename().isEmpty()) ? null : person.getSurename().toString());
		personEntity.setEmail((person.getEmail().isEmpty()) ? null : person.getEmail().toString());
		personEntity.setYearOfBirth((person.getYearOfBirth().isEmpty()) ? null : person.getYearOfBirth().getValue());
		personEntity.setComment((person.getComment().isEmpty()) ? null : person.getComment().toString());
		personEntity.setShirtsize(person.getShirtsize().isEmpty() ? null : person.getShirtsize().getSize().intValue());
	}
}