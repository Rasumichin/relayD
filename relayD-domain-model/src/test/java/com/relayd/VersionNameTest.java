package com.relayd;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Verantwortlich ist man nicht nur für das, was man tut, sondern auch für das, was man nicht tut.
 *  - Laotse
 *
 * @author  schmollc (Christian@relayd.com)
 * @author           (Dirk@relayd.com)
 * @since   01.12.2016
 *
 * TODO (Dirk, Version 1.4): Umbennen auf Version, da dieses Enum Namen UND Nummer enthält.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VersionNameTest {

	@Test
	public void testCount() {
		VersionName[] values = VersionName.values();

		assertEquals("Wrong count for Enum entries!", 5, values.length);
	}

	@Test
	public void testNewInstance_ForUnknown() {
		VersionName sut = VersionName.newInstance("Dummy");

		assertSame("Not Unknwon enum", VersionName.UNKNOWN, sut);
		assertEquals("[codename] not correct!", "Unknown Codename", sut.getCodename());
		assertEquals("[version] not correct!", "UNKNOWN", sut.getVersion());
	}

	@Test
	public void testNewInstance_ForAugustiner() {
		VersionName sut = VersionName.newInstance(VersionName.AUGUSTINER.getVersion());

		assertSame("Not Augustiner enum", VersionName.AUGUSTINER, sut);
		assertEquals("[codename] not correct!", "Augustiner", sut.getCodename());
		assertEquals("[version] not correct!", "1.0", sut.getVersion());
	}

	@Test
	public void testNewInstance_ForBitburger() {
		VersionName sut = VersionName.newInstance(VersionName.BITBURGER.getVersion());

		assertSame("Not Bitburger enum", VersionName.BITBURGER, sut);
		assertEquals("[codename] not correct!", "Bitburger", sut.getCodename());
		assertEquals("[version] not correct!", "1.1", sut.getVersion());
	}

	@Test
	public void testNewInstance_ForCoellner() {
		VersionName sut = VersionName.newInstance(VersionName.COELLNER.getVersion());

		assertSame("Not Coellner enum", VersionName.COELLNER, sut);
		assertEquals("[codename] not correct!", "Cöllner Hofbräu Früh", sut.getCodename());
		assertEquals("[version] not correct!", "1.2", sut.getVersion());
	}

	@Test
	public void testNewInstance_ForDuckstein() {
		VersionName sut = VersionName.newInstance(VersionName.DUCKSTEIN.getVersion());

		assertSame("Not Duckstein enum", VersionName.DUCKSTEIN, sut);
		assertEquals("[codename] not correct!", "Duckstein", sut.getCodename());
		assertEquals("[version] not correct!", "1.3", sut.getVersion());
	}
}