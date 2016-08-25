package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 * status initial
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String value;

	private Comment(String aComment) {
		value = aComment;
	}

	public static Comment newInstance(String aComment) {
		return new Comment(aComment);
	}

	@Override
	public String toString() {
		return value;
	}
}
