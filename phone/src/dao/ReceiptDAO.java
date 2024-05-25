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

import bus.CategoryBUS;
import database.JDBCmySQL;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;

public class ReceiptDAO implements DAOInterface<ReceiptDTO>{
	public static ReceiptDAO getInstance() {
		return new ReceiptDAO();
	}

	@Override
	public int insert(ReceiptDTO r) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
			String sql = "INSERT INTO `receipt`(`receiptId`, `supplierId`, `staffId`, `date`, `totalPrice`) VALUES (?,?,?,?,?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, r.getReceiptId());
			pst.setInt(2, r.getSupplierId());
			pst.setInt(3, r.getStaffId());
			pst.setTimestamp(4, r.getDate());
			pst.setDouble(5, r.getTotalPrice());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;	
	}

	@Override
	public int update(ReceiptDTO r) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
			String sql = "UPDATE `receipt` SET `supplierId`=?, `staffId`=?, `date`=?, `totalPrice`=? WHERE `receiptId`=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, r.getSupplierId());
			pst.setInt(2, r.getStaffId());
			pst.setTimestamp(3, r.getDate());
			pst.setDouble(4, r.getTotalPrice());
			pst.setInt(5, r.getReceiptId());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public int delete(String t) {
	    int result = 0;
	    try {
	        Connection con = (Connection) JDBCmySQL.getConnection();
	        String sql = "UPDATE `receipt` SET `status` = 0 WHERE `receiptId` = ?;";
	        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setString(1, t);
	        result = pst.executeUpdate();
	        JDBCmySQL.closeConnection(con);
	    } catch (SQLException ex) {
	        Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return result;
	}

	@Override
	public ArrayList<ReceiptDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptDTO> result = new ArrayList<ReceiptDTO>();		
		try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM receipt WHERE `status`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
            	int receiptId = rs.getInt("receiptId");
            	int supplierId = rs.getInt("supplierId");
            	int staffId = rs.getInt("staffId");
            	Timestamp purchasePrice = rs.getTimestamp("date");
            	double sellingPrice = rs.getDouble("totalPrice");
            	
            	ReceiptDTO receipt = new ReceiptDTO(receiptId, supplierId, staffId, purchasePrice, sellingPrice);
            	result.add(receipt);
            	
            }
		} catch (Exception e) {
			
		}
		return result;
	}

	@Override
	public ReceiptDTO selectById(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ReceiptDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
        int result = -1;
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'phone' AND   TABLE_NAME   = 'receipt'";
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
            Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;	
    }

	@Override
	public ArrayList<ReceiptDTO> selectAllDelete() {
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
	public ArrayList<ReceiptDTO> selectAll(String t) {
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
