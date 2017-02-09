package com.relayd.web.converter;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 08.09.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class NameValueObjectConverterTest {

	abstract NameValueObjectConverter getSut();

	@Test
	@Ignore
	// TODO (Erik, Version 1.4): Check whether we should test this case with regardo to JSF spec.
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object result = getSut().getAsObject(null, null, nullValue);

		assertNull("Expected valid instance.", result);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object result = getSut().getAsObject(null, null, emptyValue);

		assertNull("Expected valid instance.", result);
	}

	@Test
	public void testGetAsObject_ForValueContainingOnlySpaces() {
		String nameWithSpaces = "  ";
		Object result = getSut().getAsObject(null, null, nameWithSpaces);

		assertNull("Attribute is not correct.", result);
	}
}
