package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import bus.CustomerBUS;
import bus.StaffBUS;
import dao.CustomerDAO;
import dto.CustomerDTO;
import dto.PersonDTO;
import dto.StaffDTO;
import service.ExcelExporter;
import service.Formater;
import service.Validation;

public class Customer extends JPanel implements MouseListener,KeyListener{
	private JTextField textField_customer_id;
	private JTextField textField_customer_name;
	private JTextField textField_phone;
	private JTextField textField_address;
	private JTextField textField_email;
	private JTextField textField_comment;
	private ButtonGroup buttonGroup;
	private DefaultTableModel customerModel;
	private JComboBox day_comboBox;
	private JComboBox month_comboBox;
	private JComboBox year_comboBox;
	
	private JButton btnAddCus;
	private JButton btnDeleteCus;
	private JButton btnEditCus;
	private JButton btnRefreshCus;
	private JRadioButton radioButton_customer_gender_male;
	private JRadioButton radioButton_customer_gender_female;
	private JDateChooser dateChooser;
	CustomerBUS customerBUS=new CustomerBUS();
	ArrayList<CustomerDTO> listCus =customerBUS.getALL();
	CustomerDAO cusDAO=new CustomerDAO();
	private JTable table;
	private JTextField textField_customer_searching;
	private JButton btnExportExcel;
	private JButton btnImportExcel;
	

	public Customer() {
		initComponet();
		loadDataTable(listCus);
		System.out.print(listCus);
	}
	
