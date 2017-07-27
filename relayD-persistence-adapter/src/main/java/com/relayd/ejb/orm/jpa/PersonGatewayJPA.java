package com.relayd.ejb.orm.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;
import com.relayd.entity.PersonEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   12.09.2016
 *
 */
public class PersonGatewayJPA extends GatewayJPA implements PersonGateway {
	private PersonToEntityMapper personMapper = PersonToEntityMapper.newInstance();
	private EntityToPersonMapper personEntityMapper = EntityToPersonMapper.newInstance();

	@Override
	public Person get(UUID uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException("[uuid] must not be 'null'.");
		}

		PersonEntity personEntity = findById(uuid);
		Person person = getPersonEntityMapper().mapToPerson(personEntity);

		return person;
	}

	PersonEntity findById(UUID uuid) {
		PersonEntity result = getJpaDao().findById(PersonEntity.class, uuid.toString());

		return result;
	}

	PersonToEntityMapper getPersonMapper() {
		return personMapper;
	}

	EntityToPersonMapper getPersonEntityMapper() {
		return personEntityMapper;
	}

	@Override
	public List<Person> getAll() {
		List<PersonEntity> personEntities = findAll();
		List<Person> persons = mapPersonEntityListToPersonList(personEntities);

		return persons;
	}

	List<PersonEntity> findAll() {
		@SuppressWarnings("unchecked")
		List<PersonEntity> result = (List<PersonEntity>) getJpaDao().performSelectQuery("SELECT p FROM PersonEntity p");

		return result;
	}

	List<Person> mapPersonEntityListToPersonList(List<PersonEntity> personEntities) {
		List<Person> persons = new ArrayList<>();
		for (PersonEntity eachEntity : personEntities) {
			persons.add(getPersonEntityMapper().mapToPerson(eachEntity));
		}
		
		return persons;
	}

	@Override
	public void set(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("[person] must not be 'null'.");
		}

		PersonEntity personEntity = findById(person.getUuid());
		if (personEntity == null) {
			personEntity = PersonEntity.newInstance(person.getUuid());
		}
		getPersonMapper().mapPersonToEntity(person, personEntity);

		getJpaDao().mergeEntity(personEntity);
	}

	@Override
	public void remove(UUID uuid) {
		PersonEntity personEntity = findById(uuid);
		if (personEntity == null) {
			throw new EntityNotFoundException("PersonEntity with 'uuid' (" + uuid + ") does not exist!");
		}

		getJpaDao().removeEntity(personEntity);
	}
}