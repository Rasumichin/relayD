package com.relayd.attributes;

/**
 * @author  schmollc (Christian@relayd.com)
 * @since   23.08.2016
 *
 */
public enum Position {
	//@formatter:off
	UNKNOWN (-1, "Unknown"),
	FIRST	(1, "First"),
	SECOND	(2, "Second"),
	THIRD	(3, "Third"),
	FOURTH	(4, "Fourth");
	//@formatter:on

	private Integer value;
	private String description;

	Position(Integer aValue, String aDescription) {
		value = aValue;
		description = aDescription;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static Position newInstance(Integer aPosition) {
		for (Position position : values()) {
			if (position.getValue().equals(aPosition)) {
				return position;
			}
		}
		return UNKNOWN;
	}

	public boolean isEmpty() {
		return this == UNKNOWN;
	}

	@Override
	public String toString() {
		return getDescription();
	}
}