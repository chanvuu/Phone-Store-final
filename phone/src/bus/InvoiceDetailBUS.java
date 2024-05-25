package bus;

import java.util.ArrayList;

import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import dao.ProductDAO;
import dao.ReceiptDAO;
import dao.ReceiptDetailDAO;
import dto.CategoryDTO;
import dto.InvoiceDTO;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;

public class InvoiceDetailBUS {
	private final InvoiceDAO invoiceDAO = new InvoiceDAO();
	private final InvoiceDetailDAO invoiceDetailDAO = new InvoiceDetailDAO();
	private final ProductDAO productDAO = new ProductDAO();
	private ArrayList<InvoiceDTO> listInvoice = new ArrayList<InvoiceDTO>();
	private ArrayList<InvoiceDetailDTO> listInvoiceDetail = new ArrayList<InvoiceDetailDTO>();
	
	  
	public InvoiceDetailBUS() {	
		listInvoice = invoiceDAO.selectAll();
		listInvoiceDetail = invoiceDetailDAO.selectAll();
	}
	
	
    public ArrayList<InvoiceDetailDTO> getAll() {
        this.listInvoiceDetail = invoiceDetailDAO.selectAll();
        return this.listInvoiceDetail;
    }
    
    public ArrayList<InvoiceDetailDTO> getAllList() {
        return this.listInvoiceDetail;
    }
	
	public Boolean add(InvoiceDetailDTO invoiceDetail) {
		boolean check = invoiceDetailDAO.insert(invoiceDetail) != 0;
		if (check) {
			this.listInvoiceDetail.add(invoiceDetail);
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
	
	public Boolean delete(InvoiceDetailDTO invoiceDetail) {
        boolean check = invoiceDetailDAO.delete(Integer.toString(invoiceDetail.getInvoiceId())) != 0;
        if (check) {
            this.listInvoiceDetail.remove(invoiceDetail);
        }
        return check;
    }
	
   public Boolean update(InvoiceDetailDTO invoiceDetail) {
        boolean check = invoiceDetailDAO.update(invoiceDetail) != 0;
        if (check) {
            this.listInvoiceDetail.set(getIndexById(invoiceDetail.getInvoiceId()), invoiceDetail);
        }
        return check;
    }
	
	public int getIndexById(int invoiceId) {
		int i = 0;
		int locate = -1;
		while (i < this.listInvoiceDetail.size() && locate == -1) {
			if (listInvoiceDetail.get(i).getInvoiceId() == invoiceId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
}
