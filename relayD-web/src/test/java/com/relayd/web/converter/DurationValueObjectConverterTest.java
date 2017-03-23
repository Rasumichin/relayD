package com.relayd.web.converter;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Auch aus Steinen, die einem in den Weg gelegt werden, kann man Schönes bauen.
 *  - Johann Wolfgang von Goethe
 *
 * @author schmollc (Christian@relayd.de)
 * @since 18.03.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DurationValueObjectConverterTest {
	private DurationValueObjectConverter sut = new DurationValueObjectConverter();

	private String durationAsString = "03:17:43";

	@Test
	public void testGetAsObject_ForNullValue() {
		String nullValue = null;
		Object result = sut.getAsObject(null, null, nullValue);

		assertNotNull("Expected valid instance.", result);
	}

	@Test
	public void testGetAsObject_ForEmptyValue() {
		String emptyValue = "";
		Object result = sut.getAsObject(null, null, emptyValue);

		assertNotNull("Expected valid instance.", result);
	}

	@Test
	public void testGetAsObject_ForValue() {
		Duration expected = Duration.ofHours(3).plusMinutes(17).plusSeconds(43);

		Object result = sut.getAsObject(null, null, durationAsString);

		assertNotNull("Expected valid instance.", result);
		assertEquals(Duration.class, result.getClass());

		Duration duration = (Duration) result;

		assertEquals("Attribute not correct.", expected, duration);
	}

	@Test
	public void testGetAsString() {
		Duration duration = Duration.ofHours(3).plusMinutes(17).plusSeconds(43);

		String result = sut.getAsString(null, null, duration);

		assertNotNull("Expected valid instance.", result);
		assertEquals("Attribute not correct.", durationAsString, result);
	}
}
