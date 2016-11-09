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

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testMaxLengthConstant() {
		Integer actual = Comment.MAX_LENGTH;

		assertEquals("[MAX_LENGTH] not correct!", 255, actual.intValue());
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
	public void testIsEmpty_usualValue() {
		Comment sut = Comment.newInstance("Comment");

		boolean actual = sut.isEmpty();

		assertFalse("'isEmpty' check is not correct!", actual);
	}

	@Test
	public void testIsEmpty_nullValue() {
		Comment sut = Comment.newInstance();

		boolean actual = sut.isEmpty();

		assertTrue("'isEmpty' check is not correct!", actual);
	}

	@Test
	public void testSameInstanceForTwoObjects() {
		Comment firstComment = Comment.newInstance();

		Comment secondComment = Comment.newInstance();

		assertSame("Should be the same!", firstComment, secondComment);
	}

	@Test
	public void testToString() {
		String commentText = "This is a comment!";
		Comment comment = Comment.newInstance(commentText);

		String actual = comment.toString();

		assertEquals(commentText, actual);
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
		Comment secondSut = Comment.newInstance("Comment");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithBothValuesAreNull() {
		Comment sut = Comment.newInstance("Comment");
		sut.value = null;
		Comment secondSut = Comment.newInstance("Comment");
		secondSut.value = null;

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEqualsWithTwoDiffrentValues() {
		Comment sut = Comment.newInstance("Comment");
		Comment secondSut = Comment.newInstance("NotComment");

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEqualsWithSameValues() {
		Comment sut = Comment.newInstance("Comment");
		Comment secondSut = Comment.newInstance("Comment");

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}