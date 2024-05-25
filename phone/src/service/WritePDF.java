package service;



import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bus.CategoryBUS;
import bus.CustomerBUS;
import bus.InvoiceBUS;
import bus.ProductBUS;
import bus.ReceiptBUS;
import bus.StaffBUS;
import bus.SupplierBUS;
import dao.InvoiceDetailDAO;
import dao.ReceiptDetailDAO;
import dto.CategoryDTO;
import dto.CustomerDTO;
import dto.InvoiceDTO;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import dto.StaffDTO;
import dto.SupplierDTO;

public class WritePDF {
	InvoiceDetailDAO invoiceDetailDAO = new InvoiceDetailDAO();
	ArrayList<InvoiceDetailDTO> listInvoiceDetail = invoiceDetailDAO.selectAll();
	
	ReceiptDetailDAO receiptDetailDAO = new ReceiptDetailDAO();
	ArrayList<ReceiptDetailDTO> listReceiptDetail = receiptDetailDAO.selectAll();
	
	ProductBUS productBUS = new ProductBUS();
	ArrayList<ProductDTO> listProduct = productBUS.getALL();
	
	ReceiptBUS receiptBUS = new ReceiptBUS();
	ArrayList<ReceiptDTO> listReceipt = receiptBUS.getAll();
	
	InvoiceBUS invoiceBUS = new InvoiceBUS();
	ArrayList<InvoiceDTO> listInvoice = invoiceBUS.getAll();
	
	SupplierBUS supplierBUS = new SupplierBUS();
	ArrayList<SupplierDTO> listSupplier = supplierBUS.getALL();
	
	CustomerBUS customerBUS = new CustomerBUS();
	ArrayList<CustomerDTO> listCustomer = customerBUS.getALL();
	
	StaffBUS staffBUS = new StaffBUS();
	ArrayList<StaffDTO> listStaff = staffBUS.getALL();
	
