package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Calendar;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.Person;
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 07.09.2016
 * status initial
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonSortTest {

	private PersonSort sut = new PersonSort();

	@Test
	public void testSortByRelayname_FirstRelaynameIsNull() {
		Person first = Person.newInstance();
		Person second = Person.newInstance();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", -1, position);
	}

	@Test
	public void testSortByRelayname_SecondRelaynameIsNull() {
		Person first = new PersonBuilder().withRelayname("Die 4 ????").build();

		Person second = Person.newInstance();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 1, position);
	}

	@Test
	public void testSortByRelayname_WithSameName() {
		Person first = new PersonBuilder().withRelayname("A").build();

		Person second = new PersonBuilder().withRelayname("A").build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 0, position);
	}

	@Test
	public void testSortByRelayname_WithSecondPositionIsNull() {
		Person first = new PersonBuilder().withRelayname("A").withPosition(Position.FOURTH).build();

		Person second = new PersonBuilder().withRelayname("A").build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 0, position);
	}

	@Test
	public void testSortByRelayname_WithBothPositions() {
		Person first = new PersonBuilder().withRelayname("A").withPosition(Position.FOURTH).build();

		Person second = new PersonBuilder().withRelayname("A").withPosition(Position.SECOND).build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", 2, position);
	}

	@Test
	public void testSortByRelayname_WithBothPositions2() {
		Person first = new PersonBuilder().withRelayname("A").withPosition(Position.SECOND).build();

		Person second = new PersonBuilder().withRelayname("A").withPosition(Position.FOURTH).build();

		int position = sut.sortByRelayname(first, second);

		assertEquals("[position] not correct!", -2, position);
	}

	@Test
	public void testSortByBirthday() {
		Birthday birthday1 = Birthday.newInstance(LocalDate.of(2016, Calendar.AUGUST, 23));
		Birthday birthday2 = Birthday.newInstance(LocalDate.of(2016, Calendar.AUGUST, 25));

		int position = sut.sortByBirthday(birthday1, birthday2);

		assertEquals("[position] not correct!", -2, position);
	}

	@Test
	public void testSortByEmail() {
		Email email1 = Email.newInstance("Justus.Jonas@RockyBeach.com");
		Email email2 = Email.newInstance("Peter.Shaw@RockyBeach.com");

		int position = sut.sortByEmail(email1, email2);

		assertEquals("[position] not correct!", -6, position);
	}

	@Test
	public void testSortByForename() {
		Forename name1 = Forename.newInstance("Justus");
		Forename name2 = Forename.newInstance("Peter");

		int position = sut.sortByForename(name1, name2);

		assertEquals("[position] not correct!", -6, position);
	}

	@Test
	public void testSortBySurename() {
		Surename name1 = Surename.newInstance("Jonas");
		Surename name2 = Surename.newInstance("Shaw");

		int position = sut.sortBySurename(name1, name2);

		assertEquals("[position] not correct!", -9, position);
	}

	@Test
	public void testSortByshirtsize() {
		Shirtsize size1 = Shirtsize.DamenL;
		Shirtsize size2 = Shirtsize.HerrenM;

		int position = sut.sortByShirtsize(size1, size2);

		assertEquals("[position] not correct!", -4, position);
	}
}