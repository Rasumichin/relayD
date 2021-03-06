package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Es ist nicht von Bedeutung, wie langsam du gehst, solange du nicht stehen bleibst.
 *  - Konfuzius
 *
 * @author  schmollc (Christian@relayd.com)
 * @since   23.08.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PositionTest {

	@Test
	public void testCount() {
		Position[] values = Position.values();

		assertEquals("Wrong count for Enum entries!", 5, values.length);
	}

	@Test
	public void testGetDescription() {
		Position second = Position.SECOND;
		assertEquals("[description] not correct!", "Second", second.getDescription());
	}

	@Test
	public void testNewInstance_ForNull() {
		Integer position = null;

		Position defaultValue = Position.newInstance(position);

		assertEquals("[Position] not correct!", Position.UNKNOWN, defaultValue);
	}

	@Test
	public void testNewInstance_ForOne() {
		Integer position = 1;

		Position first = Position.newInstance(position);

		assertEquals("[Position] not correct!", Position.FIRST, first);
	}

	@Test
	public void testNewInstance_ForTwo() {
		Integer position = 2;

		Position second = Position.newInstance(position);

		assertEquals("[Position] not correct!", Position.SECOND, second);
	}

	@Test
	public void testNewInstance_ForThree() {
		Integer position = 3;

		Position third = Position.newInstance(position);

		assertEquals("[Position] not correct!", Position.THIRD, third);
	}

	@Test
	public void testNewInstance_ForFour() {
		Integer position = 4;

		Position fourth = Position.newInstance(position);

		assertEquals("[Position] not correct!", Position.FOURTH, fourth);
	}

	@Test
	public void testNewInstance_ForUnkwonValue() {
		Position unknwon = Position.newInstance(5);

		assertEquals("[Position] not correct!", Position.UNKNOWN, unknwon);
	}

	@Test
	public void testIsEmpty_ForDefault() {
		Position position = Position.UNKNOWN;

		boolean actual = position.isEmpty();

		assertTrue("[Position] not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForNotDefault() {
		Position position = Position.FIRST;

		boolean actual = position.isEmpty();

		assertFalse("[Position] not correct!", actual);
	}

	@Test
	public void testToString() {
		Position second = Position.SECOND;

		String result = second.toString();

		assertEquals("Second", result);
	}
}
