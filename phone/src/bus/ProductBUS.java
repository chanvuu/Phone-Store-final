package bus;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import dao.ProductDAO;
import dto.CategoryDTO;
import dto.ProductDTO;

public class ProductBUS {
	private final ProductDAO productDAO = new ProductDAO();
	private ArrayList<ProductDTO> listProduct = new ArrayList<>();
	
	public ProductBUS() {
		listProduct = productDAO.selectAll();
	}
	
	public ArrayList<ProductDTO> getALL() {
		listProduct = productDAO.selectAll();
		return this.listProduct;
	}
	
	public ArrayList<ProductDTO> getALLDeleted() {
		listProduct = productDAO.selectAllDelete();
		return this.listProduct;
	}
	
	public ProductDTO getByIndex(int index) {
		return this.listProduct.get(index);
	}
	
	public int getIndexById(int productId) {
		int i = 0;
		int locate = -1;
		while (i < this.listProduct.size() && locate == -1) {
			if (listProduct.get(i).getProductId() == productId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
	
	public Boolean add(String productName, int categoryId, double purchasePrice, double sellingPrice, int quantity, String imgURL, String description) {
		ProductDTO product = new ProductDTO(productDAO.getAutoIncrement(), productName, categoryId, purchasePrice, sellingPrice, quantity, imgURL, description);
		boolean check = productDAO.insert(product) != 0;
		if (check) {
			this.listProduct.add(product);
		}
		return check;
	}
	
	public Boolean add(ProductDTO product) {
		boolean check = productDAO.insert(product) != 0;
		if (check) {
			this.listProduct.add(product);
		}
		return check;
	}
	
	public Boolean delete(ProductDTO product) {
        boolean check = productDAO.delete(Integer.toString(product.getProductId())) != 0;
        if (check) {
            this.listProduct.remove(product);
        }
        return check;
    }
	
   public Boolean update(ProductDTO product) {
        boolean check = productDAO.update(product) != 0;
        if (check) {
            this.listProduct.set(getIndexById(product.getProductId()), product);
        }
        return check;
    }
   
   public Boolean restore(ProductDTO product) {
       boolean check = productDAO.restore(product) != 0;
       if (check) {
    	   this.listProduct.set(getIndexById(product.getProductId()), product);
       }
       return check;
   }
   

	    
   public ArrayList<ProductDTO> search(String text) {
       text = text.toLowerCase();
       ArrayList<ProductDTO> result = new ArrayList<>();
       for (ProductDTO c : this.listProduct) {
           if (Integer.toString(c.getProductId()).toLowerCase().contains(text) || c.getProductName().toLowerCase().contains(text)) {
               result.add(c);
           }
       }
       return result;
   }
   
   public String getProductName(int productId) {
       return this.listProduct.get(this.getIndexById(productId)).getProductName();
   }
   
   public boolean checkDup(String name, int productIdToExclude) {
	    boolean check = true;
	    int i = 0;
	    while (i < this.listProduct.size() && check) {
	        if (this.listProduct.get(i).getProductId() == productIdToExclude) {
	            i++;
	        } else if (this.listProduct.get(i).getProductName().equalsIgnoreCase(name)) {
	            check = false;
	        } else {
	            i++;
	        }
	    }
	    return check;
	}
   public void importExcel () { 	
	   	JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(null);
	  		int result = fileChooser.showOpenDialog(null);
	  		String url = fileChooser.getSelectedFile().getAbsolutePath();
			if(result == JFileChooser.APPROVE_OPTION) {
		    	try {
		    		FileInputStream inputStream = new FileInputStream(url);
					productDAO.importDatabase(inputStream);
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	   }
	
}
