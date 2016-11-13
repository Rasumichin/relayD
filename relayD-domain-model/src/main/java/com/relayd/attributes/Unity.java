package com.relayd.attributes;

/**
 * @author  schmollc (Christian@relayd.com)
 * @since   25.10.2016
 *
 */
public enum Unity {
	//@formatter:off
	KM("km"),
	METER("m");
	//@formatter:on

	private String description;

	Unity(String aDescription) {
		description = aDescription;
	}

	@Override
	public String toString() {
		return description;
	}
}