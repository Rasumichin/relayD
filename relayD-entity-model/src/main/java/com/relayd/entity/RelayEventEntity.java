package com.relayd.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.*;

/**
 * JPA Entity class to represent the underlying RDBMS table which persists the 'Event' data.
 * Applies Joshua Bloch's 'Builder Pattern' to create instances programmatically.
 * 
 * @author Rasumichin (Erik@relayd.de)
 * @since 22.04.2016
 * status initial
 * 
 */
@Entity
@Table(name="RELAY_EVENT")
public class RelayEventEntity {
	
	@Id
	@Column(nullable=false, length=36)
	private String id;
	
	@Column(nullable=false, length=64)
	private String eventName;
	
	@Column(nullable=false)
	private Date eventDay;
	
	public static class Builder {
		private final String id = UUID.randomUUID().toString();
		private final String eventName;
		private Date eventDay = new Date(System.currentTimeMillis());
		
		public Builder(String anEventName) {
			if (null == anEventName) {
				throw new IllegalArgumentException("'[anEventName] must not be 'null'.");
			}
			eventName = anEventName;
		}
		
		public Builder withEventDay(Date anEventDay) {
			validateEventDay(anEventDay);
			
			eventDay = anEventDay;
			return this;
		}

		private void validateEventDay(Date anEventDay) {
			if (anEventDay == null) {
				throw new IllegalArgumentException("[anEventDay] must not be 'null'.");
			}
		}

		public RelayEventEntity build() {
			return new RelayEventEntity(this);
		}
	}
	
	private RelayEventEntity(Builder aBuilder) {
		id = aBuilder.id;
		eventName = aBuilder.eventName;
		eventDay = aBuilder.eventDay;
	}

	/**
	 * Provided empty constructor to conform to the JPA spec. For that reason the entity property fields
	 * are not declared 'final'.
	 * 
	 */
	protected RelayEventEntity() {
	}
	
	public String getId() {
		return id;
	}
	
	public String getEventName() {
		return eventName;
	}

	public Date getEventDay() {
		return eventDay;
	}
}
