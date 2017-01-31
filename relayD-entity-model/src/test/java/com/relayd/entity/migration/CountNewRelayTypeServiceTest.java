package com.relayd.entity.migration;

import static org.junit.Assert.*;

import java.util.*;

import javax.persistence.EntityManager;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.relayd.entity.*;
import com.relayd.jpa.GenericJpaDao;

/**
 * F.I.R.S.T.
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  30.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountNewRelayTypeServiceTest {
	private EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
	private GenericJpaDao jpaDao = GenericJpaDao.newInstance(entityManagerMock);
	private CountRelayEntityService sut = CountNewRelayTypeService.newInstance(jpaDao);

	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNewInstances_With_Null_Value() {
		CountNewRelayTypeService.newInstance(null);
	}
	
	@Test
	public void testGetJpaDao() {
		GenericJpaDao result = ((CountNewRelayTypeService) sut).getJpaDao();
		
		assertNotNull("[jpaDao] has not been set correctly!", result);
	}

	@Test
	public void testGetJpqlStatement_RelayEntity() {
		String expected = "select r2 from Relay2Entity r2";
		
		String actual = ((CountNewRelayTypeService)sut).getJpqlStatement();
		
		assertEquals("JPQL statement is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_Empty_Result() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(0));
		expected.setParticipantCount(Integer.valueOf(0));
		
		List<?> fetchedRelays = Collections.EMPTY_LIST;
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(0));
		
		List<Relay2Entity> fetchedRelays = new ArrayList<>();
		fetchedRelays.add(Relay2Entity.newInstance());
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_One_Participant() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(1));
		
		List<Relay2Entity> fetchedRelays = new ArrayList<>();
		Relay2Entity relayEntity = Relay2Entity.newInstance();
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_Two_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(2));
		
		List<Relay2Entity> fetchedRelays = new ArrayList<>();
		Relay2Entity relayEntity = Relay2Entity.newInstance();
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_Three_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(3));
		
		List<Relay2Entity> fetchedRelays = new ArrayList<>();
		Relay2Entity relayEntity = Relay2Entity.newInstance();
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_One_Relay_And_Four_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(1));
		expected.setParticipantCount(Integer.valueOf(4));
		
		List<Relay2Entity> fetchedRelays = new ArrayList<>();
		Relay2Entity relayEntity = Relay2Entity.newInstance();
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}

	@Test
	public void testCountFetchRelayResult_For_Two_Relays_And_Three_Participants() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.setRelayCount(Integer.valueOf(2));
		expected.setParticipantCount(Integer.valueOf(3));
		
		List<Relay2Entity> fetchedRelays = new ArrayList<>();
		Relay2Entity relayEntity = Relay2Entity.newInstance();
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		fetchedRelays.add(relayEntity);

		relayEntity = Relay2Entity.newInstance();
		relayEntity.addParticipantEntity(ParticipantEntity.newInstance());
		fetchedRelays.add(relayEntity);
		
		RelayCounter actual = ((CountNewRelayTypeService)sut).countFetchRelayResult(fetchedRelays);
		
		assertEquals("[relayCounter] is not correct!", expected, actual);
	}
}
