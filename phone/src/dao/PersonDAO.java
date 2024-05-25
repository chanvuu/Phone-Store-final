package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.JDBCmySQL;
import dto.InvoiceDetailDTO;
import dto.PersonDTO;
import dto.ProductDTO;
import dto.ReceiptDetailDTO;

public class PersonDAO implements DAOInterface<PersonDTO>{

	public static PersonDAO getInstance() {
		return new PersonDAO();
	}

	@Override
	public int insert(PersonDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(PersonDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<PersonDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDTO selectById(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PersonDTO> selectByCondition(String condition) {
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
	        		+ "	                AND table_name = 'person';";
	        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	        ResultSet rs2 = pst.executeQuery();

	        if (!rs2.isBeforeFirst()) {
	            System.out.println("No data for the specified table and schema.");
	        } else {
	            while (rs2.next()) {
	                result = rs2.getInt("AUTO_INCREMENT");
	            }
	        }
	    } catch (SQLException ex) {
	        Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return result;
	}

	@Override
	public ArrayList<PersonDTO> selectAllDelete() {
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
	public ArrayList<PersonDTO> selectAll(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(InvoiceDetailDTO t, String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void importDatabase(FileInputStream inputStream) {
		// TODO Auto-generated method stub
		
	}
	

}
