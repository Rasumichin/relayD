package com.relayd.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since  03.01.2017
 *
 */
@Entity
@Table(name = "relay2")
public class Relay2Entity {

	@Id
	@Column(length=36)
	private String id;

	@Column(nullable=false, length=256)
	private String relayname;

	@Column
	private RelayEventEntity relayEventEntity;

	public static Relay2Entity newInstance() {
		Relay2Entity relayEntity = new Relay2Entity();
		relayEntity.setId(UUID.randomUUID().toString());

		return relayEntity;
	}

	public static Relay2Entity newInstance(String anId) {
		if (anId == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		try {
			UUID.fromString(anId);
		} catch (IllegalArgumentException iAEx) {
			throw new IllegalArgumentException("[anId] is not a valid representation of an UUID.");
		}
		Relay2Entity relayEntity = new Relay2Entity();
		relayEntity.setId(anId);

		return relayEntity;
	}

	public String getId() {
		return id;
	}

	void setId(String anId) {
		id = anId;
	}

	public String getRelayname() {
		return relayname;
	}

	public void setRelayname(String aRelayname) {
		if (aRelayname == null) {
			throw new IllegalArgumentException("[aRelayname] must not be 'null'.");
		}
		relayname = aRelayname;
	}

	public void setRelayEventEntity(RelayEventEntity aRelayEventEntity) {
		if (aRelayEventEntity == null) {
			throw new IllegalArgumentException("[aRelayEventEntity] must not be 'null'.");
		}
		relayEventEntity = aRelayEventEntity;
	}
	
	public RelayEventEntity getRelayEventEntity() {
		return relayEventEntity;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", relayname=" + relayname + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Relay2Entity other = (Relay2Entity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}