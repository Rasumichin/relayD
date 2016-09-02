package com.relayd.web.pagebean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;

/**
 * Holds the methods for Comboboxes in the GUI.
 * <p/>
 * @author schmollc (Christian@relayd.de)
 * @since 01.09.2016
 * status initial
 */
@ManagedBean(name = "selectItemBean")
@SessionScoped
public class SelectItemBean {

	private List<Shirtsize> shirtsizes;
	private List<Position> positions;
	private List<Locale> nationalities;

	public SelectItemBean() {
		initShirtsizes();
		initPositions();
		initAllNationalities();
	}

	private void initShirtsizes() {
		shirtsizes = new ArrayList<Shirtsize>();

		for (Shirtsize eachShirtsize : Shirtsize.values()) {
			shirtsizes.add(eachShirtsize);
		}
	}

	private void initPositions() {
		positions = new ArrayList<Position>();

		for (Position eachPosition : Position.values()) {
			positions.add(eachPosition);
		}
	}

	private void initAllNationalities() {
		nationalities = new ArrayList<Locale>();

		Locale[] locales = Locale.getAvailableLocales();
		for (Locale eachLocale : locales) {
			String code = eachLocale.getCountry();
			String name = eachLocale.getDisplayCountry();

			if (!"".equals(code) && !"".equals(name)) {
				nationalities.add(eachLocale);
			}
		}
	}

	public List<Shirtsize> getShirtsizes() {
		return Collections.unmodifiableList(shirtsizes);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
	}

	public List<Locale> getNationalities() {
		return Collections.unmodifiableList(nationalities);
	}

}