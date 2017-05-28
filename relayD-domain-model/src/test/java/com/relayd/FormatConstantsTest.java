package com.relayd;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Man kann nie glücklich werden, wenn sich das, woran man glaubt, nicht mit dem deckt, was man tut.
 *  - Ralph Waldo Emerson
 *
 * @author schmollc (Christian@relayd.de)
 * @since 28.5.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FormatConstantsTest {

	@Test
	public void testDateFormateIso() {

		String actual = FormatConstants.DATE_FORMAT_ISO;
		String expected = "yyyy-MM-dd";

		assertEquals(expected, actual);
	}
}