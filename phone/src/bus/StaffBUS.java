package bus;



import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import dao.PersonDAO;
import dao.StaffDAO;
import dto.PersonDTO;
import dto.StaffDTO;
import view.Staff;

public class StaffBUS {

	private final StaffDAO employeeDAO = new StaffDAO();
	private ArrayList<StaffDTO> listEmployee = new ArrayList<>();
	public Staff view;
	private final PersonDAO PersonDAO=new PersonDAO();
	public StaffBUS() {
		listEmployee = employeeDAO.selectAll();
	}
	
	
	public ArrayList<StaffDTO> getALL() {
		return this.listEmployee;
	}
	

	
	public  StaffBUS(Staff view) {
		this.view=view;
	
	}
	
	public int getIndexById(int customerId) {
		int i = 0;
		int locate = -1;
		while (i < listEmployee.size() && locate == -1) {
			if (listEmployee.get(i).getEmployee_ID() == customerId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
	
	public Boolean add(PersonDTO nguoi , String employee_type , Double salary , String password) {
		StaffDTO cate = new StaffDTO(nguoi,PersonDAO.getAutoIncrement(),employee_type,salary,password);
		boolean check = employeeDAO.insert(cate) != 0;
		if(check) {
			listEmployee.add(cate);
		}
	
		return check;
	}
	
	public Boolean delete(StaffDTO cate) {
        boolean check = employeeDAO.delete(Integer.toString(cate.getEmployee_ID())) != 0;
           if(check) {
        	      listEmployee.remove(cate);
           }
  
        return check;
    }



    public Boolean update(StaffDTO cate) {
        boolean check =employeeDAO.update(cate) != 0;
        if(check) {
            listEmployee.set(getIndexById(cate.getEmployee_ID()), cate);
        }
        return check;
    }
	
	
	
	   public ArrayList<StaffDTO> search(String text) {
	        text = text.toLowerCase();
	        ArrayList<StaffDTO> result = new ArrayList<>();
	        for (StaffDTO i : this.listEmployee) {
	            if (Integer.toString(i.getEmployee_ID()).toLowerCase().contains(text) || i.getperson().getName().toLowerCase().contains(text)){
	                result.add(i);
	            }
	        }
	        return result;
	    }
	
	    public boolean checkDup(String name) {
	        boolean check = true;
	        int i = 0;
	        while (i < this.listEmployee.size() && check == true) {
	            if (this.listEmployee.get(i).getperson().getName().toLowerCase().contains(name.toLowerCase())) {
	                check = false;
	            } else {
	                i++;
	            }
	        }
	        return check;
	    }
	    
	    public boolean checkDupPhone(String phone) {
	        boolean check = true;
	        int i = 0;
	        while (i < this.listEmployee.size() && check == true) {
	            if (listEmployee.get(i).getperson().getPhone().toLowerCase().contains(phone.toLowerCase())) {
	                check = false;
	            }else {
	            	i++;
	            }
	        }
	        return check;
	    }

	    public boolean checkDupEmail(String email) {
	        boolean check = true;
	        int i = 0;
	        while (i < this.listEmployee.size() && check == true) {
	            if (listEmployee.get(i).getperson().getEmail().toLowerCase().contains(email.toLowerCase())) {
	                check = false;
	            }else {
	            	i++;
	            }
	        }
	        return check;
	    }
	    
		public void importExcel(){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(null);
	   		int result = fileChooser.showOpenDialog(null);
	   		String url = fileChooser.getSelectedFile().getAbsolutePath();
			if(result == JFileChooser.APPROVE_OPTION) {
		    	try {
		    		FileInputStream inputStream = new FileInputStream(url);
					employeeDAO.importDatabase(inputStream);
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
}
