package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import bus.CategoryBUS;
import bus.CustomerBUS;
import bus.InvoiceBUS;
import bus.ProductBUS;
import bus.ReceiptBUS;
import bus.StaffBUS;
import bus.SupplierBUS;
import dao.InvoiceDAO;
import dao.ReceiptDAO;
import dto.CategoryDTO;
import dto.CustomerDTO;
import dto.InvoiceDTO;
import dto.InvoiceDetailDTO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import dto.StaffDTO;
import dto.SupplierDTO;
import service.ExcelExporter;
import service.Formater;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Invoice extends JPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JTextField findReceiptTxt;
	private JPanel invoicePanel, invoiceDetailPanel;
	private JButton addInvoiceBtn, addProductBtn, editProductBtn, deleteProductBtn, refreshProductBtn, paymentBtn, deleteInvoiceBtn, exportExcelBtn, detaiInvoiceDetailBtn, refreshReceiptBtn;
	private JTextField textField_1;
	private JTextField productIdTxt;
	private JTextField productNameTxt;
	private JTextField purchasePriceTxt;
	private JSpinner quantityTxt;
	private JTextField findProductTxt;
	private JTable productTable, invoiceTable;
	private JLabel totalPriceLbl;
	private JComboBox comboBoxCustomer, comboBoxStaff;
	private DefaultTableModel productModel, cartModel, invoiceModel;
	private JTable cartTable;
	private JPanel panel_1;
	private JScrollPane scrollPane;
    Map<Integer, Integer> indexMap = new HashMap<>();
	Map<String, Integer> customerMap = new HashMap<>();
	Map<String, Integer> staffMap = new HashMap<>();

	InvoiceBUS invoiceBUS = new InvoiceBUS();
	ArrayList<InvoiceDTO> listInvoice = invoiceBUS.getAll();
	
	ProductBUS productBUS = new ProductBUS();
	ArrayList<ProductDTO> listProduct = productBUS.getALL();
	ArrayList<ProductDTO> listCart = new ArrayList<ProductDTO>();	
	
	SupplierBUS supplierBUS = new SupplierBUS();
	ArrayList<SupplierDTO> listSupplier = supplierBUS.getALL();
	
	StaffBUS staffBUS = new StaffBUS();
	ArrayList<StaffDTO> listStaff = staffBUS.getALL();
	
	CustomerBUS customerBUS = new CustomerBUS();
	ArrayList<CustomerDTO> listCustomer = customerBUS.getALL();
	
	CategoryBUS categoryBUS = new CategoryBUS();
	ArrayList<CategoryDTO> listCategory = categoryBUS.getALL();
	
	private JLabel closePayment;
	

