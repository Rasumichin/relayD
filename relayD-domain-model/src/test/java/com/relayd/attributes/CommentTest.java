package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
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

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testMaxLengthConstant() {
		Integer result = Comment.MAX_LENGTH;

		assertEquals("[MAX_LENGTH] not correct!", 255, result.intValue());
	}

	@Test
	public void testCreateInstance() {
		// Diskutieren: Benötigt man einen "default" Constructor?
		Comment comment = Comment.newInstance();

		assertNotNull(comment);

		boolean result = comment.getClass() == Comment.class;
		assertFalse("Instance not correct!", result);

		// Diskutieren: Sollte man die Sichtbarkeit fürs testen öffnen?
		//		boolean result = comment.getClass() == Comment.CommentNullObject.class;
		//		assertTrue("Instance not correct!", result);

		//Oder
		String toString = comment.toString();
		assertEquals("", toString);

	}

	@Test
	public void testCreateInstance_ForParameter() {
		Comment comment = Comment.newInstance("Comment");

		assertNotNull(comment);

		boolean result = comment.getClass() == Comment.class;
		assertTrue("Instance not correct!", result);
		//Oder
		String toString = comment.toString();
		assertEquals("Comment", toString);
	}

	@Test
	public void testCreateInstance_ForNullValue() {
		Comment comment = Comment.newInstance(null);

		assertNotNull(comment);

		boolean result = comment.getClass() == Comment.class;
		assertFalse("Instance not correct!", result);

		// Diskutieren: Sollte man die Sichtbarkeit fürs testen öffnen?
		//		boolean result = comment.getClass() == Comment.CommentNullObject.class;
		//		assertTrue("Instance not correct!", result);

		//Oder
		String toString = comment.toString();
		assertEquals("", toString);
	}

	@Test
	public void testSameInstanceForTwoObjects() {
		Comment firstComment = Comment.newInstance(null);

		Comment secondComment = Comment.newInstance(null);

		assertSame("Should be the same!", firstComment, secondComment);
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