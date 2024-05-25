package bus;

import java.util.ArrayList;

import dao.ProductDAO;
import dao.ReceiptDAO;
import dao.ReceiptDetailDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;

public class ReceiptDetailBUS {
	private final ReceiptDAO receiptDAO = new ReceiptDAO();
	private final ReceiptDetailDAO receiptDetailDAO = new ReceiptDetailDAO();
	private final ProductDAO productDAO = new ProductDAO();
	private ArrayList<ReceiptDTO> listReceipt = new ArrayList<ReceiptDTO>();
	private ArrayList<ReceiptDetailDTO> listReceiptDetail = new ArrayList<ReceiptDetailDTO>();
	
	  
	public ReceiptDetailBUS() {	
		listReceipt = receiptDAO.selectAll();
		listReceiptDetail = receiptDetailDAO.selectAll();
	}
	
	
    public ArrayList<ReceiptDetailDTO> getAll() {
        this.listReceiptDetail = receiptDetailDAO.selectAll();
        return this.listReceiptDetail;
    }
    
    public ArrayList<ReceiptDetailDTO> getAllList() {
        return this.listReceiptDetail;
    }
	
	public Boolean add(ReceiptDetailDTO receiptDetail) {
		boolean check = receiptDetailDAO.insert(receiptDetail) != 0;
		if (check) {
			this.listReceiptDetail.add(receiptDetail);
		}
		return check;
	}
	
//	public Boolean add(ReceiptDetailDTO receiptDetail) {
//		boolean check = receiptDetailDAO.insert(receiptDetail) != 0;
//		if (check) {
//			this.listReceiptDetail.add(receiptDetail);
//		}
//		return check;
//	}
	
	public Boolean delete(ReceiptDetailDTO receiptDetail) {
        boolean check = receiptDetailDAO.delete(Integer.toString(receiptDetail.getReceiptId())) != 0;
        if (check) {
            this.listReceiptDetail.remove(receiptDetail);
        }
        return check;
    }
	
   public Boolean update(ReceiptDetailDTO receiptDetail) {
        boolean check = receiptDetailDAO.update(receiptDetail) != 0;
        if (check) {
            this.listReceiptDetail.set(getIndexById(receiptDetail.getReceiptId()), receiptDetail);
        }
        return check;
    }
	
	public int getIndexById(int receiptId) {
		int i = 0;
		int locate = -1;
		while (i < this.listReceiptDetail.size() && locate == -1) {
			if (listReceiptDetail.get(i).getReceiptId() == receiptId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
}
