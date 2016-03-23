package com.relayd;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   23.03.2016
 * status   initial<br/>
 *
 * Dient nur zum erläutern wie man an alle Locales kommt obwohl in der java.utl.Locale Klasse "nur" eine Handvoll
 * an Ländern als Konstanten definiert sind.
 */
public class EducationTestLocale {

	@Test
	public void testShowAllLocales() {
		List<Country> countries = new ArrayList<Country>();

		Locale[] locales = Locale.getAvailableLocales();
		for (Locale locale : locales) {
			String code = locale.getCountry();
			String name = locale.getDisplayCountry();

			if (!"".equals(code) && !"".equals(name)) {
				countries.add(new Country(code, name));
			}
		}

		Collections.sort(countries, new CountryComparator());
		for (Country country : countries) {
			System.out.println(country);
		}
	}
}

class CountryComparator implements Comparator<Country> {
	@SuppressWarnings("rawtypes")
	private Comparator comparator;

	CountryComparator() {
		comparator = Collator.getInstance();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Country o1, Country o2) {
		return comparator.compare(o1.name, o2.name);
	}
}

class Country {
	private String code;
	String name;

	Country(String aCode, String aName) {
		code = aCode;
		name = aName;
	}

	@Override
	public String toString() {
		return code + " - " + name.toUpperCase();
	}
}