package com.relayd.web.pagebean;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 5. Geben Sie den Anstoß zu Veränderungen
 * Man kann Leute nicht zu Veränderungen zwingen.
 * Zeigen Sie ihnen stattdessen, wie die Zukunft aussehen könnte und helfen ihnen dabei, sie mitzugestalten.
 *  - Andrew Hunt, Der Pragmatische Programmierer, Seite 7
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventDisplayTest {
	private String eventName = new String("Metro Group Marathon Düsseldorf");
	private UUID uuid = UUID.randomUUID();

	private RelayEventDisplay sut = RelayEventDisplay.newInstance(uuid, eventName);

	@Test
	public void testIsSerializable() {

		@SuppressWarnings("cast")
		boolean result = sut instanceof Serializable;

		assertTrue("Class not Serializable!", result);
	}

	@Test
	public void testCreateValidInstance() {

		UUID actualUuid = sut.getUuid();
		String actualName = sut.getName();

		assertEquals("[Uuid] not correct.", uuid, actualUuid);
		assertEquals("[Name] not correct.", eventName, actualName);
	}

	@Test
	public void testToString() {
		String expected = new String("2697d710-8967-4b2d-9ab2-8fc50ddc6138");
		RelayEventDisplay sutForThisTest = RelayEventDisplay.newInstance(UUID.fromString(expected), eventName);

		String actual = sutForThisTest.toString();

		assertEquals("[toString] not correct!", expected, actual);

	}
}