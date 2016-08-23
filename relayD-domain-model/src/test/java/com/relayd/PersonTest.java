package com.relayd;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;
import java.util.UUID;

import org.junit.Test;

import com.relayd.attributes.Birthday;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 22.03.2016
 * status initial
 */
public class PersonTest {

	@Test
	public void testCreateCompletePerson() {
		Person sut = Person.newInstance();
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setForename(Forename.newInstance("Justus"));
		sut.setBirthday(Birthday.newInstance(LocalDate.of(1956, Month.FEBRUARY, 17)));
		sut.setShirtsize(Shirtsize.HerrenM);
		sut.setNationality(Locale.GERMANY);
		sut.setEmail(Email.newInstance("Jonas.Justus@rockyBeach.com"));
		sut.setRelayname(Relayname.newInstance("Die 4 ????"));

		String personAsString = sut.toString();

		assertEquals("Justus Jonas, 1956-02-17, HerrenM, Germany, Jonas.Justus@rockyBeach.com, Die 4 ????", personAsString);
	}

	@Test
	public void testSurename() {
		Surename expected = Surename.newInstance("Justus");

		Person sut = Person.newInstance();

		sut.setSurename(expected);

		Surename result = sut.getSurename();

		assertEquals("[surename] not correct!", expected, result);
	}

	@Test
	public void testForename() {
		Forename expected = Forename.newInstance("Jonas");

		Person sut = Person.newInstance();

		sut.setForename(expected);

		Forename result = sut.getForename();

		assertEquals("[forename] not correct!", expected, result);
	}

	@Test
	public void testShirtsize() {
		Shirtsize expected = Shirtsize.HerrenXXL;

		Person sut = Person.newInstance();

		sut.setShirtsize(expected);

		Shirtsize result = sut.getShirtsize();

		assertEquals("[shirtsize] not correct!", expected, result);
	}

	@Test
	public void testNationality() {
		Locale expected = Locale.GERMANY;

		Person sut = Person.newInstance();

		sut.setNationality(expected);

		Locale result = sut.getNationality();

		assertEquals("[nationality] not correct!", expected, result);
	}

	@Test
	public void testBirthday() {
		Birthday expected = Birthday.newInstance(LocalDate.of(1972, Month.APRIL, 23));

		Person sut = Person.newInstance();

		sut.setBirthday(expected);

		Birthday result = sut.getBirthday();

		assertEquals("[birthday] not correct!", expected, result);
	}

	@Test
	public void testEmail() {
		Email expected = Email.newInstance("Justus.Jonas@rockyBeach.com");

		Person sut = Person.newInstance();

		sut.setEmail(expected);

		Email result = sut.getEmail();

		assertEquals("[email] not correct!", expected, result);
	}

	@Test
	public void testRelayname() {
		Relayname expected = Relayname.newInstance("Die 4 ????");

		Person sut = Person.newInstance();

		sut.setRelayname(expected);

		Relayname result = sut.getRelayname();

		assertEquals("[relayname] not correct!", expected, result);
	}

	@Test
	public void testUUID() {
		UUID expected = UUID.randomUUID();

		Person sut = Person.newInstance();

		sut.setUUID(expected);

		UUID result = sut.getUUID();

		assertEquals("[uuid] not correct!", expected, result);
	}
}