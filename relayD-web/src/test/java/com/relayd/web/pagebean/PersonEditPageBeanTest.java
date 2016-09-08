package com.relayd.web.pagebean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.junit.*;
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
 * @author Rasumichin (Erik@relayd.de)
 * @since 18.08.2016
 * 
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
	public void testGetDefaultEmail() {
		Email expected = getExpectedDefaultEmail();
		Email result = sut.getDefaultEmail();
		assertEquals("Default email address is not correct.", expected, result);
	}

	private Email getExpectedDefaultEmail() {
		return Email.createFromLocalAndDomainPart("forename.surename", "canda.com");
	}
	
	@Test
	public void testCreateNewPersonAndVerifyEmailHasADefaultValue() {
		sut.openDialogForCreatePerson();
		Email expected = getExpectedDefaultEmail();
		
		Email result = sut.getEmail();
		assertEquals("Person's [email] attribute is not correctly initialized.", expected, result);
	}
	
	@Test
	public void testGetCurrentLocalPartForenameIsSet() {
		sut.openDialogForCreatePerson();
		Forename forename = Forename.newInstance("Mike");
		sut.setForename(forename);
		
		String expected = forename.toString();
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}
	
	@Test
	public void testGetCurrentLocalPartSurenameIsSet() {
		sut.openDialogForCreatePerson();
		Surename surename = Surename.newInstance("Hansen");
		sut.setSurename(surename);
		
		String expected = surename.toString();
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}
	
	@Test
	public void testGetCurrentLocalPartForenameAndSurenameHaveBeenSet() {
		sut.openDialogForCreatePerson();
		Forename forename = Forename.newInstance("Mike");
		sut.setForename(forename);
		Surename surename = Surename.newInstance("Hansen");
		sut.setSurename(surename);
		
		String expected = forename.toString() + "." + surename.toString();
		String result = sut.getCurrentLocalPart();
		assertEquals("Current local part is not correct.", expected, result);
	}
	
	@Test
	public void testGetCurrentLocalPartWhenNoNameHasBeenSet() {
		sut.openDialogForCreatePerson();
		
		String result = sut.getCurrentLocalPart();
		assertNull("Current local part is not correct.", result);
	}
	
	@Test
	public void testForenameValueChanged() {
		sut.openDialogForCreatePerson();
		Forename forename = Forename.newInstance("Clark");
		Email expected = Email.createFromLocalAndDomainPart(forename.toString(), "canda.com");
		
		sut.setForename(forename);
		sut.nameValueChanged();
		
		Email result = sut.getEmail();
		assertEquals("Forename value change has not been handled correctly.", expected, result);
	}
	
	@Test
	public void testSurenameValueChanged() {
		sut.openDialogForCreatePerson();
		Surename surename = Surename.newInstance("Kent");
		Email expected = Email.createFromLocalAndDomainPart(surename.toString(), "canda.com");
		
		sut.setSurename(surename);
		sut.nameValueChanged();
		
		Email result = sut.getEmail();
		assertEquals("Surename value change has not been handled correctly.", expected, result);
	}
	
	@Test
	public void testNameValueChangedButEmailWasEdited() {
		sut.openDialogForCreatePerson();
		String expected = "edited.name" + Email.AT_SIGN + "notcanda.com";
		Email email = Email.newInstance(expected);
		sut.setEmail(email);
		
		sut.setForename(Forename.newInstance("Adele"));
		sut.nameValueChanged();
		
		String result = sut.getEmail().toString();
		assertEquals("Email was changed although otherwise edited.", expected, result);
	}
}