package com.relayd.jpa;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import javax.persistence.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

/**
 * Second Law of TDD: You may not write more of a unit test than is sufficient to fail,
 * and not compiling is failing.
 *  - Robert C. Martin (Chapter 9 of "Clean Code")
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  18.01.2017
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GenericJpaDaoTest {
	private static EntityManager EM = Mockito.mock(EntityManager.class);
	
	private EntityTransaction tx = Mockito.mock(EntityTransaction.class);
	private GenericJpaDao sut = GenericJpaDao.newInstance(EM);
	
	@Test
	public void testNewInstance() {
		assertNotNull("Instance could not be created!", sut);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewInstance_withNull() {
		GenericJpaDao.newInstance(null);
	}
	
	@Test
	public void testGetEntityManager() {
		EntityManager actual = sut.getEntityManager();
		
		assertEquals("EntityManager is not correct!", EM, actual);
	}
	
	@Test
	public void testPersistEntity() {
		String entity = "Some Entity";
		
		doReturn(tx).when(EM).getTransaction();
		sut.persistEntity(entity);
		
		verify(EM, times(1)).persist(entity);
	}

	@Test
	public void testMergeEntity() {
		String entity = "Some Entity";
		
		doReturn(tx).when(EM).getTransaction();
		sut.mergeEntity(entity);
		
		verify(EM, times(1)).merge(entity);
	}

	@Test
	public void testRemoveEntity() {
		String entity = "Some Entity";
		
		doReturn(tx).when(EM).getTransaction();
		sut.removeEntity(entity);
		
		verify(EM, times(1)).remove(entity);
	}
	
	@Test
	public void testFindById() {
		Class<String> entityClass = String.class;
		String id = "id";
		
		doReturn(tx).when(EM).getTransaction();
		sut.findById(entityClass, id);
		
		verify(EM, times(1)).find(entityClass, id);
	}
	
	@Test
	public void testPerformSelectQuery() {
		String jpql = "select m from MyEntity m";
		List<?> expected = new ArrayList<>();
		
		Query query = Mockito.mock(Query.class);
		doReturn(expected).when(query).getResultList();
		doReturn(query).when(EM).createQuery(jpql);
		
		sut.performSelectQuery(jpql);
		
		verify(EM, times(1)).createQuery(jpql);
		verify(query, times(1)).getResultList();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPerformSelectQuery_withNull() {
		sut.performSelectQuery(null);
	}
	
	@Test
	public void testClose() {
		sut.close();
		
		EntityManager actual = sut.getEntityManager();
		assertNull("Closing was not correct!", actual);
	}
}
