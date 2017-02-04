package com.relayd.entity.migration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.relayd.jpa.GenericJpaDao;

/**
 * K.I.S.S.
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  31.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MigrationServiceTest {
	private EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
	private GenericJpaDao jpaDao = GenericJpaDao.newInstance(entityManagerMock);
	private MigrationService sut = new MigrationService() {};

	@Test
	public void testNewDefaultCountRelayEntityService() {
		CountRelayEntityService sutCountRelayEntityService = MigrationService.newDefaultCountRelayEntityService(jpaDao);
		
		boolean result = sutCountRelayEntityService instanceof DefaultCountRelayEntityService;
		assertTrue("Factory method has not created the correct type!", result);
	}
	
	@Test
	public void testNewCountNewRelayTypeService() {
		CountRelayEntityService sutCountRelayEntityService = MigrationService.newCountNewRelayTypeService(jpaDao);
		
		boolean result = sutCountRelayEntityService instanceof CountNewRelayTypeService;
		assertTrue("Factory method has not created the correct type!", result);
	}
	
	@Test
	public void testNewCopyRelayService() {
		when(entityManagerMock.getTransaction()).thenReturn(mock(EntityTransaction.class));
		CopyRelayService sutCopyRelayService = MigrationService.newCopyRelayService(jpaDao);
		
		assertNotNull("Factory method has not created the correct type!", sutCopyRelayService);
	}
	
	@Test
	public void testGetRelayCounter() {
		RelayCounter expected = RelayCounter.newInstance();
		
		RelayCounter actual = sut.getRelayCounter();
		
		assertEquals("[relayCounter] has not been correctly initalized!", expected, actual);
	}
	
	@Test
	public void testSetRelayCounter() {
		RelayCounter expected = RelayCounter.newInstance();
		expected.incrementParticipants(1);
		
		sut.setRelayCounter(expected);
		
		RelayCounter actual = sut.getRelayCounter();
		
		assertEquals("[relayCounter] has not been set correctly!", expected, actual);
	}
	
	@Test
	public void testSetJpaDao() {
		sut.setJpaDao(jpaDao);
		
		GenericJpaDao actual = sut.getJpaDao();
		assertEquals("[jpaDao] has not been set correctly!", jpaDao, actual);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetJpaDao_With_Null_Value() {
		sut.setJpaDao(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testReadRelays_With_Null_Value() {
		sut.readRelays(null);
	}
	
	@Test
	public void testReadRelays() {
		GenericJpaDao jpaDaoMock = mock(GenericJpaDao.class);
		when(jpaDaoMock.performSelectQuery(anyString())).thenReturn(Collections.emptyList());
		
		sut.setJpaDao(jpaDaoMock);
		
		List<?> result = sut.readRelays("Some SELECT SQL statement");
		
		assertTrue("Read result is not correct!", result.isEmpty());
	}
}
