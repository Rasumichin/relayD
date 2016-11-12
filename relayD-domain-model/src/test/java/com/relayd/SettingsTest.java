package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * If you don't like unit testing your product, most likely your customers won't like to test it either.
 * - Anonymous
 *
 * @author schmollc (Christian@relayd.de)
 * @since 16.10.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SettingsTest {
	private Settings sut = Settings.newInstance();

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testGetVersion() {
		String actual = sut.getVersion();

		assertEquals("[getVersion] not correct!", "1.1 - Codename Bitburger", actual);
	}

	@Test
	public void testGetEmailDomain() {
		String actual = sut.getEmailDomain();

		assertEquals("[getEmailDomain] not correct!", "ToDo", actual);
	}

	@Test
	public void testGetGatewayType() {
		String actual = sut.getGatewayType();

		assertEquals("[getGatewayType] not correct!", "ToDo", actual);
	}

	@Test
	public void testGetClosingDate() {
		String actual = sut.getClosingDate();

		assertEquals("[getClosingDate] not correct!", "01.04.2017 (ToDo)", actual);
	}

	// TODO -schmollc- Diskussion: Als Dokumentation sollten beide Tests in einer Methode sein um aufzuzeigen
	// das der Wert einen DefaultWert besitzt. So sollten auch die bisherigen getNN Tests als erstes vielleicht auf assertNull prüfen
	// um zu zeigen das die Attribute mit null vorbelegt sind! Allerdings wollten wir doch auch das NullObjectPattern nutzen.. ach menno.
	// Memo an mich selbst: In Maui sollte ich das auf jeden Fall nochmal überdenken!
	@Test
	public void testTheme_ForInitialSut() {
		String expected = "cupertino";

		String actual = sut.getTheme();

		assertEquals("[getTheme] not correct!", expected, actual);
	}

	@Test
	public void testTheme() {
		String expected = "le-Frog";

		sut.setTheme(expected);

		String actual = sut.getTheme();

		assertEquals("[getTheme] not correct!", expected, actual);
	}
}