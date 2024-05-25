package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.JDBCmySQL;
import dto.InvoiceDTO;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;

public class InvoiceDAO implements DAOInterface<InvoiceDTO>{
	public static InvoiceDAO getInstance() {
		return new InvoiceDAO();
	}
	@Override
	public int insert(InvoiceDTO t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
			String sql = "INSERT INTO `invoice`(`invoiceId`, `customerId`, `staffId`, `date`, `totalPrice`) VALUES (?,?,?,?,?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, t.getInvoiceId());
			pst.setInt(2, t.getCustomerId());
			pst.setInt(3, t.getStaffId());
			pst.setTimestamp(4, t.getDate());
			pst.setDouble(5, t.getTotalPrice());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;	
	}

	@Override
	public int update(InvoiceDTO t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
			String sql = "UPDATE `invoice` SET `customerId`=?, `staffId`=?, `date`=?, `totalPrice`=? WHERE `invoiceId`=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, t.getCustomerId());
			pst.setInt(2, t.getStaffId());
			pst.setTimestamp(3, t.getDate());
			pst.setDouble(4, t.getTotalPrice());
			pst.setInt(5, t.getInvoiceId());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public int delete(String t) {
		// TODO Auto-generated method stub
	    int result = 0;
	    try {
	        Connection con = (Connection) JDBCmySQL.getConnection();
	        String sql = "UPDATE `invoice` SET `status` = 0 WHERE `invoiceId` = ?;";
	        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setString(1, t);
	        result = pst.executeUpdate();
	        JDBCmySQL.closeConnection(con);
	    } catch (SQLException ex) {
	        Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return result;
	}

	@Override
	public ArrayList<InvoiceDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<InvoiceDTO> result = new ArrayList<InvoiceDTO>();		
		try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM invoice WHERE `status`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
            	int receiptId = rs.getInt("invoiceId");
            	int supplierId = rs.getInt("customerId");
            	int staffId = rs.getInt("staffId");
            	Timestamp purchasePrice = rs.getTimestamp("date");
            	double sellingPrice = rs.getDouble("totalPrice");
            	
            	InvoiceDTO invoice = new InvoiceDTO(receiptId, supplierId, staffId, purchasePrice, sellingPrice);
            	result.add(invoice);
            	
            }
		} catch (Exception e) {
			
		}
		return result;
	}

	@Override
	public InvoiceDTO selectById(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InvoiceDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
        int result = -1;
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'phone' AND   TABLE_NAME   = 'invoice'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;	
    }
	@Override
	public ArrayList<InvoiceDTO> selectAllDelete() {
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
	public ArrayList<InvoiceDTO> selectAll(String t) {
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
