package com.relayd.ejb.orm.nosql;

import com.relayd.*;
import com.relayd.attributes.*;
import com.relayd.ejb.*;

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
	public Person get(Surename aSurename) {
		//		return db.crud.find({"surename" : aSurename.toString()});
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