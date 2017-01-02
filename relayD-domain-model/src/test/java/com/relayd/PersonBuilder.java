package com.relayd;

import java.util.UUID;

import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   30.03.2016
 *
 */
public class PersonBuilder {

	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");
	private YearOfBirth yearOfBirth = YearOfBirth.newInstance(1956);
	private Shirtsize shirtsize = Shirtsize.HerrenM;
	private Email email = Email.newInstance();

	PersonBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	PersonBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	PersonBuilder withForename(String aForename) {
		forename = Forename.newInstance(aForename);
		return this;
	}

	PersonBuilder withSurename(String aSurename) {
		surename = Surename.newInstance(aSurename);
		return this;
	}

	public PersonBuilder withEmail(String aEmail) {
		email = Email.newInstance(aEmail);
		return this;
	}

	Person build() {
		Person person = Person.newInstance();
		person.setUuid(UUID.randomUUID());
		person.setSurename(surename);
		person.setForename(forename);
		person.setEmail(email);
		person.setYearOfBirth(yearOfBirth);
		person.setShirtsize(shirtsize);
		return person;
	}
}