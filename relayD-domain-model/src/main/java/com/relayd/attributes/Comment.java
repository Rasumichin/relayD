package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 * status initial
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final CommentNullObject commentNullObjectSingelton = new CommentNullObject();

	public static final Integer MAX_LENGTH = 255;

	String value;

	private Comment() {
		super();
	}

	private Comment(String aComment) {
		value = aComment;
	}

	public static Comment newInstance() {
		return commentNullObjectSingelton;
	}

	public static Comment newInstance(String aComment) {
		if (aComment == null) {
			return commentNullObjectSingelton;
		}
		return new Comment(aComment);
	}

	private static class CommentNullObject extends Comment {
		private static final long serialVersionUID = 1L;

		@Override
		public String toString() {
			return "";
		}
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Comment other = (Comment) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}