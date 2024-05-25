package dto;

public class ReceiptDetailDTO {
	private int receiptId;
	private int productId;
	private int quantity;
	private double purchasePrice;
	
	public ReceiptDetailDTO() {
		
	}
	
	public ReceiptDetailDTO(int receiptId, int productId, int quantity, double purchasePrice) {
		super();
		this.receiptId = receiptId;
		this.productId = productId;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
	}

	public int getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(int receiptId) {
		this.receiptId = receiptId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	@Override
	public String toString() {
		return "ReceiptDetailDTO [receiptId=" + receiptId + ", productId=" + productId + ", quantity=" + quantity
				+ ", purchasePrice=" + purchasePrice + "]";
	}
	


	
	
}
