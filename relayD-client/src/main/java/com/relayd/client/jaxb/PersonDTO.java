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

	@XmlElement
	public String getId() {
		return id;
	}

	@XmlElement
	public String getForename() {
		return forename;
	}

	@XmlElement
	public String getSurename() {
		return surename;
	}

	@XmlElement
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	@XmlElement
	public String getShirtSize() {
		return shirtSize;
	}

	@XmlElement
	public String getEmail() {
		return eMail;
	}

	@XmlElement
	public String getComment() {
		return comment;
	}
}
