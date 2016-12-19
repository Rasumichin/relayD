package com.relayd.ejb.orm.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.relayd.RelayEvent;
import com.relayd.attributes.EventDay;
import com.relayd.attributes.Eventname;
import com.relayd.ejb.RelayEventGateway;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 04.10.2016
 *
 */
public class RelayEventGatewayJDBC implements RelayEventGateway {

	static final int INDEX_UUID = 1;
	static final int INDEX_EVENT_NAME = 2;
	static final int INDEX_DATE = 3;

	@Override
	public List<RelayEvent> getAll() {
		List<RelayEvent> resultList = new ArrayList<RelayEvent>();
		try {
			InitialContext cxt = new InitialContext();
			Connection connection = openConnection(cxt);
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM relay_event";
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				System.out.printf("%s, %s, %s%n", rs.getString(1), rs.getString(INDEX_EVENT_NAME), rs.getString(INDEX_DATE));
				RelayEvent readRelayEvent = mapValues(rs);
				resultList.add(readRelayEvent);
			}

		} catch (NamingException e) {
			// TODO (All, Version 1.4): Logging? Wie? Wo?
			//			System.out.println("Error :" + e);
			RelayEvent relayEvent = RelayEvent.newInstance(Eventname.newInstance("NamingException:" + e), EventDay.today());
			resultList.add(relayEvent);
		} catch (SQLException e) {
			//			System.out.println("Error :" + e);
			RelayEvent relayEvent = RelayEvent.newInstance(Eventname.newInstance("SQLException:" + e), EventDay.today());
			resultList.add(relayEvent);
		}
		return resultList;
	}

	private Connection openConnection(InitialContext cxt) throws NamingException, SQLException {
		String jndiName = "java:comp/env/res/jdbc/dataSource";
		DataSource dataSource = (DataSource) cxt.lookup(jndiName);
		Connection connection = dataSource.getConnection();
		return connection;
	}

	// TODO (Christian, Version 1.4): In eigene Klasse verschieben
	RelayEvent mapValues(ResultSet rs) throws SQLException {

		Eventname eventName = Eventname.newInstance(rs.getString(INDEX_EVENT_NAME));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(rs.getDate(INDEX_DATE));

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

		LocalDate localDate = LocalDate.of(year, month, dayOfMonth);

		EventDay eventDay = EventDay.newInstance(localDate);

		RelayEvent relayEvent = RelayEvent.newInstance(eventName, eventDay);
		relayEvent.setUuid(UUID.fromString(rs.getString(INDEX_UUID)));

		return relayEvent;
	}
}
