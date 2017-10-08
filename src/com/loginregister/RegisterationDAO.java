package com.loginregister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

public class RegisterationDAO {

	public static boolean registerUser(String firstname, String lastname,
			String address, String phonenumber, String email, String username,
			String password) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			String sql = "INSERT INTO userDetails(firstname, lastname, address, phonenumber, email, username, password) VALUES(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, address);
			ps.setString(4, phonenumber);
			ps.setString(5, email);
			ps.setString(6, username);
			ps.setString(7, password);
			ps.executeUpdate();
			System.out.println("Data Added Successfully");
			return true;

		} catch (SQLException ex) {
			System.out.println("Registeration error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
	}
	
	
	public static boolean isUserExist(String username) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select firstname from userDetails where username = ?");
			ps.setString(1, username);
			

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException ex) {
			return false;
		} finally {

		}
		return false;
	}

}
