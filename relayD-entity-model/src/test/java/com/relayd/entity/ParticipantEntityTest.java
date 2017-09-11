package com.relayd.entity;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Böses Gewerbe bringt bösen Lohn.
 *  - Friedrich Schiller
 *
 * @author schmollc (Christian@relayd.de)
 * @since  11.09.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParticipantEntityTest {

	@Test
	public void testConstructor() {
		ParticipantEntity sut = new ParticipantEntity();
		assertNull("[id] not correct!", sut.getId());
	}

	@Test
	public void testNewInstance() {
		ParticipantEntity sut = ParticipantEntity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test
	public void testNewInstance_withUuid() {
		String expected = UUID.randomUUID().toString();
		ParticipantEntity sut = ParticipantEntity.newInstance(expected);

		String actual = sut.getId();
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		ParticipantEntity.newInstance(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectEmptyUuid() {
		ParticipantEntity.newInstance("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectBlankUuid() {
		ParticipantEntity.newInstance("   ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forIncorrectFalseUuid() {
		ParticipantEntity.newInstance("987-345-aw3-123");
	}

}