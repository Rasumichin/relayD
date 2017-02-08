package com.relayd.ejb.orm.jpa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

import org.junit.*;
import org.junit.runners.MethodSorters;

import com.relayd.jpa.GenericJpaDao;

/**
 * Write your code so that it reflects, or rises above, the best parts of your personal character.
 *  - Daniel Read
 *
 * @author  schmollc (Christian@relayd.de)
 * @author  Rasumichin (Erik@relayd.de)
 * @since   25.11.2016
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayGatewayJPATest {

	private RelayGatewayJPA sut = new RelayGatewayJPA();
	
	@Test
	public void testGetJpaDao() {
		GenericJpaDao jpaDao = sut.getJpaDao();
		
		assertNull("[jpaDao] has not been correctly initialized!", jpaDao);
	}
	
	@Test
	public void testGetJpaDao_After_lazy_initialization() {
		RelayGatewayJPA sutSpy = spy(sut);
		EntityManager emMock = mock(EntityManager.class);
		doReturn(emMock).when(sutSpy).createEntityManager();
		
		sutSpy.getEntityManager();

		GenericJpaDao jpaDao = sutSpy.getJpaDao();
		
		assertNotNull("[jpaDao] has not been correctly initialized!", jpaDao);
	}
}