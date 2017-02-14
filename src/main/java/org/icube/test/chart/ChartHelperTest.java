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
	public void testGetQuestionForTab() {
		List<Question> questionList = ch.getQuestionForTab(tabId);
		for (Question q : questionList) {
			assertNotNull("PageId : " + q.getPageId());
			assertNotNull("TabId :  " + q.getTabId());
			assertNotNull("Question :  " + q.getQuestionText());

		}
	}

	@Test
	public void testGetChartMapping() {
		List<Chart> chartList = ch.getChartMapping(pageId);
		for (Chart c : chartList) {
			assertNotNull("ChartId : " + c.getChartId());
			assertNotNull("Chart type :  " + c.getChartType());
			assertNotNull("Metric Id :  " + c.getMetricId());
			assertNotNull("Metric : " + c.getMetric());
			assertNotNull("Chart Title : " + c.getChartTitle());
		}
	}

	@Test
	public void testGetChartDataForPage() {
		List<Metric> metricList = ch.getChartDataForPage(pageId);
		for (Metric m : metricList) {
			assertNotNull("M1 : " + m.getM1());
			assertNotNull("M2 : " + m.getM2());
			assertNotNull("M3 : " + m.getM3());
			assertNotNull("M4 : " + m.getM4());
			assertNotNull("M5 : " + m.getM5());
			assertNotNull("M6 : " + m.getM6());
			assertNotNull("M7 : " + m.getM7());
			assertNotNull("M8 : " + m.getM8());
			assertNotNull("M9 : " + m.getM9());
			assertNotNull("M10 : " + m.getM10());
			assertNotNull("Value : " + m.getValue());
			assertNotNull("Type : " + m.getType());
		}
	}

}
