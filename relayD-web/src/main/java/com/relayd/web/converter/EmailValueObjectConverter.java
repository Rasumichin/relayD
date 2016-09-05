package com.relayd.web.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.relayd.attributes.Email;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 29.06.2016
 * status initial
 */
@FacesConverter("com.relayd.web.converter.EmailValueObjectConverter")
public class EmailValueObjectConverter implements Converter {

	@Override
	public Object getAsObject(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, String value) {

		if (!Email.isValid(value)) {
			// TODO -schmollc- Was genau schiefgegangen ist würde auch noch in der  EMail Klasse stehen.
			// isValid() könnte auch direkt den Fehler zurückliefen als Code z.B.
			// - 150 - @ fehlt
			// - 151 - zu lang
			// - 152 - zu kurz
			// usw.
			// Dann könnte die Fehlermeldung als solches in einer ResourceDatei abgelegt werden.
			// Allerdings wäre hier nur eine "Technische" Prüfung. Die Prüfung auf doppelten Eintrag wird erst in der Save()
			// Methode gemacht. Auch irgendwie schade.
			FacesMessage msg = new FacesMessage("E-mail validation failed.", "Invalid E-mail format.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);
		}

		Email email = Email.newInstance(value);

		return email;
	}

	@Override
	public String getAsString(@SuppressWarnings("unused") FacesContext facesContext, @SuppressWarnings("unused") UIComponent uiComponent, Object value) {
		return value.toString();
	}
}