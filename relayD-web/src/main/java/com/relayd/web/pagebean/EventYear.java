package com.relayd.web.pagebean;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 19.05.2017
 *
 */
public enum EventYear {
	YEAR_2016(2016, "2016"),
	YEAR_2017(2017, "2017"),
	YEAR_2018(2018, "2018");

	private final Integer year;
	private final String description;

	EventYear(Integer aYear, String aDesciption) {
		year = aYear;
		description = aDesciption;
	}

	public Integer getYear() {
		return year;
	}

	public String getDescription() {
		return description;
	}
}