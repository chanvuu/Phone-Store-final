package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bus.CategoryBUS;
import database.JDBCmySQL;
import dto.CategoryDTO;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDetailDTO;

public class ProductDAO implements DAOInterface<ProductDTO>{
	public static ProductDAO getInstance() {
		return new ProductDAO();
	}
	@Override
	public int insert(ProductDTO t) {
//		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
			String sql = "INSERT INTO `product`(`productId`, `productName`, `categoryId`, `purchasePrice`, `sellingPrice`, `quantity`, `imgURL`, `description`) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, t.getProductId());
			pst.setString(2, t.getProductName());
			pst.setInt(3, t.getCategoryId());
			pst.setDouble(4, t.getPurchasePrice());
			pst.setDouble(5, t.getSellingPrice());
			pst.setInt(6, t.getQuantity());
			pst.setString(7, t.getImgURL());
			pst.setString(8, t.getDescription());			
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;		
	}

	@Override
	public int update(ProductDTO t) {
	    int result = 0;
	    try {
	        Connection con = JDBCmySQL.getConnection();
	        String sql = "UPDATE `product` SET `productName`=?, `categoryId`=?, `purchasePrice`=?, `sellingPrice`=?, `quantity`=?, `imgURL`=?, `description`=? WHERE `productId`=?";
	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            pst.setString(1, t.getProductName());
	            pst.setInt(2, t.getCategoryId());
	            pst.setDouble(3, t.getPurchasePrice());
	            pst.setDouble(4, t.getSellingPrice());
	            pst.setInt(5, t.getQuantity());
	            pst.setString(6, t.getImgURL());
	            pst.setString(7, t.getDescription());
	            pst.setInt(8, t.getProductId());
	            
	            result = pst.executeUpdate();
	        }
	        JDBCmySQL.closeConnection(con);
	    } catch (SQLException ex) {
	        // Log or print the exception details
	        ex.printStackTrace();
	    }
	    return result;
	}


	@Override
	public int delete(String t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "UPDATE `product` SET `status` = 0 WHERE `productId`= ?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1, t);		
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public ArrayList<ProductDTO> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();		
		try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM product WHERE `status`=1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
            	int productId = rs.getInt("productId");
            	String productName = rs.getString("productName");
            	int categoryId = rs.getInt("categoryId");
            	double purchasePrice = rs.getDouble("purchasePrice");
            	double sellingPrice = rs.getDouble("sellingPrice");
            	int quantity = rs.getInt("quantity");
            	String imgURL = rs.getString("imgURL");
            	String description = rs.getString("description");
            	
            	
            	ProductDTO product = new ProductDTO(productId, productName, categoryId, purchasePrice, sellingPrice, quantity, imgURL, description);
            	result.add(product);
            	
            }
		} catch (Exception e) {
			
		}
		return result;
	}

	@Override
	public ProductDTO selectById(String t) {
	    ProductDTO product = new ProductDTO();
	    try {
	        Connection con = JDBCmySQL.getConnection();
	        String sql = "SELECT * FROM product WHERE `productId`=?";
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setInt(1, Integer.parseInt(t)); // Thiết lập giá trị cho tham số productId
	        ResultSet rs = pst.executeQuery();
	        
	        while (rs.next()) {
	            int productId = rs.getInt("productId");
	            String productName = rs.getString("productName");
	            int categoryId = rs.getInt("categoryId");
	            double purchasePrice = rs.getDouble("purchasePrice");
	            double sellingPrice = rs.getDouble("sellingPrice");
	            int quantity = rs.getInt("quantity");
	            String imgURL = rs.getString("imgURL");
	            String description = rs.getString("description");

	            product = new ProductDTO(productId, productName, categoryId, purchasePrice, sellingPrice, quantity, imgURL, description);
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    return product;
	}


	@Override
	public ArrayList<ProductDTO> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAutoIncrement() {
		// TODO Auto-generated method stub
        int result = -1;
        try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'phone' AND   TABLE_NAME   = 'product'";
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
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
	}
	
	@Override
	public ArrayList<ProductDTO> selectAllDelete() {
		// TODO Auto-generated method stub
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();		
		try {
            Connection con = (Connection) JDBCmySQL.getConnection();
            String sql = "SELECT * FROM product WHERE `status`=0";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()) {
            	int productId = rs.getInt("productId");
            	String productName = rs.getString("productName");
            	int categoryId = rs.getInt("categoryId");
            	double purchasePrice = rs.getDouble("purchasePrice");
            	double sellingPrice = rs.getDouble("sellingPrice");
            	int quantity = rs.getInt("quantity");
            	String imgURL = rs.getString("imgURL");
            	String description = rs.getString("description");
            	
            	
            	ProductDTO product = new ProductDTO(productId, productName, categoryId, purchasePrice, sellingPrice, quantity, imgURL, description);
            	result.add(product);
            	
            }
		} catch (Exception e) {
			
		}
		return result;
	}
	
	@Override
	public int restore(ProductDTO t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection con = (Connection) JDBCmySQL.getConnection();
			String sql = "UPDATE `product` SET `status`=? WHERE `productId`=?";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setInt(1, 1);		
            pst.setInt(2, t.getProductId());
			result = pst.executeUpdate();
			JDBCmySQL.closeConnection(con);
		} catch(SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;	
	}
	@Override
	public int update(ReceiptDetailDTO r, String pk) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ArrayList<ProductDTO> selectAll(String t) {
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
		int batchSize = 20;
		try {
			
			Connection con = JDBCmySQL.getConnection();
			
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = firstSheet.iterator();
			 

			String sql = "INSERT INTO `Product`(`productName` , `categoryId` , `purchasePrice` , `sellingPrice` , `quantity`, `image`, `description`) "
					+ "VALUES (?,?,?,?,?,?,?)";
			PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);

			
			int count = 0;
			
			rowIterator.next(); // skip the header row
			
			while(rowIterator.hasNext()) {
				org.apache.poi.ss.usermodel.Row nextRow =  rowIterator.next();
                Iterator<Cell> cellIterator = ((org.apache.poi.ss.usermodel.Row) nextRow).cellIterator();
 
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    
                    switch (columnIndex) {
                    case 1:
                        String productName = cell.getStringCellValue();
                        pst.setString(1, productName);
                    case 2:
                    	CategoryBUS categoryBUS = new CategoryBUS();
                    	ArrayList<CategoryDTO> listCate = categoryBUS.getALL();
                        String categoryId = cell.getStringCellValue();
                        System.out.println(categoryId);
                        pst.setString(2, listCate.get(categoryBUS.getIndexById(Integer.parseInt(categoryId))).getCategoryName());
                    case 3:
                        Double purchasePrice = cell.getNumericCellValue();
                        pst.setDouble(3, purchasePrice);
                    case 4:
                    	Double sellingPrice = cell.getNumericCellValue();
                        pst.setDouble(4, sellingPrice);
                    case 5:
                    	 int quantity = (int) cell.getNumericCellValue();
                         pst.setInt(5, quantity);  
                    case 6:
                    	String image = null;
                    	pst.setString(6, image);
                    case 7:
                        String description = cell.getStringCellValue();
                        pst.setString(7, description);
                    }
                }
                pst.addBatch();
                
                if (++count % batchSize == 0) {
                	pst.executeBatch();
                }   
			}
			workbook.close();
            // execute the remaining queries
			pst.executeBatch();
			
			JDBCmySQL.closeConnection(con);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong doc duoc file");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Loi du lieu");
			e.printStackTrace();
		}
	}

}
