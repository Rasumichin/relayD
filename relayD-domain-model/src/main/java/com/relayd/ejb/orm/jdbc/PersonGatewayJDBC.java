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
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   15.09.2016
 *
 */
public class PersonGatewayJDBC implements PersonGateway {

	static final int INDEX_UUID = 1;
	static final int INDEX_FORENAME = 2;
	static final int INDEX_SURENAME = 3;
	static final int INDEX_BIRTHYEAR = 4;
	static final int INDEX_SHIRTSIZE = 5;
	static final int INDEX_RELAYNAME = 6;
	static final int INDEX_POS = 7;
	static final int INDEX_EMAIL = 9;
	static final int INDEX_COMMENT = 10;

	@Override
	public List<Person> getAll() {
		List<Person> resultList = new ArrayList<Person>();
		try {
			InitialContext cxt = new InitialContext();
			Connection connection = openConnection(cxt);
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM person";
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				System.out.printf("%s, %s, %s%n", rs.getString(1), rs.getString(INDEX_FORENAME), rs.getString(INDEX_SURENAME));
				Person readPerson = mapValues(rs);
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

	private Connection openConnection(InitialContext cxt) throws NamingException, SQLException {
		String jndiName = "java:comp/env/res/jdbc/dataSource";
		DataSource dataSource = (DataSource) cxt.lookup(jndiName);
		Connection connection = dataSource.getConnection();
		return connection;
	}

	@Override
	public Person get(UUID uuid) {
		Person person = Person.newInstance();
		try {
			InitialContext cxt = new InitialContext();
			Connection connection = openConnection(cxt);
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM person where id='" + uuid + "'";
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				System.out.printf("%s, %s, %s%n", rs.getString(1), rs.getString(INDEX_FORENAME), rs.getString(INDEX_SURENAME));
				person = mapValues(rs);
			}

		} catch (NamingException e) {
			// TODO -all- Logging? Wie? Wo?
			person.setComment(Comment.newInstance("NamingException:" + e));
		} catch (SQLException e) {
			person.setComment(Comment.newInstance("SQLException:" + e));
		}
		return person;
	}

	@Override
	public void set(Person aPerson) {
	}

	@Override
	public void remove(UUID aUuid) {
	}

	// TODO -schmollc- In eigene Klasse verschieben
	Person mapValues(ResultSet rs) throws SQLException {
		Person person = Person.newInstance();

		person.setUUID(UUID.fromString(rs.getString(INDEX_UUID)));
		person.setForename(Forename.newInstance(rs.getString(INDEX_FORENAME)));
		person.setSurename(Surename.newInstance(rs.getString(INDEX_SURENAME)));
		Integer birthyear = rs.getInt(INDEX_BIRTHYEAR);
		person.setYearOfBirth(YearOfBirth.newInstance(birthyear));

		person.setShirtsize(Shirtsize.decode(rs.getShort(INDEX_SHIRTSIZE)));
		person.setRelayname(Relayname.newInstance(rs.getString(INDEX_RELAYNAME)));
		person.setPosition(Position.decode(rs.getInt(INDEX_POS)));
		person.setEmail(Email.newInstance(rs.getString(INDEX_EMAIL)));
		person.setComment(Comment.newInstance(rs.getString(INDEX_COMMENT)));

		return person;
	}
}