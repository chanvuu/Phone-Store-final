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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.Action;
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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import bus.StaffBUS;
import bus.SupplierBUS;
import dao.StaffDAO;
import dao.PersonDAO;
import dto.PersonDTO;
import dto.StaffDTO;
import dto.SupplierDTO;
import service.ExcelExporter;
import service.Formater;
import service.Validation;

public class Staff extends JPanel implements MouseListener,KeyListener{
	private JTextField textField_staff_searching;
	private JTextField textField_staff_id;
	private JTextField textField_staff_name;
	private JTextField textField_phone;
	private JTextField textField_address;
	private JTextField textField_email;
	private JTextField textField_password;
	private JTextField textField_comment;
	private JTable table;
	private DefaultTableModel employModel;
	private ButtonGroup buttonGroup;
	private JComboBox employee_name;
	private JComboBox year_comboBox;
	private JComboBox day_comboBox;
	private JComboBox month_comboBox;
	StaffBUS staffBUS=new StaffBUS();
	ArrayList<StaffDTO> listEmployee =staffBUS.getALL();
	private JButton btnAddStaff;
	private JButton btnDeleteStaff;
	private JButton btnRefreshStaff;
	private JButton btnEditStaff;
	private JTextField textField_staff_salary;
	private JComboBox jcomboBox_staff_type;
	private JRadioButton radioButton_staff_gender_male;
	private JRadioButton radioButton_staff_gender_female;
	private JDateChooser dateChooser;
	StaffDAO employeeDAO=new StaffDAO();
	private JButton btnExportExcel;
	private JButton btnImportExcel;
	public Staff() {
		setBackground(Color.WHITE);
		initComponent();
		loadDataTable(listEmployee);
		System.out.print(listEmployee);
	}
	
	
	public void initComponent() {
		Color myColor = new Color(34, 33, 75);
		Color borderColor = myColor;
		Color backGroundColor = Color.white;
		Color textColor = myColor;
		Color buttonColor = myColor;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{567, 257, 98, 370, 0, 51, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 428, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel_9 = new JLabel("Thông tin nhân viên");
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
		gbc_label_staff_adding_1.insets = new Insets(0, 5, 5, 5);
		gbc_label_staff_adding_1.gridx = 1;
		gbc_label_staff_adding_1.gridy = 1;
		add(label_staff_adding_1, gbc_label_staff_adding_1);
		
		textField_staff_searching = new JTextField();
		GridBagConstraints gbc_textField_staff_searching = new GridBagConstraints();
		gbc_textField_staff_searching.gridwidth = 2;
		gbc_textField_staff_searching.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_staff_searching.insets = new Insets(0, 0, 5, 5);
		gbc_textField_staff_searching.gridx = 2;
		gbc_textField_staff_searching.gridy = 1;
		add(textField_staff_searching, gbc_textField_staff_searching);
		textField_staff_searching.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		textField_staff_searching.addKeyListener(this);
		textField_staff_searching.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_staff_searching.setColumns(10);
		
		btnExportExcel = new JButton("Xuất excel");
		btnExportExcel.setForeground(Color.WHITE);
		btnExportExcel.setBorder(new LineBorder(borderColor, 2, true));
		btnExportExcel.setFocusPainted(false);
		btnExportExcel.addMouseListener(this);
		btnExportExcel.setPreferredSize(new Dimension(100, 40));
		btnExportExcel.setBackground(new Color(34, 33, 75));
		GridBagConstraints gbc_btnImportExcel = new GridBagConstraints();
		gbc_btnImportExcel.ipadx = 10;
		gbc_btnImportExcel.ipady = 10;
		gbc_btnImportExcel.insets = new Insets(0, 5, 5, 5);
		gbc_btnImportExcel.gridx = 4;
		gbc_btnImportExcel.gridy = 1;
		add(btnExportExcel, gbc_btnImportExcel);
		
		btnImportExcel = new JButton("Nhập excel");
		btnImportExcel.setForeground(Color.WHITE);
		btnImportExcel.setFocusPainted(false);
		btnImportExcel.addMouseListener(this);
		btnImportExcel.setBorder(new LineBorder(borderColor, 2, true));
		btnImportExcel.setBackground(new Color(34, 33, 75));
		btnImportExcel.setPreferredSize(new Dimension(100, 40));
		GridBagConstraints gbc_btnExportExcel = new GridBagConstraints();
		gbc_btnExportExcel.ipady = 10;
		gbc_btnExportExcel.ipadx = 10;
		gbc_btnExportExcel.insets = new Insets(0, 0, 5, 5);
		gbc_btnExportExcel.gridx = 5;
		gbc_btnExportExcel.gridy = 1;
		add(btnImportExcel, gbc_btnExportExcel);
		
		JPanel panel_table_staff = new JPanel();
		panel_table_staff.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel_table_staff = new GridBagConstraints();
		gbc_panel_table_staff.gridwidth = 5;
		gbc_panel_table_staff.fill = GridBagConstraints.BOTH;
		gbc_panel_table_staff.gridx = 1;
		gbc_panel_table_staff.gridy = 2;
		add(panel_table_staff, gbc_panel_table_staff);
		panel_table_staff.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_table_staff.add(scrollPane, BorderLayout.CENTER);
		
		String[] columnNames ={"ID","Họ tên","Giới tính","Ngày sinh","Email","SĐT","Địa chỉ","Lương","Mật khẩu","Ghi chú"};
		 Object[][] data = {};
		
		employModel=new DefaultTableModel();
		employModel=new DefaultTableModel(data,columnNames) {
			  public boolean isCellEditable(int row, int column) {
	               return false;
	           }
		};
		employModel.setColumnIdentifiers(columnNames);
		
		 table = new JTable(employModel);
		 table.setBackground(Color.WHITE);
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
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 3;
		gbc_panel.insets = new Insets(0, 0, 0, 6);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{99, 119, 97, 75};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 20, 23, 0, 27, 34, 0, 0, 0, 96, 103, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		   Border lineBorder = BorderFactory.createLineBorder(Color.BLUE); // Line border
	        Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder(); // Lowered bevel border
	        Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder(); // Raised bevel border
	        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		JLabel label_staff_adding = new JLabel("Thêm nhân viên mới");
		label_staff_adding.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GridBagConstraints gbc_label_staff_adding = new GridBagConstraints();
		gbc_label_staff_adding.gridwidth = 2;
		gbc_label_staff_adding.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_staff_adding.insets = new Insets(0, 5, 10, 5);
		gbc_label_staff_adding.gridx = 0;
		gbc_label_staff_adding.gridy = 0;
		panel.add(label_staff_adding, gbc_label_staff_adding);
//		label_staff_adding.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JLabel label_staff_id = new JLabel("Mã nhân viên");
		label_staff_id.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_staff_id = new GridBagConstraints();
		gbc_label_staff_id.anchor = GridBagConstraints.WEST;
		gbc_label_staff_id.insets = new Insets(0, 5, 5, 5);
		gbc_label_staff_id.gridx = 0;
		gbc_label_staff_id.gridy = 1;
		panel.add(label_staff_id, gbc_label_staff_id);
		
		JLabel label_staff_gender = new JLabel("Giới tính");
		label_staff_gender.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_staff_gender = new GridBagConstraints();
		gbc_label_staff_gender.anchor = GridBagConstraints.WEST;
		gbc_label_staff_gender.insets = new Insets(0, 0, 5, 5);
		gbc_label_staff_gender.gridx = 2;
		gbc_label_staff_gender.gridy = 1;
		panel.add(label_staff_gender, gbc_label_staff_gender);
		
		textField_staff_id = new JTextField();
		textField_staff_id.setEditable(false);
		textField_staff_id.setFocusable(false);
		textField_staff_id.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_staff_id = new GridBagConstraints();
		gbc_textField_staff_id.gridwidth = 2;
		gbc_textField_staff_id.ipady = 10;
		gbc_textField_staff_id.insets = new Insets(0, 5, 15, 5);
		gbc_textField_staff_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_staff_id.gridx = 0;
		gbc_textField_staff_id.gridy = 2;
		panel.add(textField_staff_id, gbc_textField_staff_id);
		textField_staff_id.setColumns(10);

	      
	 radioButton_staff_gender_male = new JRadioButton("Nam");
	 radioButton_staff_gender_male.setSelected(true);
	 radioButton_staff_gender_male.setBackground(Color.white);
		GridBagConstraints gbc_radioButton_staff_gender_male = new GridBagConstraints();
		gbc_radioButton_staff_gender_male.anchor = GridBagConstraints.WEST;
		gbc_radioButton_staff_gender_male.ipady = 10;
		gbc_radioButton_staff_gender_male.insets = new Insets(0, 0, 15, 5);
		gbc_radioButton_staff_gender_male.gridx = 2;
		gbc_radioButton_staff_gender_male.gridy = 2;
		panel.add(radioButton_staff_gender_male, gbc_radioButton_staff_gender_male);
		
	 radioButton_staff_gender_female = new JRadioButton("Nữ");
	 radioButton_staff_gender_female.setBackground(Color.white);

		GridBagConstraints gbc_radioButton_staff_gender_female = new GridBagConstraints();
		gbc_radioButton_staff_gender_female.anchor = GridBagConstraints.WEST;
		gbc_radioButton_staff_gender_female.insets = new Insets(0, 0, 15, 0);
		gbc_radioButton_staff_gender_female.gridx = 3;
		gbc_radioButton_staff_gender_female.gridy = 2;
		panel.add(radioButton_staff_gender_female, gbc_radioButton_staff_gender_female);
		
		
		 buttonGroup = new ButtonGroup();
		 buttonGroup.add(radioButton_staff_gender_male);
		 buttonGroup.add(radioButton_staff_gender_female);
	       
	       
		JLabel label_name = new JLabel("Họ tên nhân viên");
		label_name.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_name = new GridBagConstraints();
		gbc_label_name.gridwidth = 2;
		gbc_label_name.anchor = GridBagConstraints.WEST;
		gbc_label_name.insets = new Insets(0, 5, 5, 5);
		gbc_label_name.gridx = 0;
		gbc_label_name.gridy = 3;
		panel.add(label_name, gbc_label_name);
		
		JLabel label_phone = new JLabel("Số điện thoại");
		label_phone.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_phone = new GridBagConstraints();
		gbc_label_phone.anchor = GridBagConstraints.WEST;
		gbc_label_phone.insets = new Insets(0, 0, 5, 5);
		gbc_label_phone.gridx = 2;
		gbc_label_phone.gridy = 3;
		panel.add(label_phone, gbc_label_phone);
		
		textField_staff_name = new JTextField();
		textField_staff_name.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_staff_name = new GridBagConstraints();
		gbc_textField_staff_name.gridwidth = 2;
		gbc_textField_staff_name.ipady = 10;
		gbc_textField_staff_name.insets = new Insets(0, 5, 15, 5);
		gbc_textField_staff_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_staff_name.gridx = 0;
		gbc_textField_staff_name.gridy = 4;
		panel.add(textField_staff_name, gbc_textField_staff_name);
		textField_staff_name.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_phone = new GridBagConstraints();
		gbc_textField_phone.gridwidth = 2;
		gbc_textField_phone.ipady = 10;
		gbc_textField_phone.insets = new Insets(0, 0, 15, 5);
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
		
		JLabel lblLoiNhan = new JLabel("Vị trí");
		lblLoiNhan.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblLoiNhan = new GridBagConstraints();
		gbc_lblLoiNhan.anchor = GridBagConstraints.WEST;
		gbc_lblLoiNhan.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoiNhan.gridx = 2;
		gbc_lblLoiNhan.gridy = 5;
		panel.add(lblLoiNhan, gbc_lblLoiNhan);
		
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
		
		
		String[] staff_type_list=new String[] {"Quản lý","Nhân viên"};
	    jcomboBox_staff_type = new JComboBox( staff_type_list);
	    jcomboBox_staff_type.setBorder(new LineBorder(borderColor, 1, true));
	    jcomboBox_staff_type.setBackground(Color.WHITE);
	    jcomboBox_staff_type.setForeground(textColor);	
	    GridBagConstraints gbc_jcomboBox_staff_type = new GridBagConstraints();
		gbc_jcomboBox_staff_type.gridwidth = 2;
		gbc_jcomboBox_staff_type.ipady = 10;
		gbc_jcomboBox_staff_type.insets = new Insets(0, 0, 15, 5);
		gbc_jcomboBox_staff_type.fill = GridBagConstraints.HORIZONTAL;
		gbc_jcomboBox_staff_type.gridx = 2;
		gbc_jcomboBox_staff_type.gridy = 6;
		panel.add(jcomboBox_staff_type, gbc_jcomboBox_staff_type);
		
		JLabel label_email = new JLabel("Email");
		label_email.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_email = new GridBagConstraints();
		gbc_label_email.anchor = GridBagConstraints.WEST;
		gbc_label_email.insets = new Insets(0, 5, 5, 5);
		gbc_label_email.gridx = 0;
		gbc_label_email.gridy = 7;
		panel.add(label_email, gbc_label_email);
		
		JLabel label_password = new JLabel("Password");
		label_password.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_password = new GridBagConstraints();
		gbc_label_password.anchor = GridBagConstraints.WEST;
		gbc_label_password.insets = new Insets(0, 0, 5, 5);
		gbc_label_password.gridx = 2;
		gbc_label_password.gridy = 7;
		panel.add(label_password, gbc_label_password);
		
		textField_email = new JTextField();
		textField_email.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_email = new GridBagConstraints();
		gbc_textField_email.gridwidth = 2;
		gbc_textField_email.ipady = 10;
		gbc_textField_email.insets = new Insets(0, 5, 15, 5);
		gbc_textField_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_email.gridx = 0;
		gbc_textField_email.gridy = 8;
		panel.add(textField_email, gbc_textField_email);
		textField_email.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_password = new GridBagConstraints();
		gbc_textField_password.gridwidth = 2;
		gbc_textField_password.ipady = 10;
		gbc_textField_password.insets = new Insets(0, 0, 15, 5);
		gbc_textField_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_password.gridx = 2;
		gbc_textField_password.gridy = 8;
		panel.add(textField_password, gbc_textField_password);
		textField_password.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ngày sinh");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 9;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
//		 month_comboBox = new JComboBox(month_list);
//		 month_comboBox.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
//		GridBagConstraints gbc_month_comboBox = new GridBagConstraints();
//		gbc_month_comboBox.insets = new Insets(0, 0, 15, 5);
//		gbc_month_comboBox.fill = GridBagConstraints.HORIZONTAL;
//		gbc_month_comboBox.gridx = 1;
//		gbc_month_comboBox.gridy = 12;
//		panel.add(month_comboBox, gbc_month_comboBox);
		
//		 year_comboBox = new JComboBox(year_list);
//		 year_comboBox.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
//		GridBagConstraints gbc_year_comboBox = new GridBagConstraints();
//		gbc_year_comboBox.gridwidth = 2;
//		gbc_year_comboBox.insets = new Insets(0, 0, 15, 0);
//		gbc_year_comboBox.fill = GridBagConstraints.HORIZONTAL;
//		gbc_year_comboBox.gridx = 2;
//		gbc_year_comboBox.gridy = 12;
//		panel.add(year_comboBox, gbc_year_comboBox);
		
		JLabel lblLng = new JLabel("Lương");
		lblLng.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_lblLng = new GridBagConstraints();
		gbc_lblLng.anchor = GridBagConstraints.WEST;
		gbc_lblLng.insets = new Insets(0, 5, 5, 5);
		gbc_lblLng.gridx = 2;
		gbc_lblLng.gridy = 9;
		panel.add(lblLng, gbc_lblLng);
		
//		String []day_list=new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
//		String []month_list=new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
//		String []year_list=new String[]{"2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023"};
		
		
		
//		day_comboBox = new JComboBox(day_list);
//		 day_comboBox.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		dateChooser=new  JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new Date());
	    dateChooser.setBounds(320,90,200,30);
		dateChooser.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_day_comboBox = new GridBagConstraints();
		gbc_day_comboBox.ipady = 6;
		gbc_day_comboBox.gridwidth = 2;
		gbc_day_comboBox.insets = new Insets(0, 5, 15, 5);
		gbc_day_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_day_comboBox.gridx = 0;
		gbc_day_comboBox.gridy = 10;
		panel.add(dateChooser, gbc_day_comboBox);
		
		textField_staff_salary = new JTextField();
		textField_staff_salary.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		GridBagConstraints gbc_textField_staff_salary = new GridBagConstraints();
		gbc_textField_staff_salary.ipady = 10;
		gbc_textField_staff_salary.gridwidth = 2;
		gbc_textField_staff_salary.insets = new Insets(0, 5, 15, 5);
		gbc_textField_staff_salary.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_staff_salary.gridx = 2;
		gbc_textField_staff_salary.gridy = 10;
		panel.add(textField_staff_salary, gbc_textField_staff_salary);
		textField_staff_salary.setColumns(10);
		
		JLabel label_comment = new JLabel("Ghi chú");
		label_comment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_label_comment = new GridBagConstraints();
		gbc_label_comment.anchor = GridBagConstraints.WEST;
		gbc_label_comment.insets = new Insets(0, 5, 5, 5);
		gbc_label_comment.gridx = 0;
		gbc_label_comment.gridy = 11;
		panel.add(label_comment, gbc_label_comment);
		
		textField_comment = new JTextField();
		textField_comment.setText("không");
		textField_comment.setBorder(new LineBorder(new Color(34, 33, 75), 2, true));
		textField_comment.setFont(new Font("Times New Roman", Font.BOLD, 12));
		GridBagConstraints gbc_textField_comment = new GridBagConstraints();
		gbc_textField_comment.gridwidth = 4;
		gbc_textField_comment.insets = new Insets(0, 5, 5, 5);
		gbc_textField_comment.fill = GridBagConstraints.BOTH;
		gbc_textField_comment.gridx = 0;
		gbc_textField_comment.gridy = 12;
		panel.add(textField_comment, gbc_textField_comment);
		textField_comment.setColumns(10);
		
		JPanel panelFeature = new JPanel();
		panelFeature.setBackground(Color.WHITE);
		panelFeature.setBorder(null);
		GridBagConstraints gbc_panelFeature = new GridBagConstraints();
		gbc_panelFeature.gridwidth = 4;
		gbc_panelFeature.fill = GridBagConstraints.BOTH;
		gbc_panelFeature.gridx = 0;
		gbc_panelFeature.gridy = 13;
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
		 btnAddStaff = new JButton("thêm");
		btnAddStaff.addMouseListener(this);
		btnAddStaff.setBorder(new LineBorder(borderColor, 2, true));
		btnAddStaff.setBackground(buttonColor);	
		btnAddStaff.setForeground(Color.white);
		btnAddStaff.setFocusPainted(false);
		btnAddStaff.setIcon(new ImageIcon(iconAdd));
		btnAddStaff.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnAddStaff.setPreferredSize(new Dimension(100, 40));
		panelFeature.add(btnAddStaff);
		
		
		 btnDeleteStaff = new JButton("xóa");
		 btnDeleteStaff.addMouseListener(this);
		 btnDeleteStaff.setBorder(new LineBorder(borderColor, 2, true));
		 btnDeleteStaff.setBackground(buttonColor);	
		 btnDeleteStaff.setForeground(Color.white);
		 btnDeleteStaff.setFocusPainted(false);
		 btnDeleteStaff.setIcon(new ImageIcon(delete_icon));
		 btnDeleteStaff.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 btnDeleteStaff.setPreferredSize(new Dimension(100, 40));
		panelFeature.add(btnDeleteStaff);
		
	    btnEditStaff = new JButton("sửa");
	    btnEditStaff.addMouseListener(this);
	    btnEditStaff.setBorder(new LineBorder(borderColor, 2, true));
	    btnEditStaff.setBackground(buttonColor);	
	    btnEditStaff.setForeground(Color.white);
	    btnEditStaff.setFocusPainted(false);
	    btnEditStaff.setIcon(new ImageIcon(iconEdit));
	    btnEditStaff.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    btnEditStaff.setPreferredSize(new Dimension(100, 40));
		panelFeature.add(btnEditStaff);
		
		 btnRefreshStaff = new JButton("mới");
		 btnRefreshStaff.addMouseListener(this);
		 btnRefreshStaff.setBorder(new LineBorder(borderColor, 2, true));
		 btnRefreshStaff.setBackground(buttonColor);	
		 btnRefreshStaff.setForeground(Color.white);
		 btnRefreshStaff.setFocusPainted(false);
		 btnRefreshStaff.setIcon(new ImageIcon(iconDelete));
		 btnRefreshStaff.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 btnRefreshStaff.setPreferredSize(new Dimension(100, 40));
		panelFeature.add(btnRefreshStaff);
	}
	/* Xóa dữ liệu trong bảng */
	public void newFormData() {
		textField_staff_id.setText("");
		textField_staff_name.setText("");
		textField_phone.setText("");
		textField_email.setText("");
		textField_password.setText("");
		textField_address.setText("");
	    dateChooser.setDate(null);
		textField_staff_salary.setText("");
		textField_comment.setText("");
	}
	
	public String loadName() {
		String StaffDTO="hello";
		return StaffDTO;
	}
	
	
	 public int getRowSelected() {
	        int index = table.getSelectedRow();
	        if (index == -1) {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn tên khách hàng muốn xóa!");
	        }
	        return index;
	    }
	/* Tạo dữ liệu trong bảng */
	public void loadDataTable(ArrayList<StaffDTO> result) {
	employModel.setRowCount(0);
		for (StaffDTO c : result) {
			employModel.addRow(new Object[] {
					c.getperson().getPersonId(), c.getperson().getName(), c.getperson().getGender(),Formater.FormatBirthDay(c.getperson().getBirthDay()),c.getperson().getEmail()
					,c.getperson().getPhone(),c.getperson().getAddress(),Formater.FormatVND(c.getsalary()),c.getPassword(),c.getperson().getComment()
		
			});
		
			
		}
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
	    ArrayList<StaffDTO> filteredList = new ArrayList<>();

	    for (StaffDTO c : listEmployee) {
	        if (c.getperson().getName().toLowerCase().contains(searchText) || String.valueOf(c.getEmployee_ID()).toLowerCase().contains(searchText)) {
	            filteredList.add(c);
	        }
	    }

	    loadDataTable(filteredList);
	}


	 
	 public void reloadTable() {
			loadDataTable(listEmployee);
		}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
            filterTable(textField_staff_name.getText());
        } catch (Exception  ex) {
            Logger.getLogger(testPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
				try {
					ArrayList<StaffDTO> filterList = new ArrayList<>();
					filterList = staffBUS.search(textField_staff_searching.getText());
					loadDataTable(filterList);
				} catch (Exception  ex) {
		            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}	
		
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		if (e.getSource() == btnAddStaff) {
            if (Validation.isEmpty(textField_staff_name.getText())|| Validation.isEmpty(textField_phone.getText())||
            	Validation.isEmpty(textField_address.getText())	|| Validation.isEmpty(textField_email.getText())||
            	Validation.isEmpty(textField_comment.getText())) {
                JOptionPane.showMessageDialog(this, "vui lòng điền đầy đủ thông tin!");
            
            }else if (!Validation.isEmail(textField_email.getText())) {
            	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng email!");
            }else if (!Validation.isValidMobile(textField_phone.getText())) {
         	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng số điện thoại!");
            }  else if (!Validation.isSalary(textField_staff_salary.getText())) {
            	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng lương!");
            
         } else{
                int employee_id = PersonDAO.getInstance().getAutoIncrement();
        	    String employee_gender = getSelectedRadioButton(buttonGroup);
        	    String employee_name = textField_staff_name.getText();
        	    String employee_phone = textField_phone.getText();
        	    String employee_email = textField_email.getText();
        	    String employee_password = textField_password.getText();
        	    String employee_address=textField_address.getText();
        	    String employee_type = (String) jcomboBox_staff_type.getSelectedItem();
        	    Double employee_salary = Double.parseDouble(textField_staff_salary.getText());
        	    String employee_comment=textField_comment.getText();
        	    Date datebirth = dateChooser.getDate();
					if (!staffBUS.checkDup(employee_name)) {
					    JOptionPane.showMessageDialog(this, "Tên khách hàng đã tồn tại trong danh sách !");
					} else if(!staffBUS.checkDupEmail(employee_email)) {
						JOptionPane.showMessageDialog(this,"Tên email đã tồn tại");
                    }else if(!staffBUS.checkDupPhone(employee_phone)) {
                  	  JOptionPane.showMessageDialog(this,"Số điện thoại đã tồn tại");
                    }
					
					else {
						PersonDTO nguoi=new PersonDTO(employee_id,employee_name,employee_gender, datebirth, employee_email, employee_phone, employee_address, employee_comment);
					    staffBUS.add(nguoi,employee_type, employee_salary, employee_password);
					    System.out.println(nguoi.getPersonId());
						loadDataTable(listEmployee);
					    newFormData();
					}
				
            }
        } else if (e.getSource() == btnDeleteStaff) {
            int index = getRowSelected();
            if (index != -1) {
            	staffBUS.delete(listEmployee.get(index));
                loadDataTable(listEmployee);
                newFormData();
            }
        } else if (e.getSource() == btnEditStaff) {
            int index = getRowSelected();
            if (index != -1) {
                if (Validation.isEmpty(textField_staff_name.getText())) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng mới");
                } else if (!Validation.isEmail(textField_email.getText())) {
             	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng email!");
                }else if (!Validation.isValidMobile(textField_phone.getText())) {
              	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng email!");
                } else if (!Validation.isSalary(textField_staff_salary.getText())) {
              	   JOptionPane.showMessageDialog(this, "vui lòng điền đúng định dạng lương!");
                }else {
                
                    int employee_id = PersonDAO.getInstance().getAutoIncrement();
            	    String employee_gender = getSelectedRadioButton(buttonGroup);
            	    String employee_name = textField_staff_name.getText();
            	    String employee_phone = textField_phone.getText();
            	    String employee_email = textField_email.getText();
            	    String employee_password = textField_password.getText();
            	    String employee_address=textField_address.getText();
            	    String employee_type = (String) jcomboBox_staff_type.getSelectedItem();
//            	    int employee_day = Integer.parseInt((String) day_comboBox.getSelectedItem());
//            	    int employee_month = Integer.parseInt((String) month_comboBox.getSelectedItem());
//            	    int employee_year = Integer.parseInt((String) month_comboBox.getSelectedItem());
            	    Double employee_salary = Double.parseDouble(textField_staff_salary.getText());
            	    String employee_comment=textField_comment.getText();
//            	    DateBirth datebirth = new DateBirth(employee_day, employee_month, employee_year);
       
            	   Date datebirth=dateChooser.getDate();
            	    if(!staffBUS.checkDupEmail(employee_email)) {
                  	  JOptionPane.showMessageDialog(this,"Tên email đã tồn tại");
                  }else if(!staffBUS.checkDupPhone(employee_phone)) {
                	  JOptionPane.showMessageDialog(this,"Số điện thoại đã tồn tại");
                  } else {
                	    int id=listEmployee.get(index).getEmployee_ID();
                        
                    PersonDTO nguoi=new PersonDTO(employee_id,employee_name,employee_gender, datebirth, employee_email, employee_phone, employee_address, employee_comment);
                	StaffDTO StaffDTO=new StaffDTO(nguoi,employee_id,employee_type, employee_salary, employee_password);
                	staffBUS.update(StaffDTO);
                        loadDataTable(listEmployee);
                       newFormData();
                  }           
                }
            }
        } else if (e.getSource() == table) {        
            int index = table.getSelectedRow();
            textField_staff_id.setText(String.valueOf(listEmployee.get(index).getEmployee_ID()));
            textField_staff_name.setText(listEmployee.get(index).getperson().getName());
            textField_phone.setText(listEmployee.get(index).getperson().getPhone());
            textField_email.setText(listEmployee.get(index).getperson().getEmail());
            textField_password.setText(listEmployee.get(index).getPassword());
            textField_address.setText(listEmployee.get(index).getperson().getAddress());
            jcomboBox_staff_type.setSelectedItem(listEmployee.get(index).getrole());
            textField_staff_salary.setText(Formater.FormatMoney((listEmployee.get(index).getsalary())));
            textField_comment.setText(listEmployee.get(index).getperson().getComment());
            Date ngaySinh = listEmployee.get(index).getperson().getBirthDay();
            dateChooser.setDate(ngaySinh);
            
            if(listEmployee.get(index).getperson().getGender().equals("Nam")) {
         	   System.out.println("đây là nam");
         	   radioButton_staff_gender_male.setSelected(true);
         	   radioButton_staff_gender_female.setSelected(false);
            }else if(listEmployee.get(index).getperson().getGender().equals("Nữ")) {
         	   System.out.println("đây là nữ");
         	   radioButton_staff_gender_male.setSelected(false);
         	   radioButton_staff_gender_female.setSelected(true);
            }
        } else if (e.getSource() == btnRefreshStaff) {
        	loadDataTable(listEmployee);
        	newFormData();
        } else if(e.getSource() == btnExportExcel){
        	try {
        		ExcelExporter excel = new ExcelExporter();
        		excel.exportJTableToExcel(table);
				JOptionPane.showMessageDialog(this, "Xuất thành công");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(this, "Xuất thất bại!");
			}        				
			loadDataTable(listEmployee);
		} else if(e.getSource() == btnImportExcel) {
			System.out.println("btnExport chay import");
			try {
    			staffBUS.importExcel();
    			StaffBUS staffBUS=new StaffBUS();
    			ArrayList<StaffDTO> listEmployee =staffBUS.getALL();
				JOptionPane.showMessageDialog(this, "Nhập thành công");
				loadDataTable(listEmployee);
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

