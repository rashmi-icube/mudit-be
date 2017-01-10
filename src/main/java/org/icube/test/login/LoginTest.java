package org.icube.test.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.icube.login.Login;
import org.icube.login.User;
import org.junit.Test;

public class LoginTest {
	Login l = new Login();;

	@Test
	public void testLoginEmployee() {
		try {
			User u = l.login("user1@axis.com", "abc123");
			assertNotNull(u.getUserId());
			assertNotNull(u.getRoleId());
			assertNotNull(u.getUserName());
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid credentials!!!");
		}
	}

}
