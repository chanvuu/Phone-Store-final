package bus;

import java.util.ArrayList;

import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import dao.ProductDAO;
import dao.ReceiptDAO;
import dao.ReceiptDetailDAO;
import dto.InvoiceDTO;
import dto.InvoiceDetailDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;

public class InvoiceBUS {
	private final InvoiceDAO invoiceDAO = new InvoiceDAO();
	private final InvoiceDetailDAO invoiceDetailDAO = new InvoiceDetailDAO();
	private final ProductDAO productDAO = new ProductDAO();
	private ArrayList<InvoiceDTO> listInvoice = new ArrayList<InvoiceDTO>();
	private ArrayList<InvoiceDetailDTO> listInvoiceDetail = new ArrayList<InvoiceDetailDTO>();
	
	public InvoiceBUS() {	
		listInvoice = invoiceDAO.selectAll();
		listInvoiceDetail = invoiceDetailDAO.selectAll();
	}
	
    public ArrayList<InvoiceDTO> getAll() {
        this.listInvoice = invoiceDAO.selectAll();
        return this.listInvoice;
    }
    
    public ArrayList<InvoiceDTO> getAllList() {
        return this.listInvoice;
    }
    
    public Boolean add(InvoiceDTO invoice) {
		boolean check = invoiceDAO.insert(invoice) != 0;
		if (check) {
			this.listInvoice.add(invoice);
		}
		return check;
	}
    
	public Boolean add(InvoiceDetailDTO invoiceDetail) {
		boolean check = invoiceDetailDAO.insert(invoiceDetail) != 0;
		if (check) {
			this.listInvoiceDetail.add(invoiceDetail);
		}
		return check;
	}
	
	public Boolean delete(InvoiceDTO invoice) {
        boolean check = invoiceDAO.delete(Integer.toString(invoice.getInvoiceId())) != 0;
        if (check) {
            this.listInvoice.remove(invoice);
        }
        return check;
    }
	
   public Boolean update(InvoiceDTO invoice) {
        boolean check = invoiceDAO.update(invoice) != 0;
        if (check) {
            this.listInvoice.set(getIndexById(invoice.getInvoiceId()), invoice);
        }
        return check;
    }
   
	public int getIndexById(int invoiceId) {
		int i = 0;
		int locate = -1;
		while (i < this.listInvoice.size() && locate == -1) {
			if (listInvoice.get(i).getInvoiceId() == invoiceId) {
				locate = i;
			}else {
				i++;
			}
		}
		return locate;
	}
	
}
