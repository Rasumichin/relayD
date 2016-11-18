package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Every large system that works started as a small system that worked.
 * - Anonymous
 *
 * @author  schmollc (Christian@relayd.com)
 * @since   23.03.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShirtsizeTest {

	@Test
	public void testCount() {
		Shirtsize[] values = Shirtsize.values();

		assertEquals(11, values.length);
	}

	@Test
	public void testDecodeDamenXS() {
		Integer shirtsize = Integer.valueOf(1);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenXS.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeDamenXS() {
		Shirtsize shirtsizeEnum = Shirtsize.DamenXS;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(1), shirtsize);
	}

	@Test
	public void testDecodeDamenS() {
		Integer shirtsize = Integer.valueOf(2);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenS.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeDamenS() {
		Shirtsize shirtsizeEnum = Shirtsize.DamenS;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(2), shirtsize);
	}

	@Test
	public void testDecodeDamenM() {
		Integer shirtsize = Integer.valueOf(3);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenM.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeDamenM() {
		Shirtsize shirtsizeEnum = Shirtsize.DamenM;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(3), shirtsize);
	}

	@Test
	public void testDecodeDamenL() {
		Integer shirtsize = Integer.valueOf(4);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeDamenL() {
		Shirtsize shirtsizeEnum = Shirtsize.DamenL;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(4), shirtsize);
	}

	@Test
	public void testDecodeDamenXL() {
		Integer shirtsize = Integer.valueOf(5);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenXL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeDamenXL() {
		Shirtsize shirtsizeEnum = Shirtsize.DamenXL;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(5), shirtsize);
	}

	@Test
	public void testDecodeHerrenS() {
		Integer shirtsize = Integer.valueOf(6);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenS.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeHerrenS() {
		Shirtsize shirtsizeEnum = Shirtsize.HerrenS;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(6), shirtsize);
	}

	@Test
	public void testDecodeHerrenM() {
		Integer shirtsize = Integer.valueOf(7);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenM.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeHerrenM() {
		Shirtsize shirtsizeEnum = Shirtsize.HerrenM;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(7), shirtsize);
	}

	@Test
	public void testDecodeHerrenL() {
		Integer shirtsize = Integer.valueOf(8);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeHerrenL() {
		Shirtsize shirtsizeEnum = Shirtsize.HerrenL;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(8), shirtsize);
	}

	@Test
	public void testDecodeHerrenXL() {
		Integer shirtsize = Integer.valueOf(9);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenXL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeHerrenXL() {
		Shirtsize shirtsizeEnum = Shirtsize.HerrenXL;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(9), shirtsize);
	}

	@Test
	public void testDecodeHerrenXXL() {
		Integer shirtsize = Integer.valueOf(10);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenXXL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testEncodeHerrenXXL() {
		Shirtsize shirtsizeEnum = Shirtsize.HerrenXXL;

		Integer shirtsize = Shirtsize.encode(shirtsizeEnum);

		assertEquals(Integer.valueOf(10), shirtsize);
	}

	@Test
	public void testDecodeInvalidValue() {
		Integer shirtsize = Integer.valueOf(99);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.UNKNOWN.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeForNull() {
		Integer shirtsize = null;

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.UNKNOWN.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testIsEmpty_ForDefault() {
		Shirtsize position = Shirtsize.UNKNOWN;

		boolean actual = position.isEmpty();

		assertTrue("[Shirtsize] not correct!", actual);
	}

	@Test
	public void testIsEmpty_ForNotDefault() {
		Shirtsize position = Shirtsize.DamenM;

		boolean actual = position.isEmpty();

		assertFalse("[Shirtsize] not correct!", actual);
	}

	@Test
	public void testToString() {
		Shirtsize shirtsize = Shirtsize.DamenM;

		String name = shirtsize.toString();

		assertEquals("Damen M", name);
	}
}