	public void initComponet() {
		Color myColor = new Color(34, 33, 75);
		Color borderColor = myColor;
		Color backGroundColor = Color.white;
		Color textColor = myColor;
		Color buttonColor = myColor;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{138, 96, 408, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 428, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_9 = new JLabel("Thông tin khách hàng");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.gridwidth = 2;
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 0;
		add(lblNewLabel_9, gbc_lblNewLabel_9);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel label_staff_adding_1 = new JLabel("Tìm kiếm");
		label_staff_adding_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_label_staff_adding_1 = new GridBagConstraints();
		gbc_label_staff_adding_1.anchor = GridBagConstraints.WEST;
		gbc_label_staff_adding_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_staff_adding_1.gridx = 1;
		gbc_label_staff_adding_1.gridy = 1;
		add(label_staff_adding_1, gbc_label_staff_adding_1);
		
		textField_customer_searching = new JTextField();
		textField_customer_searching.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_customer_searching.setColumns(10);
		textField_customer_searching.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_customer_searching = new GridBagConstraints();
		gbc_textField_customer_searching.insets = new Insets(0, 0, 5, 5);
		gbc_textField_customer_searching.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_customer_searching.gridx = 2;
		gbc_textField_customer_searching.gridy = 1;
		add(textField_customer_searching, gbc_textField_customer_searching);
		
		btnExportExcel = new JButton("Xuất excel");
		btnExportExcel.setPreferredSize(new Dimension(100, 40));
		btnExportExcel.setForeground(Color.WHITE);
		btnExportExcel.setFocusPainted(false);
		btnExportExcel.setBackground(new Color(34, 33, 75));
		GridBagConstraints gbc_btnImportExcel = new GridBagConstraints();
		gbc_btnImportExcel.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportExcel.gridx = 3;
		gbc_btnImportExcel.gridy = 1;
		add(btnExportExcel, gbc_btnImportExcel);
		
		btnImportExcel = new JButton("Nhập excel");
		btnImportExcel.setPreferredSize(new Dimension(100, 40));
		btnImportExcel.setForeground(Color.WHITE);
		btnImportExcel.setFocusPainted(false);
		btnImportExcel.setBackground(new Color(34, 33, 75));
		GridBagConstraints gbc_btnImportExcel1 = new GridBagConstraints();
		gbc_btnImportExcel1.insets = new Insets(0, 0, 5, 0);
		gbc_btnImportExcel1.gridx = 4;
		gbc_btnImportExcel1.gridy = 1;
		add(btnImportExcel, gbc_btnImportExcel1);
		
		JPanel panel_table_customer = new JPanel();

		GridBagConstraints gbc_panel_table_customer = new GridBagConstraints();
		gbc_panel_table_customer.gridwidth = 4;
		gbc_panel_table_customer.fill = GridBagConstraints.BOTH;
		gbc_panel_table_customer.gridx = 1;
		gbc_panel_table_customer.gridy = 2;
		add(panel_table_customer, gbc_panel_table_customer);
		panel_table_customer.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_table_customer.add(scrollPane, BorderLayout.CENTER);
		
		String[] columnNames ={"ID","Họ tên","Giới tính","Ngày sinh","Email","SĐT","Địa chỉ","Ghi chú"};
		 Object[][] data = {};
		
		customerModel=new DefaultTableModel();
		customerModel=new DefaultTableModel(data,columnNames) {
			  public boolean isCellEditable(int row, int column) {
	               return false;
	           }
		};
		customerModel.setColumnIdentifiers(columnNames);
		
		 table = new JTable(customerModel);
		 table.addMouseListener(this);
	       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	       centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	       TableColumnModel columnModel = table.getColumnModel();
	       columnModel.getColumn(0).setCellRenderer(centerRenderer);
	       columnModel.getColumn(1).setCellRenderer(centerRenderer);
	       table.setBorder(new LineBorder(borderColor, 2, false));
	       table.getTableHeader().setBorder(new LineBorder(borderColor, 2, false));
	       table.getTableHeader().setReorderingAllowed(false);
	       table.getTableHeader().setBackground(borderColor);
	       table.getTableHeader().setForeground(Color.white);
		
		
	
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		 panel.setBackground(backGroundColor);
		 panel.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(34, 33, 75)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{99, 119, 77, 61};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 20, 23, 0, 27, 34, 0, 96, 178, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		   Border lineBorder = BorderFactory.createLineBorder(Color.BLUE); // Line border
	        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder(); // Lowered bevel border
	        Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder(); // Raised bevel border
	        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		JLabel lblThmKhchHng = new JLabel("Thêm khách hàng mới");
		lblThmKhchHng.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_lblThmKhchHng = new GridBagConstraints();
		gbc_lblThmKhchHng.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblThmKhchHng.insets = new Insets(0, 5, 10, 5);
		gbc_lblThmKhchHng.gridx = 0;
		gbc_lblThmKhchHng.gridy = 0;
		panel.add(lblThmKhchHng, gbc_lblThmKhchHng);
//		label_customer_adding.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblMKhchHng = new GridBagConstraints();
		gbc_lblMKhchHng.anchor = GridBagConstraints.WEST;
		gbc_lblMKhchHng.insets = new Insets(0, 5, 5, 5);
		gbc_lblMKhchHng.gridx = 0;
		gbc_lblMKhchHng.gridy = 1;
		panel.add(lblMKhchHng, gbc_lblMKhchHng);
		
		JLabel label_customer_gender = new JLabel("Giới tính");
		label_customer_gender.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_customer_gender = new GridBagConstraints();
		gbc_label_customer_gender.anchor = GridBagConstraints.WEST;
		gbc_label_customer_gender.insets = new Insets(0, 0, 5, 5);
		gbc_label_customer_gender.gridx = 2;
		gbc_label_customer_gender.gridy = 1;
		panel.add(label_customer_gender, gbc_label_customer_gender);
		
		textField_customer_id = new JTextField();
		textField_customer_id.setEditable(false);
		textField_customer_id.setFocusable(false);
		textField_customer_id.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_customer_id = new GridBagConstraints();
		gbc_textField_customer_id.gridwidth = 2;
		gbc_textField_customer_id.ipady = 10;
		gbc_textField_customer_id.insets = new Insets(0, 5, 15, 5);
		gbc_textField_customer_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_customer_id.gridx = 0;
		gbc_textField_customer_id.gridy = 2;
		panel.add(textField_customer_id, gbc_textField_customer_id);
		textField_customer_id.setColumns(10);

	      
		radioButton_customer_gender_male = new JRadioButton("Nam");
		radioButton_customer_gender_male.setBackground(Color.WHITE);
		radioButton_customer_gender_male.setSelected(true);
		GridBagConstraints gbc_radioButton_customer_gender_male = new GridBagConstraints();
		gbc_radioButton_customer_gender_male.anchor = GridBagConstraints.WEST;
		gbc_radioButton_customer_gender_male.ipady = 10;
		gbc_radioButton_customer_gender_male.insets = new Insets(0, 0, 15, 5);
		gbc_radioButton_customer_gender_male.gridx = 2;
		gbc_radioButton_customer_gender_male.gridy = 2;
		panel.add(radioButton_customer_gender_male, gbc_radioButton_customer_gender_male);
		
		radioButton_customer_gender_female = new JRadioButton("Nữ");
 		radioButton_customer_gender_female.setBackground(Color.WHITE);
		GridBagConstraints gbc_radioButton_customer_gender_female = new GridBagConstraints();
		gbc_radioButton_customer_gender_female.anchor = GridBagConstraints.WEST;
		gbc_radioButton_customer_gender_female.insets = new Insets(0, 0, 15, 0);
		gbc_radioButton_customer_gender_female.gridx = 3;
		gbc_radioButton_customer_gender_female.gridy = 2;
		panel.add(radioButton_customer_gender_female, gbc_radioButton_customer_gender_female);
		
		
		buttonGroup = new ButtonGroup();
		 buttonGroup.add(radioButton_customer_gender_male);
		 buttonGroup.add(radioButton_customer_gender_female);
	       
	       
		JLabel lblHTnKhch = new JLabel("Họ tên khách hàng");
		lblHTnKhch.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblHTnKhch = new GridBagConstraints();
		gbc_lblHTnKhch.anchor = GridBagConstraints.WEST;
		gbc_lblHTnKhch.insets = new Insets(0, 5, 5, 5);
		gbc_lblHTnKhch.gridx = 0;
		gbc_lblHTnKhch.gridy = 3;
		panel.add(lblHTnKhch, gbc_lblHTnKhch);
		
		JLabel label_phone = new JLabel("Số điện thoại");
		label_phone.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_phone = new GridBagConstraints();
		gbc_label_phone.anchor = GridBagConstraints.WEST;
		gbc_label_phone.insets = new Insets(0, 0, 5, 5);
		gbc_label_phone.gridx = 2;
		gbc_label_phone.gridy = 3;
		panel.add(label_phone, gbc_label_phone);
		
		textField_customer_name = new JTextField();
		textField_customer_name.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_customer_name = new GridBagConstraints();
		gbc_textField_customer_name.gridwidth = 2;
		gbc_textField_customer_name.ipady = 10;
		gbc_textField_customer_name.insets = new Insets(0, 5, 15, 5);
		gbc_textField_customer_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_customer_name.gridx = 0;
		gbc_textField_customer_name.gridy = 4;
		panel.add(textField_customer_name, gbc_textField_customer_name);
		textField_customer_name.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_phone = new GridBagConstraints();
		gbc_textField_phone.gridwidth = 2;
		gbc_textField_phone.ipady = 10;
		gbc_textField_phone.insets = new Insets(0, 0, 15, 0);
		gbc_textField_phone.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_phone.gridx = 2;
		gbc_textField_phone.gridy = 4;
		panel.add(textField_phone, gbc_textField_phone);
		textField_phone.setColumns(10);
		
		JLabel label_Address = new JLabel("Địa chỉ");
		label_Address.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_Address = new GridBagConstraints();
		gbc_label_Address.anchor = GridBagConstraints.WEST;
		gbc_label_Address.insets = new Insets(0, 5, 5, 5);
		gbc_label_Address.gridx = 0;
		gbc_label_Address.gridy = 5;
		panel.add(label_Address, gbc_label_Address);
		
		
		
		JLabel label_email = new JLabel("Email");
		label_email.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_email = new GridBagConstraints();
		gbc_label_email.anchor = GridBagConstraints.WEST;
		gbc_label_email.insets = new Insets(0, 5, 5, 5);
		gbc_label_email.gridx = 2;
		gbc_label_email.gridy = 5;
		panel.add(label_email, gbc_label_email);
		
	
		
		textField_address = new JTextField();
		textField_address.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_address = new GridBagConstraints();
		gbc_textField_address.gridwidth = 2;
		gbc_textField_address.ipady = 10;
		gbc_textField_address.insets = new Insets(0, 5, 15, 5);
		gbc_textField_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_address.gridx = 0;
		gbc_textField_address.gridy = 6;
		panel.add(textField_address, gbc_textField_address);
		textField_address.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_email = new GridBagConstraints();
		gbc_textField_email.gridwidth = 2;
		gbc_textField_email.ipady = 10;
		gbc_textField_email.insets = new Insets(0, 5, 15, 0);
		gbc_textField_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_email.gridx = 2;
		gbc_textField_email.gridy = 6;
		panel.add(textField_email, gbc_textField_email);
		textField_email.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ngày sinh");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 7;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
//		String []day_list=new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
//		String []month_list=new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
//		String []year_list=new String[]{"2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
//		 day_comboBox = new JComboBox(day_list);
		
		dateChooser=new  JDateChooser();
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(320,90,200,30);
		dateChooser.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_day_comboBox = new GridBagConstraints();
		gbc_day_comboBox.gridwidth = 2;
		gbc_day_comboBox.insets = new Insets(0, 5, 5, 5);
		gbc_day_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_day_comboBox.gridx = 0;
		gbc_day_comboBox.gridy = 8;
		panel.add(dateChooser, gbc_day_comboBox);
		
//	month_comboBox = new JComboBox(month_list);
//	month_comboBox.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
//		GridBagConstraints gbc_month_comboBox = new GridBagConstraints();
//		gbc_month_comboBox.insets = new Insets(0, 0, 5, 5);
//		gbc_month_comboBox.fill = GridBagConstraints.HORIZONTAL;
//		gbc_month_comboBox.gridx = 1;
//		gbc_month_comboBox.gridy = 12;
//		panel.add(month_comboBox, gbc_month_comboBox);
		
//	 year_comboBox = new JComboBox(year_list);
//	 year_comboBox.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
//		GridBagConstraints gbc_month_comboBox_1 = new GridBagConstraints();
//		gbc_month_comboBox_1.gridwidth = 2;
//		gbc_month_comboBox_1.insets = new Insets(0, 0, 5, 5);
//		gbc_month_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
//		gbc_month_comboBox_1.gridx = 2;
//		gbc_month_comboBox_1.gridy = 12;
//		panel.add(year_comboBox, gbc_month_comboBox_1);
		
		JLabel label_comment = new JLabel("Ghi chú");
		label_comment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_comment = new GridBagConstraints();
		gbc_label_comment.anchor = GridBagConstraints.WEST;
		gbc_label_comment.insets = new Insets(0, 5, 5, 5);
		gbc_label_comment.gridx = 0;
		gbc_label_comment.gridy = 9;
		panel.add(label_comment, gbc_label_comment);
		
		textField_comment = new JTextField();
		textField_comment.setText("không");
		textField_comment.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		textField_comment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_textField_comment = new GridBagConstraints();
		gbc_textField_comment.gridwidth = 4;
		gbc_textField_comment.insets = new Insets(0, 5, 5, 0);
		gbc_textField_comment.fill = GridBagConstraints.BOTH;
		gbc_textField_comment.gridx = 0;
		gbc_textField_comment.gridy = 10;
		panel.add(textField_comment, gbc_textField_comment);
		textField_comment.setColumns(10);
		
		JPanel panelFeature = new JPanel();
		panelFeature.setBackground(Color.WHITE);
		panelFeature.setBorder(null);
		GridBagConstraints gbc_panelFeature = new GridBagConstraints();
		gbc_panelFeature.gridwidth = 4;
		gbc_panelFeature.fill = GridBagConstraints.BOTH;
		gbc_panelFeature.gridx = 0;
		gbc_panelFeature.gridy = 11;
		panel.add(panelFeature, gbc_panelFeature);
		panelFeature.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Image iconAdd = new ImageIcon("Assets/Icon/add.png").getImage();
		iconAdd = iconAdd.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	
	Image delete_icon=new ImageIcon("Assets/Icon/delete.png").getImage();//
	delete_icon=delete_icon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	Image iconEdit = new ImageIcon("Assets/Icon/edit.png").getImage();
	iconEdit = iconEdit.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	Image iconDelete = new ImageIcon("Assets/Icon/clear.png").getImage();
	iconDelete = iconDelete.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		 btnAddCus = new JButton("Thêm");
		 btnAddCus.addMouseListener(this);
		 btnAddCus.setBorder(new LineBorder(borderColor, 2, true));
		 btnAddCus.setBackground(buttonColor);	
		 btnAddCus.setForeground(Color.white);
		 btnAddCus.setFocusPainted(false);
		btnAddCus.setIcon(new ImageIcon(iconAdd));
		 btnAddCus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 btnAddCus.setPreferredSize(new Dimension(100, 40));
	
		panelFeature.add(btnAddCus);
		
		 btnDeleteCus = new JButton("Xóa");
		 btnDeleteCus.addMouseListener(this);
		btnDeleteCus.setBorder(new LineBorder(borderColor, 2, true));
		btnDeleteCus.setBackground(borderColor);
		btnDeleteCus.setForeground(Color.white);
		btnDeleteCus.setFocusPainted(false);
		 btnDeleteCus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 btnDeleteCus.setPreferredSize(new Dimension(100, 40));
		 btnDeleteCus.setIcon(new ImageIcon(delete_icon));
		 
		panelFeature.add(btnDeleteCus);
		
		 btnEditCus = new JButton("Sửa");
		 btnEditCus.addMouseListener(this);
		btnEditCus.setBorder(new LineBorder(borderColor, 2, true));
		btnEditCus.setBackground(borderColor);
		btnEditCus.setForeground(Color.white);
		btnEditCus.setFocusPainted(false);
		 btnEditCus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 btnEditCus.setPreferredSize(new Dimension(100, 40));
		btnEditCus.setIcon(new ImageIcon(iconEdit));
		panelFeature.add(btnEditCus);
		
		 btnRefreshCus = new JButton("Mới");
		 btnRefreshCus.addMouseListener(this);
		btnRefreshCus.setBorder(new LineBorder(borderColor, 2, true));
		btnRefreshCus.setBackground(borderColor);
		btnRefreshCus.setForeground(Color.white);
		btnRefreshCus.setFocusPainted(false);
		 btnRefreshCus.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 btnRefreshCus.setPreferredSize(new Dimension(100, 40));
		btnRefreshCus.setIcon(new ImageIcon(iconDelete));
		panelFeature.add(btnRefreshCus);
	}

public void newFormData() {
	textField_customer_id.setText("");
	textField_customer_name.setText("");
	textField_phone.setText("");
	textField_address.setText("");
	textField_email.setText("");
	textField_comment.setText("");
   dateChooser.setDate(null);
 
}


public void reloadTable() {
	loadDataTable(listCus);
}

public void loadDataTable(ArrayList<CustomerDTO> result) {
    SwingUtilities.invokeLater(() -> {
        customerModel.setRowCount(0);
        for (CustomerDTO c : result) {
            customerModel.addRow(new Object[] {
                    c.getCustomerID(), c.getPersonDTO().getName(), c.getPersonDTO().getGender(), Formater.FormatBirthDay(c.getPersonDTO().getBirthDay()), c.getPersonDTO().getEmail(),
                    c.getPersonDTO().getPhone(), c.getPersonDTO().getAddress(), c.getPersonDTO().getComment()
            });
        }
    });
}



public String loadName() {
	String customer_name=textField_customer_name.getText();
	
	return customer_name;
}
	
	private String getSelectedRadioButton(ButtonGroup buttonGroup) {
	    for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {
	            return button.getText();
	        }
	    }
	    return null; // Hoặc một giá trị mặc định nếu không có nút nào được chọn
	}
	
