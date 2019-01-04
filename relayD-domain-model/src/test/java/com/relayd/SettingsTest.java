package com.relayd;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.ejb.GatewayType;

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
		boolean condition = sut instanceof Serializable;

		assertTrue("Class not Serializable!", condition);
	}

	@Test
	public void testGetVersion() {
		String actual = sut.getVersion();
		assertEquals("[getVersion] not correct!", "1.9 - Codename Jungfräuliches Augustiner", actual);
	}

	@Test
	public void testGetEmailDomain() {
		String actual = sut.getEmailDomain();

		assertEquals("[getEmailDomain] not correct!", "ToDo", actual);
	}

	@Test
	public void testGatewayType() {
		GatewayType defaultValue = Settings.getGatewayType();

		assertEquals("Default GatewayType not correct!", GatewayType.JPA, defaultValue);

		GatewayType expected = GatewayType.FILE;

		Settings.setGatewayType(expected);

		GatewayType actual = Settings.getGatewayType();

		assertEquals("[gatewayType] nicht korrekt!", expected, actual);
	}

	@Test
	public void testGetRelayAppendix() {
		String actual = sut.getRelayAppendix();

		assertEquals("[getRelayAppendix] not correct!", " @ C&A", actual);
	}

	@Test
	public void testGetClosingDate() {
		String actual = sut.getClosingDate();

		assertEquals("[getClosingDate] not correct!", "01.04.2017 (ToDo)", actual);
	}

	@Test
	public void testTheme_ForInitialSut() {
		String expected = "afterwork";

		String actual = sut.getTheme();

		assertEquals("[getTheme] for initial state not correct!", expected, actual);
	}

	@Test
	public void testTheme() {
		String expected = "le-Frog";

		sut.setTheme(expected);

		String actual = sut.getTheme();

		assertEquals("[getTheme] not correct!", expected, actual);
	}

	@Test
	public void testGetProperty_ForUnknownKey() {
		String actual = sut.getProperty("Unknown Key");

		assertEquals("[getProperty] not corret!", "UNKNOWN", actual);
	}
}