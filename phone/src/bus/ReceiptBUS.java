package bus;

import java.util.ArrayList;

import dao.ProductDAO;
import dao.ReceiptDAO;
import dao.ReceiptDetailDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;

public class ReceiptBUS {
	private final ReceiptDAO receiptDAO = new ReceiptDAO();
	private final ReceiptDetailDAO receiptDetailDAO = new ReceiptDetailDAO();
	private final ProductDAO productDAO = new ProductDAO();
	private ArrayList<ReceiptDTO> listReceipt = new ArrayList<ReceiptDTO>();
	private ArrayList<ReceiptDetailDTO> listReceiptDetail = new ArrayList<ReceiptDetailDTO>();
	
	  
	public ReceiptBUS() {	
		listReceipt = receiptDAO.selectAll();
		listReceiptDetail = receiptDetailDAO.selectAll();
	}
	
	
    public ArrayList<ReceiptDTO> getAll() {
        this.listReceipt = receiptDAO.selectAll();
        return this.listReceipt;
    }
    
    public ArrayList<ReceiptDTO> getAllList() {
        return this.listReceipt;
    }
	
	public Boolean add(ReceiptDTO receipt) {
		boolean check = receiptDAO.insert(receipt) != 0;
		if (check) {
			this.listReceipt.add(receipt);
		}
		return check;
	}
	
	public Boolean add(ReceiptDetailDTO receiptDetail) {
		boolean check = receiptDetailDAO.insert(receiptDetail) != 0;
		if (check) {
			this.listReceiptDetail.add(receiptDetail);
		}
		return check;
	}
	
	public Boolean delete(ReceiptDTO receipt) {
        boolean check = receiptDAO.delete(Integer.toString(receipt.getReceiptId())) != 0;
        if (check) {
            this.listReceipt.remove(receipt);
        }
        return check;
    }
	
   public Boolean update(ReceiptDTO receipt) {
        boolean check = receiptDAO.update(receipt) != 0;
        if (check) {
            this.listReceipt.set(getIndexById(receipt.getReceiptId()), receipt);
        }
        return check;
    }
	
	public int getIndexById(int receiptId) {
		int i = 0;
		int locate = -1;
		while (i < this.listReceipt.size() && locate == -1) {
			if (listReceipt.get(i).getReceiptId() == receiptId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
}