	public void filterTable(String searchText) {
	    searchText = searchText.toLowerCase();
	    ArrayList<CustomerDTO> filteredList = new ArrayList<>();

	    for (CustomerDTO c : listCus) {
	        if (c.getPersonDTO().getName().toLowerCase().contains(searchText) || String.valueOf(c.getCustomerID()).toLowerCase().contains(searchText)) {
	            filteredList.add(c);
	        }
	    }

	    loadDataTable(filteredList);
	}
	
	public void loadSearching() {
		String staff_customer_searching=textField_customer_searching.getText();
		filterTable(staff_customer_searching);
	}

	
	 public int getRowSelected() {
	        int index = table.getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn tên khách hàng muốn xóa!");
	        }
	        return index;
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
            filterTable(textField_customer_name.getText());
        } catch (Exception  ex) {
            Logger.getLogger(testPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		if (e.getSource() == btnAddCus) {
            if (Validation.isEmpty(textField_customer_name.getText())|| Validation.isEmpty(textField_phone.getText())||
            	Validation.isEmpty(textField_address.getText())	|| Validation.isEmpty(textField_email.getText())||
            	Validation.isEmpty(textField_comment.getText())) {
                JOptionPane.showMessageDialog(this, "vui lòng điền đầy đủ thông tin!");
            } else if (!Validation.isEmail(textField_email.getText())) {
             	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng email!");
             }else if (!Validation.isValidMobile(textField_phone.getText())) {
          	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng số điện thoại!");
 
          } else{
               int id = cusDAO.getInstance().getAutoIncrement();
               System.out.println("abcdxyz"+id);
        		String customer_name=textField_customer_name.getText();
        		String customer_phone=textField_phone.getText();
        		String customer_gender = getSelectedRadioButton(buttonGroup);
        		String customer_address=textField_address.getText();
        
        	    Date customer_datebirth = dateChooser.getDate();
        		String customer_email=textField_email.getText();
        		String customer_comment=textField_comment.getText();
					if (!CustomerBUS.checkDup(customer_name)) {
					    JOptionPane.showMessageDialog(this, "Tên khách hàng đã tồn tại trong danh sách !");
					} else if(!CustomerBUS.checkDupEmail(customer_email)) {
						JOptionPane.showMessageDialog(this,"Tên email đã tồn tại");
                    }else if(!CustomerBUS.checkDupPhone(customer_phone)) {
                  	  JOptionPane.showMessageDialog(this,"Số điện thoại đã tồn tại");
					}else {
						PersonDTO PersonDTO=new PersonDTO(id,customer_name,customer_gender, customer_datebirth, customer_email, customer_phone, customer_address, customer_comment);		
						customerBUS.add(PersonDTO);
						loadDataTable(listCus);
					    newFormData();
					}
				
            }
        } else if (e.getSource() == btnDeleteCus) {
            int index = getRowSelected();
            if (index != -1) {
            	customerBUS.delete(listCus.get(index));
                loadDataTable(listCus);
                newFormData();
            }
        } else if (e.getSource() == btnEditCus) {
            int index = getRowSelected();
            if (index != -1) {
            	if (Validation.isEmpty(textField_customer_name.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng mới");
                } else if (!Validation.isEmail(textField_email.getText())) {
             	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng email!");
                }else if (!Validation.isValidMobile(textField_phone.getText())) {
              	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng email!");
                
                } else {
                
            		String customer_name=textField_customer_name.getText();
            		String customer_phone=textField_phone.getText();
            		String customer_gender = getSelectedRadioButton(buttonGroup);
            		String customer_address=textField_address.getText();
            	 
            	    Date customer_datebirth = dateChooser.getDate();
            		String customer_email=textField_email.getText();
            		String customer_comment=textField_comment.getText();
       
//                    if (CustomerBUS.checkDup(customer_name)==false) {	
//                  JOptionPane.showMessageDialog(this,"Tên khách hàng đã tồn tại");
//                    }else 
                    	
                    	if(!CustomerBUS.checkDupEmail(customer_email)) {
                    	  JOptionPane.showMessageDialog(this,"Tên email đã tồn tại");
                    }else if(!CustomerBUS.checkDupPhone(customer_phone)) {
                  	  JOptionPane.showMessageDialog(this,"Số điện thoại đã tồn tại");
                    } else {
                        int id=listCus.get(index).getCustomerID();
                    	PersonDTO PersonDTO=new PersonDTO(id,customer_name,  customer_gender, customer_datebirth, customer_email, customer_phone, customer_address, customer_comment);
                		CustomerDTO customer=new CustomerDTO(id,PersonDTO);
                    	customerBUS.update(new CustomerDTO(listCus.get(index).getCustomerID(), PersonDTO));
                    	reloadTable();
                        loadDataTable(listCus);
                       newFormData();
                    }
                }
            }
        } else if (e.getSource() == table) {        
            int index = table.getSelectedRow();
            textField_customer_id.setText(String.valueOf(listCus.get(index).getCustomerID()));
            textField_customer_name.setText(listCus.get(index).getPersonDTO().getName());
            textField_phone.setText(listCus.get(index).getPersonDTO().getPhone());
            textField_email.setText(listCus.get(index).getPersonDTO().getEmail());
            textField_address.setText(listCus.get(index).getPersonDTO().getAddress());
            textField_comment.setText(listCus.get(index).getPersonDTO().getComment());
            radioButton_customer_gender_male.setSelected(true);
            dateChooser.setDate(listCus.get(index).getPersonDTO().getBirthDay());
           if(listCus.get(index).getPersonDTO().getGender().equals("Nam")) {
        	   System.out.println("đây là nam");
        	   radioButton_customer_gender_male.setSelected(true);
        	   radioButton_customer_gender_female.setSelected(false);
           }else if(listCus.get(index).getPersonDTO().getGender().equals("Nữ")) {
        	   System.out.println("đây là nữ");
        	   radioButton_customer_gender_male.setSelected(false);
        	   radioButton_customer_gender_female.setSelected(true);
           }
        } else if (e.getSource() == btnRefreshCus) {
        	loadDataTable(listCus);
        	newFormData();
        }else if(e.getSource() == btnExportExcel){
        	try {
        		ExcelExporter excel = new ExcelExporter();
        		excel.exportJTableToExcel(table);
				JOptionPane.showMessageDialog(this, "Xuất thành công");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xuất thất bại!");
			}        				
			loadDataTable(listCus);
		} else if(e.getSource() == btnImportExcel) {
			System.out.println("btnExport chay import");
			try {
    			customerBUS.importExcel();
    			CustomerBUS customerBUS=new CustomerBUS();
    			ArrayList<CustomerDTO> listCus = customerBUS.getALL();
				JOptionPane.showMessageDialog(this, "Nhập thành công");
				loadDataTable(listCus);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Nhập thất bại!");
			}        	
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
