package com.relayd.attributes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class SurenameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateValidObject() {
		String value = "McFly";
		Surename surename = Surename.newInstance(value);

		assertEquals(value, surename.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_Null_WithExpectedAssert() {
		Surename.newInstance(null);
	}

	@Test
	public void testCreateInvalidObject_Null_WithRuleAssert() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("null");
		Surename.newInstance(null);
	}
}