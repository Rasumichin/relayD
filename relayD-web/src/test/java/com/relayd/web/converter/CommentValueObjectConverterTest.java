package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.Test;

import com.relayd.attributes.Comment;

/**
 * @author  schmollc (Christian@relayD.de)
 * @since   25.08.2016
 * status   initial
 */
public class CommentValueObjectConverterTest {
	private CommentValueObjectConverter sut = new CommentValueObjectConverter();

	private final String text = "Möchte gerne mit Personen aus dem B-Turm laufen.";

	@Test
	public void testGetAsObject() {
		Object result = sut.getAsObject(null, null, text);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Comment.class, result.getClass());
		Comment comment = (Comment) result;
		assertEquals("Attribute not correct.", text, comment.toString());
	}

	@Test
	public void testGetAsString() {
		Comment comment = Comment.newInstance(text);

		String result = sut.getAsString(null, null, comment);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", text, result);
	}
}