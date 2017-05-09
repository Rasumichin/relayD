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
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VersionTest {

	@Test
	public void testCount() {
		Version[] values = Version.values();

		assertEquals("Wrong count for Enum entries!", 13, values.length);
	}

	@Test
	public void testNewInstance_ForUnknown() {
		Version sut = Version.newInstance("Dummy");

		assertSame("Not Unknwon enum", Version.UNKNOWN, sut);
		assertEquals("[codename] not correct!", "Unknown Description", sut.getDescription());
		assertEquals("[version] not correct!", "UNKNOWN", sut.getValue());
	}

	@Test
	public void testNewInstance_ForAugustiner() {
		Version sut = Version.newInstance(Version.AUGUSTINER.getValue());

		assertSame("Not Augustiner enum", Version.AUGUSTINER, sut);
		assertEquals("[codename] not correct!", "Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.0", sut.getValue());
	}

	@Test
	public void testNewInstance_ForBitburger() {
		Version sut = Version.newInstance(Version.BITBURGER.getValue());

		assertSame("Not Bitburger enum", Version.BITBURGER, sut);
		assertEquals("[codename] not correct!", "Bitburger", sut.getDescription());
		assertEquals("[version] not correct!", "1.1", sut.getValue());
	}

	@Test
	public void testNewInstance_ForCoellner() {
		Version sut = Version.newInstance(Version.COELLNER.getValue());

		assertSame("Not Coellner enum", Version.COELLNER, sut);
		assertEquals("[codename] not correct!", "Cöllner Hofbräu Früh", sut.getDescription());
		assertEquals("[version] not correct!", "1.2", sut.getValue());
	}

	@Test
	public void testNewInstance_ForDuckstein() {
		Version sut = Version.newInstance(Version.DUCKSTEIN.getValue());

		assertSame("Not Duckstein enum", Version.DUCKSTEIN, sut);
		assertEquals("[codename] not correct!", "Duckstein", sut.getDescription());
		assertEquals("[version] not correct!", "1.3", sut.getValue());
	}

	@Test
	public void testNewInstance_ForEngel() {
		Version sut = Version.newInstance(Version.ENGEL.getValue());

		assertSame("Not Engelenum", Version.ENGEL, sut);
		assertEquals("[codename] not correct!", "Engel", sut.getDescription());
		assertEquals("[version] not correct!", "1.4", sut.getValue());
	}

	@Test
	public void testNewInstance_ForF() {
		Version sut = Version.newInstance(Version.F.getValue());

		assertSame("Not F enum", Version.F, sut);
		assertEquals("[codename] not correct!", "Frisches Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.5", sut.getValue());
	}

	@Test
	public void testNewInstance_ForG() {
		Version sut = Version.newInstance(Version.G.getValue());

		assertSame("Not G enum", Version.G, sut);
		assertEquals("[codename] not correct!", "Geiles Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.6", sut.getValue());
	}

	@Test
	public void testNewInstance_ForH() {
		Version sut = Version.newInstance(Version.H.getValue());

		assertSame("Not H enum", Version.H, sut);
		assertEquals("[codename] not correct!", "Helles Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.7", sut.getValue());
	}

	@Test
	public void testNewInstance_ForI() {
		Version sut = Version.newInstance(Version.I.getValue());

		assertSame("Not I enum", Version.I, sut);
		assertEquals("[codename] not correct!", "Intrinsisches Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.8", sut.getValue());
	}

	@Test
	public void testNewInstance_ForJ() {
		Version sut = Version.newInstance(Version.J.getValue());

		assertSame("Not J enum", Version.J, sut);
		assertEquals("[codename] not correct!", "Jungfräuliches Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.9", sut.getValue());
	}

	@Test
	public void testNewInstance_ForK() {
		Version sut = Version.newInstance(Version.K.getValue());

		assertSame("Not K enum", Version.K, sut);
		assertEquals("[codename] not correct!", "Kühles Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.10", sut.getValue());
	}

	@Test
	public void testNewInstance_ForL() {
		Version sut = Version.newInstance(Version.L.getValue());

		assertSame("Not L enum", Version.L, sut);
		assertEquals("[codename] not correct!", "Luftiges Augustiner", sut.getDescription());
		assertEquals("[version] not correct!", "1.11", sut.getValue());
	}
}