package com.relayd.web.bridge;

import java.io.Serializable;

/**
 *
 */
public class TreeNodeRelay implements Serializable, Comparable<TreeNodeRelay> {
	private static final long serialVersionUID = 1L;

	private String name;
	private String participant;

	public TreeNodeRelay(String aName, String aParticipant) {
		name = aName;
		participant = aParticipant;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String aParticipant) {
		participant = aParticipant;
	}

	//Eclipse Generated hashCode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
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
		TreeNodeRelay other = (TreeNodeRelay) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (participant == null) {
			if (other.participant != null) {
				return false;
			}
		} else if (!participant.equals(other.participant)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(TreeNodeRelay document) {
		return getName().compareTo(document.getName());
	}
}