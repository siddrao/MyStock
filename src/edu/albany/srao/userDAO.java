package edu.albany.srao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {

	public static boolean login(String username, String password){
		
		Connection con = null;
		try {
			// Setup the DataSource object
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setServerName(System.getenv("ICSI518_SERVER").toString());
			ds.setPortNumber(Integer.parseInt(System.getenv("ICSI518_PORT")));
			ds.setDatabaseName(System.getenv("icsi518_db").toString());
			ds.setUser(System.getenv("ICSI518_USER").toString());
			ds.setPassword(System.getenv("ICSI518_PASSWORD").toString());

			// Get a connection object
			con = ds.getConnection();

			// Get a prepared SQL statement
			String sql = "SELECT username, password from user where username = ? and password = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);

			// Execute the statement
			ResultSet rs = st.executeQuery();
//			dbusername = rs.getString("user");
//			dbpassword = rs.getString("password");

			// Iterate through results
			if (rs.next()) {
				System.out.println(rs.getString("username"));
				//this.Password = rs.getString("password");
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			return false;
		}
		finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
