package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.persistence.EntityNotFoundException;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.*;
import com.relayd.entity.PersonEntity;

/**
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
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetWithIllegalNullValue() {
		sut.get(null);
	}
	
	@Test
	public void testGet() {
		UUID expectedUuid = UUID.randomUUID();
		PersonEntity personEntity = new PersonEntity.Builder().withId(expectedUuid.toString()).build();
		doReturn(personEntity).when(sut).findById(expectedUuid);
		
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
		doReturn(new ArrayList<>()).when(sut).findAll();
		
		List<Person> result = sut.getAll();
		assertTrue("Restult list is not correct.", result.isEmpty());
	}
	
	// TODO: (EL, 2016-10-09): Discuss with CS: Do we need further tests here for the other cases for 'getAll' (one element, many elements)?
	
	@Test(expected=EntityNotFoundException.class)
	public void testRemoveWhenPersonDoesNotExistForUuid() {
		UUID someUuid = UUID.randomUUID();
		doReturn(null).when(sut).findById(someUuid);
		
		sut.remove(someUuid);
	}
	
	@Test
	public void testRemoveWhenPersonNotExistForUuid() {
		UUID someUuid = UUID.randomUUID();
		PersonEntity personEntity = new PersonEntity.Builder().withId(someUuid.toString()).build();
		
		doReturn(personEntity).when(sut).findById(someUuid);
		doNothing().when(sut).removePersonEntity(personEntity);
		
		sut.remove(someUuid);
		verify(sut, times(1)).removePersonEntity(personEntity);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetWithIllegalNullValue() {
		sut.set(null);
	}
	
	@Test
	public void testSetWithNewPerson() {
		Person person = Person.newInstance();
		person.setForename(Forename.newInstance("Ward"));
		person.setSurename(Surename.newInstance("Cunningham"));
		PersonEntity expectedPersonEntitiy = sut.getPersonMapper().mapPersonToEntity(person);
		
		doReturn(null).when(sut).findById(person.getUUID());
		doNothing().when(sut).persistNewPersonEntity(expectedPersonEntitiy);
		
		sut.set(person);
		verify(sut, times(1)).findById(person.getUUID());
		verify(sut, times(1)).persistNewPersonEntity(expectedPersonEntitiy);
	}
}
