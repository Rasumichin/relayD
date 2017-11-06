package com.relayd.attributes;

import java.io.Serializable;

/**
 * @author  schmollc (Christian@relayd.de)
 * @since   24.05.2016
 *
 */
public class RelayCount implements Serializable {
	private static final long serialVersionUID = 8951819633464321471L;
	Integer value;

	private RelayCount() {
	}

	private RelayCount(Integer relayCount) {
		value = relayCount;
	}

	public static RelayCount newInstance(Integer relayCount) {
		if (relayCount == null) {
			return RelayCountNullObject.instance();
		}
		return new RelayCount(relayCount);
	}

	public boolean isEmpty() {
		return false;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * Bloch, Joshua, Effective Java, 2nd Edition, Item 1, p. 5
	 */
	public static RelayCount newInstance() {
		return RelayCountNullObject.instance();
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
		RelayCount other = (RelayCount) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	static final class RelayCountNullObject extends RelayCount {
		private static final long serialVersionUID = -1319243586895164056L;

		private static final RelayCountNullObject SINGELTON = new RelayCountNullObject();

		private RelayCountNullObject() {
			super();
			value = 0;
		}

		static RelayCountNullObject instance() {
			return SINGELTON;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}
	}
}