package com.relayd.entity.migration;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.relayd.jpa.GenericJpaDao;

/**
 * F.I.R.S.T.
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  30.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Relay2EntityServiceTest {
	private EntityManager entityManagerMock = Mockito.mock(EntityManager.class);
	private GenericJpaDao jpaDao = GenericJpaDao.newInstance(entityManagerMock);
	private CountRelayEntityService sut = Relay2EntityService.newInstance(jpaDao);

	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNewInstances_With_Null_Value() {
		Relay2EntityService.newInstance(null);
	}
	
	@Test
	public void testGetJpaDao() {
		GenericJpaDao result = ((Relay2EntityService) sut).getJpaDao();
		
		assertNotNull("[jpaDao] has not been set correctly!", result);
	}

	@Test
	public void testGetJpqlStatement_RelayEntity() {
		String expected = "select r2 from Relay2Entity r2";
		
		String actual = ((Relay2EntityService)sut).getJpqlStatement();
		
		assertEquals("JPQL statement is not correct!", expected, actual);
	}
}
