package com.relayd.web.converter;

import java.time.Duration;
import java.time.LocalTime;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 18.03.2017
 *
 */
@FacesConverter("com.relayd.web.converter.DurationValueObjectConverter")
public class DurationValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {
		Duration duration;
		if (value == null || value.isEmpty()) {
			duration = Duration.ofMillis(0L);
		} else {
			duration = Duration.between(LocalTime.MIN, LocalTime.parse(value));
		}
		return duration;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return DurationFormatUtils.formatDuration(((Duration) value).toMillis(), "HH:mm:ss");
	}
}
