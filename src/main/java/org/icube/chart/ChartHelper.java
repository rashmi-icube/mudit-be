package org.icube.chart;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.icube.helper.DatabaseConnectionHelper;
import org.icube.helper.UtilHelper;
import org.icube.metric.Metric;
import org.icube.metric.TatMetric;
import org.icube.question.Question;

public class ChartHelper {

	/**
	 * Retrieves the list of Metric objects
	 * @param pageId - page number on which the chart has to be displayed
	 * @return List of Metric objects
	 */
	public List<Metric> getChartDataForPage(int pageId) {
		List<Metric> metricList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartDataForPage(?)}")) {
			cstmt.setInt("pageid", pageId);
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				Metric m = new Metric();
				m.setMetricId1(UtilHelper.hasColumnForProcedure(rs, "metric_id_1") ? rs.getInt("metric_id_1") : 0);
				m.setMetricName1(UtilHelper.hasColumnForProcedure(rs, "metric_name_1") ? rs.getString("metric_name_1") : "");
				m.setMetricId2(UtilHelper.hasColumnForProcedure(rs, "metric_id_2") ? rs.getInt("metric_id_2") : 0);
				m.setMetricName2(UtilHelper.hasColumnForProcedure(rs, "metric_name_2") ? rs.getString("metric_name_2") : "");
				m.setMetricId3(UtilHelper.hasColumnForProcedure(rs, "metric_id_3") ? rs.getInt("metric_id_3") : 0);
				m.setMetricName3(UtilHelper.hasColumnForProcedure(rs, "metric_name_3") ? rs.getString("metric_name_3") : "");
				m.setMetricId4(UtilHelper.hasColumnForProcedure(rs, "metric_id_4") ? rs.getInt("metric_id_4") : 0);
				m.setMetricName4(UtilHelper.hasColumnForProcedure(rs, "metric_name_4") ? rs.getString("metric_name_4") : "");
				m.setMetricId5(UtilHelper.hasColumnForProcedure(rs, "metric_id_5") ? rs.getInt("metric_id_5") : 0);
				m.setMetricName5(UtilHelper.hasColumnForProcedure(rs, "metric_name_5") ? rs.getString("metric_name_5") : "");
				m.setMetricId6(UtilHelper.hasColumnForProcedure(rs, "metric_id_6") ? rs.getInt("metric_id_6") : 0);
				m.setMetricName6(UtilHelper.hasColumnForProcedure(rs, "metric_name_6") ? rs.getString("metric_name_6") : "");
				m.setMetricId7(UtilHelper.hasColumnForProcedure(rs, "metric_id_7") ? rs.getInt("metric_id_7") : 0);
				m.setMetricName7(UtilHelper.hasColumnForProcedure(rs, "metric_name_7") ? rs.getString("metric_name_7") : "");
				m.setMetricId8(UtilHelper.hasColumnForProcedure(rs, "metric_id_8") ? rs.getInt("metric_id_8") : 0);
				m.setMetricName8(UtilHelper.hasColumnForProcedure(rs, "metric_name_8") ? rs.getString("metric_name_8") : "");
				m.setMetricId9(UtilHelper.hasColumnForProcedure(rs, "metric_id_9") ? rs.getInt("metric_id_9") : 0);
				m.setMetricName9(UtilHelper.hasColumnForProcedure(rs, "metric_name_9") ? rs.getString("metric_name_9") : "");
				m.setMetricId10(UtilHelper.hasColumnForProcedure(rs, "metric_id_10") ? rs.getInt("metric_id_10") : 0);
				m.setMetricName10(UtilHelper.hasColumnForProcedure(rs, "metric_name_10") ? rs.getString("metric_name_10") : "");
				m.setCandidateCount(rs.getInt("candidate_count"));
				metricList.add(m);
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number " + pageId, e);
		}
		return metricList;
	}

	/**
	 * Retrieves the list of Chart objects
	 * @param page_id - page number on which the chart has to be displayed
	 * @return List of Chart objects
	 */
	public List<Chart> getChartMapping(int pageId) {
		List<Chart> chartList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartMapping(?)}")) {
			cstmt.setInt("pageid", pageId);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					// fill the chart details & add the chart object to the chart list
					chartList.add(setChartDetails(rs));
				}

			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number " + pageId, e);
		}

		return chartList;
	}

	private Chart setChartDetails(ResultSet rs) throws SQLException {
		Chart c = new Chart();
		c.setChartId(rs.getInt("chart_id"));
		c.setChartType(rs.getNString("chart_type"));
		c.setChartTitle(rs.getString("chart_title"));
		c.setMetricId(rs.getInt("metric_id"));
		c.setMetric(rs.getString("metric"));
		return c;
	}
	
	public List<Question> getQuestionForTab(int tabId){
		List<Question> questionList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getPagesForTab(?)}")) {
			cstmt.setInt("tabid", tabId);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					// fill the question object & add the question object to the question list
					questionList.add(setQuestionDetails(rs));
				}

			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the question details for tab number " + tabId, e);
		}
		return questionList;
		
		
	}

	private Question setQuestionDetails(ResultSet rs) throws SQLException {
		Question q = new Question();
		q.setPageId(rs.getInt("page_id"));
		q.setTabId(rs.getInt("tab_id"));
		q.setQuestionText(rs.getString("question"));
		return q;
	}
	
	public List<TatMetric> getChartDataForTat() {
		List<TatMetric> metricList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartDataForTat()}")) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				TatMetric m = new TatMetric();
				m.setMetricName1(UtilHelper.hasColumnForProcedure(rs, "metric_name_1") ? rs.getString("metric_name_1") : "");
				m.setMetricName2(UtilHelper.hasColumnForProcedure(rs, "metric_name_2") ? rs.getString("metric_name_2") : "");
				m.setMetricName3(UtilHelper.hasColumnForProcedure(rs, "metric_name_3") ? rs.getString("metric_name_3") : "");
				m.setMetricName4(UtilHelper.hasColumnForProcedure(rs, "metric_name_4") ? rs.getString("metric_name_4") : "");
				m.setMetricName5(UtilHelper.hasColumnForProcedure(rs, "metric_name_5") ? rs.getString("metric_name_5") : "");
				m.setMetricName6(UtilHelper.hasColumnForProcedure(rs, "metric_name_6") ? rs.getString("metric_name_6") : "");
				m.setType(UtilHelper.hasColumnForProcedure(rs, "type") ? rs.getString("type") : "");
				m.setValue(UtilHelper.hasColumnForProcedure(rs, "value") ? rs.getDouble("value") : 0);
				metricList.add(m);
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for TAT" , e);
		}
		return metricList;
	}
	
	public List<TatMetric> getChartDataForSourceOfHire() {
		List<TatMetric> metricList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartDataForSourceOfHire()}")) {
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				TatMetric m = new TatMetric();
				m.setMetricName1(UtilHelper.hasColumnForProcedure(rs, "metric_name_1") ? rs.getString("metric_name_1") : "");
				m.setMetricName2(UtilHelper.hasColumnForProcedure(rs, "metric_name_2") ? rs.getString("metric_name_2") : "");
				m.setMetricName3(UtilHelper.hasColumnForProcedure(rs, "metric_name_3") ? rs.getString("metric_name_3") : "");
				m.setMetricName4(UtilHelper.hasColumnForProcedure(rs, "metric_name_4") ? rs.getString("metric_name_4") : "");
				m.setMetricName5(UtilHelper.hasColumnForProcedure(rs, "metric_name_5") ? rs.getString("metric_name_5") : "");
				m.setMetricName6(UtilHelper.hasColumnForProcedure(rs, "metric_name_6") ? rs.getString("metric_name_6") : "");
				m.setType(UtilHelper.hasColumnForProcedure(rs, "type") ? rs.getString("type") : "");
				m.setValue(UtilHelper.hasColumnForProcedure(rs, "value") ? rs.getDouble("value") : 0);
				metricList.add(m);
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for TAT" , e);
		}
		return metricList;
	}

}
