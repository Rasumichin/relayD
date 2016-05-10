package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.relayd.web.api.jaxb.EventDTO;

/**
 * @author schmollc (Christian@cloud.franke-net.com)
 * @since 09.05.2016
 * status initial
 */
@ManagedBean
@ViewScoped
public class BasicView implements Serializable {
	private static final long serialVersionUID = 1L;

	public List<EventDTO> getEvents() {
		return EventDTO.getRandomEvents();
	}
}