package com.relayd.web.pagebean;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

import com.relayd.Person;
import com.relayd.attributes.Birthday;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;

/**
 * TODO -schmollc- Noch zu klären wo genau die Builder hinsollen oder jede DomainKlasse Ihren eigenen innerClass Builder hat.
 * @author  schmollc (Christian@relayD.de)
 * @since   30.03.2016
 * status   initial
 */
public class PersonBuilder {

	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");
	private Relayname relayname = Relayname.newInstance("");
	private Position position = null;
	private Birthday birthday = Birthday.newInstance(LocalDate.of(1956, Calendar.FEBRUARY, 17));
	private Shirtsize shirtsize = Shirtsize.HerrenM;
	private Locale locale = Locale.GERMANY;

	PersonBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	PersonBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	PersonBuilder withRelayname(String aRelayname) {
		relayname = Relayname.newInstance(aRelayname);
		return this;
	}

	public PersonBuilder withPosition(Position aPosition) {
		position = aPosition;
		return this;
	}

	Person build() {
		Person person = Person.newInstance();
		person.setSurename(surename);
		person.setForename(forename);
		person.setRelayname(relayname);
		person.setPosition(position);
		person.setBirthday(birthday);
		person.setShirtsize(shirtsize);
		person.setNationality(locale);
		return person;
	}
}