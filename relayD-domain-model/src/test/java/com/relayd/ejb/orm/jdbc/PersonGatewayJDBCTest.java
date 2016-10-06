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
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

import static org.mockito.Mockito.*;

/**
 * Testen ist wie Rudern gegen den Strom. Hört man damit auf, treibt man zurück.
 *  - Laotse
 *
 * @author  schmollc (Christian@relayd.de)
 * @since   15.09.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonGatewayJDBCTest {

	private PersonGatewayJDBC sut = new PersonGatewayJDBC();

	private static final String ID = "85d7094b-2146-4f52-8bd9-901e71723f31";
	private static final String SURENAME = "Jonas";
	private static final String FORENAME = "Justus";
	private static final Integer SHIRTSIZE = 3;
	private static final Integer YEAROFBIRTH = 1971;
	private static final String RELAYNAME = "Die 4 ????";
	private static final Integer POS = 1;
	private static final String EMAIL = "Justus.Jonas@RockyBeach.com";
	private static final String COMMENT = "Erster Detektiv!";

	@Mock
	private ResultSet resultSetMock;

	@Before
	public void setUp() throws SQLException {
		doReturn(ID).when(resultSetMock).getString(PersonGatewayJDBC.INDEX_UUID);
		doReturn(FORENAME).when(resultSetMock).getString(PersonGatewayJDBC.INDEX_FORENAME);
		doReturn(SURENAME).when(resultSetMock).getString(PersonGatewayJDBC.INDEX_SURENAME);
		doReturn(YEAROFBIRTH).when(resultSetMock).getInt(PersonGatewayJDBC.INDEX_YEAROFBIRTH);
		doReturn(SHIRTSIZE).when(resultSetMock).getInt(PersonGatewayJDBC.INDEX_SHIRTSIZE);
		doReturn(RELAYNAME).when(resultSetMock).getString(PersonGatewayJDBC.INDEX_RELAYNAME);
		doReturn(POS).when(resultSetMock).getInt(PersonGatewayJDBC.INDEX_POS);
		doReturn(EMAIL).when(resultSetMock).getString(PersonGatewayJDBC.INDEX_EMAIL);
		doReturn(COMMENT).when(resultSetMock).getString(PersonGatewayJDBC.INDEX_COMMENT);
	}

	@Test
	public void testMapValues() throws SQLException {

		Person person = sut.mapValues(resultSetMock);

		assertNotNull(person);
		assertEquals("[uuid] not correct!", UUID.fromString(ID), person.getUUID());
		assertEquals("[forename] not correct!", Forename.newInstance(FORENAME), person.getForename());
		assertEquals("[surename] not correct!", Surename.newInstance(SURENAME), person.getSurename());
		assertEquals("[yearOfBirth] not correct!", YearOfBirth.newInstance(YEAROFBIRTH), person.getYearOfBirth());
		assertEquals("[shirtsize] not correct!", Shirtsize.decode(SHIRTSIZE), person.getShirtsize());
		assertEquals("[relayname] not correct!", Relayname.newInstance(RELAYNAME), person.getRelayname());
		assertEquals("[pos] not correct!", Position.decode(POS), person.getPosition());
		assertEquals("[email] not correct!", Email.newInstance(EMAIL), person.getEmail());
		assertEquals("[comment] not correct!", Comment.newInstance(COMMENT), person.getComment());
	}
}