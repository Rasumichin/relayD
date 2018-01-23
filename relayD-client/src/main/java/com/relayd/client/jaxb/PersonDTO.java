package com.relayd.client.jaxb;

import javax.xml.bind.annotation.*;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.01.2018
 *
 */
@XmlRootElement(name = "person")
public class PersonDTO {
	private String id;
	private String forename;
	private String surename;
	private Integer yearOfBirth;
	private String shirtSize;
	private String eMail;
	private String comment;


	public static PersonDTO newInstance() {
		return new PersonDTO();
	}

	@XmlElement
	public String getId() {
		return id;
	}

	public void setId(String anId) {
		id = anId;
	}
	
	@XmlElement
	public String getForename() {
		return forename;
	}

	public void setForename(String aForename) {
		forename = aForename;
	}

	@XmlElement
	public String getSurename() {
		return surename;
	}

	public void setSurename(String aSurename) {
		surename = aSurename;
	}

	@XmlElement
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(Integer aYearOfBirth) {
		yearOfBirth = aYearOfBirth;
	}

	@XmlElement
	public String getShirtSize() {
		return shirtSize;
	}

	public void setEmail(String anEmail) {
		eMail = anEmail;
	}

	@XmlElement
	public String getEmail() {
		return eMail;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	@XmlElement
	public String getComment() {
		return comment;
	}

	public void setComment(String aComment) {
		comment = aComment;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", forename=" + forename + ", surename=" + surename + "]";
	}
}
