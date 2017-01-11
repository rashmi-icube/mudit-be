package org.icube.chart;

import java.util.ArrayList;
import java.util.List;

import org.icube.metric.Metric;

public class ChartHelper {
	
	/**
	 * Retrieves the list of Metric objects
	 * @param pageNumber - page number on which the chart has to be displayed
	 * @return List of Metric objects
	 */
	public List<Metric> getChartDataForPage (int pageNumber){
		List<Metric> metricList = new ArrayList<>();
		
		return metricList;
	}
	
	/**
	 * Retrieves the list of Chart objects
	 * @param pageNumber - page number on which the chart has to be displayed
	 * @return List of Chart objects
	 */
	public List<Chart> getChartType(int pageNumber){
		List<Chart> chartList = new ArrayList<>();
		
		return chartList;
	}

}
