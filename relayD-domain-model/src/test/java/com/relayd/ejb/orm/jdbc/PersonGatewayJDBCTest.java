package com.relayd.ejb.orm.jdbc;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.Person;
import com.relayd.attributes.Forename;

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

	@Mock
	private ResultSet rs;

	@Test
	public void testMapValues() throws SQLException {
		doReturn("Jonas").when(rs).getString(PersonGatewayJDBC.INDEX_FORENAME);
		Person person = sut.mapValues(rs);
		assertNotNull(person);
		assertEquals("[forename] not correct!", Forename.newInstance("Jonas"), person.getForename());
	}

}
