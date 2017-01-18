package com.relayd.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

/**
 * Second Law of TDD: You are not allowed to write any more of a unit test than is sufficient to fail;
 * and compilation failures are failures.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  18.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GenericJpaDaoTest {

	@Test
	public void testNewInstance() {
		EntityManager em = Mockito.mock(EntityManager.class);

		GenericJpaDao sut = GenericJpaDao.newInstance(em);
		assertNotNull("Instance could not be created!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_withNull() {
		@SuppressWarnings("unused")
		GenericJpaDao sut = GenericJpaDao.newInstance(null);
	}
}
