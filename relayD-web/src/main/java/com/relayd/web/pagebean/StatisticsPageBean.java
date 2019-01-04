package com.relayd.web.pagebean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 * @author schmollc (Christian@relayd.de)
 * @since 21.11.2018
 *
 */
@ManagedBean
@SessionScoped
public class StatisticsPageBean implements Serializable {
	private static final long serialVersionUID = 5490060877627841411L;

	private HorizontalBarChartModel horizontalBarModel;

	@PostConstruct
	public void init() {
		createHorizontalBarModel();
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	private void createHorizontalBarModel() {
		ChartSeries participants = getParticipants();

		horizontalBarModel = new HorizontalBarChartModel();
		horizontalBarModel.addSeries(participants);

		horizontalBarModel.setTitle("Relay Participation");
		horizontalBarModel.setLegendPosition("e");
		horizontalBarModel.setStacked(false);
		horizontalBarModel.setShadow(true);

		setAxisX(horizontalBarModel.getAxis(AxisType.X));
		setAxisY(horizontalBarModel.getAxis(AxisType.Y));
	}

	private ChartSeries getParticipants() {
		ChartSeries participants = new ChartSeries();
		participants.setLabel("Participants");
		participants.set("Alan Smithee", 0);
		participants.set("Peter Shaw", 1);
		participants.set("Bob Andrews", 1);
		participants.set("Justus Jonas", 2);
		participants.set("Christian Schmoll", 4);
		participants.set("Dirk Aderhold", 5);
		return participants;
	}

	private void setAxisX(Axis axis) {
		axis.setLabel("Runs");
		axis.setMin(0);
		axis.setMax(10);
		axis.setTickInterval("1");
	}
	
	private void setAxisY(Axis axis) {
		axis.setLabel("Participants");
	}
}