package com.relayd.entity.migration;

/**
 *
 * @author Rasumichin (Erik@relayd.de)
 * @since  23.01.2017
 *
 */
public class RelayCounter {
	public static final Integer NOT_YET_COUNTED_VALUE = Integer.valueOf(-1);

	private Integer relays = NOT_YET_COUNTED_VALUE;
	private Integer participants = NOT_YET_COUNTED_VALUE;

	private RelayCounter() {
	}
	
	public static RelayCounter newInstance() {
		return new RelayCounter();
	}

	public Integer getRelays() {
		return relays;
	}

	public void setRelayCount(Integer count) {
		if (count.intValue() < 0) {
			throw new IllegalArgumentException("[count] must be greater than or equal to '0'.");
		}
		relays = count;
	}

	public Integer getParticipants() {
		return participants;
	}

	public void setParticipantCount(Integer count) {
		if (count.intValue() < 0) {
			throw new IllegalArgumentException("[count] must be greater than or equal to '0'.");
		}
		participants = count;
	}
}
