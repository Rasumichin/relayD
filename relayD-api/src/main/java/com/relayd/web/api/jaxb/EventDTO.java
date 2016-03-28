package com.relayd.web.api.jaxb;

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
}