	CategoryBUS cateBUS = new CategoryBUS();
	ArrayList<CategoryDTO> listCate = cateBUS.getALL();
	Document document = new Document();
    FileOutputStream file;
    JFrame jf = new JFrame();
    FileDialog fd = new FileDialog(jf, "Xuất pdf", FileDialog.SAVE);
	Font fontNormal10;
	Font fontBold15;
	Font fontBold25;
	Font fontBoldItalic15;
	public WritePDF() {
	    try {
	        fontNormal10 = new Font(BaseFont.createFont("./lib/TimesNewRoman/SVN-Times New Roman.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12, Font.NORMAL);
	        fontBold25 = new Font(BaseFont.createFont("./lib/TimesNewRoman/SVN-Times New Roman Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25, Font.NORMAL);
	        fontBold15 = new Font(BaseFont.createFont("./lib/TimesNewRoman/SVN-Times New Roman Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL);
	        fontBoldItalic15 = new Font(BaseFont.createFont("./lib/TimesNewRoman/SVN-Times New Roman Bold Italic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 15, Font.NORMAL);
	    } catch (DocumentException | FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException ex) {
	        Logger.getLogger(WritePDF.class.getName()).log(Level.SEVERE, null, ex);
	    }
	} 
	
    public void chooseURL(String url) {
        try {
            document.close();
            document = new Document();
            file = new FileOutputStream(url);
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Khong tim thay duong dan file " + url);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Khong goi duoc document !");
        }
    }
    
    public void setTitle(String title) {
        try {
            Paragraph pdfTitle = new Paragraph(new Phrase(title, fontBold25));
            pdfTitle.setAlignment(Element.ALIGN_CENTER);
            document.add(pdfTitle);
            document.add(Chunk.NEWLINE);
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }
    
    private String getFile(String name) {
        fd.pack();
        fd.setSize(800, 600);
        fd.validate();
        Rectangle rect = jf.getContentPane().getBounds();
        double width = fd.getBounds().getWidth();
        double height = fd.getBounds().getHeight();
        double x = rect.getCenterX() - (width / 2);
        double y = rect.getCenterY() - (height / 2);
        Point leftCorner = new Point();
        leftCorner.setLocation(x, y);
        fd.setLocation(leftCorner);
        fd.setFile(name);
        fd.setVisible(true);
        String url = fd.getDirectory() + fd.getFile();
        if (url.equals("null")) {
            return null;
        }
        return url;
    }

    private void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static Chunk createWhiteSpace(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(" ");
        }
        return new Chunk(builder.toString());
    }
    
    public void writeReceipt(int receiptId) {
        String url = "";
        try {
            fd.setTitle("In phiếu nhập");
            fd.setLocationRelativeTo(null);
            url = getFile(receiptId + "");
            if (url.equals("nullnull")) {
                return;
            }
            url = url + ".pdf";
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            Paragraph company = new Paragraph("Hệ thống quản lý điện thoại nhóm Quốc Hưng", fontBold15);
            company.add(new Chunk(createWhiteSpace(20)));
            Timestamp today = new Timestamp(System.currentTimeMillis());
            company.add(new Chunk("Thời gian in phiếu: " + Formater.FormatTime(today), fontNormal10));
            company.setAlignment(Element.ALIGN_LEFT);
            document.add(company);
            // Thêm tên công ty vào file PDF
            document.add(Chunk.NEWLINE);
            Paragraph header = new Paragraph("THÔNG TIN PHIẾU NHẬP", fontBold25);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            ReceiptDTO  receipt = listReceipt.get(receiptBUS.getIndexById(receiptId));
            System.out.println(receiptId);
            System.out.println(listReceipt.get(receiptBUS.getIndexById(receiptId)) +" cc");
            System.out.println(receipt.getSupplierId());
            // Thêm dòng Paragraph vào file PDF

            Paragraph paragraph1 = new Paragraph("Mã phiếu: receipt-" + receiptId, fontNormal10);
            int suppId = listReceipt.get(receiptBUS.getIndexById(receiptId)).getSupplierId();
            String supp = listSupplier.get(supplierBUS.getIndexById(suppId)).getSupplierName();
            Paragraph paragraph2 = new Paragraph("Nhà cung cấp: " + supp, fontNormal10);
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            paragraph2.add(new Chunk("-"));
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            String diachincc = listSupplier.get(supplierBUS.getIndexById(suppId)).getSupplierAddress();
            paragraph2.add(new Chunk(diachincc, fontNormal10));

            int staffId = listReceipt.get(receiptBUS.getIndexById(receiptId)).getStaffId();
            String staff = listCustomer.get(staffId).getPersonDTO().getName();
            Paragraph paragraph3 = new Paragraph("Người thực hiện: " + staff, fontNormal10);
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("-"));
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("Mã nhân viên: " + receipt.getStaffId(), fontNormal10));
            Paragraph paragraph4 = new Paragraph("Thời gian nhập: " + Formater.FormatTime(receipt.getDate()), fontNormal10);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(Chunk.NEWLINE);
            // Thêm table 5 cột vào file PDF
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{30f, 35f, 20f, 15f, 20f});
            PdfPCell cell;

            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Phiên bản", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Giá", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Tổng tiền", fontBold15)));
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                table.addCell(cell);
            }

            //Truyen thong tin tung chi tiet vao table
            for (ReceiptDetailDTO rd : ReceiptDetailDAO.getInstance().selectAll(receiptId + "")) {
            	ProductDTO product = new ProductDTO();
            	product = listProduct.get(rd.getProductId());
            	table.addCell(new PdfPCell(new Phrase(product.getProductName(), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(listCate.get(cateBUS.getIndexById(product.getCategoryId())).getCategoryName(), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(Formater.FormatVND(rd.getPurchasePrice()), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(String.valueOf(rd.getQuantity()), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(Formater.FormatVND(rd.getQuantity() * rd.getPurchasePrice()), fontNormal10)));
            }     

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + Formater.FormatVND(receipt.getTotalPrice()), fontBold15));
            paraTongThanhToan.setIndentationLeft(300);

            document.add(paraTongThanhToan);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph paragraph = new Paragraph();
            paragraph.setIndentationLeft(22);
            paragraph.add(new Chunk("Người lập phiếu", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Nhân viên nhận", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Nhà cung cấp", fontBoldItalic15));

            Paragraph sign = new Paragraph();
            sign.setIndentationLeft(23);
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(30)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(28)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            document.add(paragraph);
            document.add(sign);

            document.close();
            writer.close();
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }
    
    public void writeInvoice(int invoiceId) {
        String url = "";
        try {
            fd.setTitle("In hóa đơn");
            fd.setLocationRelativeTo(null);
            url = getFile(invoiceId + "");
            if (url.equals("nullnull")) {
                return;
            }
            url = url + ".pdf";
            file = new FileOutputStream(url);
            document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();

            Paragraph company = new Paragraph("Hệ thống quản lý điện thoại nhóm Quốc Hưng", fontBold15);
            company.add(new Chunk(createWhiteSpace(20)));
            Timestamp today = new Timestamp(System.currentTimeMillis());
            company.add(new Chunk("Thời gian in phiếu: " + Formater.FormatTime(today), fontNormal10));
            company.setAlignment(Element.ALIGN_LEFT);
            document.add(company);
            // Thêm tên công ty vào file PDF
            document.add(Chunk.NEWLINE);
            Paragraph header = new Paragraph("THÔNG TIN HÓA ĐƠN", fontBold25);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            InvoiceDTO  invoice = listInvoice.get(invoiceBUS.getIndexById(invoiceId));
//            System.out.println(invoiceId);
//            System.out.println(listReceipt.get(receiptBUS.getIndexById(receiptId)) +" cc");
//            System.out.println(receipt.getSupplierId());
            // Thêm dòng Paragraph vào file PDF
//            int suppId = listReceipt.get(receiptBUS.getIndexById(receiptId)).getSupplierId();
//            String supp = listSupplier.get(supplierBUS.getIndexById(suppId)).getSupplierName();
            Paragraph paragraph1 = new Paragraph("Mã hóa đơn: invoice-" + invoiceId, fontNormal10);
            int cussId = listInvoice.get(invoiceBUS.getIndexById(invoiceId)).getCustomerId();
            String cuss = listCustomer.get(customerBUS.getIndexById(cussId)).getPersonDTO().getName();
            Paragraph paragraph2 = new Paragraph("Khác hàng: " + cuss, fontNormal10);
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            paragraph2.add(new Chunk("-"));
            paragraph2.add(new Chunk(createWhiteSpace(5)));
            String diachincc = listCustomer.get(customerBUS.getIndexById(cussId)).getPersonDTO().getName();
            paragraph2.add(new Chunk(diachincc, fontNormal10));
            
            int staffId = listInvoice.get(invoiceBUS.getIndexById(invoiceId)).getStaffId();
            String staff = listStaff.get(staffBUS.getIndexById(staffId)).getperson().getName();
            Paragraph paragraph3 = new Paragraph("Người thực hiện: " + staff, fontNormal10);
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("-"));
            paragraph3.add(new Chunk(createWhiteSpace(5)));
            paragraph3.add(new Chunk("Mã nhân viên: " + invoice.getStaffId(), fontNormal10));
            Paragraph paragraph4 = new Paragraph("Thời gian nhập: " + Formater.FormatTime(invoice.getDate()), fontNormal10);
            document.add(paragraph1);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(Chunk.NEWLINE);
            // Thêm table 5 cột vào file PDF
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{30f, 35f, 20f, 15f, 20f});
            PdfPCell cell;

            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Phiên bản", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Giá", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", fontBold15)));
            table.addCell(new PdfPCell(new Phrase("Tổng tiền", fontBold15)));
            for (int i = 0; i < 5; i++) {
                cell = new PdfPCell(new Phrase(""));
                table.addCell(cell);
            }

            //Truyen thong tin tung chi tiet vao table

            for (InvoiceDetailDTO rd : InvoiceDetailDAO.getInstance().selectAll(invoiceId + "")) {
            	ProductDTO product = new ProductDTO();
            	product = listProduct.get(rd.getProductId());
            	table.addCell(new PdfPCell(new Phrase(product.getProductName(), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(listCate.get(cateBUS.getIndexById(product.getCategoryId())).getCategoryName(), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(Formater.FormatVND(rd.getSellingPrice()), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(String.valueOf(rd.getQuantity()), fontNormal10)));
            	table.addCell(new PdfPCell(new Phrase(Formater.FormatVND(rd.getQuantity() * rd.getSellingPrice()), fontNormal10)));
            	System.out.println(product);
            }     

            document.add(table);
            document.add(Chunk.NEWLINE);

            Paragraph paraTongThanhToan = new Paragraph(new Phrase("Tổng thành tiền: " + Formater.FormatVND(invoice.getTotalPrice()), fontBold15));
            paraTongThanhToan.setIndentationLeft(300);

            document.add(paraTongThanhToan);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph paragraph = new Paragraph();
            paragraph.setIndentationLeft(22);
            paragraph.add(new Chunk("Người lập phiếu", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Nhân viên nhận", fontBoldItalic15));
            paragraph.add(new Chunk(createWhiteSpace(30)));
            paragraph.add(new Chunk("Khách Hàng", fontBoldItalic15));

            Paragraph sign = new Paragraph();
            sign.setIndentationLeft(23);
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(30)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            sign.add(new Chunk(createWhiteSpace(28)));
            sign.add(new Chunk("(Ký và ghi rõ họ tên)", fontNormal10));
            document.add(paragraph);
            document.add(sign);

            document.close();
            writer.close();
            openFile(url);

        } catch (DocumentException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi ghi file " + url);
        }

    }


	public static void main(String[] args) throws FileNotFoundException{
//		String path = ".pdf";
//		PdfWriter pdfwriter = new PdfWriter();
//		pdfwriter.set
//		PdfDocument pdfDocutment = new PdfDocument(pdfwriter);
	}
}
