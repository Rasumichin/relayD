package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;
import com.relayd.entity.PersonEntity;

import static org.mockito.Mockito.*;

/**
 * Designers make the mistake of not taking error into account.
 * 		- Donald A. Norman (The Design of Everyday Things) -
 *
 * @author  Rasumichin (Erik@relayd.de)
 * @since   03.10.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayJPATest {

	@Spy
	private PersonGatewayJPA sut = new PersonGatewayJPA();

	@Test
	public void testGetPersonMapper() {
		PersonToEntityMapper result = sut.getPersonMapper();
		assertNotNull("[personMapper] has not been initialized.", result);
	}

	@Test
	public void testGetPersonEntityMapper() {
		EntityToPersonMapper result = sut.getPersonEntityMapper();
		assertNotNull("[personEntityMapper] has not been initialized!", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetWithIllegalNullValue() {
		sut.get(null);
	}

	@Test
	public void testGet() {
		UUID expectedUuid = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(expectedUuid);
		doReturn(personEntity).when(sut).findById(expectedUuid);

		Person result = sut.get(expectedUuid);
		assertNotNull("Result 'Person' must not be 'null'!", result);

		UUID actualUuid = result.getUuid();
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
		personEntities.add(PersonEntity.newInstance());

		List<Person> persons = sut.mapPersonEntityListToPersonList(personEntities);

		int expectedResult = 1;
		int actualResult = persons.size();
		assertEquals("Mapping from a list of PersonEntities to Persons was not correct.", expectedResult, actualResult);
	}

	@Test
	public void testMapPersonEntityListToPersonList_withManyElement() {
		List<PersonEntity> personEntities = new ArrayList<>();
		personEntities.add(PersonEntity.newInstance());
		personEntities.add(PersonEntity.newInstance());
		personEntities.add(PersonEntity.newInstance());

		List<Person> persons = sut.mapPersonEntityListToPersonList(personEntities);

		int expectedResult = 3;
		int actualResult = persons.size();
		assertEquals("Mapping from a list of PersonEntities to Persons was not correct.", expectedResult, actualResult);
	}

	@Test
	public void testGetAllWhenResultIsEmpty() {
		doReturn(new ArrayList<>()).when(sut).findAll();

		List<Person> result = sut.getAll();
		assertTrue("Restult list is not correct.", result.isEmpty());
	}

	// TODO (Erik, Version 1.4): Discuss with CS: Do we need further tests here for the other cases for 'getAll' (one element, many elements)?

	@Test(expected = EntityNotFoundException.class)
	public void testRemoveWhenPersonDoesNotExistForUuid() {
		UUID someUuid = UUID.randomUUID();
		doReturn(null).when(sut).findById(someUuid);

		sut.remove(someUuid);
	}

	@Test
	public void testRemoveWhenPersonNotExistForUuid() {
		UUID someUuid = UUID.randomUUID();
		PersonEntity personEntity = PersonEntity.newInstance(someUuid);

		doReturn(personEntity).when(sut).findById(someUuid);
		doNothing().when(sut).removePersonEntity(personEntity);

		sut.remove(someUuid);
		verify(sut, times(1)).removePersonEntity(personEntity);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetWithIllegalNullValue() {
		sut.set(null);
	}

	@Test
	public void testSet_whenPersonHasToBeUpdated() {
		Person person = getPersonToSet();
		PersonEntity expectedPersonEntity = PersonEntity.newInstance();
		sut.getPersonMapper().mapPersonToEntity(person, expectedPersonEntity);

		doReturn(expectedPersonEntity).when(sut).findById(person.getUuid());
		doNothing().when(sut).mergePersonEntity(expectedPersonEntity);

		sut.set(person);
		verify(sut, times(1)).mergePersonEntity(expectedPersonEntity);
	}

	// TODO (Erik, Version 1.4): Been unable to stub the method call 'findById' to return 'null'. Any attempt is starting JPA ?!
	@Ignore
	@Test
	public void testSet_whenPersonIsNew() {
		Person person = getPersonToSet();
		PersonEntity expectedPersonEntity = PersonEntity.newInstance();
		sut.getPersonMapper().mapPersonToEntity(person, expectedPersonEntity);

		doNothing().when(sut).mergePersonEntity(expectedPersonEntity);

		sut.set(person);
		verify(sut, times(1)).mergePersonEntity(expectedPersonEntity);
	}

	private Person getPersonToSet() {
		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Ward"));
		person.setSurename(Surename.newInstance("Cunningham"));

		return person;
	}
}
