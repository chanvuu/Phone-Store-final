package bus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import dao.CustomerDAO;
import dto.CustomerDTO;
import dto.PersonDTO;
import view.Customer;

public class CustomerBUS {
	private CustomerDAO cusDAO = new CustomerDAO();
	private static ArrayList<CustomerDTO> listCus = new ArrayList<>();
	public Customer view;
	
	public CustomerBUS() {
		listCus = cusDAO.selectAll();
	
	}
	
	public CustomerBUS(Customer view) {
		this.view=view;
	
	}
	
	
	public ArrayList<CustomerDTO> getALL() {
		return CustomerBUS.listCus;
	}
	
	public CustomerDTO getByIndex(int index) {
		return CustomerBUS.listCus.get(index);
	}
	
	public int getIndexById(int customerId) {
		int i = 0;
		int locate = -1;
		while (i < CustomerBUS.listCus.size() && locate == -1) {
			if (listCus.get(i).getCustomerID() == customerId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
	
	public Boolean add(PersonDTO personDTO) {
		CustomerDTO cate = new CustomerDTO(cusDAO.getAutoIncrement(), personDTO);
		boolean check = cusDAO.insert(cate) != 0;
		
		if (check) {
			CustomerBUS.listCus.add(cate);
		}
		return check;
	}
	
	public Boolean delete(CustomerDTO cate) {
        boolean check = cusDAO.delete(Integer.toString(cate.getCustomerID())) != 0;
            CustomerBUS.listCus.remove(cate);
        return check;
    }



    public Boolean update(CustomerDTO cate) {
        boolean check = cusDAO.update(cate) != 0;
        if (check) {
          listCus.set(getIndexById(cate.getCustomerID()), cate);
        }
        return check;
    }
    
    public ArrayList<CustomerDTO> search(String text) {
        text = text.toLowerCase();
        ArrayList<CustomerDTO> result = new ArrayList<>();
        for (CustomerDTO i : this.listCus) {
            if (Integer.toString(i.getCustomerID()).toLowerCase().contains(text) || i.getPersonDTO().getName().toLowerCase().contains(text)) {
                result.add(i);
            }
        }
        return result;
    }
    
    public String[] getArrCategoryName() {
        int size = listCus.size();
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = listCus.get(i).getPersonDTO().getName();
        }
        return result;
    }

    public String getCategoryName(int categoryId) {
        return this.listCus.get(this.getIndexById(categoryId)).getPersonDTO().getName();
    }

    public static boolean checkDup(String name) {
        boolean check = true;
        int i = 0;
        while (i < listCus.size() && check == true) {
            if (listCus.get(i).getPersonDTO().getName().toLowerCase().contains(name.toLowerCase())) {
            	System.out.println(listCus.get(i).getPersonDTO().getName());
                check = false;
            }else {
            	i++;
            }
        }
        return check;
    }
    
    public static boolean checkDupPhone(String phone) {
        boolean check = true;
        int i = 0;
        while (i < listCus.size() && check == true) {
            if (listCus.get(i).getPersonDTO().getPhone().toLowerCase().contains(phone.toLowerCase())) {
            	System.out.println(listCus.get(i).getPersonDTO().getName());
                check = false;
            }else {
            	i++;
            }
        }
        return check;
    }
    
    public static boolean checkDupEmail(String email) {
        boolean check = true;
        int i = 0;
        while (i < listCus.size() && check == true) {
            if (listCus.get(i).getPersonDTO().getEmail().toLowerCase().contains(email.toLowerCase())) {
            	System.out.println(listCus.get(i).getPersonDTO().getName());
                check = false;
            }else {
            	i++;
            }
        }
        return check;
    }
 
    public void show_user() {
    	
    }
    
    public void importExcel() {
    	JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(null);
   		int result = fileChooser.showOpenDialog(null);
   		String url = fileChooser.getSelectedFile().getAbsolutePath();
		if(result == JFileChooser.APPROVE_OPTION) {
	    	try {
	    		FileInputStream inputStream = new FileInputStream(url);
				cusDAO.importDatabase(inputStream);
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

}



