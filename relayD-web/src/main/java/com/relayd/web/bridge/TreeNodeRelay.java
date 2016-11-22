package com.relayd.web.bridge;

import java.io.Serializable;

import com.relayd.PersonRelay;
import com.relayd.attributes.Relayname;

/**
 * Klasse übernommen aus dem Primefaces-Beispiel.
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 */
public class TreeNodeRelay implements Serializable, Comparable<TreeNodeRelay> {
	private static final long serialVersionUID = 1L;

	private Relayname name;
	private PersonRelay participant;

	// TODO -schmollc- Treenode ist entweder ein Relay oder ein Participant!
	public TreeNodeRelay(Relayname aRelayname) {
		name = aRelayname;
	}

	public TreeNodeRelay(PersonRelay aParticipant) {
		participant = aParticipant;
	}

	public static TreeNodeRelay newInstance(Relayname relayname) {
		return new TreeNodeRelay(relayname);
	}

	public static TreeNodeRelay newInstance(PersonRelay personRelay) {
		return new TreeNodeRelay(personRelay);
	}

	public Relayname getName() {
		return name;
	}

	public PersonRelay getParticipant() {
		return participant;
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
		return name.toString();
	}

	@Override
	public int compareTo(TreeNodeRelay document) {
		return getName().compareTo(document.getName());
	}

	public void setParticipant(PersonRelay participant) {
		this.participant = participant;
	}
}