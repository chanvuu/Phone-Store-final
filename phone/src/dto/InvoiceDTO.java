package dto;

import java.sql.Timestamp;
import java.util.Objects;

public class InvoiceDTO {
	private int invoiceId;
	private int customerId;
	private int staffId;
	private Timestamp date;
	private double totalPrice;
	
	public InvoiceDTO() {
		super();
	}

	public InvoiceDTO(int invoiceId, int customerId, int staffId, Timestamp date, double totalPrice) {
		super();
		this.invoiceId = invoiceId;
		this.customerId = customerId;
		this.staffId = staffId;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "InvoiceDTO [invoiceId=" + invoiceId + ", customerId=" + customerId + ", staffId=" + staffId + ", date="
				+ date + ", totalPrice=" + totalPrice + "]";
	}

	
	
	

	
	
}
