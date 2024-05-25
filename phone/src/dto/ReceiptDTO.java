package dto;

import java.sql.Timestamp;
import java.util.Objects;

public class ReceiptDTO {
	private int receiptId;
	private int supplierId;
	private int staffId;
	private Timestamp date;
	private double totalPrice;
	
	public ReceiptDTO() {
		super();
	}

	public ReceiptDTO(int receiptId, int supplierId, int staffId, Timestamp date, double totalPrice) {
		super();
		this.receiptId = receiptId;
		this.supplierId = supplierId;
		this.staffId = staffId;
		this.date = date;
		this.totalPrice = totalPrice;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffName(int staffId) {
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
		return "ReceiptDTO [receiptId=" + receiptId + ", supplierId=" + supplierId + ", staffName=" + staffId
				+ ", date=" + date + ", totalPrice=" + totalPrice + "]";
	}

	
	
}
