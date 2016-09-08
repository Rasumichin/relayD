package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.ejb.PersonGateway;
import com.relayd.web.pagebean.PersonBuilder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonBridgeImplTest {

	private static final String EMAIL_JUSTUS = "Justus.Jonas@RockyBeach.com";

	private static final String EMAIL_PETER = "Peter.Shaw@RockyBeach.com";

	private static final String EMAIL_BOB = "Bob.Andrews@RockyBeach.com";

	@InjectMocks
	private PersonBridgeImpl sut = new PersonBridgeImpl();

	@Mock
	private PersonGateway gateway;

	@Test
	public void testValidateEMail_ForNewPersonWithSameEMail() {
		doReturn(listWithPersons()).when(gateway).getAll();
		Person newPerson = Person.newInstance();
		newPerson.setEmail(Email.newInstance(EMAIL_JUSTUS));

		ValidationResult result = sut.validateEMail(newPerson);

		assertEquals("[result] not correct!", "EMail does exist!", result.getMessage());
	}

	@Test
	public void testValidateEMail_ForNewPersonWithNotSameEMail() {
		doReturn(listWithPersons()).when(gateway).getAll();
		Person newPerson = Person.newInstance();
		newPerson.setEmail(Email.newInstance(EMAIL_BOB));

		ValidationResult result = sut.validateEMail(newPerson);

		assertTrue("[result] not correct!", result.getMessage().isEmpty());
	}

	@Test
	public void testValidateEMail_ForExistingPersonWithEMail() {
		List<Person> somePersons = listWithPersons();
		doReturn(somePersons).when(gateway).getAll();

		int positionFomrPeterShaw = 2;
		Person person = somePersons.get(positionFomrPeterShaw);

		ValidationResult result = sut.validateEMail(person);

		assertTrue("[result] not correct!", result.getMessage().isEmpty());
	}

	@Test
	public void testGetEmailList() {
		doReturn(listWithPersons()).when(gateway).getAll();

		String result = sut.getEmailList();

		assertNotNull("[result] invalid!", result);
		assertFalse("[result] has not be empty!", result.isEmpty());
		assertEquals("[result] not correct!", "Peter.Shaw@RockyBeach.com, Justus.Jonas@RockyBeach.com", result);
	}

	@Test
	public void testGetEmailList_ForSelectedPersons() {
		List<Person> selectedPersons = listWithPersons();

		String result = sut.getEmailList(selectedPersons);

		assertNotNull("[result] invalid!", result);
		assertFalse("[result] has not be empty!", result.isEmpty());
		assertEquals("[result] not correct!", "Peter.Shaw@RockyBeach.com, Justus.Jonas@RockyBeach.com", result);
	}

	@Test
	public void testAllWithoutRelay_For2PersonsWithoutRelay() {
		doReturn(listWithPersons()).when(gateway).getAll();

		List<Person> result = sut.allWithoutRelay();

		assertNotNull("[result] keine gültige Instanz!", result);
		assertEquals("[result] größe nicht korrekt!", 2, result.size());
		Person personFirst = result.get(0);
		assertEquals("[forename] nicht korrekt!", "Dirk", personFirst.getForename().toString());
	}

	private List<Person> listWithPersons() {
		List<Person> somePersons = new ArrayList<Person>();
		PersonBuilder builder = new PersonBuilder();

		Person personOne = builder.withForename(Forename.newInstance("Dirk")).withEmail("").build();
		somePersons.add(personOne);

		Person personTwo = builder.withForename(Forename.newInstance("Christian")).withEmail("").build();
		somePersons.add(personTwo);

		Person personThree = builder.withForename(Forename.newInstance("Peter")).withEmail(EMAIL_PETER).withRelayname("Die 4 ???").build();
		somePersons.add(personThree);

		Person personFour = builder.withForename(Forename.newInstance("Justus")).withEmail(EMAIL_JUSTUS).withRelayname("Die 4 ???").build();
		somePersons.add(personFour);

		return somePersons;
	}
}