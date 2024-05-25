package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.JDBCmySQL;
import dto.StaffDTO;

public class ForgotPasswordDAO{
	
	public static ForgotPasswordDAO  getInstance() {
		return new ForgotPasswordDAO ();
	}
	
	public int updatePassword(String email) {
	    int newPassword = getOTP();
	    try {
	        Connection connection = JDBCmySQL.getConnection();
	        
	        int employeeId = findEmployeeIdByEmail(connection, email);
	        System.out.println("ID của nhân viên là "+employeeId);
	        // Update password in the "employee" table
	        String sql = "UPDATE employee , person SET employee.Password=? WHERE EmployeeID=?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, newPassword);
	            preparedStatement.setInt(2, employeeId);
	            preparedStatement.executeUpdate();
	        }

	        JDBCmySQL.closeConnection(connection);

	        return newPassword;
	    } catch (Exception e) {
	        Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	    }

	    // Handle the failure case, you might throw an exception or return a special value
	    return -1; // or any other special value indicating failure
	}


	private int findEmployeeIdByEmail(Connection connection, String email) throws SQLException {
	    String sql = "SELECT employee.EmployeeID FROM employee ,person WHERE Email=? and employee.EmployeeID=person.personID";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, email);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                return resultSet.getInt("EmployeeId");
	            } else {
	                return -1; // Indicates that the email was not found
	            }
	        }
	    }
	}
	public static int getOTP() {
	    int min = 100000;
	    int max = 999999;
	    return (int) ((Math.random() * (max - min + 1)) + min);
	}


}
