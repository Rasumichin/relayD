package com.relayd.ejb.orm.jdbc;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;

import static org.mockito.Mockito.*;

/**
 * Wer sichere Schritte tun will, muß sie langsam tun.
 *  - Johann Wolfgang von Goethe
 *
 * @author schmollc (Christian@relayd.de)
 * @since 04.10.2016
 *
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RelayEventGatewayJDBCTest {

	private RelayEventGatewayJDBC sut = new RelayEventGatewayJDBC();

	private static final String ID = "85d7094b-2146-4f52-8bd9-901e7111a431";
	private static final String EVENT_NAME = "Metro Group Marathon Düsseldorf";
	private static final Date DATE = new Date(new GregorianCalendar(2016, Calendar.AUGUST, 13).getTimeInMillis());

	@Mock
	private ResultSet rs;

	@Before
	public void setUp() throws SQLException {
		doReturn(ID).when(rs).getString(RelayEventGatewayJDBC.INDEX_UUID);
		doReturn(EVENT_NAME).when(rs).getString(RelayEventGatewayJDBC.INDEX_EVENT_NAME);
		doReturn(DATE).when(rs).getDate(RelayEventGatewayJDBC.INDEX_DATE);
	}

	@Test
	public void testMapValues() throws SQLException {

		RelayEvent relayEvent = sut.mapValues(rs);

		assertNotNull(relayEvent);
		assertEquals("[uuid] not correct!", UUID.fromString(ID), relayEvent.getUuid());
		assertEquals("[eventname] not correct!", Eventname.newInstance(EVENT_NAME), relayEvent.getName());
		// TODO (Christian, Version 1.4): Mann sollte nicht Whisky trinken und dann coden....
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DATE);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		LocalDate localDate = LocalDate.of(year, month, dayOfMonth);

		assertEquals("[day] not correct!", EventDay.newInstance(localDate), relayEvent.getEventDay());
	}
}