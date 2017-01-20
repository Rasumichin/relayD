package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.relayd.attributes.Position;
import com.relayd.attributes.Shirtsize;
import com.relayd.ejb.GatewayType;

/**
 * Holds the methods for Comboboxes in the GUI.
 *
 * @author schmollc (Christian@relayd.de)
 * @since 01.09.2016
 *
 */
@ManagedBean(name = "selectItemBean")
@SessionScoped
public class SelectItemBean implements Serializable {
	private static final long serialVersionUID = -8028809269452661427L;

	private List<Shirtsize> shirtsizes;
	private List<Position> positions;
	private List<GatewayType> gateways;

	// TODO -small- (Version 1.4): Wird nicht mehr benötigt. Kann gelöscht werden.
	private List<Locale> nationalities;

	public SelectItemBean() {
		initShirtsizes();
		initPositions();
		initAllNationalities();
		initGateways();
	}

	private void initGateways() {
		gateways = new ArrayList<GatewayType>();

		for (GatewayType eachGatewayType : GatewayType.values()) {
			gateways.add(eachGatewayType);
		}
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

	public List<GatewayType> getGatewayTypes() {
		return Collections.unmodifiableList(gateways);
	}
}