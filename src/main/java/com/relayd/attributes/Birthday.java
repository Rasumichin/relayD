package com.relayd.attributes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  schmollc (Christian@cloud.franke-net.com)
 * @since   22.03.2016
 * status   initial
 */
public class Birthday implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date value;

	static public Birthday newInstance(Date aDate) {
		validate(aDate);
		return new Birthday(aDate);
	}

	private Birthday(Date aDate) {
		super();
		value = aDate;
	}

	private static void validate(Date aDate) {
		if (aDate == null) {
			throw new IllegalArgumentException("null");

		}
		if (aDate.after(new Date())) {
			throw new IllegalArgumentException("Datum darf nicht in der Zukunft liegen.");
		}
	}

	@Override
	public String toString() {
		return "Geboren am: " + new SimpleDateFormat("dd.MM.yyyy").format(value);
	}
}