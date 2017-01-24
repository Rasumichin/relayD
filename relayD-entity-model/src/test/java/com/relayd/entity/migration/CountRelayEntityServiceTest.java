package com.relayd.entity.migration;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

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
	private EntityManager entityManager = Mockito.mock(EntityManager.class);
	private GenericJpaDao jpaDao = GenericJpaDao.newInstance(entityManager);
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
	public void testGetRelayCounter() {
		RelayCounter result = sut.getRelayCounter();
		
		assertNotNull("[relayCounter] has not been initialized correctly!", result);
	}
}
