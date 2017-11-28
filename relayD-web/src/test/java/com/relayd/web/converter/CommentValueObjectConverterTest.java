package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.relayd.attributes.Comment;

/**
 * Quality is free, but only to those who are willing to pay heavily for it.
 *  - Tom DeMarco
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   25.08.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentValueObjectConverterTest {
	private CommentValueObjectConverter sut = new CommentValueObjectConverter();

	private final String text = "Möchte gerne mit Personen aus dem B-Turm laufen!";

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object object = sut.getAsObject(null, null, nullValue);

		assertNull("Expected valid instance!", object);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object object = sut.getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance!", object);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Object object = sut.getAsObject(null, null, text);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Class not correct!", Comment.class, object.getClass());
		Comment comment = (Comment) object;
		assertEquals("Attribute not correct!", text, comment.toString());
	}

	@Test
	public void testGetAsString() {
		Comment comment = Comment.newInstance(text);

		String object = sut.getAsString(null, null, comment);

		assertNotNull("Expected valid instance!", object);
		assertEquals("Attribute not correct!", text, object);
	}
}