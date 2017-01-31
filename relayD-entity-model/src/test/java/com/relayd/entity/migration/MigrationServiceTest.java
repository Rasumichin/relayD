package com.relayd.entity.migration;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

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

	@Test
	public void testNewDefaultCountRelayEntityService() {
		CountRelayEntityService sut = MigrationService.newDefaultCountRelayEntityService(jpaDao);
		
		boolean result = sut instanceof DefaultCountRelayEntityService;
		assertTrue("Factory method has not created the correct type!", result);
	}
	
	@Test
	public void testNewCountNewRelayTypeService() {
		CountRelayEntityService sut = MigrationService.newCountNewRelayTypeService(jpaDao);
		
		boolean result = sut instanceof CountNewRelayTypeService;
		assertTrue("Factory method has not created the correct type!", result);
	}
	
	@Test
	public void testSetJpaDao() {
		MigrationService sut = new MigrationService() {
		};
		
		sut.setJpaDao(jpaDao);
		
		GenericJpaDao actual = sut.getJpaDao();
		assertEquals("[jpaDao] has not been set correctly!", jpaDao, actual);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetJpaDao_With_Null_Value() {
		MigrationService sut = new MigrationService() {
		};
		
		sut.setJpaDao(null);
	}
}
