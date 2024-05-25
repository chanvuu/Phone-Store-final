package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.JDBCmySQL;
import dto.CustomerDTO;
import dto.InvoiceDetailDTO;
//import dto.InvoiceDetailDTO;
import dto.PersonDTO;
import dto.ProductDTO;
import dto.ReceiptDetailDTO;


public class CustomerDAO implements DAOInterface<CustomerDTO>{

	public static CustomerDAO getInstance() {
		return new CustomerDAO();
	}
	
	@Override
	public int insert(CustomerDTO t) {
	    Connection connection = null;

	    try {
	        connection = JDBCmySQL.getConnection();

	        String sqlPerson = "INSERT INTO person (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Comment) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        String sqlCustomer = "INSERT INTO customer (customerID) VALUES (?)";

	        try (PreparedStatement personStatement = connection.prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
	             PreparedStatement customerStatement = connection.prepareStatement(sqlCustomer, Statement.RETURN_GENERATED_KEYS)) {

	            personStatement.setString(1, t.getPersonDTO().getName());
	            personStatement.setString(2, t.getPersonDTO().getGender());
	         
	            java.util.Date utilDate = t.getPersonDTO().getBirthDay();

	             // Convert java.util.Date to java.sql.Date
	             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	             // Set the date in the personStatement
	             personStatement.setDate(3, sqlDate);
	            personStatement.setString(4, t.getPersonDTO().getEmail());
	            personStatement.setString(5, t.getPersonDTO().getPhone());
	            personStatement.setString(6, t.getPersonDTO().getAddress());
	            personStatement.setString(7, t.getPersonDTO().getComment());

	            int resultPerson = personStatement.executeUpdate();
	            ResultSet generatedKeysPerson = personStatement.getGeneratedKeys();

	            if (generatedKeysPerson.next()) {
	                int personID = generatedKeysPerson.getInt(1);

	                customerStatement.setInt(1, personID);

	                int resultCustomer = customerStatement.executeUpdate();

	                System.out.println("Number of rows changed in Person table: " + resultPerson);
	                System.out.println("Number of rows changed in Customer table: " + resultCustomer);

	                // Close and reopen the connection to refresh the database view
	                JDBCmySQL.closeConnection(connection);

	                return resultCustomer;
	            } else {
	                System.out.println("Error: No auto-generated keys returned for Person table.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception, log it, or return an error code
	        return -1; // or any other appropriate error code
	    }

	    // Add a return statement here based on your logic
	    return -1; // or any other appropriate default value
	}






	@Override
	public int update(CustomerDTO t) {
	    try {
	        // Step 1: Get connection to the database
	        Connection connection = JDBCmySQL.getConnection();

	        // Step 3: Create PreparedStatement
	        String sql = "UPDATE person SET fullName=?, Gender=?, dateOfBirth=?, Email=?, phoneNumber=?, Address=?, Comment=? WHERE personID=?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            // Step 4: Set parameters
	            preparedStatement.setString(1, t.getPersonDTO().getName());
	            preparedStatement.setString(2, t.getPersonDTO().getGender());
	            
	            
	            java.util.Date utilDate = t.getPersonDTO().getBirthDay();

	             // Convert java.util.Date to java.sql.Date
	             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

	             // Set the date in the personStatement
	             preparedStatement.setDate(3, sqlDate);
	            preparedStatement.setString(4, t.getPersonDTO().getEmail());
	            preparedStatement.setString(5, t.getPersonDTO().getPhone());
	            preparedStatement.setString(6, t.getPersonDTO().getAddress());
	            preparedStatement.setString(7, t.getPersonDTO().getComment());
	            preparedStatement.setInt(8, t.getPersonDTO().getPersonId());

	            // Step 5: Execute the update operation
	            int ketqua = preparedStatement.executeUpdate();

	            // For testing purposes, print the executed SQL statement
	            System.out.println(preparedStatement);

	            // Step 8: Close the connection
	            JDBCmySQL.closeConnection(connection);

	            return ketqua;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return 0;
	}


	@Override
	public int delete(String t) {
	    int ketqua = 0;

	    try {
	        Connection con = JDBCmySQL.getConnection();

	        // Using PreparedStatement to avoid SQL injection
	        String sql = "UPDATE customer, person\r\n"
	        		+ "SET customer.Status = 0, person.Status = 0\r\n"
	        		+ "WHERE customer.customerID = ? AND person.personID = ?;\r\n"
	        		+ "";
	        try (PreparedStatement preparedStatementPerson = con.prepareStatement(sql)) {

	            // Set parameters for both statements
	            preparedStatementPerson.setString(1, t);
	            preparedStatementPerson.setString(2, t);

	            // Execute the delete operations
	            int resultPerson = preparedStatementPerson.executeUpdate();;

	        }

	        JDBCmySQL.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return ketqua;
	}




    @Override
    public ArrayList<CustomerDTO> selectAll() {
        ArrayList<CustomerDTO> result = new ArrayList<CustomerDTO>();
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM person , customer where personID=customerID and person.Status=1 and customer.Status=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int customerId = rs.getInt("personID");
                String customerName = rs.getString("fullName");
                String customerGender=rs.getString("Gender");
       
                java.sql.Date customerDateOfBirth = rs.getDate("dateOfBirth");


                String customerEmail=rs.getString("Email");
                String customerPhoneNumber=rs.getString("phoneNumber");
                String customerAddress=rs.getString("Address");
                String customerComment=rs.getString("Comment");            		
//                PersonDTO PersonDTO=new PersonDTO(customerId,customerName,customerGender,customerDateOfBirth,customerEmail,
//                		customerPhoneNumber,customerAddress,customerComment);
             PersonDTO PersonDTO=new PersonDTO(customerId,customerName ,customerGender, customerDateOfBirth,  customerEmail, customerPhoneNumber, customerAddress, customerComment);  
                CustomerDTO lh = new CustomerDTO(customerId, PersonDTO);
                result.add(lh);
            }
            JDBCmySQL.closeConnection(con);
        } catch (Exception e) {
        	
        }
        return result;
    }

    @Override
    public CustomerDTO selectById(String t) {
       CustomerDTO result = null; 
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "select personID , fullName , Gender , dateOfBirth , Email , phoneNumber , Address , Comment from person , customer where personID=customerID and customerID=t";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
            	 int customerId = rs.getInt("personID");
                 String customerName = rs.getString("fullName");
                 String customerGender=rs.getString("Gender");
                 java.sql.Date customerDateOfBirth = rs.getDate("dateOfBirth");
                 String customerEmail=rs.getString("Email");
                 String customerPhoneNumber=rs.getString("phoneNumber");
                 String customerAddress=rs.getString("Address");
                 String customerComment=rs.getString("Comment"); 
                 PersonDTO PersonDTO=new PersonDTO(customerId,customerName ,customerGender, customerDateOfBirth,  customerEmail, customerPhoneNumber, customerAddress, customerComment);  
                 result=new CustomerDTO(customerId, PersonDTO);
            }
            JDBCmySQL.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
	@Override
	public ArrayList<CustomerDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getAutoIncrement() {
	    int result = -1;
	    try {
	        Connection con = (Connection) JDBCmySQL.getConnection();
	        String sql = "SELECT AUTO_INCREMENT \r\n"
	        		+ "	               FROM information_schema.tables \r\n"
	        		+ "	                WHERE table_schema = 'phone'\r\n"
	        		+ "	                AND table_name = 'customer';";
	        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rs2 = pst.executeQuery();

	        if (!rs2.isBeforeFirst()) {
	            System.out.println("No data for the specified table and schema.");
	        } else {
	            while (rs2.next()) {
	                result = rs2.getInt("AUTO_INCREMENT");
	                System.out.println("AUTO_INCREMENT value: " + result);
	            }
	        }
	        rs2.close();
	        pst.close();
	        con.close();
	    } catch (SQLException ex) {
	        Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return result;
	}

	@Override
	public ArrayList<CustomerDTO> selectAllDelete() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int restore(ProductDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ReceiptDetailDTO r, String pk) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void importDatabase(FileInputStream inputStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<CustomerDTO> selectAll(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(InvoiceDetailDTO t, String pk) {
		// TODO Auto-generated method stub
		return 0;
	}




	

}
