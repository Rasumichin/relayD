package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.*;
import com.relayd.attributes.Relayname;
import com.relayd.entity.*;

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
	private Relay2Entity relay2Entity = Relay2Entity.newInstance();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		relay = Relay.newInstance(RelayEvent.duesseldorf());
		relay.setRelayname(Relayname.newInstance("Some name"));
		relay2Entity.setRelayEventEntity(new RelayEventEntity.Builder("Some event").build());
	}
	
	@Test
	public void testNewInstance() {
		assertNotNull("Instance creation is not correct!", sut);
	}

	@Test
	public void testMapDomainToEntity_whenDomainIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relay] must not be 'null'!");

		sut.mapRelayToEntity2(null, relay2Entity);
	}

	@Test
	public void testMapDomainToEntity_whenEntityIsNull() {
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("[relayEntity] must not be 'null'!");

		sut.mapRelayToEntity2(relay, null);
	}

	@Test
	public void testMapDomainToEntity_id() {
		Relay2Entity relayEntity = Relay2Entity.newInstance(relay.getUuid().toString());
		relayEntity.setRelayEventEntity(new RelayEventEntity.Builder("Some event").build());
		String expected = relay.getUuid().toString();

		sut.mapRelayToEntity2(relay, relayEntity);

		String actual = relayEntity.getId();
		assertEquals("Mapping of [uuid] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayname() {
		String expected = "Die 4 ????";
		relay.setRelayname(Relayname.newInstance(expected));

		sut.mapRelayToEntity2(relay, relay2Entity);

		String actual = relay2Entity.getRelayname();
		assertEquals("Mapping of [relayname] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayevent() {
		String expected = RelayEvent.duesseldorf().getName().toString();
		relay2Entity.setRelayEventEntity(new RelayEventEntity.Builder(expected).build());

		sut.mapRelayToEntity2(relay, relay2Entity);
		
		String actual = relay2Entity.getRelayEventEntity().getEventName();
		assertEquals("Mapping of [relayevent] is not correct!", expected, actual);
	}

	@Test
	public void testMapDomainToEntity_relayEventEntityIsNull() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage("[relayEventEntity] must not be 'null' at this point!");

		sut.mapRelayToEntity2(relay, Relay2Entity.newInstance());
	}

	@Test
	public void testMapRelayToEntity() {
		sut.mapRelayToEntity2(relay, relay2Entity);
	}
}