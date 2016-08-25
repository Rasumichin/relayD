package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.FormatConstants;
import com.relayd.Person;
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.web.bridge.PersonBridge;
import com.relayd.web.bridge.ValidationResultImpl;

import static org.mockito.Mockito.*;

/**
 * Every large system that works started as a small system that worked.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @since 18.08.2016
 */
@RunWith(MockitoJUnitRunner.class)
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
		assertNull("[workingPerson] not corret!", sut.workingPerson);
		assertFalse("[isNewPerson] not correct!", sut.isNewPerson);

		sut.openDialogForCreatePerson();

		assertNotNull("[workingPerson] not corret!", sut.workingPerson);
		assertTrue("[isNewPerson] not correct!", sut.isNewPerson);
		verify(sut).openDialog();
	}

	@Test
	public void testOpenDialogFor() {
		UUID uuid = UUID.fromString("12345-42-32-23-32");
		doReturn(Person.newInstance()).when(sut).getPerson(uuid);

		assertNull("[workingPerson] not corret!", sut.workingPerson);
		assertFalse("[isNewPerson] not correct!", sut.isNewPerson);

		sut.openDialogFor(uuid);

		assertNotNull("[workingPerson] not corret!", sut.workingPerson);
		assertFalse("[isNewPerson] not correct!", sut.isNewPerson);
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
	public void testBirthday() {
		sut.openDialogForCreatePerson();
		Birthday expected = Birthday.newInstance(LocalDate.now());

		sut.setBirthday(expected);

		Birthday result = sut.getBirthday();

		assertEquals("[Birthday] not correct!", expected, result);
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
	public void testNationality() {
		sut.openDialogForCreatePerson();
		Locale expected = Locale.GERMANY;

		sut.setNationality(expected);

		Locale result = sut.getNationality();

		assertEquals("[Nationality] not correct!", expected, result);
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
	public void testRelayname() {
		sut.openDialogForCreatePerson();
		Relayname expected = Relayname.newInstance("Die 4 ????");

		sut.setRelayname(expected);

		Relayname result = sut.getRelayname();

		assertEquals("[Relayname] not correct!", expected, result);
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
	public void testGetDatePattern() {
		String result = sut.getDatePatttern();

		assertEquals("[DatePattern] nicht korrekt!", FormatConstants.DATE_FORMAT_ISO, result);
	}

	@Test
	public void testCreateDialogOptions() {
		Map<String, Object> optionMap = sut.createDialogOptions();

		int size = optionMap.size();
		assertEquals("[optionMap] size not correct!", 6, size);
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
	public void testFillPositions() {
		// TODO -schmollc- Interessanter Fall. Wie teste ich ein verify im Constructor!!!
		int size = sut.positions.size();

		assertEquals("[positions] not correct!", 4, size);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyPositions() {
		sut.getPositions().add(Position.FIRST);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyNationalities() {
		sut.getNationalities().add(Locale.CANADA);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testModifyShirtsizes() {
		sut.getShirtsizes().add(Shirtsize.DamenL);
	}
}