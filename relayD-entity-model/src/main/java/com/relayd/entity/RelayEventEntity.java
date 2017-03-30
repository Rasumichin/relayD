package com.relayd.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 *
 */
@Entity
@Table(name = "relay_event")
public class RelayEventEntity {

	@Id
	@Column(length = 36)
	private String id;

	@Column(length = 256, nullable = false)
	private String eventName;

	@Column(nullable = false)
	private Date eventDay;

	public static RelayEventEntity newInstance() {
		return RelayEventEntity.newInstance(UUID.randomUUID());
	}

	public static RelayEventEntity newInstance(UUID anUuid) {
		if (anUuid == null) {
			throw new IllegalArgumentException("[anUuid] must not be 'null'.");
		}
		RelayEventEntity relayEventEntity = new RelayEventEntity();
		relayEventEntity.setId(anUuid.toString());

		return relayEventEntity;
	}

	public String getId() {
		return id;
	}

	void setId(String anId) {
		id = anId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String anEventName) {
		eventName = anEventName;
	}

	public Date getEventDay() {
		return eventDay;
	}

	public void setEventDay(Date anEventDay) {
		eventDay = anEventDay;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id + ", eventName=" + eventName + ", eventDay=" + eventDay + "]";
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
		RelayEventEntity other = (RelayEventEntity) obj;
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