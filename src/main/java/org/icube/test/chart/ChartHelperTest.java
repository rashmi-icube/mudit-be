package org.icube.test.chart;

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
			System.out.println("PageId : " + q.getPageId());
			System.out.println("TabId :  " + q.getTabId());
			System.out.println("Question :  " + q.getQuestionText());
			
		}
	}
	
	
	@Test
	public void testGetChartMapping(){
		List<Chart> chartList = ch.getChartMapping(pageId);
		for(Chart c : chartList){
			System.out.println("ChartId : " + c.getChartId());
			System.out.println("Chart type :  " + c.getChartType());
			System.out.println("Metric Id :  " + c.getMetricId());
			System.out.println("Metric : " + c.getMetric());
			System.out.println("Chart Title : " + c.getChartTitle());
	}
	}
	
	@Test
	public void testGetChartDataForPage(){
		List<Metric> metricList = ch.getChartDataForPage(pageId);
		for(Metric m : metricList){
			System.out.println("MetricId1 : " + m.getMetricId1());
			System.out.println("MetricName1 : " + m.getMetricName1());
			System.out.println("MetricId2 :  " + m.getMetricId2());
			System.out.println("MetricName2 : " + m.getMetricName2());
			System.out.println("MetricId3 :  " + m.getMetricId3());
			System.out.println("MetricName3 : " + m.getMetricName3());
			System.out.println("MetricId4 : " + m.getMetricId4());
			System.out.println("MetricName4 : " + m.getMetricName4());
			System.out.println("MetricId5 : " + m.getMetricId5());
			System.out.println("MetricName5 : " + m.getMetricName5());
			System.out.println("MetricId6 : " + m.getMetricId6());
			System.out.println("MetricName6 : " + m.getMetricName6());
			System.out.println("MetricId7 : " + m.getMetricId7());
			System.out.println("MetricName7 : " + m.getMetricName7());
			System.out.println("MetricId8 : " + m.getMetricId8());
			System.out.println("MetricName8 : " + m.getMetricName8());
			System.out.println("MetricId9 : " + m.getMetricId9());
			System.out.println("MetricName9 : " + m.getMetricName9());
			System.out.println("MetricId10 : " + m.getMetricId10());
			System.out.println("MetricName10 : " + m.getMetricName10());
			System.out.println("Candidate count : " + m.getCandidateCount());
		}
	}
	

}
