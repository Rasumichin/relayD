package com.relayd.ejb.orm.jdbc;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

import static org.mockito.Mockito.*;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   15.09.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayJDBCTest {

	private PersonGatewayJDBC sut = new PersonGatewayJDBC();

	private static final String uuid = "85d7094b-2146-4f52-8bd9-901e71723f31";

	@Mock
	private ResultSet rs;

	@Before
	public void setUp() throws SQLException {
		doReturn(uuid).when(rs).getString(PersonGatewayJDBC.INDEX_UUID);
		doReturn("Justus").when(rs).getString(PersonGatewayJDBC.INDEX_FORENAME);
		doReturn("Jonas").when(rs).getString(PersonGatewayJDBC.INDEX_SURENAME);
	}

	@Test
	public void testMapValues() throws SQLException {

		Person person = sut.mapValues(rs);

		assertNotNull(person);
		assertEquals("[uuid] not correct!", UUID.fromString(uuid), person.getUUID());
		assertEquals("[forename] not correct!", Forename.newInstance("Justus"), person.getForename());
		assertEquals("[surename] not correct!", Surename.newInstance("Jonas"), person.getSurename());
	}
}