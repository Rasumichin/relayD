package com.relayd.attributes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   23.03.2016
 * status   initial
 */
public class RelaynameTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateValidObject() {
		final String NAME = "Die vier ????";
		Relayname realyname = Relayname.newInstance(NAME);

		assertEquals(NAME, realyname.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInvalidObject_Null_WithExpectedAssert() {
		Relayname.newInstance(null);
	}

	@Test
	public void testCreateInvalidObject_Null_WithRuleAssert() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("null");
		Relayname.newInstance(null);
	}

}
