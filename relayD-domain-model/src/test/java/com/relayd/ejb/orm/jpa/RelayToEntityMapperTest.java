package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.relayd.*;
import com.relayd.attributes.Position;
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
	private RelayEntity relayEntity = RelayEntity.newInstance();
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

	@Ignore
	@Test
	public void testMapDomainToEntity_participants() {

		Participant participantOne = new ParticipantBuilder().withForename("Justus").withSurename("Jonas").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantOne, Position.FIRST);

		Participant participantTwo = new ParticipantBuilder().withForename("Peter").withSurename("Shaw").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantTwo, Position.SECOND);

		Participant participantThree = new ParticipantBuilder().withForename("Bob").withSurename("Andrews").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantThree, Position.THIRD);

		Participant participantFour = new ParticipantBuilder().withForename("Tante").withSurename("Mathilda").withUUIDPerson(UUID.randomUUID()).build();
		relay.addParticipant(participantFour, Position.FOURTH);

		sut.mapRelayToEntity(relay, relayEntity);

		UUID actualOne = relayEntity.getParticipantOne();
		assertEquals("Mapping of [participantFirst] is not correct!", participantOne.getUuidPerson(), actualOne);

		UUID actualTwo = relayEntity.getParticipantTwo();
		assertEquals("Mapping of [participantSecond] is not correct!", participantTwo.getUuidPerson(), actualTwo);

		UUID actualThree = relayEntity.getParticipantThree();
		assertEquals("Mapping of [participantThird] is not correct!", participantThree.getUuidPerson(), actualThree);

		UUID actualFour = relayEntity.getParticipantFour();
		assertEquals("Mapping of [participantFourth] is not correct!", participantFour.getUuidPerson(), actualFour);
	}
	
	@Test
	public void testMapRelayToEntity() {
		sut.mapRelayToEntity2(relay, relay2Entity);
	}
}