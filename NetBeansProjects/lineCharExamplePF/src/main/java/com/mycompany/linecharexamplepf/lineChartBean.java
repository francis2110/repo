/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.linecharexamplepf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author fran
 */
@Named("lineChartBean")
@RequestScoped

public class lineChartBean implements Serializable {

    private LineChartModel chartModel;

    /**
     * Creates a new instance of lineChartBean
     */
    @PostConstruct
    public void init() {
        createLineModels();
        System.out.println("init method called");
    }

    public LineChartModel getChartModel() {
        return chartModel;
    }

    public void setChartModel(LineChartModel chartModel) {
        this.chartModel = chartModel;
    }

    private void createLineModels() {
        chartModel = initLinearModel();

        getChartModel().setTitle("linear chart");
        Axis yAxis = getChartModel().getAxis(AxisType.Y);
        yAxis.setMin(-10);
        yAxis.setMax(10);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel("root");
        series.set(1.3, 2);
        series.set(2, 9);
        series.set(3,2);
        LineChartSeries series2 = new LineChartSeries();
        series2.set(1.3,-2);
        series2.set(2,-9);
        series2.set(3,2);
        model.setSeriesColors("4000FF,4000FF");
        model.setExtender("spline");
        model.addSeries(series);
        model.addSeries(series2);

        return model;
    }

}
