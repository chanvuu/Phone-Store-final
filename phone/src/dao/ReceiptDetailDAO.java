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
import dto.ProductDTO;
import dto.ReceiptDetailDTO;

public class ReceiptDetailDAO implements DAOInterface<ReceiptDetailDTO>{
	public static ReceiptDetailDAO getInstance() {
		return new ReceiptDetailDAO();
	}
	
	@Override
	public int insert(ReceiptDetailDTO r) {
	    int result = 0;
	    try {
	        Connection con = (Connection) JDBCmySQL.getConnection();
	        String sql = "INSERT INTO `receiptdetail`(`receiptId`, `productId`, `quantity`, `purchasePrice`) VALUES (?,?,?,?)";
	        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, r.getReceiptId());
	        pst.setInt(2, r.getProductId());
	        pst.setInt(3, r.getQuantity());
	        pst.setDouble(4, r.getPurchasePrice());
	        result = pst.executeUpdate();
	        JDBCmySQL.closeConnection(con);
	    } catch(SQLException ex) {
	        Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return result;    
	}

	@Override
	public int update(ReceiptDetailDTO r, String pk) {
		// TODO Auto-generated method stub
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(r);
        }
        return result;
	}

	@Override
	public int delete(String t) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "UPDATE `receipt` SET `status` = 0 WHERE `receiptId`= ?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1, t);		
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public ArrayList<ReceiptDetailDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<ReceiptDetailDTO> result = new ArrayList<ReceiptDetailDTO>();		
		try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM receiptdetail WHERE `status`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
            	int receiptId = rs.getInt("receiptId");
            	int productId = rs.getInt("productId");
            	double purchasePrice = rs.getDouble("purchasePrice");
            	int quantity = rs.getInt("quantity");
            	
            	ReceiptDetailDTO receiptDetail = new ReceiptDetailDTO(receiptId, productId, quantity, purchasePrice);
            	result.add(receiptDetail);
            }
		} catch (Exception e) {
			
		}
		return result;	
	}
	
    @Override
    public ArrayList<ReceiptDetailDTO> selectAll(String t) {
        ArrayList<ReceiptDetailDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM receiptdetail WHERE receiptId = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
            	int receiptId = rs.getInt("receiptId");
            	int productId = rs.getInt("productId");
            	double purchasePrice = rs.getDouble("purchasePrice");
            	int quantity = rs.getInt("quantity");
            	ReceiptDetailDTO receiptDetail = new ReceiptDetailDTO(receiptId, productId, quantity, purchasePrice);
                result.add(receiptDetail);
            }
			JDBCmySQL.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
	@Override
	public ReceiptDetailDTO selectById(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ReceiptDetailDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<ReceiptDetailDTO> selectAllDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int restore(ProductDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ReceiptDetailDTO t) {
		// TODO Auto-generated method stub
		return 0;
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
