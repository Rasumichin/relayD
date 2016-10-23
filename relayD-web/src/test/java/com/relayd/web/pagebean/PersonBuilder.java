package com.relayd.web.pagebean;

import java.util.UUID;

import com.relayd.Person;
import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Position;
import com.relayd.attributes.Relayname;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * TODO -schmollc- Noch zu klären wo genau die Builder hinsollen oder jede DomainKlasse Ihren eigenen innerClass Builder hat.
 * @author  schmollc (Christian@relayd.de)
 * @since   30.03.2016
 *
 */
public class PersonBuilder {

	private UUID uuid = UUID.randomUUID();
	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");
	private Relayname relayname;
	private Position position;
	private YearOfBirth yearOfBirth = YearOfBirth.newInstance(1956);
	private Shirtsize shirtsize = Shirtsize.HerrenM;
	private Email email;
	private Comment comment;

	public PersonBuilder withUuid(UUID anUuid) {
		uuid = anUuid;
		return this;
	}

	public PersonBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	public PersonBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	public PersonBuilder withRelayname(String aRelayname) {
		relayname = Relayname.newInstance(aRelayname);
		return this;
	}

	public PersonBuilder withPosition(Position aPosition) {
		position = aPosition;
		return this;
	}

	public PersonBuilder withShirtsize(Shirtsize aShirtsize) {
		shirtsize = aShirtsize;
		return this;
	}

	public PersonBuilder withEmail(String anEmail) {
		email = Email.newInstance(anEmail);
		return this;
	}

	public PersonBuilder withYearOfBirth(YearOfBirth aYearOfBirth) {
		yearOfBirth = aYearOfBirth;
		return this;
	}

	public PersonBuilder withComment(Comment aComment) {
		comment = aComment;
		return this;
	}

	public Person build() {
		Person person = Person.newInstance();
		person.setUuid(uuid);
		person.setSurename(surename);
		person.setForename(forename);
		person.setRelayname(relayname);
		person.setPosition(position);
		person.setYearOfBirth(yearOfBirth);
		person.setShirtsize(shirtsize);
		person.setEmail(email);
		person.setComment(comment);
		return person;
	}
}