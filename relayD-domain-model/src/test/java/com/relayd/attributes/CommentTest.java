package com.relayd.attributes;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;

/**
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 * status initial
 *
 */
public class CommentTest {

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
		final String COMMENTTEXT = "Dies ist ein Kommentar.";
		Comment comment = Comment.newInstance(COMMENTTEXT);

		String result = comment.toString();

		assertEquals(COMMENTTEXT, result);
	}

	@Test
	public void testIsSerializable() {
		String dummyString = "";
		Comment sut = Comment.newInstance(dummyString);

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}
}