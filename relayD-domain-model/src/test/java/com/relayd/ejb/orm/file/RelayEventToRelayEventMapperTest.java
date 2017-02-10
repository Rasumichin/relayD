package com.relayd.ejb.orm.file;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;

/**
 *  1. Testen beginnt mit Respekt und endet mit Respekt.
 *  - Gichin Funakoshi
 *
 * @author schmollc
 * @since 10.02.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventToRelayEventMapperTest {
	RelayEventToRelayEventMapper sut = RelayEventToRelayEventMapper.newInstance();

	private RelayEvent source = RelayEvent.newInstance();
	private RelayEvent target = RelayEvent.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapRelayEventToRelayEvent_ForSourceIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		sut.mapRelayEventToRelayEvent(null, target);
	}

	@Test
	public void testMapRelayEventToRelayEvent_ForTargetIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		sut.mapRelayEventToRelayEvent(source, null);
	}

	@Test
	public void testMapRelayEventToRelayEvent_ForEventDay() {
		EventDay expected = EventDay.today();
		source.setEventDay(expected);

		sut.mapRelayEventToRelayEvent(source, target);

		EventDay actual = target.getEventDay();
		assertEquals("Mapping of [eventDay] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayEventToRelayEvent_ForName() {
		Eventname expected = Eventname.newInstance("Rund um Ennepetal");
		source.setName(expected);

		sut.mapRelayEventToRelayEvent(source, target);

		Eventname actual = target.getName();
		assertEquals("Mapping of [name] is not correct!", expected, actual);
	}

}