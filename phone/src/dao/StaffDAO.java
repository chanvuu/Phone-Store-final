package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import database.JDBCmySQL;
//import dto.InvoiceDetailDTO;
import dto.PersonDTO;
import dto.ProductDTO;
import dto.ReceiptDetailDTO;
import dto.StaffDTO;
import dto.SupplierDTO;
import service.Formater;
import service.Validation;

public class StaffDAO implements DAOInterface<StaffDTO>{
	
	
	public static StaffDAO getInstance() {
		return new StaffDAO();
	}
	
	@Override
	public int insert(StaffDTO t) {
	    int result = 0;
	    try (Connection connection = JDBCmySQL.getConnection()) {
	        String sqlPerson = "INSERT INTO person (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Comment) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        String sqlEmployee = "INSERT INTO employee (employeeID, Position, Salary, Password) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement personStatement = connection.prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
	             PreparedStatement employeeStatement = connection.prepareStatement(sqlEmployee)) {

	            personStatement.setString(1, t.getperson().getName());
	            personStatement.setString(2, t.getperson().getGender());

	            java.util.Date utilDate = t.getperson().getBirthDay();
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	            personStatement.setDate(3, sqlDate);
	            personStatement.setString(4, t.getperson().getEmail());
	            personStatement.setString(5, t.getperson().getPhone());
	            personStatement.setString(6, t.getperson().getAddress());
	            personStatement.setString(7, t.getperson().getComment());

	            // Thực hiện câu lệnh cho bảng person
	            result = personStatement.executeUpdate();

	            // Lấy PersonID sử dụng truy vấn riêng
	            int personID;
	            try (ResultSet generatedKeys = personStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    personID = generatedKeys.getInt(1);

	                    // Đặt PersonID vào employeeStatement
	                    employeeStatement.setInt(1, personID);
	                    employeeStatement.setString(2, t.getrole());
	                    employeeStatement.setDouble(3, t.getsalary());
	                    employeeStatement.setString(4, t.getPassword());

	                    // Thực hiện câu lệnh cho bảng employee
	                    result = employeeStatement.executeUpdate();
	                } else {
	                    throw new SQLException("Không thể lấy được PersonID.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	    }

	    return result;
	}







	@Override
	public int update(StaffDTO t) {
		int result=0;
	    try {
	        Connection connection = JDBCmySQL.getConnection();
	        String sql = "UPDATE employee , person SET fullName=?, Gender=?, dateOfBirth=?, Email=?, phoneNumber=?, Address=?, Comment=?, Position=?, Salary=? WHERE EmployeeID=?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, t.getperson().getName());
	            preparedStatement.setString(2, t.getperson().getGender());
	            java.util.Date utilDate = t.getperson().getBirthDay();
	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	             preparedStatement.setDate(3, sqlDate);
	            preparedStatement.setString(4, t.getperson().getEmail());
	            preparedStatement.setString(5, t.getperson().getPhone());
	            preparedStatement.setString(6, t.getperson().getAddress());
	            preparedStatement.setString(7, t.getperson().getComment());
	            preparedStatement.setString(8, t.getrole());
	            preparedStatement.setDouble(9, t.getsalary());
	            preparedStatement.setInt(10, t.getEmployee_ID());
	            result = preparedStatement.executeUpdate();
	            JDBCmySQL.closeConnection(connection);

	            return result;
	        }
	    } catch (Exception e) {
	    	Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	    }

	    return result;
	}


	@Override
	public int delete(String t) {
	    int result = 0;

	    try {
	        Connection con = JDBCmySQL.getConnection();

	        // Using PreparedStatement to avoid SQL injection
	        String sql = "UPDATE employee, person\r\n"
	        		+ "SET employee.Status = 0, person.Status = 0\r\n"
	        		+ "WHERE employee.EmployeeID = ? AND person.personID = ?;\r\n"
	        		+ "";
	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, t);
	            preparedStatement.setString(2, t);
	            result = preparedStatement.executeUpdate();
	            JDBCmySQL.closeConnection(con);
	        }
	      
	    } catch (Exception e) {
	    	Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	    }

	    return result;
	}

