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
	
	public void incrementParticipants(int increment) {
		Integer actual = getParticipants();
		actual += increment;
		setParticipantCount(actual);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [relays=" + getRelays() + ", participants=" + getParticipants() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((participants == null) ? 0 : participants.hashCode());
		result = prime * result + ((relays == null) ? 0 : relays.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelayCounter other = (RelayCounter) obj;
		if (participants == null) {
			if (other.participants != null)
				return false;
		} else if (!participants.equals(other.participants))
			return false;
		if (relays == null) {
			if (other.relays != null)
				return false;
		} else if (!relays.equals(other.relays))
			return false;
		return true;
	}
}
