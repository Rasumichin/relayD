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
public class ForenameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateValidObject() {
		final String NAME = "Marty";
		Forename surename = Forename.newInstance(NAME);

		assertEquals(NAME, surename.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_Null_WithExpectedAssert() {
		Forename.newInstance(null);
	}

	@Test
	public void testCreateInvalidObject_Null_WithRuleAssert() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("null");
		Forename.newInstance(null);
	}
}