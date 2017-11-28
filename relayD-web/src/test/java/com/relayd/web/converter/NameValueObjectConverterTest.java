package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * It is unit tests that keep our code flexible, maintainable, and reusable.
 * The reason is simple.
 * If you have tests, you do not fear making changes to the code!
 * Without tests every change is a possible bug.
 *  - Robert C. Martin
 *
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 08.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class NameValueObjectConverterTest {

	abstract NameValueObjectConverter getSut();

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object object = getSut().getAsObject(null, null, nullValue);

		assertNull("Attribute is not correct!", object);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object object = getSut().getAsObject(null, null, emptyValue);

		assertNull("Attribute is not correct!", object);
	}

	@Test
	public void testGetAsObject_ForValueContainingOnlySpaces() {
		String nameWithSpaces = "  ";
		Object object = getSut().getAsObject(null, null, nameWithSpaces);

		assertNull("Attribute is not correct!", object);
	}
}
