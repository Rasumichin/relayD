package com.relayd.attributes;

/**
 * @author  schmollc (Christian@relayd.com)
 * @since   23.08.2016
 * status   initial
 */
public enum Position implements Comparable<Position> {
	//@formatter:off
	FIRST	(1, "1"),
	SECOND	(2, "2"),
	THIRD	(3, "3"),
	FOURTH	(4, "4");
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

	public static Position decode(Integer aPosition) {
		for (Position position : values()) {
			if (position.getValue().equals(aPosition)) {
				return position;
			}
		}
		throw new IllegalArgumentException("[" + aPosition + "] is an invalid parameter for Position");
	}
}