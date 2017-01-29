package com.relayd.entity.migration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.jpa.GenericJpaDao;

/**
 * Tell, don't ask.
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  29.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayMigrationServiceBridgeTest {

	private RelayMigrationServiceBridge sut = mock(RelayMigrationServiceBridge.class);

	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}
	
	@Test
	public void testGetJpaDao() {
		// TODO (2017-01-29, EL): Does this entire test method make sense (besides for documentation purposes)? Discuss with CS.
		GenericJpaDao jpaDaoMock = mock(GenericJpaDao.class);
		when(sut.getJpaDao()).thenReturn(jpaDaoMock);
		
		GenericJpaDao result = sut.getJpaDao();
		
		assertEquals("[jpaDao] is not correct!", jpaDaoMock, result);
	}
	
	@Test
	public void testCountOldRelays() {
		// TODO (2017-01-29, EL): This documents the API of 'sut', but no need to test that Mockito works. See above.
		sut.countOldRelays();
	}
}
