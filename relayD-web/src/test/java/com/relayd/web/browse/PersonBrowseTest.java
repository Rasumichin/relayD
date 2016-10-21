package com.relayd.web.browse;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * Auch aus Steinen, die einem in den Weg gelegt werden, kann man Schönes bauen.
 *  - Johann Wolfgang von Goethe
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   21.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonBrowseTest {

	@Test
	public void testIsSerializable() {
		PersonBrowse sut = new PersonBrowse.Builder().build();

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateInstance() {
		PersonBrowse sut = new PersonBrowse.Builder().build();

		assertNotNull(sut);
	}

	@Test
	public void testGetSurename() {
		Surename expected = Surename.newInstance("Justus");

		PersonBrowse sut = new PersonBrowse.Builder().withSurename(expected).build();

		Surename actual = sut.getSurename();

		assertEquals("[surename] not correct!", expected, actual);
	}

	@Test
	public void testForename() {
		Forename expected = Forename.newInstance("Jonas");

		PersonBrowse sut = new PersonBrowse.Builder().withForename(expected).build();

		Forename actual = sut.getForename();

		assertEquals("[forename] not correct!", expected, actual);
	}

	@Test
	public void testShirtsize() {
		Shirtsize expected = Shirtsize.HerrenXXL;

		PersonBrowse sut = new PersonBrowse.Builder().withShirtsize(expected).build();

		Shirtsize actual = sut.getShirtsize();

		assertEquals("[shirtsize] not correct!", expected, actual);
	}

	@Test
	public void testYearOfBirth() {
		YearOfBirth expected = YearOfBirth.newInstance(1972);

		PersonBrowse sut = new PersonBrowse.Builder().withYearOfBirth(expected).build();

		YearOfBirth actual = sut.getYearOfBirth();

		assertEquals("[yearOfBirth] not correct!", expected, actual);
	}

	@Test
	public void testEmail() {
		Email expected = Email.newInstance("Justus.Jonas@rockyBeach.com");

		PersonBrowse sut = new PersonBrowse.Builder().withEmail(expected).build();

		Email actual = sut.getEmail();

		assertEquals("[email] not correct!", expected, actual);
	}

	@Test
	public void testComment() {
		Comment expected = Comment.newInstance("Spitzenläufer");

		PersonBrowse sut = new PersonBrowse.Builder().withComment(expected).build();

		Comment actual = sut.getComment();

		assertEquals("[comment] not correct!", expected, actual);
	}

	@Test
	public void testUUID() {
		UUID expected = UUID.randomUUID();

		PersonBrowse sut = new PersonBrowse.Builder().withUuidPerson(expected).build();

		UUID actual = sut.getUuidPerson();

		assertEquals("[uuid] not correct!", expected, actual);
	}

}