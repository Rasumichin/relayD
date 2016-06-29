package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShirtsizeTest {

	@Test
	public void testCount() {
		Shirtsize[] values = Shirtsize.values();

		assertEquals(11, values.length);
	}

	@Test
	public void testDecodeDamenXS() {
		Short shirtsize = Short.valueOf((short) 1);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenXS.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeDamenS() {
		Short shirtsize = Short.valueOf((short) 2);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenS.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeDamenM() {
		Short shirtsize = Short.valueOf((short) 3);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenM.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeDamenL() {
		Short shirtsize = Short.valueOf((short) 4);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeDamenXL() {
		Short shirtsize = Short.valueOf((short) 5);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.DamenXL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeHerrenS() {
		Short shirtsize = Short.valueOf((short) 6);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenS.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeHerrenM() {
		Short shirtsize = Short.valueOf((short) 7);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenM.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeHerrenL() {
		Short shirtsize = Short.valueOf((short) 8);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeHerrenXL() {
		Short shirtsize = Short.valueOf((short) 9);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenXL.getDescription(), shirtsizeEnum.getDescription());
	}

	@Test
	public void testDecodeHerrenXXL() {
		Short shirtsize = Short.valueOf((short) 10);

		Shirtsize shirtsizeEnum = Shirtsize.decode(shirtsize);

		assertEquals(Shirtsize.HerrenXXL.getDescription(), shirtsizeEnum.getDescription());
	}
}