package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Comment.CommentNullObject;

/**
 * Is 100% code coverage realistic?
 * Of course it is.
 * If you can write a line of code, you can write another that tests it.
 *  - Robert C. Martin
 *
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentTest {

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Comment sut = Comment.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class is not Serializable!", result);
	}

	@Test
	public void testMaxLengthConstant() {
		Integer actual = Comment.MAX_LENGTH;

		assertEquals("[MAX_LENGTH] not correct!", 255, actual.intValue());
	}

	@Test
	public void testCreateInstance() {
		Comment sut = Comment.newInstance();

		assertNotNull("Not a valid instance!", sut);

		boolean result = sut.getClass() == CommentNullObject.class;

		assertTrue("Instance is not correct!", result);
	}

	@Test
	public void testCreateInstance_ForParameter() {
		Comment sut = Comment.newInstance("Comment");

		assertNotNull(sut);

		boolean result = sut.getClass() == Comment.class;

		assertTrue("Instance not correct!", result);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Comment sut = Comment.newInstance(null);

		assertNotNull(sut);

		boolean actual = sut.getClass() == CommentNullObject.class;

		assertTrue("Instance not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForEmptyValue() {
		Comment sut = Comment.newInstance("");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == CommentNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testCreateInstance_ForBlankValue() {
		Comment sut = Comment.newInstance("   ");

		assertNotNull("Not a valid instance!", sut);

		boolean actual = sut.getClass() == CommentNullObject.class;

		assertTrue("Instance is not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForValueEmpty() {
		Comment sut = Comment.newInstance("");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilled() {
		String comment = "Gerne kurze Strecke";
		Comment sut = Comment.newInstance(comment);

		boolean result = sut.isEmpty();

		assertFalse("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueFilledWithBlank() {
		Comment sut = Comment.newInstance("    ");

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testIsEmpty_ForValueNull() {
		Comment sut = Comment.newInstance(null);

		boolean result = sut.isEmpty();

		assertTrue("[result] for isEmpty is not correct!", result);
	}

	@Test
	public void testSamenessOfTwoNullComment() {
		Comment sut = Comment.newInstance();
		Comment otherNullComment = Comment.newInstance();

		assertSame("Two EventnameNullObjects are not the same!", sut, otherNullComment);
	}

	@Test
	public void testToString() {
		String expected = "a comment";

		Comment sut = Comment.newInstance(expected);

		String actual = sut.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void testToString_ForEmptyValue() {
		String expected = "";
		Comment sut = Comment.newInstance(expected);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testToString_ForBlankValue() {
		Comment sut = Comment.newInstance("   ");

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testToString_ForNullValue() {
		Comment sut = Comment.newInstance(null);

		String actual = sut.toString();

		assertEquals("String representation is not correct!", "", actual);
	}

	@Test
	public void testHashCode() {
		Comment sut = Comment.newInstance("Comment");

		int hashCode = sut.hashCode();

		assertEquals(-1679915426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		Comment sut = Comment.newInstance("Comment");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		Comment sut = Comment.newInstance("Comment");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Comment sut = Comment.newInstance("Comment");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Comment sut = Comment.newInstance("Comment");
		sut.value = null;
		Comment secondSut = Comment.newInstance("Comment");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Comment sut = Comment.newInstance("Comment");
		sut.value = null;
		Comment secondSut = Comment.newInstance("Comment");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithTwoDiffrentValues() {
		Comment sut = Comment.newInstance("Comment");
		Comment secondSut = Comment.newInstance("NotComment");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		Comment sut = Comment.newInstance("Comment");
		Comment secondSut = Comment.newInstance("Comment");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}