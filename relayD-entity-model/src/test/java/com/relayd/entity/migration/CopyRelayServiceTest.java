package com.relayd.entity.migration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.UUID;

import javax.persistence.*;

import org.junit.*;
import org.mockito.Mockito;

import com.relayd.entity.*;
import com.relayd.jpa.GenericJpaDao;

/**
* Favour composition over inheritance.
* 
* @author Rasumichin (Erik@relayd.de)
* @since  04.02.2017
*
*/
public class CopyRelayServiceTest {
	private EntityManager entityManagerMock;
	private GenericJpaDao jpaDao;
	private CopyRelayService sut;

	@Before
	public void setUp() {
		entityManagerMock = Mockito.mock(EntityManager.class);
		jpaDao = GenericJpaDao.newInstance(entityManagerMock);

		String eventId = CopyRelayService.EVENT_ID;
		RelayEventEntity staticRelayEventEntity = new RelayEventEntity.Builder("some name")
				.withId(eventId)
				.build();
		when(entityManagerMock.getTransaction()).thenReturn(mock(EntityTransaction.class));
		when(entityManagerMock.find(RelayEventEntity.class, eventId)).thenReturn(staticRelayEventEntity);

		sut = MigrationService.newCopyRelayService(jpaDao);
	}
	
	@Test
	public void testSetEvent() {
		String expected = CopyRelayService.EVENT_ID;
		
		RelayEventEntity relayEventEntity = sut.getRelayEventEntity();
		
		String actual = relayEventEntity.getId();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCopyToRelay2Entity() {
		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.setRelayname("some name");
		
		Relay2Entity result = sut.copyToRelay2Entity(relayEntity);
		
		assertNotNull("RelayEntity has not been copied correctly!", result);
	}

	@Test
	public void testCopyToRelay2Entity_Check_Relayname() {
		RelayEntity relayEntity = RelayEntity.newInstance();
		String expected = "Gold Runners";
		relayEntity.setRelayname(expected);
		
		Relay2Entity relay2Entity = sut.copyToRelay2Entity(relayEntity);
		
		String actual = relay2Entity.getRelayname();
		assertEquals("RelayEntity has not been copied correctly!", expected, actual);
	}
	
	@Test
	public void testCopyToRelay2Entity_Check_RelayEventEntity() {
		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.setRelayname("some name");
		
		Relay2Entity result = sut.copyToRelay2Entity(relayEntity);
		
		String expected = CopyRelayService.EVENT_ID;
		String actual = result.getRelayEventEntity().getId();
		
		assertEquals("RelayEntity has not been copied correctly!", expected, actual);
	}

	@Test
	public void testCopyToRelay2Entity_No_Participants() {
		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.setRelayname("some name");
		
		Relay2Entity relay2Entity = sut.copyToRelay2Entity(relayEntity);
		
		int actual = relay2Entity.getParticipantEntities().size();
		int expected = 0;
		assertEquals("RelayEntity has not been copied correctly!", expected, actual);
	}
	
	@Test
	public void testAddParticipantAtPositionToRelay2Entity_No_Participants() {
		Relay2Entity relay2Entity = Relay2Entity.newInstance();
		
		sut.addParticipantAtPositionToRelay2Entity(null, Integer.valueOf(1), relay2Entity);

		int actual = relay2Entity.getParticipantEntities().size();
		int expected = 0;
		assertEquals("RelayEntity has not been copied correctly!", expected, actual);
	}

	@Test
	public void testAddParticipantAtPositionToRelay2Entity_One_Participant() {
		Relay2Entity relay2Entity = Relay2Entity.newInstance();
		
		UUID personId = UUID.randomUUID();
		PersonEntity staticPersonEntity = PersonEntity.newInstance(personId);
		when(entityManagerMock.getTransaction()).thenReturn(mock(EntityTransaction.class));
		when(entityManagerMock.find(PersonEntity.class, personId.toString())).thenReturn(staticPersonEntity);
		
		sut.addParticipantAtPositionToRelay2Entity(personId, Integer.valueOf(1), relay2Entity);

		int actual = relay2Entity.getParticipantEntities().size();
		int expected = 1;
		assertEquals("RelayEntity has not been copied correctly!", expected, actual);
		
		ParticipantEntity participantEntity = relay2Entity.getParticipantEntities().get(0);
		PersonEntity personEntity = participantEntity.getPersonEntity();
		UUID actualPersonId = UUID.fromString(personEntity.getId());
		assertEquals("RelayEntity has not been copied correctly!", personId, actualPersonId);
	}
}
