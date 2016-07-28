package com.relayd.web.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.relayd.FormatConstants;
import com.relayd.attributes.EventDay;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 22.06.2016
 * status initial
 */
@FacesConverter("com.relayd.web.converter.EventDayValueObjectConverter")
public class EventDayValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(FormatConstants.DATE_FORMAT_ISO));
		EventDay eventDay = EventDay.newInstance(localDate);
		
		return eventDay;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		return value.toString();
	}
}