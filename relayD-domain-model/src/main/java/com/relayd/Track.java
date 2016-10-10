package com.relayd;

import java.io.Serializable;
import java.math.BigDecimal;
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

	public static final Track firstTrack = Track.newInstance(Distance.newInstance(new BigDecimal("11.3")));
	public static final Track secondTrack = Track.newInstance(Distance.newInstance(new BigDecimal("8.6")));
	public static final Track thirdTrack = Track.newInstance(Distance.newInstance(new BigDecimal("9.2")));
	public static final Track fourthTrack = Track.newInstance(Distance.newInstance(new BigDecimal("13.1")));

	private UUID uuid;
	private Distance distance;
	private Comment comment;
	private Person person;

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person aPerson) {
		person = aPerson;
	}

	@Override
	public String toString() {
		return distance + "km " + comment;
	}
}