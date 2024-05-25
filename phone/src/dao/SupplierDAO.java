package dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.JDBCmySQL;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDetailDTO;
import dto.SupplierDTO;
import service.Validation;
import view.Supplier;

public class SupplierDAO implements DAOInterface<SupplierDTO>{
	
	public static SupplierDAO getInstance() {
		return new SupplierDAO();
	}
	

	@Override
	public int insert(SupplierDTO t) {
		int result = 0;
		try {
			Connection con = JDBCmySQL.getConnection();
			String sql = "INSERT INTO `supplier`(`supplierName` , `supplierPhone` , `supplierAddress` , `supplierEmail` , `supplierNote`) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1, t.getSupplierName());
			pst.setString(2, t.getSupplierPhone());
			pst.setString(3, t.getSupplierAddress());
			pst.setString(4, t.getSupplierEmail());
			pst.setString(5, t.getSupplierNote());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		return result;
	}

	@Override
	public int update(SupplierDTO t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = JDBCmySQL.getConnection();
            String sql = "UPDATE `supplier` SET `supplierName`=? , `supplierPhone`=? , `supplierAddress`=? , `supplierEmail`=? , `supplierNote`=?"
            		+ " WHERE `supplierId`=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t.getSupplierName());
			pst.setString(2, t.getSupplierPhone());
			pst.setString(3, t.getSupplierAddress());
			pst.setString(4, t.getSupplierEmail());
			pst.setString(5, t.getSupplierNote());
			pst.setInt(6, t.getSupplierId());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		}catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		return result;
	}

	@Override
	public int delete(String t) {
		  int result = 0 ;
	        try {
	            Connection con = JDBCmySQL.getConnection();
	            String sql = "UPDATE `supplier` SET `status` = 0 WHERE `supplierId`= ?";
	            PreparedStatement pst = con.prepareStatement(sql);
	            pst.setString(1, t);
	            result = pst.executeUpdate();
	            JDBCmySQL.closeConnection(con);
	        } catch (SQLException ex) {
	            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return result;
	}

	@Override
	public ArrayList<SupplierDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<SupplierDTO> result = new ArrayList<SupplierDTO>();
        try {
            Connection con = JDBCmySQL.getConnection();
            String sql = "SELECT * FROM supplier WHERE `status`=1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int supplierId = rs.getInt("supplierId");
                String supplierName = rs.getString("supplierName");
                String supplierPhone = rs.getString("supplierPhone");
                String supplierAddress = rs.getString("supplierAddress");
                String supplierEmail = rs.getString("supplierEmail");
                String supplierNote = rs.getString("supplierNote");
                SupplierDTO lh = new SupplierDTO(supplierId, supplierName, supplierPhone, supplierAddress, supplierEmail, supplierNote);
                result.add(lh);
            }
            JDBCmySQL.closeConnection(con);
        } catch (SQLException ex) {
           
        }
        return result;
	}

	@Override
	public SupplierDTO selectById(String t) {
		// TODO Auto-generated method stub
		SupplierDTO result = null;
		try {
			Connection con = JDBCmySQL.getConnection();
			String sql = "SELECT * FROM supplier WHERE supplierId=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, t);
			ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int supplierId = rs.getInt("supplierId");
                String supplierName = rs.getString("supplierName");
                String supplierPhone = rs.getString("supplierPhone");
                String supplierAddress = rs.getString("supplierAddress");
                String supplierEmail = rs.getString("supplierEmail");
                String supplierNote = rs.getString("supplierNote");
                result = new SupplierDTO(supplierId, supplierName, supplierPhone, supplierAddress, supplierEmail, supplierNote);
            }
            JDBCmySQL.closeConnection(con);
		} catch (SQLException ex) {
            
        }
		return null;
	}

	@Override
	public ArrayList<SupplierDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			Connection con = JDBCmySQL.getConnection();
			String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'phone' AND   TABLE_NAME   = 'supplier'";
			PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if(!rs2.isBeforeFirst()) {
            	System.out.println("No data");
            } else {
            	  while ( rs2.next() ) {
                      result = rs2.getInt("AUTO_INCREMENT");     
                  }
            }
		} catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
		return result;
	}


	@Override
	public ArrayList<SupplierDTO> selectAllDelete() {
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
	public ArrayList<SupplierDTO> selectAll(String t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int update(InvoiceDetailDTO t, String pk) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void importDatabase(FileInputStream inputStream){
		try {
			
			Connection con = JDBCmySQL.getConnection();
			
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = firstSheet.iterator();
			
			rowIterator.next(); // skip the header row
			
			while(rowIterator.hasNext()) {
				org.apache.poi.ss.usermodel.Row nextRow =  rowIterator.next();
                Iterator<Cell> cellIterator = ((org.apache.poi.ss.usermodel.Row) nextRow).cellIterator();
                SupplierDTO s = new SupplierDTO();
                try {
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
	                    int rowIndex = cell.getRowIndex();
	                    if(rowIndex == 0) {
	                    	break;
	                    }
	                    int columnIndex = cell.getColumnIndex();
	                    
	                    switch (columnIndex) {
	                    case 1:
	                    	String name = cell.getStringCellValue();
	                        s.setSupplierName(name);
	                    case 2:
	                    	String phone = cell.getStringCellValue();
	                    	if(Validation.isValidMobileNo(phone)) 
	                    		s.setSupplierPhone(phone);
	                    	break;
	                    case 3:
	                    	String address = cell.getStringCellValue();
	                        s.setSupplierAddress(address);

	                    case 4:
	                    	String email = cell.getStringCellValue();
	                    	if(Validation.isEmail(email)) {
	                    		s.setSupplierEmail(email);
	                    		
	                    	}
	                    	System.out.println(cell);
	                        System.out.println(s.getSupplierEmail());
	                        break;
	                    case 5:
	                    	 s.setSupplierNote(cell.getStringCellValue());
	                    	 break;
	                    }
	                }
	            }catch (Exception e) {
	            	System.out.println("Lỗi định dạng thông tin trong file excel!");
	            	e.printStackTrace();
	            }
                if(s.getSupplierName() != null && s.getSupplierPhone() != null && s.getSupplierEmail() != null){
                    try {
                    	String sql = "INSERT INTO `supplier`(`supplierName` , `supplierPhone` , `supplierAddress` , `supplierEmail` , `supplierNote`) "
            					+ "VALUES (?,?,?,?,?)";
            			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                    	 pst.setString(1, s.getSupplierName());
                    	 pst.setString(2, s.getSupplierPhone());
                    	 pst.setString(3, s.getSupplierAddress());
                    	 pst.setString(4, s.getSupplierEmail());
                         pst.setString(5, s.getSupplierNote());  
                         int result = pst.executeUpdate();
                    } catch (SQLException e) {
                    	System.out.println("Lỗi dữ liệu");
                    }
                }
			}
			workbook.close();
			
			JDBCmySQL.closeConnection(con);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong doc duoc file");
			e.printStackTrace();
		}
	}



}
