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

	@Column(name = "info")
	private String comment;

	@Column
	private String relayname;

	@Column
	private Integer pos;

	public static PersonEntity newInstance() {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(UUID.randomUUID().toString());
		
		return personEntity;
	}

	/**
	 * Provided empty constructor to conform to the JPA spec. For that reason the entity property fields
	 * are not declared 'final'.
	 *
	 */
	PersonEntity() {
		super();
	}

	public static PersonEntity newInstance(UUID anUuid) {
		if (anUuid == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		PersonEntity personEntity = new PersonEntity();
		personEntity.setId(anUuid.toString());
		
		return personEntity;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String aSurename) {
		surename = aSurename;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String aForename) {
		forename = aForename;
	}

	public Integer getShirtsize() {
		return shirtsize;
	}

	public void setShirtsize(Integer aShirtsize) {
		shirtsize = aShirtsize;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String aNationality) {
		nationality = aNationality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String anEmail) {
		email = anEmail;
	}

	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer aYearOfBirth) {
		yearOfBirth = aYearOfBirth;
	}

	public String getId() {
		return id;
	}
	
	void setId(String anId) {
		id = anId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String aComment) {
		comment = aComment;
	}

	public String getRelayname() {
		return relayname;
	}

	public void setRelayname(String aRelayname) {
		relayname = aRelayname;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer aPosition) {
		pos = aPosition;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PersonEntity other = (PersonEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}