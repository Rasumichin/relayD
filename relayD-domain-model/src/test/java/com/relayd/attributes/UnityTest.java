package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * A good programmer is someone who looks both ways before crossing a one-way street.
 *  - Anonymous
 *
 * @author  schmollc (Christian@relayd.com)
 * @since   25.10.2016
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnityTest {

	@Test
	public void testCount() {
		Unity[] values = Unity.values();

		assertEquals("Wrong count for Enum entries!", 2, values.length);
	}

	@Test
	public void testToString_ForMeters() {
		Unity sut = Unity.METER;

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "m", actual);
	}

	@Test
	public void testToString_ForKilometers() {
		Unity sut = Unity.KM;

		String actual = sut.toString();

		assertEquals("[toString] not correct!", "km", actual);
	}
}