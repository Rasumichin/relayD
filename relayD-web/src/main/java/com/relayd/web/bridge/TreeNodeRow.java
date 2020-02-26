package com.relayd.web.bridge;

import java.io.Serializable;

import com.relayd.Member;
import com.relayd.Relay;
import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;

/**
 * Klasse übernommen aus dem Primefaces-Beispiel.
 *
 * @author schmollc (Christian@relayd.de)
 * @since 10.10.2016
 */
public abstract class TreeNodeRow implements Serializable {
	private static final long serialVersionUID = 1L;

	TreeNodeRow() {
	}

	public static TreeNodeRow newInstance(Member member, Position aPosition) {
		return new TreeNodeRowMember(member, aPosition);
	}

	public static TreeNodeRow newInstance(Relay relay) {
		return new TreeNodeRowRelay(relay);
	}

	public Relay getRelay() {
		return Relay.newInstance();
	}

	public boolean isRelay() {
		return false;
	}

	public String getRelayname() {
		return "";
	}

	public Member getMember() {
		return Member.newInstance();
	}

	public void setMember(@SuppressWarnings("unused") Member member) {
		// TODO - REL-307 - Idee ist diese abstract zu machen, so daß beim klicken auf
		// Member Zeile genau dort eingefügt wird,
		// bei klicken auf RelayZeile die nächste freie stelle befüllt wird. Somit haben
		// beide mit dieser Methode zu tun.
	}

	public String getShirtsize() {
		return Shirtsize.UNKNOWN.getDescription();
	}

	public String getYearOfBirth() {
		return "";
	}

	public Position getPosition() {
		return Position.UNKNOWN;
	}

	public void setPosition(@SuppressWarnings("unused") Position position) {
		// TODO - REL-308 - Siehe methode setMember.
	}

	public String getStatus() {
		return "";
	}

	public abstract String getDuration();
}