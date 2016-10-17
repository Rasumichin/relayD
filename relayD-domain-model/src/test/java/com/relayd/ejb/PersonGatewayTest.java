package com.relayd.ejb;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   23.06.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class PersonGatewayTest {

	public abstract PersonGateway getSut();

	@Test
	public void testGetForExistingEntry() {
		Person firstMember = createJustusJonas();
		getSut().set(firstMember);
		getSut().set(createPeterShaw());

		Person result = getSut().get(firstMember.getUUID());

		assertEquals("[Forename] not correct.", firstMember.getForename(), result.getForename());
		assertEquals("[Surename] not correct.", firstMember.getSurename(), result.getSurename());
		assertEquals("[YearOfBirth] not correct.", firstMember.getYearOfBirth(), result.getYearOfBirth());
		assertEquals("[Shirtsize] not correct.", firstMember.getShirtsize(), result.getShirtsize());
		assertEquals("[Email] not correct.", firstMember.getEmail(), result.getEmail());
	}

	@Test
	public void testGetForNonExistingEntry() {
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
	public void testRemove() {
		Person member = createJustusJonas();
		getSut().set(member);
		UUID uuid = member.getUUID();

		assertEquals("Error for create member. ", 1, getSut().getAll().size());

		getSut().remove(uuid);

		Person removedPerson = getSut().get(uuid);

		assertNull("Expected invalid instance.", removedPerson);
	}

	@Test
	public void update() {
		// ARRANGE
		Person firstMember = createJustusJonas();
		UUID uuidFromFirstMember = firstMember.getUUID();

		getSut().set(firstMember);
		getSut().set(createPeterShaw());

		Person updateMember = getSut().get(uuidFromFirstMember);

		Forename newForename = Forename.newInstance("Bob");
		updateMember.setForename(newForename);
		Surename newSurename = Surename.newInstance("Andrews");
		updateMember.setSurename(newSurename);
		YearOfBirth newYearOfBirth = YearOfBirth.newInstance(1971);
		updateMember.setYearOfBirth(newYearOfBirth);
		Shirtsize newShirtsize = Shirtsize.DamenXS;
		updateMember.setShirtsize(newShirtsize);
		Email newEmail = Email.newInstance("Bob.Andrews@rockyBeach.com");
		updateMember.setEmail(newEmail);
		// ACT
		getSut().set(updateMember);

		// ASSERT
		Person checkPerson = getSut().get(uuidFromFirstMember);
		assertNotNull(checkPerson);
		assertEquals(newForename, checkPerson.getForename());

		assertEquals("[Forename] not correct.", newForename, checkPerson.getForename());
		assertEquals("[Surename] not correct.", newSurename, checkPerson.getSurename());
		assertEquals("[YearOfBirth] not correct.", newYearOfBirth, checkPerson.getYearOfBirth());
		assertEquals("[Shirtsize] not correct.", newShirtsize, checkPerson.getShirtsize());
		assertEquals("[Email] not correct.", newEmail, checkPerson.getEmail());

	}

	private Person createJustusJonas() {
		return createPerson("Justus", "Jonas", 1971, Locale.GERMAN, "Justus.Jonas@rockyBech.com");
	}

	private Person createPeterShaw() {
		return createPerson("Peter", "Shaw", 1973, Locale.ENGLISH, "Perter.Shaw@rockyBeach.com");
	}

	private Person createPerson(String forename, String surename, Integer yearofBirth, Locale locale, String email) {
		Person person = Person.newInstance();

		person.setForename(Forename.newInstance(forename));
		person.setSurename(Surename.newInstance(surename));
		person.setYearOfBirth(YearOfBirth.newInstance(yearofBirth));
		person.setNationality(locale);
		person.setShirtsize(Shirtsize.Unknown);
		person.setEmail(Email.newInstance(email));
		return person;
	}

}