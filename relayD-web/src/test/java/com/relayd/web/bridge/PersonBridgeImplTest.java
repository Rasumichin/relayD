package com.relayd.web.bridge;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.ejb.GatewayType;
import com.relayd.ejb.PersonGateway;
import com.relayd.web.browse.PersonBrowse;
import com.relayd.web.pagebean.PersonBuilder;

import static org.mockito.Mockito.*;

/**
 * The software isn't finished until the last user is dead.
 *  - Anonymous
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   20.06.2016
 *
 */
// TODO (Erik, Version 1.3): This test produces console output saying a JPA runtime could not be started. Check mocks.

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
		List<Person> somePersons = listWithPersons();

		String result = sut.getEmailList(somePersons);

		assertNotNull("[result] invalid!", result);
		assertFalse("[result] has not be empty!", result.isEmpty());
		assertEquals("[result] not correct!", "Peter.Shaw@RockyBeach.com, Justus.Jonas@RockyBeach.com", result);
	}

	private List<Person> listWithPersons() {
		List<Person> somePersons = new ArrayList<Person>();
		PersonBuilder builder = new PersonBuilder();

		Person personOne = builder.withForename(Forename.newInstance("Dirk")).build();
		somePersons.add(personOne);

		Person personTwo = builder.withForename(Forename.newInstance("Christian")).build();
		somePersons.add(personTwo);

		Person personThree = builder.withForename(Forename.newInstance("Peter")).withEmail(EMAIL_PETER).build();
		somePersons.add(personThree);

		Person personFour = builder.withForename(Forename.newInstance("Justus")).withEmail(EMAIL_JUSTUS).build();
		somePersons.add(personFour);

		builder = new PersonBuilder();

		Person personFive = builder.withForename(Forename.newInstance("Michi")).build();
		somePersons.add(personFive);

		Person personSix = builder.withForename(Forename.newInstance("Smudo")).build();
		somePersons.add(personSix);

		Person personSeven = builder.withForename(Forename.newInstance("Andy")).build();
		somePersons.add(personSeven);

		Person personEight = builder.withForename(Forename.newInstance("Thomas")).build();
		somePersons.add(personEight);

		return somePersons;
	}

	@Test
	public void testGatewayType() {
		GatewayType result = sut.getGatewayType();

		assertEquals("[gatewayType] not correct!", GatewayType.JPA, result);
	}

	@Test
	public void testAllPersonBrowse() {
		doReturn(listWithPersons()).when(gateway).getAll();

		List<PersonBrowse> actual = sut.allPersonBrowse();

		assertNotNull("[resultList] not a valid instance!", actual);

		int size = actual.size();
		assertEquals("[size] of resultList not correct!", 8, size);
	}

	@Test
	public void testGetPersonBrowseFor() {
		// Arrange
		UUID expectedUuid = UUID.randomUUID();
		Forename expectedForename = Forename.newInstance("Peter");
		Surename expectedSurename = Surename.newInstance("Shaw");
		YearOfBirth expectedYearOfBirth = YearOfBirth.newInstance(1971);
		Comment expectedComment = Comment.newInstance("Comment");

		//@formatter:off
		Shirtsize expectedShirtsize = Shirtsize.HerrenL;
		Person person = new PersonBuilder()
								.withUuid(expectedUuid)
								.withForename(expectedForename)
								.withSurename(expectedSurename)
								.withYearOfBirth(expectedYearOfBirth)
								.withShirtsize(expectedShirtsize)
								.withEmail(EMAIL_PETER)
								.withComment(expectedComment)
								.build();
		//@formatter:on

		// Act
		PersonBrowse actual = sut.getPersonBrowseFor(person);

		// Assert
		assertEquals("[uuid] not correct!", expectedUuid, actual.getUuidPerson());
		assertEquals("[forename] not correct!", expectedForename, actual.getForename());
		assertEquals("[surename] not correct!", expectedSurename, actual.getSurename());
		assertEquals("[yearOfBirth] not correct!", expectedYearOfBirth, actual.getYearOfBirth());
		assertEquals("[shirtsize] not correct!", expectedShirtsize, actual.getShirtsize());
		assertEquals("[comment] not correct!", expectedComment, actual.getComment());
		assertEquals("[email] not correct!", Email.newInstance(EMAIL_PETER), actual.getEmail());

	}
}