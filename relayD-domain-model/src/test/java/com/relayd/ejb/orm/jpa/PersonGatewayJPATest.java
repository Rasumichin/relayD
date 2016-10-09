package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.entity.PersonEntity;

/**
 * @author  Rasumichin (Erik@relayd.de)
 * @since   03.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayJPATest {
	
	private PersonGatewayJPA sut = new PersonGatewayJPA() {
		@Override
		PersonEntity findById(UUID uuid) {
			PersonEntity personEntity = new PersonEntity.Builder().withId(uuid.toString()).build();
			return personEntity;
		}
	};

	@Test
	public void testGetPersonMapper() {
		PersonToEntityMapper result = sut.getPersonMapper();
		assertNotNull("[personMapper] has not been initialized.", result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetWithIllegalNullValue() {
		sut.get(null);
	}
	
	@Test
	public void testGet() {
		UUID expectedUuid = UUID.randomUUID();
		Person result = sut.get(expectedUuid);
		
		assertNotNull("Result 'Person' must not be 'null'.", result);
		
		UUID actualUuid = result.getUUID();
		assertEquals("Mapping from PersonEntity to Person was not correct.", expectedUuid, actualUuid);
	}

	@Test
	public void testMapPersonEntityListToPersonList_whenEmpty() {
		List<PersonEntity> personEntities = new ArrayList<>();
		List<Person> result = sut.mapPersonEntityListToPersonList(personEntities);
		
		assertTrue("Mapping from a list of PersonEntities to Persons was not correct.", result.isEmpty());
	}
	
	@Test
	public void testMapPersonEntityListToPersonList_withOneElement() {
		List<PersonEntity> personEntities = new ArrayList<>();
		personEntities.add(new PersonEntity.Builder().build());
		
		List<Person> persons = sut.mapPersonEntityListToPersonList(personEntities);
		
		int expectedResult = 1;
		int actualResult = persons.size();
		assertEquals("Mapping from a list of PersonEntities to Persons was not correct.", expectedResult, actualResult);
	}
	
	@Test
	public void testMapPersonEntityListToPersonList_withManyElement() {
		List<PersonEntity> personEntities = new ArrayList<>();
		personEntities.add(new PersonEntity.Builder().build());
		personEntities.add(new PersonEntity.Builder().build());
		personEntities.add(new PersonEntity.Builder().build());
		
		List<Person> persons = sut.mapPersonEntityListToPersonList(personEntities);
		
		int expectedResult = 3;
		int actualResult = persons.size();
		assertEquals("Mapping from a list of PersonEntities to Persons was not correct.", expectedResult, actualResult);
	}
	
	@Test
	public void testGetAllWhenResultIsEmpty() {
		PersonGatewayJPA sut = new PersonGatewayJPA() {
			@Override
			List<PersonEntity> findAll() {
				return new ArrayList<>();
			}
		};
		
		List<Person> result = sut.getAll();
		assertTrue("Restult list is not correct.", result.isEmpty());
	}
	
	// TODO: (EL, 2016-10-09): Discuss with CS: Do we need further tests here for the other cases (one element, many elements)?
}
