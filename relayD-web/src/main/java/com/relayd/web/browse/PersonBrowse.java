package com.relayd.web.browse;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Email;
import com.relayd.attributes.Forename;
import com.relayd.attributes.Shirtsize;
import com.relayd.attributes.Surename;
import com.relayd.attributes.YearOfBirth;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   21.10.2016
 *
 */
public class PersonBrowse implements Serializable {
	private static final long serialVersionUID = 3821396913949463684L;

	private UUID uuidPerson;
	private Surename surename;
	private Forename forename;
	private YearOfBirth yearOfBirth;
	private Shirtsize shirtsize;
	private Email email;
	private Comment comment;

	private PersonBrowse(Builder builder) {
		super();
		uuidPerson = builder.uuidPerson;
		surename = builder.surename;
		forename = builder.forename;
		yearOfBirth = builder.yearOfBirth;
		shirtsize = builder.shirtsize;
		email = builder.email;
		comment = builder.comment;
	}

	public UUID getUuidPerson() {
		return uuidPerson;
	}

	public Surename getSurename() {
		return surename;
	}

	public Forename getForename() {
		return forename;
	}

	public YearOfBirth getYearOfBirth() {
		return yearOfBirth;
	}

	public Shirtsize getShirtsize() {
		return shirtsize;
	}

	public Email getEmail() {
		return email;
	}

	public Comment getComment() {
		return comment;
	}

	public static class Builder {
		private UUID uuidPerson;
		private Surename surename;
		private Forename forename = Forename.newInstance(null);
		private YearOfBirth yearOfBirth;
		private Shirtsize shirtsize;
		private Email email;
		private Comment comment = Comment.newInstance();

		public Builder withUuidPerson(UUID uuid) {
			uuidPerson = uuid;
			return this;
		}

		public Builder withSurename(Surename aSurename) {
			surename = aSurename;
			return this;
		}

		public Builder withForename(Forename aForename) {
			forename = aForename;
			return this;
		}

		public Builder withYearOfBirth(YearOfBirth aYearOfBirth) {
			yearOfBirth = aYearOfBirth;
			return this;
		}

		public Builder withShirtsize(Shirtsize aShirtsize) {
			shirtsize = aShirtsize;
			return this;
		}

		public Builder withEmail(Email anEmail) {
			email = anEmail;
			return this;
		}

		public Builder withComment(Comment aComment) {
			comment = aComment;
			return this;
		}

		public PersonBrowse build() {
			return new PersonBrowse(this);
		}
	}
}