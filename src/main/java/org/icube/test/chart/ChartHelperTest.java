package org.icube.test.chart;

import static org.junit.Assert.*;

import java.util.List;

import org.icube.chart.Chart;
import org.icube.chart.ChartHelper;
import org.icube.metric.Metric;
import org.icube.question.Question;
import org.junit.Test;

public class ChartHelperTest {
	ChartHelper ch = new ChartHelper();
	int pageId = 1;
	int tabId = 1;
	
	
	@Test
	public void testGetQuestionForTab(){
		List<Question> questionList = ch.getQuestionForTab(tabId);
		for(Question q : questionList){
			assertNotNull("PageId : " + q.getPageId());
			assertNotNull("TabId :  " + q.getTabId());
			assertNotNull("Question :  " + q.getQuestionText());
			
		}
	}
	
	
	@Test
	public void testGetChartMapping(){
		List<Chart> chartList = ch.getChartMapping(pageId);
		for(Chart c : chartList){
			assertNotNull("ChartId : " + c.getChartId());
			assertNotNull("Chart type :  " + c.getChartType());
			assertNotNull("Metric Id :  " + c.getMetricId());
			assertNotNull("Metric : " + c.getMetric());
			assertNotNull("Chart Title : " + c.getChartTitle());
	}
	}
	
	@Test
	public void testGetChartDataForPage(){
		List<Metric> metricList = ch.getChartDataForPage(pageId);
		for(Metric m : metricList){
			assertNotNull("MetricName1 : " + m.getMetricName1());
			assertNotNull("MetricName2 : " + m.getMetricName2());
			assertNotNull("MetricName3 : " + m.getMetricName3());
			assertNotNull("MetricName4 : " + m.getMetricName4());
			assertNotNull("MetricName5 : " + m.getMetricName5());
			assertNotNull("MetricName6 : " + m.getMetricName6());
			assertNotNull("MetricName7 : " + m.getMetricName7());
			assertNotNull("MetricName8 : " + m.getMetricName8());
			assertNotNull("MetricName9 : " + m.getMetricName9());
			assertNotNull("MetricName10 : " + m.getMetricName10());
			assertNotNull("Value : " + m.getValue());
			assertNotNull("Type : " + m.getType());
		}
	}
	

}
