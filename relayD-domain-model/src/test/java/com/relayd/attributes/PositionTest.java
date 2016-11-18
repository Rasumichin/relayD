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
	public void testDecodeForDefault() {
		Integer position = null;

		Position defaultValue = Position.decode(position);

		assertEquals("[Position] not correct!", Position.DEFAULT, defaultValue);
	}

	@Test
	public void testDecodeForOne() {
		Integer position = 1;

		Position first = Position.decode(position);

		assertEquals("[Position] not correct!", Position.FIRST, first);
	}

	@Test
	public void testDecodeForTwo() {
		Integer position = 2;

		Position second = Position.decode(position);

		assertEquals("[Position] not correct!", Position.SECOND, second);
	}

	@Test
	public void testDecodeForThree() {
		Integer position = 3;

		Position third = Position.decode(position);

		assertEquals("[Position] not correct!", Position.THIRD, third);
	}

	@Test
	public void testDecodeForFour() {
		Integer position = 4;

		Position fourth = Position.decode(position);

		assertEquals("[Position] not correct!", Position.FOURTH, fourth);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDecodeForUnkwonValue() {
		Position.decode(5);
	}

	@Test
	public void testIsEmpty_ForDefault() {
		Position position = Position.DEFAULT;

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