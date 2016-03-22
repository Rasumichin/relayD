package com.relayd;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.relayd.attributes.Birthday;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class PersonTest {

	@Test
	public void testCreateCompletePerson() {
		Person sut = Person.newInstance();
		sut.setSurename(Surename.newInstance("Jonas"));
		sut.setForename(Forename.newInstance("Justus"));
		sut.setBirthday(Birthday.newInstance(new GregorianCalendar(1956, Calendar.FEBRUARY, 6).getTime()));

		String personAsString = sut.toString();

		assertEquals("Justus Jonas, Geboren am: 06.02.1956", personAsString);
	}

	@Test
	public void testChangeSurenameFromPerson() {
		final String KLINE = "Kline";
		final String MC_FLY = "McFly";

		Person sut = Person.newInstance();
		Surename surename = sut.getSurename();

		assertNull("Erwarte gültige Instanz.", surename);

		sut.setSurename(Surename.newInstance(MC_FLY));

		surename = sut.getSurename();

		assertNotNull(surename);
		assertEquals("Erwarte den gesetzten Surenamen.", MC_FLY, surename.toString());

		Surename newSurename = Surename.newInstance(KLINE);

		sut.setSurename(newSurename);

		surename = sut.getSurename();

		assertNotNull(surename);
		assertEquals("Erwarte den neu gesetzten Surenamen.", KLINE, surename.toString());
	}

	@Test
	public void testChangeForenameFromPerson() {
		final String CELVIN = "Celvin";
		final String MARTY = "Marty";

		Person sut = Person.newInstance();
		Forename forename = sut.getForename();

		assertNull(forename);

		sut.setForename(Forename.newInstance(MARTY));

		forename = sut.getForename();

		assertNotNull("Erwarte gültige Instanz.", forename);
		assertEquals("Erwarte den gesetzten Surenamen.", MARTY, forename.toString());

		Forename newForename = Forename.newInstance(CELVIN);

		sut.setForename(newForename);

		forename = sut.getForename();

		assertNotNull(forename);
		assertEquals("Erwarte den neu gesetzten Surenamen.", CELVIN, forename.toString());
	}

}
