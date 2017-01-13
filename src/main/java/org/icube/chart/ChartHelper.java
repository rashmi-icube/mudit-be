package org.icube.chart;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.icube.helper.DatabaseConnectionHelper;
import org.icube.helper.UtilHelper;
import org.icube.metric.Metric;

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

}
