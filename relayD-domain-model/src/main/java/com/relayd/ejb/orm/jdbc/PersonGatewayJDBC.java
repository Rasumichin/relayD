package com.relayd.ejb.orm.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Surename;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   15.09.2016
 *
 */
public class PersonGatewayJDBC implements PersonGateway {

	private static final int INDEX_FORENAME = 2;
	private static final int INDEX_SURENAME = 3;
	private static final int INDEX_RELAYNAME = 6;
	private static final int INDEX_COMMENT = 10;

	@Override
	public List<Person> getAll() {
		List<Person> resultList = new ArrayList<Person>();
		InitialContext cxt;
		try {
			cxt = new InitialContext();
			String jndiName = "java:comp/env/res/jdbc/dataSource";
			DataSource dataSource = (DataSource) cxt.lookup(jndiName);
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM PERSON";
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				System.out.printf("%s, %s, %s%n", rs.getString(1), rs.getString(INDEX_FORENAME), rs.getString(INDEX_SURENAME));
				Person readPerson = Person.newInstance();
				readPerson.setForename(Forename.newInstance(rs.getString(INDEX_FORENAME)));
				readPerson.setSurename(Surename.newInstance(rs.getString(INDEX_SURENAME)));
				readPerson.setRelayname(Relayname.newInstance(rs.getString(INDEX_RELAYNAME)));
				readPerson.setComment(Comment.newInstance(rs.getString(INDEX_COMMENT)));
				resultList.add(readPerson);
			}

		} catch (NamingException e) {
			// TODO -all- Logging? Wie? Wo?
			//			System.out.println("Error :" + e);
			Person person = Person.newInstance();
			person.setComment(Comment.newInstance("NamingException:" + e));
			resultList.add(person);
		} catch (SQLException e) {
			//			System.out.println("Error :" + e);
			Person person = Person.newInstance();
			person.setComment(Comment.newInstance("SQLException:" + e));
			resultList.add(person);
		}
		return resultList;
	}

	@Override
	public Person get(UUID aUuid) {
		return null;
	}

	@Override
	public void set(Person aPerson) {
	}

	@Override
	public void remove(UUID aUuid) {
	}
}