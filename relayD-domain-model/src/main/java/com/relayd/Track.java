package com.relayd;

import java.io.Serializable;
import java.util.UUID;

import com.relayd.attributes.Comment;
import com.relayd.attributes.Distance;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 05.10.2016
 *
 */
public class Track implements Serializable {
	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private Distance distance;
	private Comment comment;
	private Participant participant;

	private Track(Distance aDistance, Comment aComment) {
		uuid = UUID.randomUUID();
		distance = aDistance;
		comment = aComment;
	}

	public static Track newInstance(Distance aDistance) {
		return new Track(aDistance, Comment.newInstance());

	}

	public static Track newInstance(Distance aDistance, Comment aComment) {
		return new Track(aDistance, aComment);
	}

	public Participant getParticipantRelay() {
		return participant;
	}

	public void setParticipant(Participant aParticipant) {
		participant = aParticipant;
	}

	@Override
	public String toString() {
		return distance.toStringWithUnity() + " " + comment;
	}

	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}
}