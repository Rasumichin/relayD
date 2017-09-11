package com.relayd;

import java.util.UUID;

import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Surename;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   25.11.2016
 *
 */
public class ParticipantBuilder {

	private UUID uuidPerson = UUID.fromString("89d7134b-2326-4f52-7bd7-901e71723f31");
	private Surename surename = Surename.newInstance("Surename");
	private Forename forename = Forename.newInstance("Forename");
	private Email email = Email.newInstance();

	public ParticipantBuilder withSurename(Surename aSurename) {
		surename = aSurename;
		return this;
	}

	public ParticipantBuilder withSurename(String aSurename) {
		surename = Surename.newInstance(aSurename);
		return this;
	}

	public ParticipantBuilder withForename(Forename aForename) {
		forename = aForename;
		return this;
	}

	public ParticipantBuilder withForename(String aForename) {
		forename = Forename.newInstance(aForename);
		return this;
	}

	public ParticipantBuilder withUUIDPerson(UUID anUUIDPerson) {
		uuidPerson = anUUIDPerson;
		return this;
	}

	public ParticipantBuilder withEmail(String aEmail) {
		email = Email.newInstance(aEmail);
		return this;
	}

	public Participant build() {
		Person person = Person.newInstance();

		person.setUuid(uuidPerson);
		person.setForename(forename);
		person.setSurename(surename);
		person.setEmail(email);

		Participant participant = Participant.newInstance(person);
		return participant;
	}
}