package com.relayd.client.jaxb;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author Rasumichin (Erik@relayd.de)
 * @since 28.03.2016
 *
 */
@XmlRootElement(name = "event")
public class EventDTO {
	private String id;
	private String name;
	private LocalDate eventDay;

	public EventDTO() {
	}

	public EventDTO(String anId) {
		id = anId;
	}

	/**
	 * Creates always three instances with three static names, each with a
	 * random id and a static event day (2017-04-30).
	 *
	 * @return A list with three elements.
	 *
	 */
	public static List<EventDTO> getRandomEvents() {
		List<EventDTO> result = new ArrayList<>();
		String[] names = { "Metro Duesseldorf Marathon", "Schmolleks Ennepetal Staffel Hulli Gulli", "Boston Marathon Relay Event" };

		for (String eachName : Arrays.asList(names)) {
			EventDTO event = new EventDTO(UUID.randomUUID().toString());
			event.setName(eachName);
			event.setEventDay(LocalDate.of(2017, 4, 30));
			result.add(event);
		}

		return result;
	}

	/**
	 * Fake the check whether a corresponding instance is existing for the given 'id'.
	 *
	 * @param	anEventId		The hash code of this value is part of building the fake value.
	 * @return	true or false	with a 50 percent probability for each case
	 *
	 */
	public static boolean isEventExistingFor(String anEventId) {
		long randomValue = System.currentTimeMillis() + anEventId.hashCode();

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
	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}

	@XmlElement()
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	public LocalDate getEventDay() {
		return eventDay;
	}

	public void setEventDay(LocalDate dayOfEvent) {
		eventDay = dayOfEvent;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventDTO other = (EventDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " {" + "id=" + id + ", name=" + name + ", eventDay=" + eventDay + "}";
	}
}
