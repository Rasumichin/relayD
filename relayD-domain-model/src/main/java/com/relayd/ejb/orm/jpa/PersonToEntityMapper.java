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
		personEntity.setForename((person.getForename() == null) || (person.getForename().isEmpty()) ? null : person.getForename().toString());
		personEntity.setSurename((person.getSurename() == null) ? null : person.getSurename().toString());
		personEntity.setEmail((person.getEmail() == null) ? null : person.getEmail().toString());
		personEntity.setYearOfBirth((person.getYearOfBirth() == null) ? null : person.getYearOfBirth().getValue());
		personEntity.setComment((person.getComment() == null) ? null : person.getComment().toString());
		personEntity.setRelayname((person.getRelayname() == null) ? null : person.getRelayname().toString());
		personEntity.setPos((person.getPosition() == null) ? null : person.getPosition().getValue());
		personEntity.setShirtsize((person.getShirtsize() == null) ? null : person.getShirtsize().getSize().intValue());
	}
}
