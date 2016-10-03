package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;


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
	
	@Test
	public void testGet() {
		UUID expectedUuid = UUID.randomUUID();
		Person result = sut.get(expectedUuid);
		
		assertNotNull("Result 'Person' must not be 'null'.", result);
		
		UUID actualUuid = result.getUUID();
		assertEquals("Mapping from PersonEntity to Person was not correct.", expectedUuid, actualUuid);
	}
}
