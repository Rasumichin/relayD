package com.relayd.web.rest.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This simple POJO class has nothing to do with relayD. It is still needed to test the automatic conversion from
 * JSON payload to a custom type (such as this class).
 * Visit "http://jsonplaceholder.typicode.com/posts" to find the REST service provider that delivers a JSON
 * representation of this class.
 * 
 * Will be moved with the entire package to a dedicated new module (jar) representing an abstraction
 * layer for the JAX-RS 2.0 API.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 04.06.2016
 * status initial
 * 
 */
@XmlRootElement(name = "post")
public class JsonPlaceholderPost {
	private int id;
	private int userId;
	private String title;
	private String body;
	
	@XmlElement
	public int getId() {
		return id;
	}
	
	public void setId(int anId) {
		id = anId;
	}
	
	@XmlElement
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int anUserId) {
		userId = anUserId;
	}
	
	@XmlElement
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String aTitle) {
		title = aTitle;
	}
	
	@XmlElement
	public String getBody() {
		return body;
	}
	
	public void setBody(String aBody) {
		body = aBody;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", userId=" + userId + ", title=" + title + "]";
	}
}
