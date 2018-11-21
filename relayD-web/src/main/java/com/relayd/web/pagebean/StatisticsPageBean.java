package com.relayd.web.pagebean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 21.11.2018
 *
 */
@ManagedBean
@SessionScoped
public class StatisticsPageBean implements Serializable {
	private static final long serialVersionUID = 5490060877627841411L;
	
	public String getToDo() {
		return "ToDo!";
	}
}