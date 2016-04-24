package com.relayd.entity;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class RelayEventEntityIT {

	@Test
	public void test() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dataSource");
		EntityManager em = emf.createEntityManager();
	}
}
