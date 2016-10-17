package com.relayd.client.jaxb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 28.03.2016
 *
 */
@XmlRootElement(name = "event")
public class EventDTO {
	private String id;
	private String title;
	private Integer year;
	private Integer numberOfParticipants;

	public EventDTO() {
	}

	public EventDTO(String anId) {
		id = anId;
	}

	/**
	 * Creates always three instances with three static titles, each with a
	 * random id, a static year (2017) and a random number of particants
	 * (0, 4, 8 or 12).
	 *
	 * @return A list of three elements.
	 *
	 */
	public static List<EventDTO> getRandomEvents() {
		List<EventDTO> result = new ArrayList<>();
		String[] titles = { "Metro Duesseldorf Marathon", "Schmolleks Ennepetal Staffel Hulli Gulli", "Boston Marathon Relay Event" };

		for (String eachTitle : Arrays.asList(titles)) {
			EventDTO event = new EventDTO(UUID.randomUUID().toString());
			event.setTitle(eachTitle);
			event.setYear(2017);
			event.setNumberOfParticipants(new Random().nextInt(4) * 4);
			result.add(event);
		}

		return result;
	}

	/**
	 * Fake the check whether a corresponding instance is existing for the
	 * given 'id'.
	 *
	 * @param anEventId will not be considered for the algorythm
	 * @return true or false with a 50 percent probability for each case
	 *
	 */
	public static boolean isEventExistingFor(String anEventId) {
		long randomValue = System.currentTimeMillis();

		return (randomValue % 2) == 0;
	}

	@XmlElement
	public String getId() {
		return id;
	}

	public void setId(String anId) {
		id = anId;
	}

	@XmlElement
	public String getTitle() {
		return title;
	}

	public void setTitle(String aTitle) {
		title = aTitle;
	}

	@XmlElement
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer aYear) {
		year = aYear;
	}

	@XmlElement
	public Integer getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(Integer participants) {
		numberOfParticipants = participants;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " {"
				+ "id=" + id
				+ ", title=" + title
				+ ", year=" + year + "}";
	}
}
