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

public class InvoiceDetailDAO implements DAOInterface<InvoiceDetailDTO>{
	public static InvoiceDetailDAO getInstance() {
		return new InvoiceDetailDAO();
	}

	@Override
	public int insert(InvoiceDetailDTO t) {
		// TODO Auto-generated method stub
	    int result = 0;
	    try {
	        Connection con = (Connection) JDBCmySQL.getConnection();
	        String sql = "INSERT INTO `invoicedetail`(`invoiceId`, `productId`, `quantity`, `sellingPrice`) VALUES (?,?,?,?)";
	        PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
	        pst.setInt(1, t.getInvoiceId());
	        pst.setInt(2, t.getProductId());
	        pst.setInt(3, t.getQuantity());
	        pst.setDouble(4, t.getSellingPrice());
	        result = pst.executeUpdate();
	        JDBCmySQL.closeConnection(con);
	    } catch(SQLException ex) {
	        Logger.getLogger(ReceiptDAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    return result;   
	}

	@Override
	public int update(InvoiceDetailDTO t, String pk) {
		// TODO Auto-generated method stub
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
	}

	@Override
	public int delete(String t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "UPDATE `invoicedetail` SET `status` = 0 WHERE `invoiceId`= ?";
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
	public ArrayList<InvoiceDetailDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<InvoiceDetailDTO> result = new ArrayList<InvoiceDetailDTO>();		
		try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM invoicedetail WHERE `status`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
            	int invoiceId = rs.getInt("invoiceId");
            	int productId = rs.getInt("productId");
            	double sellingPrice = rs.getDouble("sellingPrice");
            	int quantity = rs.getInt("quantity");
            	
            	InvoiceDetailDTO invoiceDetail = new InvoiceDetailDTO(invoiceId, productId, quantity, sellingPrice);
            	result.add(invoiceDetail);
            }
		} catch (Exception e) {
			
		}
		return result;
	}

	@Override
	public InvoiceDetailDTO selectById(String t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<InvoiceDetailDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<InvoiceDetailDTO> selectAllDelete() {
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
    public ArrayList<InvoiceDetailDTO> selectAll(String t) {
        ArrayList<InvoiceDetailDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM invoicedetail WHERE invoiceId = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
            	int invoiceId = rs.getInt("invoiceId");
            	int productId = rs.getInt("productId");
            	double sellingPrice = rs.getDouble("sellingPrice");
            	int quantity = rs.getInt("quantity");
            	InvoiceDetailDTO invoiceDetail = new InvoiceDetailDTO(invoiceId, productId, quantity, sellingPrice);
                result.add(invoiceDetail);
            }
			JDBCmySQL.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

	@Override
	public int update(InvoiceDetailDTO t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void importDatabase(FileInputStream inputStream) {
		// TODO Auto-generated method stub
		
	}



}
