package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.UUID;

public class RelayEventDisplay implements Serializable {
	private static final long serialVersionUID = -2614193910079180514L;
	private UUID uuid;
	private String name;

	private RelayEventDisplay(UUID anUuid, String aName) {
		uuid = anUuid;
		name = aName;
	}

	public static RelayEventDisplay newInstance(UUID anUuid, String aName) {
		return new RelayEventDisplay(anUuid, aName);
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return uuid.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
		RelayEventDisplay other = (RelayEventDisplay) obj;
		if (uuid == null) {
			if (other.uuid != null) {
				return false;
			}
		} else if (!uuid.equals(other.uuid)) {
			return false;
		}
		return true;
	}
}