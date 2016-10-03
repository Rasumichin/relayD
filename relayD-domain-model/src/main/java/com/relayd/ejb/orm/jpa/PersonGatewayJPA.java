package com.relayd.ejb.orm.jpa;

import java.util.*;

import javax.persistence.*;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;
import com.relayd.entity.PersonEntity;

/**
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   12.09.2016
 *
 */
public class PersonGatewayJPA implements PersonGateway {
	private static EntityManagerFactory EM_FACTORY = Persistence.createEntityManagerFactory("dataSource");
	
	private PersonToEntityMapper personMapper = PersonToEntityMapper.newInstance();

	@Override
	public Person get(UUID uuid) {
		PersonEntity personEntity = findById(uuid);
		Person person = getPersonMapper().mapEntityToPerson(personEntity);
		
		return person;
	}

	PersonEntity findById(UUID uuid) {
		EntityManager em = getEntityManager();
		PersonEntity result = em.find(PersonEntity.class, uuid.toString());
		
		return result;
	}
	
	private EntityManager getEntityManager() {
		return EM_FACTORY.createEntityManager();
	}

	PersonToEntityMapper getPersonMapper() {
		return personMapper;
	}

	@Override
	public List<Person> getAll() {
		EntityManager em = getEntityManager();
		List<PersonEntity> result = em.createQuery("SELECT p FROM PersonEntity p", PersonEntity.class).getResultList();
		System.out.println("Result getAll: " + result.size());
		
		return new ArrayList<>();
	}

	@Override
	public void set(Person person) {
	}

	@Override
	public void remove(UUID uuid) {
	}
}