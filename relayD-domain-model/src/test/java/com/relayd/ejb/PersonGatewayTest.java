package com.relayd.ejb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.Test;

import com.relayd.Person;
import com.relayd.attributes.Birthday;
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
		Person teilnehmer = createJustusJonas();
		getSut().set(teilnehmer);
		getSut().set(createPeterShaw());

		Person result = getSut().get(teilnehmer.getUUID());

		assertEquals(teilnehmer.getForename(), result.getForename());
	}

	@Test
	public void testGetForNonEntry() {
		getSut().set(createJustusJonas());
		getSut().set(createPeterShaw());

		Person result = getSut().get(UUID.randomUUID());

		assertNull(result);
	}

	@Test
	public void testGetAll() {
		getSut().set(createJustusJonas());
		getSut().set(createPeterShaw());

		List<Person> result = getSut().getAll();
		assertEquals(2, result.size());
	}

	@Test
	public void remove() {
		Person teilnehmer = createJustusJonas();
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
		Person ersterTeilnehmer = createJustusJonas();
		UUID uuidFromErstenTeilnehmer = ersterTeilnehmer.getUUID();

		getSut().set(ersterTeilnehmer);
		getSut().set(createPeterShaw());

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

	private Person createJustusJonas() {
		return createPerson("Justus", "Jonas", LocalDate.of(1971, Month.APRIL, 17), Locale.GERMAN);
	}

	private Person createPeterShaw() {
		return createPerson("Peter", "Shaw", LocalDate.of(1973, Month.AUGUST, 23), Locale.ENGLISH);
	}

	private Person createPerson(String forename, String surename, LocalDate dateOfBirth, Locale locale) {
		Person person = Person.newInstance();

		person.setForename(Forename.newInstance(forename));
		person.setSurename(Surename.newInstance(surename));
		person.setBirthday(Birthday.newInstance(dateOfBirth));
		person.setNationality(locale);
		return person;
	}

}