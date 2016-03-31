package com.relayd.web.api.jaxb;

import java.util.*;
import javax.xml.bind.annotation.*;

/**
 * @author Rasumichin (Erik@cloud.franke-net.com)
 * @since 28.03.2016
 * status initial
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
    
    public static List<EventDTO> getRandomEvents() {
        List<EventDTO> result = new ArrayList<>();
        String[] titles = {"Metro Duesseldorf Marathon", "Schmolleks Ennepetal Staffel Hulli Gulli", "Boston Marathon Relay Event"};

        for (String eachTitle: Arrays.asList(titles)) {
            EventDTO event = new EventDTO(UUID.randomUUID().toString());
            event.setTitle(eachTitle);
            event.setYear(2017);
            event.setNumberOfParticipants(new Random().nextInt(4) * 4);
            result.add(event);
        }
        
        return result;
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
