package com.relayd.web.pagebean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.themeswitcher.ThemeSwitcher;

import com.relayd.Settings;
import com.relayd.web.theme.Theme;
import com.relayd.web.theme.ThemeService;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 08.10.2016
 *
 */
@ManagedBean
@SessionScoped
public class SettingsPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	Settings settings = Settings.newInstance();

	private List<Theme> themes;

	@ManagedProperty("#{themeService}")
	ThemeService service;

	@PostConstruct
	public void init() {
		themes = service.getThemes();
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setService(ThemeService aService) {
		service = aService;
	}

	public String getVersion() {
		return settings.getVersion();
	}

	public String getEmailDomain() {
		return settings.getEmailDomain();
	}

	public String getGatewayType() {
		return settings.getGatewayType();
	}

	public String getClosingDate() {
		return settings.getClosingDate();
	}

	public String getTheme() {
		return settings.getTheme();
	}

	public void setTheme(String aTheme) {
		settings.setTheme(aTheme);
	}

	public void saveTheme(AjaxBehaviorEvent ajax) {
		setTheme((String) ((ThemeSwitcher) ajax.getSource()).getValue());
	}
}