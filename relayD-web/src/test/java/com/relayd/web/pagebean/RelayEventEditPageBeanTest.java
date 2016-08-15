package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.FormatConstants;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 15.08.2016
 *
 * Is 100% code coverage realistic?
 * Of course it is.
 * If you can write a line of code, you can write another that tests it.
 *  - Robert C. Martin
 */
public class RelayEventEditPageBeanTest {
	private RelayEventEditPageBean sut = new RelayEventEditPageBean();

	@Test
	public void testGetDatePatttern() {
		String expected = FormatConstants.DATE_FORMAT_ISO;

		String result = sut.getDatePatttern();

		assertEquals("DatePattern not correct!", expected, result);
	}
}