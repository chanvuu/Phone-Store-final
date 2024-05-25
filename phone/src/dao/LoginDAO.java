package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import database.JDBCmySQL;
import view.Menu;

public class LoginDAO {
	
	    
    public static boolean checkPassword(String usrname, char[] password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        char[] pwCharArray = null;

        try {
            // Assuming you have a valid connection object named "connection"
            // Replace "YourConnectionClass" with the actual class you are using for database connection.
            connection = JDBCmySQL.getConnection();

            String query = "SELECT Password FROM employee , person WHERE Email = ?  and personID=EmployeeID";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usrname);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pwCharArray = resultSet.getString(1).toCharArray();
                System.out.println(Arrays.toString(pwCharArray));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            // Close resources in the reverse order of their creation to avoid memory leaks
            // Also, handle exceptions that may occur during closing
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return Arrays.equals(pwCharArray, password);
    }
    

    public static boolean isEmployee(String email, char[] password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isEmployee = false;

        try {
            connection = JDBCmySQL.getConnection();

            // Hash the password before comparing (replace this with your actual hashing logic)
            String hashedPassword = hashPassword(password);

            String query = "SELECT Position FROM employee, person WHERE email = ? AND password = ? AND personID = EmployeeID";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String position = resultSet.getString("Position");
                isEmployee = "Nhân Viên".equals(position);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            closeResources(connection, preparedStatement, resultSet);
        }

        return isEmployee;
    }
    
    private static String hashPassword(char[] password) {
        // Replace this with your actual password hashing logic (e.g., using bcrypt)
        // Here, we're converting the char array to a string for demonstration purposes
        return new String(password);
    }

    private static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
