package com.relayd.client.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Source of wisdom: https://stackoverflow.com/questions/36156741/marshalling-localdate-using-jaxb
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 08.06.2017
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String localDateAsString) throws Exception {
		return LocalDate.parse(localDateAsString);
	}

	@Override
	public String marshal(LocalDate localDate) throws Exception {
		return localDate.toString();
	}
}
