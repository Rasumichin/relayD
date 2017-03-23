package com.relayd.ejb.orm.file;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Participant;
import com.relayd.ParticipantBuilder;
import com.relayd.Relay;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;

/**
 * Es gibt nichts zu erreichen, nichts zu tun und nichts zu besitzen.
 *  - Zen Weisheit
 *
 * @author schmollc (Christian@relayd.de)
 * @since 28.12.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayToRelayMapperTest {
	private RelayToRelayMapper sut = RelayToRelayMapper.newInstance();

	private Relay source = Relay.newInstance();
	private Relay target = Relay.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapRelayToRelay_ForSourceIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[source] must not be 'null'!");

		sut.mapRelayToRelay(null, target);
	}

	@Test
	public void testMapRelayToRelay_ForTargetIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[target] must not be 'null'!");

		sut.mapRelayToRelay(source, null);
	}

	@Test
	public void testMapRelayToRelay_Relayname() {
		Relayname expected = Relayname.newInstance("Die 4 ????");
		source.setRelayname(expected);

		sut.mapRelayToRelay(source, target);

		Relayname actual = target.getRelayname();

		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayToRelay_Duration() {
		Duration expected = Duration.ofMillis(7892L);
		source.setDuration(expected);

		sut.mapRelayToRelay(source, target);

		Duration actual = target.getDuration();

		assertEquals("Mapping of [duration] is not correct!", expected, actual);
	}

	@Test
	public void testMapRelayToRelay_ForFilledSourceAndEmptyTarget() {
		Participant actual = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").build();
		source.addParticipant(actual, Position.SECOND);

		target.setUuid(source.getUuid());

		sut.mapRelayToRelay(source, target);

		Participant expected = target.getParticipantFor(Position.SECOND);

		assertEquals("[mapRelayToRelay] nicht korrekt!", expected, actual);
	}
}