package dto;

public class InvoiceDetailDTO {
	private int invoiceId;
	private int productId;
	private int quantity;
	private double sellingPrice;
	
	public InvoiceDetailDTO() {
		
	}

	public InvoiceDetailDTO(int invoiceId, int productId, int quantity, double sellingPrice) {
		super();
		this.invoiceId = invoiceId;
		this.productId = productId;
		this.quantity = quantity;
		this.sellingPrice = sellingPrice;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
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

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	@Override
	public String toString() {
		return "InvoiceDetailDTO [invoiceId=" + invoiceId + ", productId=" + productId + ", quantity=" + quantity
				+ ", sellingPrice=" + sellingPrice + "]";
	}

	
	
	


	
	
}
