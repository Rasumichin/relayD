package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author schmollc (Christian@relayd.de)
 * @author Rasumichin (Erik@relayd.de)
 * @since 09.09.2016
 *
 */
@Entity
@Table(name = "person")
public class PersonEntity {

	@Id
	@Column
	private String id;

	@Column
	private String surename;

	@Column
	private String forename;

	@Column
	private Integer yearOfBirth;

	@Column
	private Integer shirtsize;

	@Column
	private String nationality;

	@Column
	private String email;

	@Column(name="info")
	private String comment;

	@Column
	private String relayname;

	@Column
	private Integer pos;

	private PersonEntity(Builder aBuilder) {
		id = aBuilder.id;
		forename = aBuilder.forename;
		surename = aBuilder.surename;
		email = aBuilder.email;
		yearOfBirth = aBuilder.yearOfBirth;
		relayname = aBuilder.relayname;
		pos = aBuilder.pos;
		comment = aBuilder.comment;
		nationality = aBuilder.nationality;
		shirtsize = aBuilder.shirtsize;
	}

	/**
	 * Provided empty constructor to conform to the JPA spec. For that reason the entity property fields
	 * are not declared 'final'.
	 *
	 */
	PersonEntity() {
		super();
	}

	public String getSurename() {
		return surename;
	}

	public String getForename() {
		return forename;
	}

	public Integer getShirtsize() {
		return shirtsize;
	}

	public String getNationality() {
		return nationality;
	}

	public String getEmail() {
		return email;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public String getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public String getRelayname() {
		return relayname;
	}

	public Integer getPos() {
		return pos;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", surename=" + surename + ", forename=" + forename + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonEntity other = (PersonEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static class Builder {
		private String id = UUID.randomUUID().toString();
		private String forename;
		private String surename;
		private String email;
		private Integer yearOfBirth;
		private Integer shirtsize;
		private String nationality;
		private String comment;
		private String relayname;
		private Integer pos;

		public Builder() {
			super();
		}

		// TODO (EL, 2016-09-25): Discuss nullability of 'anId' and where to handle it.
		public Builder withId(String anId) {
			id = anId;
			return this;
		}
		
		public Builder withForename(String aForename) {
			forename = aForename;
			return this;
		}

		public Builder withSurename(String aSurename) {
			surename = aSurename;
			return this;
		}

		public Builder withEmail(String anEmail) {
			email = anEmail;
			return this;
		}

		public Builder withYearOfBirth(Integer aYearOfBirth) {
			yearOfBirth = aYearOfBirth;
			return this;
		}

		public Builder withShirtsize(Integer aShirtsize) {
			shirtsize = aShirtsize;
			return this;
		}

		public Builder withNationality(String aNationality) {
			nationality = aNationality;
			return this;
		}

		public Builder withRelayname(String aRelayname) {
			relayname = aRelayname;
			return this;
		}

		public Builder withPos(Integer aPos) {
			pos = aPos;
			return this;
		}

		public Builder withComment(String aComment) {
			comment = aComment;
			return this;
		}

		public PersonEntity build() {
			return new PersonEntity(this);
		}
	}
}