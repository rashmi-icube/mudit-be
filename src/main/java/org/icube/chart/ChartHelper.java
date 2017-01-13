package org.icube.chart;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.icube.helper.DatabaseConnectionHelper;
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
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {

					ResultSetMetaData rsmd = rs.getMetaData();
					int columns = rsmd.getColumnCount();
					Metric m = new Metric();
					for (int x = 1; x <= columns; x++) {
						// fill the chart details
						m = setMetricDetails(rs, rsmd.getColumnName(x));
					}

					// add the chart object to the chart list
					metricList.add(m);
				}

			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number " + pageId, e);
		}
		return metricList;
	}

	private Metric setMetricDetails(ResultSet rs, String columnName) throws SQLException {
		Metric m = new Metric();
		switch (columnName) {
		case "metric_id_1":
			m.setMetricId1(rs.getInt("metric_id_1"));
			break;
		case "metric_id_2":
			m.setMetricId2(rs.getInt("metric_id_2"));
			break;
		case "metric_id_3":
			m.setMetricId3(rs.getInt("metric_id_3"));
			break;
		case "metric_id_4":
			m.setMetricId4(rs.getInt("metric_id_4"));
			break;
		case "metric_id_5":
			m.setMetricId5(rs.getInt("metric_id_5"));
			break;
		case "metric_id_6":
			m.setMetricId6(rs.getInt("metric_id_6"));
			break;
		}

		return m;
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
