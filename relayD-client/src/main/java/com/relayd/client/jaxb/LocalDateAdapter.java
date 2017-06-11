package com.relayd.client.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * This is a specialised JAXB adapter to handle the (un-) marshaling of type {@link LocalDate}.
 * 
 * Source of wisdom: <a href="https://stackoverflow.com/questions/36156741/marshalling-localdate-using-jaxb">Stackoverflow</a>.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since  08.06.2017
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	/**
	 * Recreates a {@link LocalDate} instance from a string representation, e. g. {@literal "2001-11-21"}.
	 * 
	 * @param localDateAsString A valid string representation of a {@link LocalDate} or {@code null}.
	 * @return An instance of type {@link LocalDate} or {@code null} when the method argument has been {@code null}.
	 * @throws Exception In case parsing the string value fails.
	 * 
	 */
	@Override
	public LocalDate unmarshal(String localDateAsString) throws Exception {
		if (localDateAsString == null) {
			return null;
		}
		
		return LocalDate.parse(localDateAsString);
	}

	/**
	 * Answers a string representation of a {@link LocalDate}, e. g. {@literal "2001-11-21"}.
	 * 
	 * @param localDate An instance of {@link LocalDate} or {@code null}.
	 * @return An instance of String or {@code null} when the method argument has been {@code null}.
	 * 
	 */
	@Override
	public String marshal(LocalDate localDate) {
		if (localDate == null) {
			return "";
		}
		
		return localDate.toString();
	}
}
