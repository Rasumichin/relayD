package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Email;
import com.relayd.ejb.PersonGateway;
import com.relayd.web.pagebean.PersonBuilder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonBridgeImplTest {

	private static final String EMAIL_JUSTUS = "Justus.Jonas@RockyBeach.com";

	private static final String EMAIL_PETER = "Peter.Shaw@RockyBeach.com";

	private static final String EMAIL_BOB = "Bob.Andrews@RockyBeach.com";

	@InjectMocks
	private PersonBridgeImpl sut = new PersonBridgeImpl();

	@Mock
	private PersonGateway gateway;

	@Test
	public void testNewPersonWithSameEMail() {
		doReturn(listWithPersons()).when(gateway).getAll();
		Person newPerson = Person.newInstance();
		newPerson.setEmail(Email.newInstance(EMAIL_JUSTUS));

		ValidationResult message = sut.validateEMail(newPerson);

		assertEquals("[message] not correct!", "EMail does exist!", message.getMessage());
	}

	@Test
	public void testNewPersonWithNotSameEMail() {
		doReturn(listWithPersons()).when(gateway).getAll();
		Person newPerson = Person.newInstance();
		newPerson.setEmail(Email.newInstance(EMAIL_BOB));

		ValidationResult message = sut.validateEMail(newPerson);

		assertTrue("[message] not correct!", message.getMessage().isEmpty());
	}

	private List<Person> listWithPersons() {
		List<Person> somePersons = new ArrayList<Person>();

		Person personOneWithEmail = Person.newInstance();
		personOneWithEmail.setEmail(Email.newInstance(EMAIL_PETER));

		somePersons.add(personOneWithEmail);

		Person personTwoWithEmail = Person.newInstance();
		personTwoWithEmail.setEmail(Email.newInstance(EMAIL_JUSTUS));

		somePersons.add(personTwoWithEmail);

		Person personThreeWithoutEmail = Person.newInstance();

		somePersons.add(personThreeWithoutEmail);

		return somePersons;
	}

	@Test
	public void testExistingPersonWithEMail() {
		List<Person> somePersons = listWithPersons();
		doReturn(somePersons).when(gateway).getAll();

		Person updatePerson = somePersons.get(1);

		ValidationResult message = sut.validateEMail(updatePerson);

		assertTrue("[message] not correct!", message.getMessage().isEmpty());
	}

	@Test
	public void testGetEmailList() {
		doReturn(createDummyData()).when(gateway).getAll();

		String commaSeperatedEmails = sut.getEmailList();

		assertNotNull("Erwarte gueltige Instanz.", commaSeperatedEmails);
		assertFalse("Erwarte keinen leeren String.", commaSeperatedEmails.isEmpty());
		assertEquals("Wert nicht korrekt.", "Christian.Schmoll@canda.com, Dirk.Aderhold@canda.com", commaSeperatedEmails);
	}

	private List<Person> createDummyData() {
		List<Person> somePersons = new ArrayList<Person>();
		PersonBuilder builder = new PersonBuilder();

		somePersons.add(builder.withEmail("Christian.Schmoll@canda.com").build());
		somePersons.add(builder.withEmail("").build());
		somePersons.add(builder.withEmail("Dirk.Aderhold@canda.com").build());

		return somePersons;
	}
}