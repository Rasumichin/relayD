package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Don't pass 'Null'.
 *  - Michael Feathers (Chapter 7 of Robert C. Martin's "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  03.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Relay2EntityTest {

	@Test
	public void testNewInstance() {
		Relay2Entity sut = Relay2Entity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_withUuid() {
		UUID expected = UUID.randomUUID();
		Relay2Entity sut = Relay2Entity.newInstance(expected);

		UUID actual = UUID.fromString(sut.getId());
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		@SuppressWarnings("unused")
		Relay2Entity sut = Relay2Entity.newInstance(null);
	}

	@Test
	public void testRelayname() {
		String relayname = "Staubwolke";
		Relay2Entity sut = Relay2Entity.newInstance();

		sut.setRelayname(relayname);
		assertEquals("[relayname] has not been set correctly!", relayname, sut.getRelayname());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRelayname_withNull() {
		Relay2Entity sut = Relay2Entity.newInstance();
		sut.setRelayname(null);
	}
	
	@Test
	public void testRelayEventEntity() {
		Relay2Entity sut = Relay2Entity.newInstance();
		RelayEventEntity expected = new RelayEventEntity.Builder("MyRelayEvent").build();
		
		sut.setRelayEventEntity(expected);
		RelayEventEntity actual = sut.getRelayEventEntity();
		
		assertEquals("[relayEventEntity] has not been set correctly!", expected, actual);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRelayEventEntity_withNull() {
		Relay2Entity sut = Relay2Entity.newInstance();
		sut.setRelayEventEntity(null);
	}
	
	@Test
	public void testToString() {
		Relay2Entity sut = Relay2Entity.newInstance();
		String relayname = "Staubwolke";
		sut.setRelayname(relayname);

		String expected = "Relay2Entity [id=" + sut.getId() + ", relayname=" + relayname + "]";

		String actual = sut.toString();
		assertEquals("String representation is not correct!", expected, actual);
	}

	@Test
	public void testHashCode() {
		Relay2Entity sut = Relay2Entity.newInstance();
		sut.setId("5697d710-8967-4b2d-9ab2-8fc50ddc6138");

		int hashCode = sut.hashCode();

		assertEquals(2031501961, hashCode);

		sut.setId(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEquals_WithMyself() {
		Relay2Entity sut = Relay2Entity.newInstance();

		boolean result = sut.equals(sut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithNull() {
		Relay2Entity sut = Relay2Entity.newInstance();

		boolean result = sut.equals(null);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithNotCompatibleClass() {
		Relay2Entity sut = Relay2Entity.newInstance();

		boolean result = sut.equals(new String());

		assertFalse(result);
	}

	@Test
	public void testEquals_WithDiffrentValues() {
		Relay2Entity sut = Relay2Entity.newInstance();
		Relay2Entity secondSut = Relay2Entity.newInstance();

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithSameValues() {
		Relay2Entity sut = Relay2Entity.newInstance();
		Relay2Entity secondSut = Relay2Entity.newInstance();
		secondSut.setId(sut.getId());

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}

	@Test
	public void testEquals_WithValueIsNull() {
		Relay2Entity sut = Relay2Entity.newInstance();
		sut.setId(null);
		Relay2Entity secondSut = Relay2Entity.newInstance();

		boolean result = sut.equals(secondSut);

		assertFalse(result);
	}

	@Test
	public void testEquals_WithBothValuesAreNull() {
		Relay2Entity sut = Relay2Entity.newInstance();
		sut.setId(null);

		Relay2Entity secondSut = Relay2Entity.newInstance();
		secondSut.setId(null);

		boolean result = sut.equals(secondSut);

		assertTrue(result);
	}
}