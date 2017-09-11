package com.relayd;

import java.util.UUID;

import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   25.11.2016
 *
 */
public class MemberBuilder {

	private UUID uuidPerson = UUID.fromString("89d7134b-2326-4f52-7bd7-901e71723f31");
	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");

	public MemberBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	public MemberBuilder withSurename(String aSurename) {
		surename = Surename.newInstance(aSurename);
		return this;
	}

	public MemberBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	public MemberBuilder withForename(String aForename) {
		forename = Forename.newInstance(aForename);
		return this;
	}

	public MemberBuilder withUUIDPerson(UUID anUUIDPerson) {
		uuidPerson = anUUIDPerson;
		return this;
	}

	public Member build() {
		Person person = Person.newInstance();

		person.setUuid(uuidPerson);
		person.setForename(forename);
		person.setSurename(surename);
		Participant participant = Participant.newInstance(person);

		Member member = Member.newInstance(participant);
		return member;
	}
}