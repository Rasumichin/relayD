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
		person.setComment((personEntity.getComment() == null) ? null : (Comment.newInstance(personEntity.getComment())));
		person.setEmail((personEntity.getEmail() == null) ? person.getEmail() : (Email.newInstance(personEntity.getEmail())));

		// Thank god for the 'NullObjectPattern'.
		person.setForename(Forename.newInstance(personEntity.getForename()));
		person.setYearOfBirth(YearOfBirth.newInstance(personEntity.getYearOfBirth()));
		
		person.setPosition((personEntity.getPos() == null) ? null : (Position.decode(personEntity.getPos())));
		person.setRelayname((personEntity.getRelayname() == null) ? null : (Relayname.newInstance(personEntity.getRelayname())));
		person.setShirtsize((personEntity.getShirtsize() == null) ? null : (Shirtsize.decode(personEntity.getShirtsize())));
		person.setSurename((personEntity.getSurename() == null) ? null : (Surename.newInstance(personEntity.getSurename())));

		return person;
	}
}
