package com.relayd.client.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 20.01.2018
 *
 */
@XmlRootElement(name = "persons")
public class PersonsDTO implements Serializable {
	private static final long serialVersionUID = -7228416984757372589L;

	@Override
	public String toString() {
		return getClass().getSimpleName() + " []";
	}
}
