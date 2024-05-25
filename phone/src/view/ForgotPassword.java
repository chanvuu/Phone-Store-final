package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import dao.ForgotPasswordDAO;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class ForgotPassword extends JDialog implements MouseListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnSend;
	private JButton btnBack;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1.0");
		try {
			ForgotPassword dialog = new ForgotPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ForgotPassword() {
		int r = 34;
		int g = 33;
		int b = 75;


		Color myColor = new Color(r, g, b);
		Color borderColor = myColor;
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(100, 100, 836, 342);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{277, 495, 0};
		gridBagLayout.rowHeights = new int[]{304, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblNewLabel = new JLabel();
			ImageIcon myPicture = new ImageIcon("Assets/sendEmail.png");
			Image image = myPicture.getImage().getScaledInstance(260, 300, Image.SCALE_SMOOTH);
			myPicture = new ImageIcon(image);
			lblNewLabel.setIcon(myPicture);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		}
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_contentPanel = new GridBagConstraints();
		gbc_contentPanel.fill = GridBagConstraints.BOTH;
		gbc_contentPanel.gridx = 1;
		gbc_contentPanel.gridy = 0;
		getContentPane().add(contentPanel, gbc_contentPanel);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 228, 200, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 37, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel_1 = new JLabel("QUÊN MẬT KHẨU?");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
			lblNewLabel_1.setForeground(myColor);
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.gridwidth = 2;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 3;
			gbc_lblNewLabel_1.gridy = 1;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(34, 33, 75)));
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		     Color defaultTextColor = Color.GRAY;
		     txtEmail.setForeground(defaultTextColor);
			txtEmail.addFocusListener(new FocusListener() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (txtEmail.getText().equals("Email...")) {
	                	txtEmail.setText("");
	                }
	                txtEmail.setForeground(Color.BLACK); 
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (txtEmail.getText().isEmpty()) {
	                	txtEmail.setText("Email...");
	                	txtEmail.setForeground(defaultTextColor);
	                }
	            }
	        });
			{
				lblNewLabel_2 = new JLabel("Email:");
				lblNewLabel_2.setForeground(new Color(34, 33, 75));
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 3;
				gbc_lblNewLabel_2.gridy = 3;
				contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			GridBagConstraints gbc_txtEmail = new GridBagConstraints();
			gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEmail.gridwidth = 2;
			gbc_txtEmail.insets = new Insets(15, 0, 15, 10);
			gbc_txtEmail.gridx = 3;
			gbc_txtEmail.gridy = 4;
			contentPanel.add(txtEmail, gbc_txtEmail);
			txtEmail.setColumns(10);
		}
		{
			lblNewLabel_3 = new JLabel("Captcha:");
			lblNewLabel_3.setForeground(new Color(34, 33, 75));
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 3;
			gbc_lblNewLabel_3.gridy = 5;
			contentPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(34, 33, 75)));
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(15, 0, 15, 10);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 3;
			gbc_textField_1.gridy = 6;
			
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			textField = new JTextField();
			textField.setText(service.Captcha.generateRandomString());
			textField.setBackground(Color.LIGHT_GRAY);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
			textField.setColumns(10);
			textField.setForeground(Color.white);
			textField.setBorder(new LineBorder(borderColor, 2, true));
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(5, 0, 5, 5);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 4;
			gbc_textField.gridy = 6;
			contentPanel.add(textField, gbc_textField);
		}
		{
			Image iconSend=new ImageIcon("Assets/Icon/send.png").getImage();
			iconSend=iconSend.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnSend = new JButton("Gửi");
			btnSend.addMouseListener(this);
			btnSend.setRequestFocusEnabled(true);
			btnSend.setForeground(Color.WHITE);
			btnSend.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnSend.setFocusPainted(false);
			btnSend.setIcon(new ImageIcon(iconSend));
			btnSend.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnSend.setBackground(new Color(34, 33, 75));
			btnSend.setPreferredSize(new Dimension(100, 40));
			GridBagConstraints gbc_btnSend = new GridBagConstraints();
			gbc_btnSend.ipadx = 70;
			gbc_btnSend.insets = new Insets(0, 0, 0, 5);
			gbc_btnSend.gridx = 3;
			gbc_btnSend.gridy = 7;
			contentPanel.add(btnSend, gbc_btnSend);
		}
		{
			Image iconBack = new ImageIcon("Assets/Icon/clear.png").getImage();
			iconBack = iconBack.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnBack = new JButton("Trở về");
			btnBack.addMouseListener(this);
			btnBack.setRequestFocusEnabled(true);
			btnBack.setPreferredSize(new Dimension(100, 40));
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
			btnBack.setIcon(new ImageIcon(iconBack));
			btnBack.setFocusPainted(false);
			btnBack.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			btnBack.setBackground(new Color(34, 33, 75));
			btnBack.setPreferredSize(new Dimension(100, 40));
			GridBagConstraints gbc_btnBack = new GridBagConstraints();
			gbc_btnBack.ipadx = 50;
			gbc_btnBack.insets = new Insets(0, 0, 0, 5);
			gbc_btnBack.gridx = 4;
			gbc_btnBack.gridy = 7;
			contentPanel.add(btnBack, gbc_btnBack);
		}
        this.setLocationRelativeTo(null);


	}
	
	public static void sendGmail(String to, String subject, String message) {
	    // Thay thế các giá trị này bằng thông tin đăng nhập Gmail của bạn
	    final String username = "lenguyenquochunghello@gmail.com";
	    final String password = "ovct vlyo slba yukl";

	    // Cài đặt các thuộc tính cho máy chủ email
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    // Xác thực phiên làm việc
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });

	    try {
	        Message mimeMessage = new MimeMessage(session);

	        // Thiết lập trường From: 
	        mimeMessage.setFrom(new InternetAddress(username));

	        // Thiết lập trường To:
	        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

	        // Thiết lập trường Subject:
	        mimeMessage.setSubject(subject);

	        // Bây giờ thiết lập nội dung thư
	        mimeMessage.setText(message);

	        // Gửi thư
	        Transport.send(mimeMessage);

	        System.out.println("Gửi email thành công.");

	    } catch (MessagingException e) {
	        System.err.println("Lỗi khi gửi email: " + e.getMessage());
	    }
	    
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnSend) {
			String input_captcha=textField_1.getText();
			String input_email=txtEmail.getText();
			if(service.Captcha.isValid(input_captcha)) {
				JOptionPane.showMessageDialog(this,"bạn đã thành công");
				int newpassword=ForgotPasswordDAO.getInstance().updatePassword(input_email);
			    String to = "lenguyenquochunghello@gmail.com";
	            String subject = "Phone store";
	            String message = "Your pass change to "+newpassword;

	            // Call the method to send the email
	           sendGmail(to, subject, message);
	           dispose();
	           Login login = new Login();
	           login.setVisible(true);
			}			
		}
		 else if (e.getSource() == btnBack) {
				Login login = new Login();
				login.displayLogin();				
				dispose();
			} 
		 else {
				JOptionPane.showMessageDialog(this,"Email không tồn tại hoặc mã captcha không đúng");
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
