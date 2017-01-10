package org.icube.login;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.icube.helper.DatabaseConnectionHelper;

public class Login {

	public User login(String loginId, String password) throws Exception {

		User u = new User();
		DatabaseConnectionHelper dch = DatabaseConnectionHelper.getDBHelper();

		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call verifyLogin(?,?)}")) {
			cstmt.setString("loginid", loginId);
			cstmt.setString("pass", password);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					if (rs.getInt("user_id") == 0) {
						org.apache.log4j.Logger.getLogger(Login.class).error("Invalid username/password");
						throw new Exception("Invalid credentials!!!");
					} else {
						u.setUserId(rs.getInt("user_id"));
						u.setLoginId(loginId);
						u.setRoleId(rs.getInt("role_id"));
						u.setUserName(rs.getString("name"));
						org.apache.log4j.Logger.getLogger(Login.class).debug("Successfully validated user with userID : " + loginId);
					}
				}
			}
		} catch (SQLException e1) {
			org.apache.log4j.Logger.getLogger(Login.class).error("Exception while retrieving the company database", e1);
		}
		return u;
	}

}
