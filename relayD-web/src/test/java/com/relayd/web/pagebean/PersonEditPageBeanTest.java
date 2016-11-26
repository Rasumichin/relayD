package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.ValidationResultImpl;

import static org.mockito.Mockito.*;

/**
 * Every large system that works started as a small system that worked.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 18.08.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonEditPageBeanTest {

	@Spy
	@InjectMocks
	private PersonEditPageBean sut;

	@Mock
	private PersonBridge personBridge;

	@Before
	public void setUp() {
		doNothing().when(sut).openDialog();
		doNothing().when(sut).closeDialog();
		doNothing().when(sut).showError();
	}

	@Test
	public void testOpenDialogForCreatePerson() {
		sut.openDialogForCreatePerson();

		verify(sut).prepareNewPerson();
		verify(sut).openDialog();
	}

	@Test
	public void testOpenDialogFor() {
		UUID uuid = UUID.fromString("12345-42-32-23-32");
		doReturn(Person.newInstance()).when(sut).getPerson(uuid);

		assertNull("[workingPerson] not corret!", sut.workingPerson);

		sut.openDialogFor(uuid);

		assertNotNull("[workingPerson] not corret!", sut.workingPerson);
		verify(sut).getPerson(uuid);
		verify(sut).openDialog();
	}

	@Test
	public void testSave() {
		doReturn(new ValidationResultImpl("")).when(personBridge).validateEMail(any(Person.class));

		sut.save();

		verify(sut).persistPerson();
		verify(sut).closeDialog();

		verify(sut, never()).showError();
	}

	@Test
	public void testSaveAndNext_ForNewPerson() {
		doReturn(new ValidationResultImpl("")).when(personBridge).validateEMail(any(Person.class));

		sut.openDialogForCreatePerson();
		sut.saveAndNext();

		verify(sut).persistPerson();
		verify(sut, times(2)).createNewPerson(); // Einmal beim openDialogForCreatePerson() und einmal beim saveAndNext()
		verify(sut, never()).closeDialog();
	}

	@Test
	public void testSaveAndNext_ForExistingPerson() {
		UUID dummyUUID = null;
		sut.openDialogFor(dummyUUID);

		sut.saveAndNext();

		verify(sut).persistPerson();
		verify(sut).createNewPerson();
		verify(sut, never()).closeDialog();
	}

	@Test
	public void testCancel() {
		sut.cancel();

		verify(sut, never()).persistPerson();
		verify(sut).closeDialog();
	}

	@Test
	public void testForename() {
		sut.openDialogForCreatePerson();
		Forename expected = Forename.newInstance("Justus");

		sut.setForename(expected);

		Forename result = sut.getForename();
		assertEquals("[Forename] not correct!", expected, result);
	}

	@Test
	public void testSurename() {
		sut.openDialogForCreatePerson();
		Surename expected = Surename.newInstance("Jonas");

		sut.setSurename(expected);

		Surename result = sut.getSurename();
		assertEquals("[Surename] not correct!", expected, result);
	}

	@Test
	public void testYearOfBirth() {
		sut.openDialogForCreatePerson();
		YearOfBirth expected = YearOfBirth.newInstance(1971);

		sut.setYearOfBirth(expected);

		YearOfBirth result = sut.getYearOfBirth();
		assertEquals("[YearOfBirth] not correct!", expected, result);
	}

	@Test
	public void testComment() {
		sut.openDialogForCreatePerson();
		Comment expected = Comment.newInstance("Superläufer");

		sut.setComment(expected);

		Comment result = sut.getComment();
		assertEquals("[Comment] not correct!", expected, result);
	}

	@Test
	public void testShirtsize() {
		sut.openDialogForCreatePerson();
		Shirtsize expected = Shirtsize.DamenM;

		sut.setShirtsize(expected);

		Shirtsize result = sut.getShirtsize();
		assertEquals("[Shirtsize] not correct!", expected, result);
	}

	@Test
	public void testEmail() {
		sut.openDialogForCreatePerson();
		Email expected = Email.newInstance("Justus.Jonas@rockyBeach.com");

		sut.setEmail(expected);

		Email result = sut.getEmail();
		assertEquals("[Email] not correct!", expected, result);
	}

	@Test
	public void testPosition() {
		sut.openDialogForCreatePerson();
		Position expected = Position.SECOND;

		sut.setPosition(expected);

		Position result = sut.getPosition();
		assertEquals("[Position] not correct!", expected, result);
	}

	@Test
	public void testSave_ForTwoIdenticalEmails() {
		doReturn(new ValidationResultImpl("Exits!")).when(personBridge).validateEMail(any(Person.class));

		sut.save();

		verify(sut).showError();

		verify(sut, never()).persistPerson();
		verify(sut, never()).closeDialog();
	}

	@Test
	public void testGetMaxLengthForComment() {
		Integer expected = sut.getMaxLengthForComment();

		assertEquals("[maxLengthForComment] not correct!", Comment.MAX_LENGTH, expected);
	}

	@Test
	public void testCreateNewPerson() {
		Person result = sut.createNewPerson();
		assertNotNull("Person is not initialized.", result);
	}

	@Test
	public void testPrepareNewPerson() {
		sut.prepareNewPerson();

		assertNotNull("[workingPerson] has not been created.", sut.workingPerson);
	}
}