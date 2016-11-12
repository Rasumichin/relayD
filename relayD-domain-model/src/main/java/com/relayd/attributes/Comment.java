package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 *
 */
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final Integer MAX_LENGTH = 255;

	String value;

	private Comment() {
		super();
	}

	private Comment(String aComment) {
		value = aComment;
	}

	public static Comment newInstance() {
		return CommentNullObject.instance();
	}

	public static Comment newInstance(String aComment) {
		if (aComment == null || aComment.trim().isEmpty()) {
			return CommentNullObject.instance();
		}
		return new Comment(aComment);
	}

	public boolean isEmpty() {
		return false;
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

	static class CommentNullObject extends Comment {
		private static final long serialVersionUID = -319144040520563172L;

		private static final CommentNullObject SINGLETON = new CommentNullObject();

		private static CommentNullObject instance() {
			return SINGLETON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public String toString() {
			return "";
		}
	}
}