	@Override
	public ArrayList<StaffDTO> selectAll() {
	    ArrayList<StaffDTO> result = new ArrayList<>();
	    try {
	        Connection con = JDBCmySQL.getConnection();

	        // Use a PreparedStatement to avoid SQL injection
	        String sql = "SELECT person.personID, fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Comment, EmployeeID, Position, Salary, Password FROM employee, person WHERE employee.EmployeeID=person.personID and "
	        		+ "employee.Status=1 and person.Status=1";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int employeeID = rs.getInt("EmployeeID");
	                String position = rs.getString("Position");
	                double salary = rs.getDouble("Salary");
	                String employeePassword = rs.getString("Password");

	                String employeeName = rs.getString("fullName");
	                String employeeGender = rs.getString("Gender");
	                java.sql.Date customerDateOfBirth = rs.getDate("dateOfBirth");
	                String employeeEmail = rs.getString("Email");
	                String employeePhoneNumber = rs.getString("phoneNumber");
	                String employeeAddress = rs.getString("Address");
	                String employeeComment = rs.getString("Comment");

	                PersonDTO nguoi = new PersonDTO(employeeID, employeeName, employeeGender, customerDateOfBirth, employeeEmail, employeePhoneNumber, employeeAddress, employeeComment);
	                StaffDTO staffDTO = new StaffDTO(nguoi, employeeID, position, salary, employeePassword);
	                result.add(staffDTO);
	            }
	        } catch (SQLException e) {
	        	Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	        }

