package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Every large system that works started as a small system that worked.
 * - Anonymous
 *
 * @author  schmollc (Christian@relayd.com)
 * @since   19.05.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventYearTest {

	@Test
	public void testCount() {
		EventYear[] values = EventYear.values();

		assertEquals(3, values.length);
	}

	@Test
	public void testYear2016() {
		EventYear eventYearEnum = EventYear.YEAR_2016;

		assertEquals("[description] not correct!", "2016", eventYearEnum.getDescription());
		assertEquals("[year] not correct!", Integer.valueOf(2016), eventYearEnum.getYear());
	}

	@Test
	public void testYear2017() {
		EventYear eventYearEnum = EventYear.YEAR_2017;

		assertEquals("[description] not correct!", "2017", eventYearEnum.getDescription());
		assertEquals("[year] not correct!", Integer.valueOf(2017), eventYearEnum.getYear());
	}

	@Test
	public void testYear2018() {
		EventYear eventYearEnum = EventYear.YEAR_2018;

		assertEquals("[description] not correct!", "2018", eventYearEnum.getDescription());
		assertEquals("[year] not correct!", Integer.valueOf(2018), eventYearEnum.getYear());
	}

}