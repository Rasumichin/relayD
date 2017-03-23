package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.time.Duration;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.Relay;
import com.relayd.RelayEvent;
import com.relayd.attributes.Relayname;
import com.relayd.entity.RelayEntity;
import com.relayd.entity.RelayEventEntity;

/**
 * If the code and the comments disagree, then both are probably wrong.
 *  - Norm Schryer
 *
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayToEntityMapperTest {

	private RelayToEntityMapper sut = RelayToEntityMapper.newInstance();
	private Relay relay;
	private RelayEntity relayEntity = RelayEntity.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		relay = Relay.newInstance(RelayEvent.duesseldorf());
		relay.setRelayname(Relayname.newInstance("Some name"));
		relayEntity.setRelayEventEntity(new RelayEventEntity.Builder("Some event").build());
	}

	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapDomainToEntity_whenDomainIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relay] must not be 'null'!");

		sut.mapRelayToEntity(null, relayEntity);
	}

	@Test
	public void testMapDomainToEntity_whenEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relayEntity] must not be 'null'!");

		sut.mapRelayToEntity(relay, null);
	}

	@Test
	public void testMapDomainToEntity_id() {
		RelayEntity relayEntity = RelayEntity.newInstance(relay.getUuid().toString());
		relayEntity.setRelayEventEntity(new RelayEventEntity.Builder("Some event").build());
		String expected = relay.getUuid().toString();

		sut.mapRelayToEntity(relay, relayEntity);

		String actual = relayEntity.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayname() {
		String expected = "Die 4 ????";
		relay.setRelayname(Relayname.newInstance(expected));

		sut.mapRelayToEntity(relay, relayEntity);

		String actual = relayEntity.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_duration() {
		Long expected = 3463L;
		relay.setDuration(Duration.ofMillis(expected));

		sut.mapRelayToEntity(relay, relayEntity);

		Long actual = relayEntity.getDuration();
		assertEquals("Mapping of [duration] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayevent() {
		String expected = RelayEvent.duesseldorf().getName().toString();
		relayEntity.setRelayEventEntity(new RelayEventEntity.Builder(expected).build());

		sut.mapRelayToEntity(relay, relayEntity);

		String actual = relayEntity.getRelayEventEntity().getEventName();
		assertEquals("Mapping of [relayevent] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayEventEntityIsNull() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("[relayEventEntity] must not be 'null' at this point!");

		sut.mapRelayToEntity(relay, RelayEntity.newInstance());
	}

	@Test
	public void testMapRelayToEntity() {
		sut.mapRelayToEntity(relay, relayEntity);
	}
}