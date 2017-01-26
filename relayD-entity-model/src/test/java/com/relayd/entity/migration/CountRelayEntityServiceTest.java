package com.relayd.entity.migration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.relayd.entity.RelayEntity;
import com.relayd.jpa.GenericJpaDao;

/**
 * First Law of TDD: You may not write production code until you have written a failing unit test.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  17.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountRelayEntityServiceTest {
	private EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
	private GenericJpaDao jpaDao = GenericJpaDao.newInstance(entityManagerMock);
	private CountRelayEntityService sut = DefaultRelayEntityService.newInstance(jpaDao);

	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNewInstances_With_Null_Value() {
		DefaultRelayEntityService.newInstance(null);
	}
	
	@Test
	public void testGetJpaDao() {
		GenericJpaDao result = ((DefaultRelayEntityService) sut).getJpaDao();
		
		assertNotNull("[jpaDao] has not been set correctly!", result);
	}
	
	@Test
	public void testCount() {
		Query queryMock = Mockito.mock(Query.class);
		doReturn(queryMock).when(entityManagerMock).createQuery(Mockito.anyString());
		
		RelayCounter result = sut.count();
		
		assertNotNull("[relayCounter] has not been initialized correctly!", result);
	}
	
	@Test
	public void testReadRelays() {
		GenericJpaDao jpaDaoMock = Mockito.mock(GenericJpaDao.class);
		doReturn(new ArrayList<>()).when(jpaDaoMock).performSelectQuery(Mockito.anyString());
		
		CountRelayEntityService relayEntityService = DefaultRelayEntityService.newInstance(jpaDaoMock);
		
		((DefaultRelayEntityService)relayEntityService).readRelays();
		
		verify(jpaDaoMock, times(1)).performSelectQuery(Mockito.anyString());
	}
	
	@Test
	public void testGetJpqlStatement_RelayEntity() {
		String expected = "select r from RelayEntity r";
		
		String actual = ((DefaultRelayEntityService)sut).getJpqlStatement();
		
		assertEquals("JPQL statement is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_Empty_Result() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(0));
		expected.setParticipantCount(Integer.valueOf(0));
		
		List<?> fetchedRelays = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult((List<RelayEntity>) fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(0));
		
		List<RelayEntity> fetchedRelays = new ArrayList<>();
		fetchedRelays.add(RelayEntity.newInstance());
		
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_One_Participant() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(1));
		
		List<RelayEntity> fetchedRelays = new ArrayList<>();
		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.setParticipantOne(UUID.randomUUID());
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_Two_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(2));
		
		List<RelayEntity> fetchedRelays = new ArrayList<>();
		RelayEntity relayEntity = RelayEntity.newInstance();

		UUID uuid = UUID.randomUUID();
		relayEntity.setParticipantOne(uuid);
		relayEntity.setParticipantTwo(uuid);
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_Three_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(3));
		
		List<RelayEntity> fetchedRelays = new ArrayList<>();
		RelayEntity relayEntity = RelayEntity.newInstance();

		UUID uuid = UUID.randomUUID();
		relayEntity.setParticipantOne(uuid);
		relayEntity.setParticipantTwo(uuid);
		relayEntity.setParticipantThree(uuid);
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_Four_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(4));
		
		List<RelayEntity> fetchedRelays = new ArrayList<>();
		RelayEntity relayEntity = RelayEntity.newInstance();

		UUID uuid = UUID.randomUUID();
		relayEntity.setParticipantOne(uuid);
		relayEntity.setParticipantTwo(uuid);
		relayEntity.setParticipantThree(uuid);
		relayEntity.setParticipantFour(uuid);
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_Two_Relays_And_Three_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(2));
		expected.setParticipantCount(Integer.valueOf(3));
		
		List<RelayEntity> fetchedRelays = new ArrayList<>();
		UUID uuid = UUID.randomUUID();

		RelayEntity relayEntity = RelayEntity.newInstance();
		relayEntity.setParticipantOne(uuid);
		relayEntity.setParticipantTwo(uuid);
		fetchedRelays.add(relayEntity);

		relayEntity = RelayEntity.newInstance();
		relayEntity.setParticipantThree(uuid);
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((DefaultRelayEntityService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}
}
