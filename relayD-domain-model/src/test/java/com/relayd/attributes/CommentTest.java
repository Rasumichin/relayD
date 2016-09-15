package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 * status initial
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

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testMaxLengthConstant() {
		Integer result = Comment.MAX_LENGTH;

		assertEquals("[MAX_LENGTH] not correct!", 255, result.intValue());
	}

	@Test
	public void testCreateInstance() {
		Comment comment = Comment.newInstance("Comment");

		assertNotNull(comment);
	}

	@Test
	public void testCreateInstanceWithIllegalNullValue() {
		Comment comment = Comment.newInstance(null);

		assertNotNull(comment);
	}

	@Test
	public void testToString() {
		final String COMMENTTEXT = "This is a comment!";
		Comment comment = Comment.newInstance(COMMENTTEXT);

		String result = comment.toString();

		assertEquals(COMMENTTEXT, result);
	}

	@Test
	public void testGetHashCode() {
		Comment sut = Comment.newInstance("Comment");

		int hashCode = sut.hashCode();

		assertEquals(-1679915426, hashCode);

		sut.value = null;

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		Comment sut = Comment.newInstance("Comment");

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithNull() {
		Comment sut = Comment.newInstance("Comment");

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		Comment sut = Comment.newInstance("Comment");

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEqualsWithValueIsNull() {
		Comment sut = Comment.newInstance("Comment");
		sut.value = null;
		Comment secondName = Comment.newInstance("Comment");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Comment sut = Comment.newInstance("Comment");
		sut.value = null;
		Comment secondName = Comment.newInstance("Comment");
		secondName.value = null;

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Comment sut = Comment.newInstance("Comment");
		Comment secondName = Comment.newInstance("NotComment");

		boolean result = sut.equals(secondName);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Comment sut = Comment.newInstance("Comment");
		Comment secondName = Comment.newInstance("Comment");

		boolean result = sut.equals(secondName);

		assertTrue(result);
	}
}