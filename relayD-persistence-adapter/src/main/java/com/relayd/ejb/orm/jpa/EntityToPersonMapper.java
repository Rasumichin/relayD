package com.relayd.ejb.orm.jpa;

import java.util.UUID;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.entity.PersonEntity;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   23.10.2016
 *
 */
public class EntityToPersonMapper {

	private EntityToPersonMapper() {
	}

	public static EntityToPersonMapper newInstance() {
		return new EntityToPersonMapper();
	}

	public Person mapToPerson(PersonEntity personEntity) {
		if (personEntity == null) {
			throw new IllegalArgumentException("[personEntity] must not be 'null'.");
		}

		Person person = Person.newInstance();
		person.setUuid(UUID.fromString(personEntity.getId()));
		person.setForename(Forename.newInstance(personEntity.getForename()));
		person.setSurename(Surename.newInstance(personEntity.getSurename()));
		person.setComment(Comment.newInstance(personEntity.getComment()));
		person.setEmail(Email.newInstance(personEntity.getEmail()));
		person.setYearOfBirth(YearOfBirth.newInstance(personEntity.getYearOfBirth()));
		person.setShirtsize(Shirtsize.newInstance(personEntity.getShirtsize()));

		return person;
	}
}
