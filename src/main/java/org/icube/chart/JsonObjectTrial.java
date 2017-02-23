package org.icube.chart;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.icube.helper.DatabaseConnectionHelper;
import org.icube.helper.Stopwatch;
import org.icube.helper.UtilHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonObjectTrial {
	
	public JSONArray getChartDataForPageNested(int pageId) {
		Stopwatch sw = new Stopwatch();
		JSONArray jArray = new JSONArray();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		Stopwatch sw1 = new Stopwatch();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartDataForPage(?)}")) {
			cstmt.setInt("pageid", pageId);
			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) {
				String metric1 = UtilHelper.hasColumnForProcedure(rs, "metric_name_1") ? rs.getString("metric_name_1") : "";
				String metric2 = UtilHelper.hasColumnForProcedure(rs, "metric_name_2") ? rs.getString("metric_name_2") : "";
				String metric3 = UtilHelper.hasColumnForProcedure(rs, "metric_name_3") ? rs.getString("metric_name_3") : "";
				String metric4 = UtilHelper.hasColumnForProcedure(rs, "metric_name_4") ? rs.getString("metric_name_4") : "";
				String metric5 = UtilHelper.hasColumnForProcedure(rs, "metric_name_5") ? rs.getString("metric_name_5") : "";
				String metric6 = UtilHelper.hasColumnForProcedure(rs, "metric_name_6") ? rs.getString("metric_name_6") : "";
				String metric7 = UtilHelper.hasColumnForProcedure(rs, "metric_name_7") ? rs.getString("metric_name_7") : "";
				String metric8 = UtilHelper.hasColumnForProcedure(rs, "metric_name_8") ? rs.getString("metric_name_8") : "";
				String metric9 = UtilHelper.hasColumnForProcedure(rs, "metric_name_9") ? rs.getString("metric_name_9") : "";
				String metric10 = UtilHelper.hasColumnForProcedure(rs, "metric_name_10") ? rs.getString("metric_name_10") : "";
				String type = UtilHelper.hasColumnForProcedure(rs, "type") ? rs.getString("type") : "";
				int value = rs.getInt("value");
				JSONObject m1Obj = new JSONObject();
				m1Obj.put("m1", metric1);
				JSONArray m1Array = new JSONArray();
				m1Obj.put("data", m1Array);

				JSONObject m2Obj = new JSONObject();
				m2Obj.put("m2", metric2);
				JSONArray m2Array = new JSONArray();
				m2Obj.put("data", m2Array);
				m1Array.put(m2Obj);

				JSONObject m3Obj = new JSONObject();
				m3Obj.put("m3", metric3);
				JSONArray m3Array = new JSONArray();
				m3Obj.put("data", m3Array);
				m2Array.put(m3Obj);

				JSONObject m4Obj = new JSONObject();
				m4Obj.put("m4", metric4);
				JSONArray m4Array = new JSONArray();
				m4Obj.put("data", m4Array);
				m3Array.put(m4Obj);

				JSONObject m5Obj = new JSONObject();
				m5Obj.put("m5", metric5);
				JSONArray m5Array = new JSONArray();
				m5Obj.put("data", m5Array);
				m4Array.put(m5Obj);

				JSONObject m6Obj = new JSONObject();
				m6Obj.put("m6", metric6);
				JSONArray m6Array = new JSONArray();
				m6Obj.put("data", m6Array);
				m5Array.put(m6Obj);

				JSONObject m7Obj = new JSONObject();
				m7Obj.put("m7", metric7);
				JSONArray m7Array = new JSONArray();
				m7Obj.put("data", m7Array);
				m6Array.put(m7Obj);

				JSONObject m8Obj = new JSONObject();
				m8Obj.put("m8", metric8);
				JSONArray m8Array = new JSONArray();
				m8Obj.put("data", m8Array);
				m7Array.put(m8Obj);

				JSONObject m9Obj = new JSONObject();
				m9Obj.put("m9", metric9);
				JSONArray m9Array = new JSONArray();
				m9Obj.put("data", m9Array);
				m8Array.put(m9Obj);

				JSONObject m10Obj = new JSONObject();
				m10Obj.put("m10", metric10);
				JSONArray m10Array = new JSONArray();
				m10Obj.put("data", m10Array);
				m9Array.put(m10Obj);

				JSONObject typeObj = new JSONObject();
				typeObj.put("type", type);
				JSONArray typeArray = new JSONArray();
				typeObj.put("data", typeArray);
				m10Array.put(typeObj);

				JSONObject valueObj = new JSONObject();
				valueObj.put("value", value);
				JSONArray valueArray = new JSONArray();
				valueObj.put("data", valueArray);
				typeArray.put(valueObj);

				jArray.put(m1Obj);
			}
			org.apache.log4j.Logger.getLogger(ChartHelper.class).debug("In the try block :::: " + sw1.elapsedTime());
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number " + pageId, e);
		}
		org.apache.log4j.Logger.getLogger(ChartHelper.class).debug("Entire function :::: " + sw.elapsedTime());
		return jArray;
	}

	public String getChartDataForPageNestedList(int pageId) {
		Stopwatch sw = new Stopwatch();
		List<List<Object>> result = new ArrayList<>();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();
		Stopwatch sw1 = new Stopwatch();
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getChartDataForPage(?)}")) {
			cstmt.setInt("pageid", pageId);
			ResultSet rs = cstmt.executeQuery();
			// List<Object> header = new ArrayList<>();
			// header.add("m1");
			// header.add("m2");
			// header.add("m3");
			// header.add("m4");
			// header.add("m5");
			// header.add("m6");
			// header.add("m7");
			// header.add("m8");
			// header.add("m9");
			// header.add("m10");
			// header.add("type");
			// header.add("count");
			// result.add(header);
			while (rs.next()) {
				List<Object> row = new ArrayList<>();
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_1") ? rs.getString("metric_name_1") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_2") ? rs.getString("metric_name_2") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_3") ? rs.getString("metric_name_3") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_4") ? rs.getString("metric_name_4") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_5") ? rs.getString("metric_name_5") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_6") ? rs.getString("metric_name_6") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_7") ? rs.getString("metric_name_7") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_8") ? rs.getString("metric_name_8") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_9") ? rs.getString("metric_name_9") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "metric_name_10") ? rs.getString("metric_name_10") : "");
				row.add(UtilHelper.hasColumnForProcedure(rs, "type") ? rs.getString("type") : "");
				row.add(rs.getInt("value"));
				result.add(row);

			}

			System.out.println("JSON Object List " + gson.toJson(result));
			org.apache.log4j.Logger.getLogger(ChartHelper.class).debug("In the try block :::: " + sw1.elapsedTime());
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(ChartHelper.class).error("Unable to retrieve the chart details for page number " + pageId, e);
		}
		org.apache.log4j.Logger.getLogger(ChartHelper.class).debug("Entire function :::: " + sw.elapsedTime());
		return gson.toJson(result);
	}
}
