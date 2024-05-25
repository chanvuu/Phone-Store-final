package dto;


public class SupplierDTO {
	@Override
	public String toString() {
		return "SupplierDTO [SupplierId=" + SupplierId + ", SupplierName=" + SupplierName + ", SupplierPhone="
				+ SupplierPhone + ", SupplierAddress=" + SupplierAddress + ", SupplierEmail=" + SupplierEmail + ", SupplierNote=" + SupplierNote+ "]";
	}
	public int getSupplierId() {
		return SupplierId;
	}
	public void setSupplierId(int supplierId) {
		SupplierId = supplierId;
	}
	public String getSupplierName() {
		return SupplierName;
	}
	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}
	public String getSupplierPhone() {
		return SupplierPhone;
	}
	public void setSupplierPhone(String supplierPhone) {
		SupplierPhone = supplierPhone;
	}
	public String getSupplierAddress() {
		return SupplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		SupplierAddress = supplierAddress;
	}
	public String getSupplierEmail() {
		return SupplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		SupplierEmail = supplierEmail;
	}
	public String getSupplierNote() {
		return SupplierNote;
	}
	public void setSupplierNote(String supplierNote) {
		SupplierNote = supplierNote;
	}
	private int SupplierId;
	private String SupplierName;
	private String SupplierPhone;
	private String SupplierAddress;
	private String SupplierEmail;
	private String SupplierNote;

	public SupplierDTO(int supplierId, String supplierName, String supplierPhone, String supplierAddress, String supplierEmail, String supplierNote) {
		SupplierId = supplierId;
		SupplierName = supplierName;
		SupplierPhone = supplierPhone;
		SupplierAddress = supplierAddress;
		SupplierEmail = supplierEmail;
		SupplierNote = supplierNote;
	}
	public SupplierDTO() {
		
	}
	
}
