package com.loginregister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

public class LoginDAO {

	public static boolean validate(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select firstname from userDetails where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap()
						.put("firstname", rs.getString("firstname"));
				DataConnect.close(con);
				return true;
			}

		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {

		}
		return false;
	}

}
