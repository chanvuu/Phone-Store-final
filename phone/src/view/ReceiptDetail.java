package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import bus.ProductBUS;
import bus.ReceiptBUS;
import bus.StaffBUS;
import bus.SupplierBUS;
import dao.ReceiptDAO;
import dao.ReceiptDetailDAO;
import dto.ProductDTO;
import dto.ReceiptDTO;
import dto.ReceiptDetailDTO;
import dto.StaffDTO;
import dto.SupplierDTO;
import service.Formater;
import service.WritePDF;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import java.awt.ComponentOrientation;

public class ReceiptDetail extends JDialog implements MouseListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField receiptTxt;
	private JTextField staffTxt;
	private JTextField supplierTxt;
	private JTextField dateTxt;
	private JTable table;
	private DefaultTableModel model;
	private JButton okButton, cancelButton;
	ReceiptDTO receipt;
	
	ReceiptDetailDAO receiptDetailDAO = new ReceiptDetailDAO();
	ArrayList<ReceiptDetailDTO> listReceiptDetail = receiptDetailDAO.selectAll();
	
	ProductBUS productBUS = new ProductBUS();
	ArrayList<ProductDTO> listProduct = productBUS.getALL();
	
	ReceiptBUS receiptBUS = new ReceiptBUS();
	ArrayList<ReceiptDTO> listReceipt = receiptBUS.getAll();
	
	SupplierBUS supplierBUS = new SupplierBUS();
	ArrayList<SupplierDTO> listSupplier = supplierBUS.getALL();
	
	StaffBUS staffBUS = new StaffBUS();
	ArrayList<StaffDTO> listStaff = staffBUS.getALL();
	private int receiptId;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReceiptDetail dialog = new ReceiptDetail();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReceiptDetail() {
		initComponet();
		model.setRowCount(0);
		for (ReceiptDetailDTO rd : listReceiptDetail) {
			int index = productBUS.getIndexById(rd.getProductId());
			model.addRow(new Object[] {
					rd.getReceiptId(), listProduct.get(index).getProductName(), rd.getQuantity(), Formater.FormatVND(rd.getPurchasePrice())
			});
		}
	}
	
	public ReceiptDetail(int receiptId) {
		this.receiptId = receiptId;
		initComponet();
		loadData(receiptId);
		setInfo(receiptId);
	}

	public void initComponet() {
		Color myColor = new Color(34, 33, 75);
		Color backGroundColor = Color.white;
		Color borderColor = myColor;
		Color textColor = myColor;
		Color buttonColor = myColor;
        this.setSize(new Dimension(1100, 500));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_1 = new JLabel("Mã Phiếu Nhập");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 10, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Nhân Viên Nhập");
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 10, 5, 5);
			gbc_lblNewLabel_2.gridx = 1;
			gbc_lblNewLabel_2.gridy = 0;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Nhà Cung Cấp");
			lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 10, 5, 5);
			gbc_lblNewLabel_3.gridx = 2;
			gbc_lblNewLabel_3.gridy = 0;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			JLabel datelbl = new JLabel("Thời Gian Tạo");
			datelbl.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			GridBagConstraints gbc_datelbl = new GridBagConstraints();
			gbc_datelbl.anchor = GridBagConstraints.WEST;
			gbc_datelbl.insets = new Insets(0, 10, 5, 0);
			gbc_datelbl.gridx = 3;
			gbc_datelbl.gridy = 0;
			contentPanel.add(datelbl, gbc_datelbl);
		}
		{
			receiptTxt = new JTextField();
			receiptTxt.setBorder(new LineBorder(borderColor, 2, true));
			receiptTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			receiptTxt.setEditable(false);
			receiptTxt.setFocusable(false);
			GridBagConstraints gbc_receiptTxt = new GridBagConstraints();
			gbc_receiptTxt.ipady = 10;
			gbc_receiptTxt.insets = new Insets(0, 10, 5, 10);
			gbc_receiptTxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_receiptTxt.gridx = 0;
			gbc_receiptTxt.gridy = 1;
			contentPanel.add(receiptTxt, gbc_receiptTxt);
			receiptTxt.setColumns(10);
		}
		{
			staffTxt = new JTextField();
			staffTxt.setBorder(new LineBorder(borderColor, 2, true));
			staffTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			staffTxt.setFocusable(false);
			staffTxt.setEditable(false);
			GridBagConstraints gbc_staffTxt = new GridBagConstraints();
			gbc_staffTxt.ipady = 10;
			gbc_staffTxt.insets = new Insets(0, 10, 5, 10);
			gbc_staffTxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_staffTxt.gridx = 1;
			gbc_staffTxt.gridy = 1;
			contentPanel.add(staffTxt, gbc_staffTxt);
			staffTxt.setColumns(10);
		}
		{
			supplierTxt = new JTextField();
			supplierTxt.setBorder(new LineBorder(borderColor, 2, true));
			supplierTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			supplierTxt.setFocusable(false);
			supplierTxt.setEditable(false);
			GridBagConstraints gbc_supplierTxt = new GridBagConstraints();
			gbc_supplierTxt.ipady = 10;
			gbc_supplierTxt.insets = new Insets(0, 10, 5, 10);
			gbc_supplierTxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_supplierTxt.gridx = 2;
			gbc_supplierTxt.gridy = 1;
			contentPanel.add(supplierTxt, gbc_supplierTxt);
			supplierTxt.setColumns(10);
		}
		{
			dateTxt = new JTextField();
			dateTxt.setBorder(new LineBorder(borderColor, 2, true));
			dateTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			dateTxt.setEditable(false);
			dateTxt.setFocusable(false);
			GridBagConstraints gbc_dateTxt = new GridBagConstraints();
			gbc_dateTxt.ipady = 10;
			gbc_dateTxt.insets = new Insets(0, 10, 5, 10);
			gbc_dateTxt.fill = GridBagConstraints.HORIZONTAL;
			gbc_dateTxt.gridx = 3;
			gbc_dateTxt.gridy = 1;
			contentPanel.add(dateTxt, gbc_dateTxt);
			dateTxt.setColumns(10);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 4;
			gbc_panel.insets = new Insets(10, 0, 0, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 2;
			contentPanel.add(panel, gbc_panel);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setPreferredSize(new Dimension(1050, 300));
				panel.add(scrollPane);
				{
					String[] columnNames = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Giá Nhập"};
					Object[][] data = {

					};
					model = new DefaultTableModel();
					model = new DefaultTableModel(data, columnNames) {
			           @Override
			           public boolean isCellEditable(int row, int column) {
			               return false;
			           }
			       };
					model.setColumnIdentifiers(columnNames);
					table = new JTable(model);
					table.addMouseListener(this);
					table.setRowHeight(20);
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.CENTER);
					TableColumnModel columnModel = table.getColumnModel();
					columnModel.getColumn(0).setCellRenderer(centerRenderer);
					columnModel.getColumn(1).setCellRenderer(centerRenderer);
					columnModel.getColumn(2).setCellRenderer(centerRenderer);
					columnModel.getColumn(3).setCellRenderer(centerRenderer);
					
					table.setRowHeight(20);		
					table.getColumnModel().getColumn(0).setPreferredWidth(70);
					table.getColumnModel().getColumn(1).setPreferredWidth(200);
					table.getColumnModel().getColumn(2).setPreferredWidth(70);
					table.getColumnModel().getColumn(3).setPreferredWidth(200);

					table.setBorder(new LineBorder(borderColor, 2, false));
					table.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
					table.getTableHeader().setReorderingAllowed(false);
					table.getTableHeader().setBackground(borderColor);
					table.getTableHeader().setForeground(Color.white);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Xuất Hóa Đơn");
				okButton.setActionCommand("OK");
				okButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				okButton.setBorder(new LineBorder(borderColor, 2, true));
		     	Image icon = new ImageIcon("Assets/Icon/print.png").getImage();
		     	icon = icon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		     	okButton.setPreferredSize(new Dimension(120, 40));
		     	okButton.setBackground(buttonColor);
		     	okButton.setForeground(Color.white);
		     	okButton.setFocusPainted(false);
		     	okButton.setIcon(new ImageIcon(icon));
		     	okButton.addMouseListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Hủy Bỏ");
				cancelButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
				cancelButton.setBorder(new LineBorder(borderColor, 2, true));
				cancelButton.setPreferredSize(new Dimension(120, 40));
		     	cancelButton.setBackground(buttonColor);
		     	cancelButton.setForeground(Color.white);
		     	cancelButton.setFocusPainted(false);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(this);
				buttonPane.add(cancelButton);

			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(myColor);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("THÔNG TIN PHIẾU NHẬP");
				lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
				lblNewLabel.setForeground(Color.white);
				panel.add(lblNewLabel);
			}
		}
        this.setLocationRelativeTo(null);

	}
	
	public void setInfo(int receiptId) {
		int indexReceipt = receiptBUS.getIndexById(receiptId);
		int suppId = listReceipt.get(indexReceipt).getSupplierId();
		int staffId = listReceipt.get(indexReceipt).getStaffId();		
		String suppName = listSupplier.get(supplierBUS.getIndexById(suppId)).getSupplierName();
		String staffName = listStaff.get(staffBUS.getIndexById(staffId)).getperson().getName();

		receiptTxt.setText(String.valueOf(receiptId));
		supplierTxt.setText(suppName);
		staffTxt.setText(staffName);
	
		dateTxt.setText(Formater.FormatTime(listReceipt.get(indexReceipt).getDate()));
	}
	
	public void loadData(int receiptId) {
		model.setRowCount(0);
		for (ReceiptDetailDTO rd : listReceiptDetail) {
			if (rd.getReceiptId() == receiptId) {
				int index = productBUS.getIndexById(rd.getProductId());
				model.addRow(new Object[] {
						rd.getReceiptId(), listProduct.get(index).getProductName(), rd.getQuantity(), Formater.FormatVND(rd.getPurchasePrice())
				});
			}
		}	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == okButton) {
           WritePDF w = new WritePDF();
           System.out.println(receiptId);
           w.writeReceipt(receiptId);
		} else if (e.getSource() == cancelButton){
            dispose();
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
}
