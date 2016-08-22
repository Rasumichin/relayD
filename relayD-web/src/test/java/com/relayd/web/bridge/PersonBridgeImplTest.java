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

		Person personOne = Person.newInstance();
		personOne.setEmail(Email.newInstance(EMAIL_PETER));

		somePersons.add(personOne);

		Person personTwo = Person.newInstance();
		personTwo.setEmail(Email.newInstance(EMAIL_JUSTUS));
		somePersons.add(personTwo);

		somePersons.add(Person.newInstance());

		return somePersons;
	}
}