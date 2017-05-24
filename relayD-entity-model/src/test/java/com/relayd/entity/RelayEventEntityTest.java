package com.relayd.entity;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Es gibt nur eine Zeit, in der es wesentlich ist, Tests zu schreiben.
 * Diese Zeit ist jetzt.
 *  - Siddhartha Gautama
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventEntityTest {

	@Test
	public void testConstructor() {
		RelayEventEntity sut = new RelayEventEntity();
		assertNull("[id] not correct!", sut.getId());
		assertNull("[eventDay] not correct!", sut.getEventDay());
		assertNull("[eventName] not correct!", sut.getEventName());
	}

	@Test
	public void testNewInstance() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		String result = sut.getId();
		assertNotNull("Instance has not been created correctly!", result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_forUuidWithNullValue() {
		@SuppressWarnings("unused")
		RelayEventEntity sut = RelayEventEntity.newInstance(null);
	}

	@Test
	public void testNewInstance_isCreatedWithoutAnyFurtherInformation() {
		RelayEventEntity sut = RelayEventEntity.newInstance();
		assertNotNull("Id of RelayEventEntity must not be 'null' after creation!", sut.getId());
		assertNull("[eventName] not correct!", sut.getEventName());
		assertNull("[eventDay] not correct!", sut.getEventDay());
	}

	@Test
	public void testNewInstance_withUuid() {
		UUID expected = UUID.randomUUID();
		RelayEventEntity sut = RelayEventEntity.newInstance(expected);

		UUID actual = UUID.fromString(sut.getId());
		assertEquals("[id] has not been set correctly!", expected, actual);
	}

	@Test
	public void testEquals_true() {
		UUID someId = UUID.randomUUID();
		RelayEventEntity rundUmEnnepetal = RelayEventEntity.newInstance(someId);
		rundUmEnnepetal.setEventName("Rund Um Ennepetal");

		RelayEventEntity metroMarathon = RelayEventEntity.newInstance(someId);
		metroMarathon.setEventName("Metro Marathon");

		assertEquals("Equality has not been tested correctly!", metroMarathon, rundUmEnnepetal);
	}

	@Test
	public void testEquals_false() {
		RelayEventEntity rundUmEnnepetal = RelayEventEntity.newInstance();
		rundUmEnnepetal.setEventName("Rund um Ennepetal");

		RelayEventEntity metroMarathon = RelayEventEntity.newInstance();
		metroMarathon.setEventName("Metro Marathon");

		assertNotEquals("Equality has not been tested correctly!", metroMarathon, rundUmEnnepetal);
	}

	@Test
	public void testSetEventName() {
		String eventName = "Rund um Ennepetal";
		RelayEventEntity sut = RelayEventEntity.newInstance();

		sut.setEventName(eventName);
		assertEquals("[eventName] has not been set correctly!", eventName, sut.getEventName());
	}

	@Test
	public void testSetEventDay() {
		Date eventDay = new Date(new GregorianCalendar(2016, Calendar.MARCH, 23).getTimeInMillis());
		RelayEventEntity sut = RelayEventEntity.newInstance();

		sut.setEventDay(eventDay);
		assertEquals("[eventDay] has not been set correctly!", eventDay, sut.getEventDay());
	}

	@Test
	public void testAddRelayEntity() {
		RelayEntity expected = RelayEntity.newInstance(UUID.randomUUID().toString());
		RelayEventEntity sut = new RelayEventEntity();

		sut.addRelay(expected);

		List<RelayEntity> someRelayEntities = sut.getRelayEntities();
		assertFalse("[RelayEntities] are empty!", someRelayEntities.isEmpty());

		RelayEntity actual = someRelayEntities.get(0);
		assertEquals("[RelayEntity] is not equals!", expected, actual);
	}

	@Test
	public void testToString() {
		RelayEventEntity sut = RelayEventEntity.newInstance();
		String eventName = "Metro Marathon";
		sut.setEventName(eventName);

		Date eventDay = new Date(new GregorianCalendar(2016, Calendar.APRIL, 30).getTimeInMillis());
		sut.setEventDay(eventDay);

		String expectedResult = "RelayEventEntity [id=" + sut.getId() + ", eventName=" + eventName + ", eventDay=" + eventDay + "]";

		String actualResult = sut.toString();
		assertEquals("String representation is not correct!", expectedResult, actualResult);
	}

	@Test
	public void testHashCode() {
		RelayEventEntity sut = RelayEventEntity.newInstance();
		sut.setId("5697d710-8967-4b2d-9ab2-8fc50ddc6138");

		int hashCode = sut.hashCode();

		assertEquals(2031501961, hashCode);

		sut.setId(null);

		hashCode = sut.hashCode();

		assertEquals(31, hashCode);
	}

	@Test
	public void testEqualsWithMyself() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		boolean condition = sut.equals(sut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithNull() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		boolean condition = sut.equals(null);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithNotCompatibleClass() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		boolean condition = sut.equals(new String());

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithIdIsNull() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		sut.setId(null);

		RelayEventEntity secondSut = RelayEventEntity.newInstance();
		secondSut.setId(UUID.randomUUID().toString());

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithBothIdsAreNull() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		sut.setId(null);

		RelayEventEntity secondSut = RelayEventEntity.newInstance();
		secondSut.setId(null);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}

	@Test
	public void testEqualsWithTwoDiffrentIds() {
		RelayEventEntity sut = RelayEventEntity.newInstance();

		RelayEventEntity secondSut = RelayEventEntity.newInstance();

		boolean condition = sut.equals(secondSut);

		assertFalse(condition);
	}

	@Test
	public void testEqualsWithSameIds() {
		UUID id = UUID.randomUUID();
		RelayEventEntity sut = RelayEventEntity.newInstance(id);
		RelayEventEntity secondSut = RelayEventEntity.newInstance(id);

		boolean condition = sut.equals(secondSut);

		assertTrue(condition);
	}
}