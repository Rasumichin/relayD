package com.relayd.entity;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA Entity class to represent the underlying RDBMS table which persists the 'Event' data.
 * Applies Joshua Bloch's 'Builder Pattern' to create instances programmatically.
 * 
 * @author Rasumichin (Erik@cloud.franke-net.com)
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
	private String title;
	
	@Column(nullable=true)
	private Integer yearHappened;
	
	public static class Builder {
		private final String id = UUID.randomUUID().toString();
		private final String title;
		private Integer yearHappened;
		
		public Builder(String aTitle) {
			if (null == aTitle) {
				throw new IllegalArgumentException("'aTitle' must not be 'null'.");
			}
			title = aTitle;
		}
		
		public Builder withYearHappened(Integer aYearHappened) {
			validateYearHappened(aYearHappened);
			
			yearHappened = aYearHappened;
			return this;
		}
		
		private void validateYearHappened(Integer aYearHappened) {
			Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
			if (aYearHappened < currentYear) {
				throw new IllegalArgumentException("'aYearHappened' must not be before currenty year.");
			}
		}

		public RelayEventEntity build() {
			return new RelayEventEntity(this);
		}
	}
	
	private RelayEventEntity(Builder aBuilder) {
		id = aBuilder.id;
		title = aBuilder.title;
		yearHappened = aBuilder.yearHappened;
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
	
	public String getTitle() {
		return title;
	}

	public Integer getYearHappened() {
		return yearHappened;
	}
}