//	SupplierBUS supplierBUS = new SupplierBUS();
//	ArrayList<SupplierDTO> listSupplier = supplierBUS.getALL();

	/**
	 * Create the panel.
	 */
	public Invoice() {
//		System.out.println(listSupplier.get(0).getSupplierName());
		inintComponet();
        showPanel("invoicePanel");  
        loadDataProductTable(listProduct);
        loadDataInvoiceTable(listInvoice);
        resetCombo();
	}

	public void inintComponet() {
		Color myColor = new Color(34, 33, 75);
		Color backGroundColor = Color.white;
		Color borderColor = myColor;
		Color textColor = myColor;
		Color buttonColor = myColor;
		
		setLayout(new CardLayout(0, 0));		
		
		invoicePanel = new JPanel();
		invoicePanel.setMinimumSize(new Dimension(100, 200));
		add(invoicePanel, "invoicePanel");
		GridBagLayout gbl_invoicePanel = new GridBagLayout();
		gbl_invoicePanel.columnWidths = new int[]{0, 0};
		gbl_invoicePanel.rowHeights = new int[] {80, 600};
		gbl_invoicePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_invoicePanel.rowWeights = new double[]{1.0, 1.0};
		invoicePanel.setLayout(gbl_invoicePanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(backGroundColor);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.weighty = 0.1;
		gbc_panel.weightx = 1.0;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		invoicePanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		addInvoiceBtn = new JButton("Thanh Toán");
		addInvoiceBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addInvoiceBtn.setBorder(new LineBorder(borderColor, 2, true));
     	Image iconAdd = new ImageIcon("Assets/Icon/payment.png").getImage();
     	iconAdd = iconAdd.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
     	addInvoiceBtn.setPreferredSize(new Dimension(80, 60));
     	addInvoiceBtn.setBackground(buttonColor);
     	addInvoiceBtn.setForeground(Color.white);
     	addInvoiceBtn.setFocusPainted(false);
     	addInvoiceBtn.setIcon(new ImageIcon(iconAdd));
     	addInvoiceBtn.setHorizontalTextPosition(JButton.CENTER);
     	addInvoiceBtn.setVerticalTextPosition(JButton.BOTTOM);     	
		addInvoiceBtn.addMouseListener(this);
		GridBagConstraints gbc_addInvoiceBtn = new GridBagConstraints();
		gbc_addInvoiceBtn.insets = new Insets(10, 5, 0, 10);
		gbc_addInvoiceBtn.gridx = 0;
		gbc_addInvoiceBtn.gridy = 0;
		panel.add(addInvoiceBtn, gbc_addInvoiceBtn);
		
		deleteInvoiceBtn = new JButton("Xóa");
		deleteInvoiceBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		deleteInvoiceBtn.setBorder(new LineBorder(borderColor, 2, true));
     	Image iconDelete = new ImageIcon("Assets/Icon/delete.png").getImage();
     	iconDelete = iconDelete.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
     	deleteInvoiceBtn.setPreferredSize(new Dimension(80, 60));
     	deleteInvoiceBtn.setBackground(buttonColor);
     	deleteInvoiceBtn.setForeground(Color.white);
     	deleteInvoiceBtn.setFocusPainted(false);
     	deleteInvoiceBtn.setIcon(new ImageIcon(iconDelete));
     	deleteInvoiceBtn.setHorizontalTextPosition(JButton.CENTER);
     	deleteInvoiceBtn.setVerticalTextPosition(JButton.BOTTOM);
     	deleteInvoiceBtn.addMouseListener(this);
		GridBagConstraints gbc_deleteInvoiceBtn = new GridBagConstraints();
		gbc_deleteInvoiceBtn.insets = new Insets(10, 5, 0, 10);
		gbc_deleteInvoiceBtn.gridx = 1;
		gbc_deleteInvoiceBtn.gridy = 0;
		panel.add(deleteInvoiceBtn, gbc_deleteInvoiceBtn);
		
		detaiInvoiceDetailBtn = new JButton("Chi tiết");	
		detaiInvoiceDetailBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		detaiInvoiceDetailBtn.setBorder(new LineBorder(borderColor, 2, true));
     	Image iconDetail = new ImageIcon("Assets/Icon/detail.png").getImage();
     	iconDetail = iconDetail.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
     	detaiInvoiceDetailBtn.setPreferredSize(new Dimension(80, 60));
     	detaiInvoiceDetailBtn.setBackground(buttonColor);
     	detaiInvoiceDetailBtn.setForeground(Color.white);
     	detaiInvoiceDetailBtn.setFocusPainted(false);
     	detaiInvoiceDetailBtn.setIcon(new ImageIcon(iconDetail));
     	detaiInvoiceDetailBtn.setHorizontalTextPosition(JButton.CENTER);
     	detaiInvoiceDetailBtn.setVerticalTextPosition(JButton.BOTTOM);
     	detaiInvoiceDetailBtn.addMouseListener(this);
		GridBagConstraints gbc_detaiInvoiceDetailBtn = new GridBagConstraints();
		gbc_detaiInvoiceDetailBtn.insets = new Insets(10, 5, 0, 10);
		gbc_detaiInvoiceDetailBtn.gridx = 2;
		gbc_detaiInvoiceDetailBtn.gridy = 0;
		panel.add(detaiInvoiceDetailBtn, gbc_detaiInvoiceDetailBtn);
		
		exportExcelBtn = new JButton("Xuất Excel");
		exportExcelBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		exportExcelBtn.setBorder(new LineBorder(borderColor, 2, true));
     	Image iconExcel = new ImageIcon("Assets/Icon/excel.png").getImage();
     	iconExcel = iconExcel.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
     	exportExcelBtn.setPreferredSize(new Dimension(80, 60));
     	exportExcelBtn.setBackground(buttonColor);
     	exportExcelBtn.setForeground(Color.white);
     	exportExcelBtn.setFocusPainted(false);
     	exportExcelBtn.setIcon(new ImageIcon(iconExcel));
     	exportExcelBtn.setHorizontalTextPosition(JButton.CENTER);
     	exportExcelBtn.setVerticalTextPosition(JButton.BOTTOM);
     	exportExcelBtn.addMouseListener(this);
		GridBagConstraints gbc_exportExcelBtn = new GridBagConstraints();
		gbc_exportExcelBtn.insets = new Insets(10, 5, 0, 10);
		gbc_exportExcelBtn.gridx = 3;
		gbc_exportExcelBtn.gridy = 0;
		panel.add(exportExcelBtn, gbc_exportExcelBtn);
		
		findReceiptTxt = new JTextField();
		findReceiptTxt.addKeyListener(this);
		findReceiptTxt.setBorder(new LineBorder(borderColor, 2, true));
		findReceiptTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		findReceiptTxt.setForeground(textColor);
		GridBagConstraints gbc_findReceiptTxt = new GridBagConstraints();
		gbc_findReceiptTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_findReceiptTxt.ipady = 10;
		gbc_findReceiptTxt.insets = new Insets(10, 200, 0, 5);
		gbc_findReceiptTxt.gridx = 5;
		gbc_findReceiptTxt.gridy = 0;
		panel.add(findReceiptTxt, gbc_findReceiptTxt);
		findReceiptTxt.setColumns(10);
		
		refreshReceiptBtn = new JButton("Mới");
		refreshReceiptBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		refreshReceiptBtn.setBorder(new LineBorder(borderColor, 2, true));
     	Image iconRefresh = new ImageIcon("Assets/Icon/clear.png").getImage();
     	iconRefresh = iconRefresh.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     	refreshReceiptBtn.setPreferredSize(new Dimension(80, 30));
     	refreshReceiptBtn.setBackground(buttonColor);
     	refreshReceiptBtn.setForeground(Color.white);
     	refreshReceiptBtn.setFocusPainted(false);
     	refreshReceiptBtn.setIcon(new ImageIcon(iconRefresh));
     	refreshReceiptBtn.addMouseListener(this);
		GridBagConstraints gbc_refreshReceiptBtn = new GridBagConstraints();
		gbc_refreshReceiptBtn.insets = new Insets(10, 0, 0, 5);
		gbc_refreshReceiptBtn.gridx = 6;
		gbc_refreshReceiptBtn.gridy = 0;
		panel.add(refreshReceiptBtn, gbc_refreshReceiptBtn);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		invoicePanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(borderColor, 2));
//        scrollPane.setBackground(backGroundColor);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.weighty = 1.0;
		gbc_scrollPane.weightx = 1.0;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		String[] columnReceiptName = {"Mã Hóa Đơn", "Khách Hàng", "Nhân Viên Nhập", "Thời Gian", "Tổng Tiền"};
		Object[][] data1 = {
				
	     };
	     
	    invoiceModel = new DefaultTableModel(data1, columnReceiptName) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };		
		invoiceTable = new JTable(invoiceModel);
		invoiceTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		invoiceTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		invoiceTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		invoiceTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		invoiceTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		invoiceTable.setBackground(backGroundColor);
		invoiceTable.setRowHeight(20);		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		TableColumnModel columninvoiceModel = invoiceTable.getColumnModel();
		columninvoiceModel.getColumn(0).setCellRenderer(centerRenderer);
		columninvoiceModel.getColumn(1).setCellRenderer(centerRenderer);
		columninvoiceModel.getColumn(2).setCellRenderer(centerRenderer);
		columninvoiceModel.getColumn(3).setCellRenderer(centerRenderer);
		columninvoiceModel.getColumn(4).setCellRenderer(centerRenderer);
		invoiceTable.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
		invoiceTable.getTableHeader().setReorderingAllowed(false);
		invoiceTable.getTableHeader().setBackground(borderColor);
		invoiceTable.getTableHeader().setForeground(Color.white);
		scrollPane.setViewportView(invoiceTable);
		
		
		
		invoiceDetailPanel = new JPanel();
		invoiceDetailPanel.setBackground(Color.WHITE);
		add(invoiceDetailPanel, "invoiceDetailPanel");
		GridBagLayout gbl_invoiceDetailPanel = new GridBagLayout();
		gbl_invoiceDetailPanel.columnWidths = new int[] {750, 250};
		gbl_invoiceDetailPanel.rowHeights = new int[] {400, 130, 400};
		gbl_invoiceDetailPanel.columnWeights = new double[]{1.0, 1.0};
		gbl_invoiceDetailPanel.rowWeights = new double[]{1.0, 1.0, 1.0};
		invoiceDetailPanel.setLayout(gbl_invoiceDetailPanel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		invoiceDetailPanel.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] {400, 200};
		gbl_panel_4.rowHeights = new int[]{0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_4.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_4.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] {10, 300};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_6.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblNewLabel_11 = new JLabel("Tìm kiếm:");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.weightx = 0.1;
		gbc_lblNewLabel_11.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_11.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 0;
		panel_6.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		findProductTxt = new JTextField();
		findProductTxt.setForeground(textColor);
		findProductTxt.setBorder(new LineBorder(borderColor, 2, true));
		GridBagConstraints gbc_findProductTxt = new GridBagConstraints();
		gbc_findProductTxt.weightx = 0.9;
		gbc_findProductTxt.insets = new Insets(5, 0, 5, 5);
		gbc_findProductTxt.ipady = 5;
		gbc_findProductTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_findProductTxt.gridx = 1;
		gbc_findProductTxt.gridy = 0;
		findProductTxt.addKeyListener(this);
		panel_6.add(findProductTxt, gbc_findProductTxt);
		findProductTxt.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 5, 0, 5);
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		panel_6.add(scrollPane_1, gbc_scrollPane_1);
		
	     String[] columnNames = {"Mã SP", "Tên SP", "Số Lượng", "Giá Nhập"};
	     Object[][] data = {
	    		 
	     };
	     
	     productModel = new DefaultTableModel(data, columnNames) {
//	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	     };
	    
        productModel.setColumnIdentifiers(columnNames);
	    productTable = new JTable(productModel);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        productTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        productTable.setRowHeight(20);
        productTable.addMouseListener(this);
        
        TableColumnModel columnModel = productTable.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        
        productTable.setBorder(new LineBorder(borderColor, 2, false));
        productTable.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.getTableHeader().setBackground(borderColor);
        productTable.getTableHeader().setForeground(Color.white);
        
		scrollPane_1.setViewportView(productTable);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new MatteBorder(2, 2, 2, 2, borderColor));
		panel_7.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panel_4.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] {10, 280};
		gbl_panel_7.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{1.0, 1.0};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Thông tin sản phẩm");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.gridwidth = 2;
		gbc_lblNewLabel_6.insets = new Insets(5, 0, 10, 0);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 0;
		panel_7.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Mã sản phẩm:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.weighty = 0.2;
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 1;
		panel_7.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Tên sản phẩm:");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 1;
		panel_7.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		productIdTxt = new JTextField();
		productIdTxt.setForeground(textColor);
		productIdTxt.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		productIdTxt.setMinimumSize(new Dimension(80, 20));
		productIdTxt.setFocusable(false);
		productIdTxt.setEditable(false);
		GridBagConstraints gbc_productIdTxt = new GridBagConstraints();
		gbc_productIdTxt.weighty = 0.2;
		gbc_productIdTxt.ipady = 5;
		gbc_productIdTxt.anchor = GridBagConstraints.WEST;
		gbc_productIdTxt.insets = new Insets(0, 5, 5, 5);
		gbc_productIdTxt.gridx = 0;
		gbc_productIdTxt.gridy = 2;
		panel_7.add(productIdTxt, gbc_productIdTxt);
		productIdTxt.setColumns(10);
		
		productNameTxt = new JTextField();
		productNameTxt.setForeground(textColor);
		productNameTxt.setBorder(new LineBorder(borderColor, 2, true));
		productNameTxt.setEditable(false);
		productNameTxt.setFocusable(false);
		GridBagConstraints gbc_productNameTxt = new GridBagConstraints();
		gbc_productNameTxt.ipady = 5;
		gbc_productNameTxt.insets = new Insets(0, 20, 5, 5);
		gbc_productNameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_productNameTxt.gridx = 1;
		gbc_productNameTxt.gridy = 2;
		panel_7.add(productNameTxt, gbc_productNameTxt);
		productNameTxt.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Số lượng:");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.weighty = 0.2;
		gbc_lblNewLabel_10.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_10.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 3;
		panel_7.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JLabel lblNewLabel_9 = new JLabel("Giá nhập:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 20, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 3;
		panel_7.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		quantityTxt = new JSpinner();
		quantityTxt.setForeground(textColor);
		quantityTxt.setBorder(new LineBorder(borderColor, 2, true));
		GridBagConstraints gbc_quantityTxt = new GridBagConstraints();
		gbc_quantityTxt.weighty = 0.2;
		gbc_quantityTxt.anchor = GridBagConstraints.WEST;
		gbc_quantityTxt.ipady = 5;
		gbc_quantityTxt.ipadx = 50;
		gbc_quantityTxt.insets = new Insets(0, 5, 5, 5);
		gbc_quantityTxt.gridx = 0;
		gbc_quantityTxt.gridy = 4;
		panel_7.add(quantityTxt, gbc_quantityTxt);
		
		purchasePriceTxt = new JTextField();
		purchasePriceTxt.setForeground(textColor);
		purchasePriceTxt.setBorder(new LineBorder(borderColor, 2, true));
		purchasePriceTxt.setFocusable(false);
		purchasePriceTxt.setEditable(false);
		GridBagConstraints gbc_purchasePriceTxt = new GridBagConstraints();
		gbc_purchasePriceTxt.ipady = 5;
		gbc_purchasePriceTxt.insets = new Insets(0, 20, 5, 5);
		gbc_purchasePriceTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_purchasePriceTxt.gridx = 1;
		gbc_purchasePriceTxt.gridy = 4;
		panel_7.add(purchasePriceTxt, gbc_purchasePriceTxt);
		purchasePriceTxt.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 3;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		invoiceDetailPanel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		closePayment = new JLabel("");
		closePayment.addMouseListener(this);
		closePayment.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ImageIcon closeIcon = new ImageIcon("Assets/Icon/close.png");
		Image imageClose = closeIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		closeIcon = new ImageIcon(imageClose);
		closePayment.setIcon(closeIcon);
		closePayment.setVerticalAlignment(SwingConstants.BOTTOM);
		closePayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPanel("invoicePanel");
			}
		});
		GridBagConstraints gbc_closePayment = new GridBagConstraints();
		gbc_closePayment.anchor = GridBagConstraints.NORTHEAST;
		gbc_closePayment.insets = new Insets(0, 0, 5, 0);
		gbc_closePayment.gridx = 1;
		gbc_closePayment.gridy = 0;
		panel_2.add(closePayment, gbc_closePayment);
		
		JLabel lblNewLabel = new JLabel("Thông tin phiếu nhập");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(5, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã phiếu nhập: ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(7, 5, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new LineBorder(borderColor, 2, true));
		textField_1.setForeground(textColor);
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.ipady = 7;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(10, 5, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 3;
		panel_2.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nhân viên nhập:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(10, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		comboBoxStaff = new JComboBox();
		comboBoxStaff.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		comboBoxStaff.setBackground(Color.WHITE);
		comboBoxStaff.setForeground(textColor);
		for (StaffDTO staff : listStaff) {			
			comboBoxStaff.addItem(staff.getperson().getName());
		    staffMap.put(staff.getperson().getName(), staff.getEmployee_ID());
		}
				
		GridBagConstraints gbc_comboBoxStaff = new GridBagConstraints();
		gbc_comboBoxStaff.ipady = 5;
		gbc_comboBoxStaff.gridwidth = 2;
		gbc_comboBoxStaff.insets = new Insets(10, 5, 5, 5);
		gbc_comboBoxStaff.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStaff.gridx = 0;
		gbc_comboBoxStaff.gridy = 5;
		panel_2.add(comboBoxStaff, gbc_comboBoxStaff);
		
		JLabel lblNewLabel_3 = new JLabel("Khách Hàng");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(10, 5, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 6;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		comboBoxCustomer = new JComboBox();
		comboBoxCustomer.setBorder(new LineBorder(borderColor, 1, true));
		comboBoxCustomer.setBackground(Color.WHITE);
		comboBoxCustomer.setForeground(textColor);
		for (CustomerDTO customer : listCustomer) {			
			comboBoxCustomer.addItem(customer.getPersonDTO().getName());
			customerMap.put(customer.getPersonDTO().getName(), customer.getCustomerID());
		}
		GridBagConstraints gbc_comboBoxCustomer = new GridBagConstraints();
		gbc_comboBoxCustomer.ipady = 5;
		gbc_comboBoxCustomer.gridwidth = 2;
		gbc_comboBoxCustomer.insets = new Insets(10, 5, 5, 5);
		gbc_comboBoxCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCustomer.gridx = 0;
		gbc_comboBoxCustomer.gridy = 7;
		panel_2.add(comboBoxCustomer, gbc_comboBoxCustomer);
		
		JLabel lblNewLabel_4 = new JLabel("TỔNG TIỀN: ");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(260, 5, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 8;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		totalPriceLbl = new JLabel("0đ");
		totalPriceLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GridBagConstraints gbc_totalPriceLbl = new GridBagConstraints();
		gbc_totalPriceLbl.insets = new Insets(260, 0, 5, 0);
		gbc_totalPriceLbl.gridx = 1;
		gbc_totalPriceLbl.gridy = 8;
		panel_2.add(totalPriceLbl, gbc_totalPriceLbl);
		
		paymentBtn = new JButton("Thanh Toán");
     	Image iconReceipt = new ImageIcon("Assets/Icon/receipt.png").getImage();
     	iconReceipt = iconReceipt.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     	paymentBtn.setIcon(new ImageIcon(iconReceipt));
		paymentBtn.setBackground(buttonColor);
		paymentBtn.setForeground(Color.WHITE);
		paymentBtn.setPreferredSize(new Dimension(150, 35));
		paymentBtn.addMouseListener(this);
		GridBagConstraints gbc_paymentBtn = new GridBagConstraints();
		gbc_paymentBtn.ipady = 10;
		gbc_paymentBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_paymentBtn.insets = new Insets(30, 5, 0, 5);
		gbc_paymentBtn.gridwidth = 2;
		gbc_paymentBtn.gridx = 0;
		gbc_paymentBtn.gridy = 9;
		panel_2.add(paymentBtn, gbc_paymentBtn);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 1;
		invoiceDetailPanel.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		addProductBtn = new JButton("Thêm Sản Phẩm");
		addProductBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addProductBtn.setBorder(new LineBorder(borderColor, 2, true));
		addProductBtn.setText("Thêm");
		iconAdd = iconAdd.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		addProductBtn.setPreferredSize(new Dimension(150, 35));
		addProductBtn.setBackground(buttonColor);
		addProductBtn.setForeground(Color.white);
		addProductBtn.setFocusPainted(false);
		addProductBtn.setIcon(new ImageIcon(iconAdd));
		addProductBtn.addMouseListener(this);
		panel_5.add(addProductBtn);
		
		editProductBtn = new JButton("Sửa Sản Phẩm");
		editProductBtn.addMouseListener(this);
		editProductBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		editProductBtn.setBorder(new LineBorder(borderColor, 2, true));
		editProductBtn.setText("Sửa");
     	Image iconEdit = new ImageIcon("Assets/Icon/edit.png").getImage();
     	iconEdit = iconEdit.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     	editProductBtn.setPreferredSize(new Dimension(150, 35));
     	editProductBtn.setBackground(buttonColor);
     	editProductBtn.setForeground(Color.white);
		editProductBtn.setFocusPainted(false);
		editProductBtn.setIcon(new ImageIcon(iconEdit));
		panel_5.add(editProductBtn);
		
		deleteProductBtn = new JButton("Xóa Sản Phẩm");
		deleteProductBtn.addMouseListener(this);
	  	deleteProductBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
     	deleteProductBtn.setBorder(new LineBorder(borderColor, 2, true));
     	deleteProductBtn.setText("Xóa");
     	iconDelete = iconDelete.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
     	deleteProductBtn.setPreferredSize(new Dimension(150, 35));
     	deleteProductBtn.setBackground(buttonColor);
     	deleteProductBtn.setForeground(Color.white);
     	deleteProductBtn.setFocusPainted(false);
     	deleteProductBtn.setIcon(new ImageIcon(iconDelete));
		panel_5.add(deleteProductBtn);
		
		refreshProductBtn = new JButton("Mới");
		refreshProductBtn.addMouseListener(this);
     	refreshProductBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
     	refreshProductBtn.setBorder(new LineBorder(borderColor, 2, true));
     	refreshProductBtn.setText("Mới");
     	iconRefresh = iconRefresh.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		refreshProductBtn.setPreferredSize(new Dimension(150, 35));
		refreshProductBtn.setBackground(buttonColor);
		refreshProductBtn.setForeground(Color.white);
		refreshProductBtn.setFocusPainted(false);
		refreshProductBtn.setIcon(new ImageIcon(iconRefresh));
		panel_5.add(refreshProductBtn);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(5, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 2;
		invoiceDetailPanel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 0;
		gbc_scrollPane_2.gridy = 0;
		panel_3.add(scrollPane_2, gbc_scrollPane_2);
		
	     String[] columnNamesCart = {"Mã SP", "Tên Sản Phẩm", "Loại SP", "Giá Bán", "Số Lượng"};
	     Object[][] dataCart = {
	    		 
	     };
	     
	     cartModel = new DefaultTableModel(dataCart, columnNamesCart) {
//	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
        cartModel.setColumnIdentifiers(columnNamesCart);
        cartTable = new JTable(cartModel);	
        cartTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        cartTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        cartTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        cartTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        cartTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		cartTable.setRowHeight(20);
		cartTable.addMouseListener(this);
        TableColumnModel columnModelCart = cartTable.getColumnModel();
        columnModelCart.getColumn(0).setCellRenderer(centerRenderer);
        columnModelCart.getColumn(1).setCellRenderer(centerRenderer);
        columnModelCart.getColumn(2).setCellRenderer(centerRenderer);
        columnModelCart.getColumn(3).setCellRenderer(centerRenderer);
        columnModelCart.getColumn(4).setCellRenderer(centerRenderer);
        
        cartTable.setBorder(new LineBorder(borderColor, 2, false));
        cartTable.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
        cartTable.getTableHeader().setReorderingAllowed(false);
        cartTable.getTableHeader().setBackground(borderColor);
        cartTable.getTableHeader().setForeground(Color.white);
		scrollPane_2.setViewportView(cartTable);
	}

   public void showPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, panelName);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
        if (e.getSource() == addInvoiceBtn) {
        	loadDataProductTable(listProduct);
            showPanel("invoiceDetailPanel");  
        } else if (e.getSource() == productTable) {
        	displaySelectedProduct();        	
        } else if (e.getSource() == addProductBtn) {
        	addToCart();
        } else if (e.getSource() == editProductBtn) {
        	editProductInCart();
        } else if (e.getSource() == deleteProductBtn) {
        	deleteProductFromCart();
        } else if (e.getSource() == cartTable) {
        	selectProductInCart();
        } else if (e.getSource() == paymentBtn){
        	payment();
            loadDataInvoiceTable(listInvoice);
            listCart.removeAll(listCart);
        	loadDataCartTable(listCart);
        	resetForm();
            totalPriceLbl.setText("0đ");            	
        } else if (e.getSource() == deleteInvoiceBtn) {
        	deleteInvoice();
        } else if (e.getSource() == refreshReceiptBtn || e.getSource() == refreshProductBtn || e.getSource() == closePayment) {   
        	loadDataInvoiceTable(listInvoice);
        	loadDataCartTable(listCart);
        	loadDataProductTable(listProduct);        	
        	listCart.removeAll(listCart);
        	resetForm();
            totalPriceLbl.setText("0đ");            	
        } else if (e.getSource() == detaiInvoiceDetailBtn) {
        	int index = invoiceTable.getSelectedRow();
        	if (index == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn để xem chi tiết!");
        	} else {
        		InvoiceDetail invoiceDetailDialog = new InvoiceDetail(listInvoice.get(index).getInvoiceId());        		
        		invoiceDetailDialog.setVisible(true);
        	}        	
        } else if (e.getSource() == exportExcelBtn) {
        	try {
        		ExcelExporter excel = new ExcelExporter();
            	excel.exportJTableToExcel(invoiceTable);
    		} catch (Exception e2) {
                JOptionPane.showMessageDialog(this, "Lỗi Xuất Excel !");
    		}
        }
	}
	public void loadDataProductTable(ArrayList<ProductDTO> result) {
		productModel.setRowCount(0);	
		for (ProductDTO p : result) {
			productModel.addRow(new Object[] {
					p.getProductId(), p.getProductName(), p.getQuantity(), Formater.FormatVND(p.getPurchasePrice())
			});
		}		
	}
	
	public void loadDataCartTable(ArrayList<ProductDTO> result) {
		cartModel.setRowCount(0);	
		for (ProductDTO p : result) {
			cartModel.addRow(new Object[] {
					p.getProductId(), p.getProductName(), listCategory.get(categoryBUS.getIndexById(p.getCategoryId())).getCategoryName(), Formater.FormatVND(p.getPurchasePrice()), p.getQuantity()
			});
		}		
	}
	
	public void loadDataInvoiceTable(ArrayList<InvoiceDTO> result) {
		invoiceModel.setRowCount(0);	
		for (InvoiceDTO r : result) {
			invoiceModel.addRow(new Object[] {
					r.getInvoiceId(), listCustomer.get(customerBUS.getIndexById(r.getCustomerId())).getPersonDTO().getName()
					, listStaff.get(staffBUS.getIndexById(r.getStaffId())).getperson().getName(), Formater.FormatTime(r.getDate()), Formater.FormatVND(r.getTotalPrice()),
			});
		}		
	}
	
	private void resetForm() {
		productIdTxt.setText("");
        productNameTxt.setText("");
        purchasePriceTxt.setText("");
        quantityTxt.setValue(0);
        findProductTxt.setText("");
        findReceiptTxt.setText("");
//    	SupplierBUS supplierBUS = new SupplierBUS();
//    	ArrayList<SupplierDTO> listSupp = supplierBUS.getALL();
//    	StaffBUS staffBUS = new StaffBUS();
//    	ArrayList<StaffDTO> listStaff = staffBUS.getALL();
//    	comboBoxSupplier.removeAllItems();
//		for (SupplierDTO supplier : listSupp) {
//			comboBoxSupplier.addItem(supplier.getSupplierName());
//		}
//    	comboBoxStaff.removeAllItems();
//		for (StaffDTO staff : listStaff) {
//			comboBoxStaff.addItem(staff.getperson().getName());
//		}
	}
	
    private void displaySelectedProduct() {
        int index = productTable.getSelectedRow();
        productIdTxt.setText(String.valueOf(listProduct.get(index).getProductId()));
        productNameTxt.setText(listProduct.get(index).getProductName());		        
        purchasePriceTxt.setText(String.valueOf(Formater.FormatVND(listProduct.get(index).getPurchasePrice())));
        indexMap.put(listProduct.get(index).getProductId(), index);

    }
    
    private void selectProductInCart() {
        int index = cartTable.getSelectedRow();
        productIdTxt.setText(String.valueOf(listCart.get(index).getProductId()));
        productNameTxt.setText(listCart.get(index).getProductName());		
        purchasePriceTxt.setText(String.valueOf(Formater.FormatMoney(listCart.get(index).getPurchasePrice())));
        quantityTxt.setValue(listCart.get(index).getQuantity());
    }
    
    private ProductDTO setInfo(JTable table, ArrayList<ProductDTO> list) {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
        }
        ProductDTO product = new ProductDTO();
        product.setProductId(list.get(index).getProductId());
        product.setProductName(list.get(index).getProductName());
        product.setCategoryId(list.get(index).getCategoryId());
        product.setPurchasePrice(list.get(index).getPurchasePrice());    
        product.setQuantity((Integer) quantityTxt.getValue());
        return product;
    }
    
    private void addToCart() {
    	ProductDTO product = setInfo(productTable, listProduct);
        boolean duplicate = false;
        int quantityInProduct = listProduct.get(productTable.getSelectedRow()).getQuantity();
        for (ProductDTO p : listCart) {
            if (p.getProductId() == product.getProductId()) {
                // Sản phẩm đã tồn tại trong giỏ hàng
                int newQuantity = p.getQuantity() + product.getQuantity();
                
                // Kiểm tra số lượng mới có vượt quá số lượng trong danh sách sản phẩm hay không
                if (newQuantity <= quantityInProduct) {
                    // Cập nhật số lượng trong giỏ hàngs
                    p.setQuantity(newQuantity);
                } 
                else {
                	int quantityRemain = quantityInProduct - p.getQuantity();
                    JOptionPane.showMessageDialog(this, "Số lượng của" + product.getProductName() + " không đủ\n" + "Số lượng còn lại: " + quantityRemain 
                    +"\n" + "Vui lòng nhập lại số lượng !");
                }
                
                duplicate = true;
                break;
            }
        }

        if (!duplicate) {
        	if (product.getQuantity() < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng không âm!");
        	} else if (listProduct.get(productTable.getSelectedRow()).getQuantity() >= product.getQuantity()) {
                listCart.add(product);
            }
        	else {
                JOptionPane.showMessageDialog(this, "Số lượng của" + product.getProductName() + " không đủ\n" + "Số lượng còn lại: " + quantityInProduct 
                																+ "\n"+ "Vui lòng nhập lại số lượng !");
            }
        }   
	
        loadDataCartTable(listCart);  		
        setTotalPrice(listCart);
        resetForm();
    }
    
    private void editProductInCart() {
    	ProductDTO productInCart = setInfo(cartTable, listCart);
        boolean duplicate = false;
        int quantityInProduct = listProduct.get(productTable.getSelectedRow()).getQuantity();
        for (ProductDTO p : listCart) {
            if (p.getProductId() == productInCart.getProductId()) {
                // Sản phẩm đã tồn tại trong giỏ hàng
                int newQuantity = p.getQuantity() + productInCart.getQuantity();
                
                // Kiểm tra số lượng mới có vượt quá số lượng trong danh sách sản phẩm hay không
                if (newQuantity <= quantityInProduct) {
                    // Cập nhật số lượng trong giỏ hàngs
                    p.setQuantity(newQuantity);
                } 
                else {
                	int quantityRemain = quantityInProduct - p.getQuantity();
                    JOptionPane.showMessageDialog(this, "Số lượng của" + productInCart.getProductName() + " không đủ\n" + "Số lượng còn lại: " + quantityRemain 
                    +"\n" + "Vui lòng nhập lại số lượng !");
                }
                
                duplicate = true;
                break;
            }
        }

        if (!duplicate) {
        	if (productInCart.getQuantity() < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng không âm!");
        	} else if (listProduct.get(productTable.getSelectedRow()).getQuantity() >= productInCart.getQuantity()) {
                listCart.add(productInCart);
            } 
        	else {
                JOptionPane.showMessageDialog(this, "Số lượng của" + productInCart.getProductName() + " không đủ\n" + "Số lượng còn lại: " + quantityInProduct 
                																+ "\n"+ "Vui lòng nhập lại số lượng !");
            }
        }   
	
        loadDataCartTable(listCart);  		
        setTotalPrice(listCart);
        resetForm();
    }
    
    private void deleteProductFromCart() {
    	ProductDTO productInCart = setInfo(cartTable, listCart);    	    
    	listCart.remove(productInCart);
    	loadDataCartTable(listCart);
        setTotalPrice(listCart);
    	resetForm();
    	
    }
    
    private void setTotalPrice(ArrayList<ProductDTO> list) {
        double total = 0;
        for (ProductDTO p : list) {
        	total += p.getPurchasePrice() * p.getQuantity();
        }
        totalPriceLbl.setText(Formater.FormatVND(total));
    }
    
    private void payment() {
		int invoiceId = InvoiceDAO.getInstance().getAutoIncrement();
    	if (listCart.isEmpty()) {
    		JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong giỏ hảng !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
        	showPanel("invoiceDetailPanel");
    	} else {
    		int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn tạo hóa đơn !", "Xác nhận tạo hóa đơn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
    		if (input == 0) {
    			String selectedStaffName = (String) comboBoxStaff.getSelectedItem();
    			int staffId = staffMap.get(selectedStaffName);
    			String slectedCustomerName = (String) comboBoxCustomer.getSelectedItem();
    			int customerId = customerMap.get(slectedCustomerName);
    			long now = System.currentTimeMillis();
                Timestamp date = new Timestamp(now);
                String totalPriceSub = totalPriceLbl.getText().replace("đ", "").replace(",", "");
                double totalPrice = Double.parseDouble(totalPriceSub);
                System.out.println(totalPriceSub);
                InvoiceDTO invoice = new InvoiceDTO(invoiceId, customerId, staffId, date, totalPrice);                              
                boolean result = invoiceBUS.add(invoice);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công !");
                    //load table o ngoai panel
                    //load giao dien reciept
                } else {
                    JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công !", "Cảnh báo !", JOptionPane.ERROR_MESSAGE);
                	showPanel("invoiceDetailPanel");
                }
    		}
    	}
    	addInvoiceDetail(invoiceId);
    	resetForm();
    	showPanel("invoicePanel");
    }
    
    public void addInvoiceDetail(int invoiceId) {
    	for (ProductDTO p : listCart) {
    		int prodcutId = p.getProductId();
    		int quantity = p.getQuantity();            
    		double prucharsePrice = p.getPurchasePrice();
    		InvoiceDetailDTO invoiceDetail = new InvoiceDetailDTO(invoiceId, prodcutId, quantity, prucharsePrice);
    		invoiceBUS.add(invoiceDetail);  
    		//Sửa đổi số lượng sản phẩm khi thanh toán
    	    for (Map.Entry<Integer, Integer> index : indexMap.entrySet()) {    	 
    	    	if (prodcutId == index.getKey()) {
    	    		int newQuantity = listProduct.get(index.getValue()).getQuantity() - quantity;
    	    		ProductDTO product = new ProductDTO();
    	    		product.setProductId(prodcutId);
    	    		product.setProductName(listProduct.get(index.getValue()).getProductName());
    	    		product.setCategoryId(listProduct.get(index.getValue()).getCategoryId());
    	    		product.setPurchasePrice(listProduct.get(index.getValue()).getPurchasePrice());
    	    		product.setSellingPrice(listProduct.get(index.getValue()).getSellingPrice());
    	    		product.setImgURL(listProduct.get(index.getValue()).getImgURL());
    	    		product.setDescription(listProduct.get(index.getValue()).getDescription());
    	    		product.setQuantity(newQuantity);
    	    		productBUS.update(product);    	    		
    	    	}
    	    }

    	}    
    }
    private void deleteInvoice() {
        int index = invoiceTable.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn!");
        }
    	invoiceBUS.delete(listInvoice.get(index));
        loadDataInvoiceTable(listInvoice);
        
    }
    
	public void filterInvoiceTable(String searchText) {
	    searchText = searchText.toLowerCase();
	    ArrayList<InvoiceDTO> filteredList = new ArrayList<>();

	    for (InvoiceDTO r : listInvoice) {	    
	        if (String.valueOf(r.getInvoiceId()).toLowerCase().contains(searchText)
	        		|| listCustomer.get(customerBUS.getIndexById(r.getCustomerId())).getPersonDTO().getName().toLowerCase().contains(searchText)){
	            filteredList.add(r);
	        }	        
	    }

	    loadDataInvoiceTable(filteredList);
	}
	
	public void filterProductTable(String searchText) {
	    searchText = searchText.toLowerCase();
	    ArrayList<ProductDTO> filteredList = new ArrayList<>();

	    for (ProductDTO p : listProduct) {
	        if (p.getProductName().toLowerCase().contains(searchText) || String.valueOf(p.getProductId()).toLowerCase().contains(searchText) 
	        		|| listCategory.get(categoryBUS.getIndexById(p.getCategoryId())).getCategoryName().toLowerCase().contains(searchText)) {
	            filteredList.add(p);
	        }
	    }

	    loadDataProductTable(filteredList);
	}
	
	public void resetCombo() {
    	SupplierBUS supplierBUS = new SupplierBUS();
    	ArrayList<SupplierDTO> listSupp = supplierBUS.getALL();
    	StaffBUS staffBUS = new StaffBUS();
    	ArrayList<StaffDTO> listStaff = staffBUS.getALL();
    	comboBoxCustomer.removeAllItems();
		for (CustomerDTO customer : listCustomer) {
			comboBoxCustomer.addItem(customer.getPersonDTO().getName());
		}
    	comboBoxStaff.removeAllItems();
		for (StaffDTO staff : listStaff) {
			comboBoxStaff.addItem(staff.getperson().getName());
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
        try {
        	filterInvoiceTable(findReceiptTxt.getText());
        	filterProductTable(findProductTxt.getText());
        } catch (Exception  ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
