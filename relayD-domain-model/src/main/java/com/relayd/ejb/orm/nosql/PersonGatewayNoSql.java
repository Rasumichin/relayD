package com.relayd.ejb.orm.nosql;

import java.util.UUID;

import com.relayd.Person;
import com.relayd.ejb.PersonGateway;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   26.03.2016
 * status   initial
 */
public class PersonGatewayNoSql implements PersonGateway {

	//	private Mongo mongo = new Mongo("locahost", 27017);
	//	private DB db = mongo .getDB("relayD");
	//	private DBCollection persons = db.getCollection("person");

	@Override
	public Person get(UUID uuid) {
		//		return db.crud.find({"surename" : uuid.toString()});
		return null;
	}

	@Override
	public void set(Person aPerson) {
		//		BasicDBObject person = new BasicDBObject();
		//		person.put("surename", aPerson.getSurename().toString());
		//		person.put("foreename", aPerson.getForename().toString());
		//		// usw. gehoert eigentlich in eine MapperKlasse!
		//		persons.insert(person);
	}
}