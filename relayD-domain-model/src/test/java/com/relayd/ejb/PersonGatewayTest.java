package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.relayd.Person;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 * status   initial
 */
public abstract class PersonGatewayTest {

	public abstract PersonGateway getSut();

	@Test
	public void testGetForExistingEntry() {
		Person teilnehmer = createPerson("Justus", "Jonas");
		getSut().set(teilnehmer);
		getSut().set(createPerson("Peter", "Shaw"));

		Person result = getSut().get(teilnehmer.getUUID());

		assertEquals(teilnehmer.getForename(), result.getForename());
	}

	private Person createPerson(String forename, String surename) {
		Person person = Person.newInstance();

		person.setForename(Forename.newInstance(forename));
		person.setSurename(Surename.newInstance(surename));

		return person;
	}

	@Test
	public void testGetForNonEntry() {
		getSut().set(createPerson("Justus", "Jonas"));
		getSut().set(createPerson("Peter", "Shaw"));

		Person result = getSut().get(UUID.randomUUID());

		assertNull(result);
	}

	@Test
	public void testGetAll() {
		getSut().set(createPerson("Justus", "Jonas"));
		getSut().set(createPerson("Peter", "Shaw"));

		List<Person> result = getSut().getAll();
		assertEquals(2, result.size());
	}

	@Test
	public void remove() {
		Person teilnehmer = createPerson("Justus", "Jonas");
		getSut().set(teilnehmer);
		UUID uuid = teilnehmer.getUUID();

		assertEquals("Error for TestFile. ", 1, getSut().getAll().size());

		getSut().remove(uuid);

		Person removedPerson = getSut().get(uuid);

		assertNull("Expected invalid instance.", removedPerson);
	}

	@Test
	public void update() {
		// ARRANGE
		Person ersterTeilnehmer = createPerson("Justus", "Jonas");
		UUID uuidFromErstenTeilnehmer = ersterTeilnehmer.getUUID();

		getSut().set(ersterTeilnehmer);
		getSut().set(createPerson("Peter", "Shaw"));

		Person updateTeilnehmer = getSut().get(uuidFromErstenTeilnehmer);

		Forename newForename = Forename.newInstance("Johannes");
		updateTeilnehmer.setForename(newForename);

		// ACT
		getSut().set(updateTeilnehmer);

		// ASSERT
		Person checkPerson = getSut().get(uuidFromErstenTeilnehmer);
		assertNotNull(checkPerson);
		assertEquals(newForename, checkPerson.getForename());
	}

}