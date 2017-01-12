package org.icube.chart;

public class Chart {
	private int chart_id;
	private String chart_type;
	private int metric_id;
	private String metric;
	private String chart_title;
	
	
	public int getChart_id() {
		return chart_id;
	}
	public void setChart_id(int chart_id) {
		this.chart_id = chart_id;
	}
	public String getChart_type() {
		return chart_type;
	}
	public void setChart_type(String chart_type) {
		this.chart_type = chart_type;
	}
	public int getMetric_id() {
		return metric_id;
	}
	public void setMetric_id(int metric_id) {
		this.metric_id = metric_id;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public String getChart_title() {
		return chart_title;
	}
	public void setChart_title(String chart_title) {
		this.chart_title = chart_title;
	}
	
}
