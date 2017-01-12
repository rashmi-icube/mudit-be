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
	 * @param page_id - page number on which the chart has to be displayed
	 * @return List of Metric objects
	 */
	public List<Metric> getChartDataForPage (int page_id){
		List<Metric> metricList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try(CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartDataForPage(?)}")){
			cstmt.setInt("pageid", page_id);
			try(ResultSet rs = cstmt.executeQuery()){
				while(rs.next()){
					
					 ResultSetMetaData rsmd = rs.getMetaData();
					 int columns = rsmd.getColumnCount();
					 for (int x = 1; x <= columns; x++) {
						//fill the chart details
							Metric m = setMetricDetails(rs,rsmd.getColumnName(x));
					        }
					
					
					
					//add the chart object to the chart list
					//metricList.add(m);
				}
				
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number "+ page_id,e);
		}
		return metricList;
	}
	
	private Metric setMetricDetails(ResultSet rs, String columnName) throws SQLException {
		Metric m = new Metric();
		switch(columnName){
		case "metric_id_1":
			m.setMetric_id_1(rs.getInt("metric_id_1"));
			break;
		case "metric_id_2":
			m.setMetric_id_2(rs.getInt("metric_id_2"));
			break;
		case "metric_id_3":
			m.setMetric_id_3(rs.getInt("metric_id_3"));
			break;
		case "metric_id_4":
			m.setMetric_id_4(rs.getInt("metric_id_4"));
			break;
		case "metric_id_5":
			m.setMetric_id_5(rs.getInt("metric_id_5"));
			break;
		case "metric_id_6":
			m.setMetric_id_6(rs.getInt("metric_id_6"));
			break;
		}
		
	
		return m;
	}

	/**
	 * Retrieves the list of Chart objects
	 * @param page_id - page number on which the chart has to be displayed
	 * @return List of Chart objects
	 */
	public List<Chart> getChartMapping(int page_id){
		List<Chart> chartList = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		try(CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartMapping(?)}")){
			cstmt.setInt("pageid", page_id);
			try(ResultSet rs = cstmt.executeQuery()){
				while(rs.next()){
					
					//fill the chart details
					Chart c = setChartDetails(rs);
					
					//add the chart object to the chart list
					chartList.add(c);
				}
				
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number "+ page_id,e);
		}
		
		
		return chartList;
	}

	private Chart setChartDetails(ResultSet rs) throws SQLException {
		Chart c = new Chart();
		c.setChart_id(rs.getInt("chart_id"));
		c.setChart_type(rs.getNString("chart_type"));
		c.setChart_title(rs.getString("chart_title"));
		c.setMetric_id(rs.getInt("metric_id"));
		c.setMetric(rs.getString("metric"));
		return c;
	}

}