	        JDBCmySQL.closeConnection(con);
	    } catch (Exception e) {
	    	Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	    }

	    return result;
	}

	@Override
	public ArrayList<StaffDTO> selectByCondition(String condition) {
		return null;
	}

	@Override
	public StaffDTO selectById(String condition) {
	   StaffDTO result = null;
	    try {
	        Connection con = JDBCmySQL.getConnection();

	        // Use a PreparedStatement to avoid SQL injection
	        String sql = "SELECT personID, fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Comment, EmployeeID, Position, Salary, Password FROM employee, person WHERE EmployeeID=personID";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int employeeID = rs.getInt("EmployeeID");
	                String position = rs.getString("Position");
	                double salary = rs.getDouble("Salary");
	                String employeePassword = rs.getString("Password");

	                String employeeName = rs.getString("fullName");
	                String employeeGender = rs.getString("Gender");
	                java.sql.Date customerDateOfBirth = rs.getDate("dateOfBirth");
	                String employeeEmail = rs.getString("Email");
	                String employeePhoneNumber = rs.getString("phoneNumber");
	                String employeeAddress = rs.getString("Address");
	                String employeeComment = rs.getString("Comment");

	                PersonDTO nguoi = new PersonDTO(employeeID, employeeName, employeeGender, customerDateOfBirth, employeeEmail, employeePhoneNumber, employeeAddress, employeeComment);
	               result = new StaffDTO(nguoi, employeeID, position, salary, employeePassword);
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        JDBCmySQL.closeConnection(con);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}


	@Override
	public int getAutoIncrement() {
		  int result = -1;
		    try {
		        Connection con = JDBCmySQL.getConnection();
		        String sql = "SELECT AUTO_INCREMENT \r\n"
		        		+ "	               FROM information_schema.tables \r\n"
		        		+ "	                WHERE table_schema = 'phone'\r\n"
		        		+ "	                AND table_name = 'employee';";
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
	public ArrayList<StaffDTO> selectAllDelete() {
	    ArrayList<StaffDTO> result = new ArrayList<>();
	    try {
	        Connection con = JDBCmySQL.getConnection();

	        // Use a PreparedStatement to avoid SQL injection
	        String sql = "SELECT person.personID, fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Comment, EmployeeID, Position, Salary, Password FROM employee, person WHERE employee.EmployeeID=person.personID and "
	        		+ "employee.Status=1 and person.Status=1";
	        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	            ResultSet rs = pstmt.executeQuery();

	            while (rs.next()) {
	                int employeeID = rs.getInt("EmployeeID");
	                String position = rs.getString("Position");
	                double salary = rs.getDouble("Salary");
	                String employeePassword = rs.getString("Password");

	                String employeeName = rs.getString("fullName");
	                String employeeGender = rs.getString("Gender");
	                java.sql.Date customerDateOfBirth = rs.getDate("dateOfBirth");
	                String employeeEmail = rs.getString("Email");
	                String employeePhoneNumber = rs.getString("phoneNumber");
	                String employeeAddress = rs.getString("Address");
	                String employeeComment = rs.getString("Comment");

	                PersonDTO nguoi = new PersonDTO(employeeID, employeeName, employeeGender, customerDateOfBirth, employeeEmail, employeePhoneNumber, employeeAddress, employeeComment);
	                StaffDTO staffDTO = new StaffDTO(nguoi, employeeID, position, salary, employeePassword);
	                result.add(staffDTO);
	            }
	        } catch (SQLException e) {
	        	Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	        }

	        JDBCmySQL.closeConnection(con);
	    } catch (Exception e) {
	    	Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
	    }

	    return result;
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

//	@Override
//	public ArrayList<StaffDTO> selectAll(String t) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int update(InvoiceDetailDTO t, String pk) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public void importDatabase(FileInputStream inputStream){
		// TODO Auto-generated method stub
		try {
			
			Connection con = JDBCmySQL.getConnection();
			
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = firstSheet.iterator();
			
			rowIterator.next(); // skip the header row
			
			while(rowIterator.hasNext()) {
				org.apache.poi.ss.usermodel.Row nextRow =  rowIterator.next();
                Iterator<Cell> cellIterator = ((org.apache.poi.ss.usermodel.Row) nextRow).cellIterator();
                StaffDTO s = new StaffDTO();
                PersonDTO p = new PersonDTO();
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
		                        p.setName(name);	  
	                            s.setperson(p);	
	                            break;
		                    case 2:
		                    	String gender = cell.getStringCellValue();
		                    	p.setGender(gender);
		                    	s.setperson(p);;
		                    	break;
		                    case 3:
		                        CellType cellType1 = cell.getCellType();

		                        if (cellType1 == CellType.NUMERIC) {
		                            Date birthDay = cell.getDateCellValue();
		                            p.setBirthDay(birthDay);
		                        } else if (cellType1 == CellType.STRING) {
		                            // Xử lý nếu ô chứa dữ liệu chuỗi
		                            String dateString = cell.getStringCellValue();
		                            // Chuyển đổi chuỗi ngày thành đối tượng Date
		                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		                            try {
		                                Date birthDay = dateFormat.parse(dateString);
		                                p.setBirthDay(birthDay);
		                                s.setperson(p);
		                            } catch (ParseException e) {
		                                // Xử lý lỗi chuyển đổi ngày
		                                e.printStackTrace();
		                            }
		                        } else {
		                            // Xử lý trường hợp khác nếu cần
		                        }
		                        break;
		                    case 4:
		                    	String email = cell.getStringCellValue();              	
		                   		p.setEmail(email);
		                   		s.setperson(p);
		                        break;
		                    case 5:
		                    	String phone = cell.getStringCellValue();
		                    	 p.setPhone(phone);
		                    	 s.setperson(p);
		                    	 break;
		                    case 6:
		                    	String address = cell.getStringCellValue();
		                    	 p.setAddress(address);
		                    	 s.setperson(p);
		                    	 break;
		                    case 7:
		                        CellType cellType2 = cell.getCellType();

		                        if (cellType2 == CellType.NUMERIC) {
		                            double salary = cell.getNumericCellValue();
		                            Formater.FormatMoney(salary);
		                            s.setsalary(salary);
		                            System.out.println(salary+ "If");
		                        } else if (cellType2 == CellType.STRING) {
		                            // Xử lý nếu ô chứa dữ liệu chuỗi
		                            String salaryString = cell.getStringCellValue();

		         
		                             System.out.println(salaryString+ "else If");      
		                            // Remove non-numeric characters, assuming 'đ' is the currency symbol
		                            salaryString = salaryString.replaceAll("[^\\d.]", "");
		                            salaryString = Formater.FormatMoney(Double.parseDouble(salaryString.replaceAll("\\.", ""))); 
		                            	System.out.println(salaryString);
		                            try {
		                                double salary = Double.parseDouble(salaryString);
		                         
		                                s.setsalary(salary);
		                            } catch (NumberFormatException e) {
		                                // Xử lý lỗi nếu không thể chuyển đổi thành số
		                                e.printStackTrace();
		                            }
		                        } else {
		                            // Xử lý trường hợp khác nếu cần
		                        }
		                        break;

		                    case 8:
		                    	String password = cell.getStringCellValue();
		                    	 s.setPassword(password);
		                    	 break;
		                    case 9:
		                    	String comment = cell.getStringCellValue();
		                    	 p.setComment(comment);
		                    	 s.setperson(p);
		                    	 break;
	                    	}
	                }
	            }catch (Exception e) {
	            	System.out.println("Lỗi định dạng thông tin trong file excel!");
	            	e.printStackTrace();
	            }
                if(p.getAddress() != null || p.getBirthDay() != null|| p.getEmail() != null || p.getGender() != null || p.getName()  != null|| p.getPhone() != null){
                	String sqlPerson = "INSERT INTO person (fullName, Gender, dateOfBirth, Email, phoneNumber, Address, Comment) VALUES (?, ?, ?, ?, ?, ?, ?)";
                	String sqlEmployee = "INSERT INTO employee (employeeID, Position, Salary, Password) VALUES (?, ?, ?, ?)";
                	int result;
                	System.out.println(s.getperson().getName());
                	Connection connection = JDBCmySQL.getConnection();
                	try (PreparedStatement personStatement = connection.prepareStatement(sqlPerson, Statement.RETURN_GENERATED_KEYS);
           	             PreparedStatement employeeStatement = connection.prepareStatement(sqlEmployee)) {

           	            personStatement.setString(1, s.getperson().getName());
           	            personStatement.setString(2, s.getperson().getGender());

           	            java.util.Date utilDate = s.getperson().getBirthDay();
           	            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           	            personStatement.setDate(3, sqlDate);
           	            personStatement.setString(4, s.getperson().getEmail());
           	            personStatement.setString(5, s.getperson().getPhone());
           	            personStatement.setString(6, s.getperson().getAddress());
           	            personStatement.setString(7, s.getperson().getComment());

           	            // Thực hiện câu lệnh cho bảng person
           	            result = personStatement.executeUpdate();

           	            // Lấy PersonID sử dụng truy vấn riêng
           	            int personID;
           	            try (ResultSet generatedKeys = personStatement.getGeneratedKeys()) {
           	                if (generatedKeys.next()) {
           	                    personID = generatedKeys.getInt(1);

           	                    // Đặt PersonID vào employeeStatement
           	                    employeeStatement.setInt(1, personID);
           	                    employeeStatement.setString(2, s.getrole());
           	                    employeeStatement.setDouble(3, s.getsalary());
           	                    employeeStatement.setString(4, s.getPassword());

           	                    // Thực hiện câu lệnh cho bảng employee
           	                    result = employeeStatement.executeUpdate();
           	                } else {
           	                    throw new SQLException("Không thể lấy được PersonID.");
           	                }
           	            }
                }catch (SQLException e) {
        	        Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, e);
        	    }
			}
			workbook.close();
			
			JDBCmySQL.closeConnection(con);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Khong doc duoc file");
			e.printStackTrace();
		}
	}
}
