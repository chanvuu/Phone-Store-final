package bus;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JOptionPane;

import dao.LoginDAO;
import view.Login;
import view.Menu;

public class LoginBUS implements Action{
public static Login view;
public LoginDAO loginDAO;
public Menu menuModel;
public boolean isEmployee;

public LoginBUS(Login view) {
    this.view = view;
}

public static void openMenu(boolean isEmployee) {
	Menu menu=new Menu();
	menu.setIsEmployee(isEmployee);
	menu.setVisible(true);
	view.dispose();
}


 



	@Override
	public void actionPerformed(ActionEvent e) {
		String cm=e.getActionCommand();
	if(cm.equals("Đăng Nhập")) {
		String email=view.LoadataEmail();
		char[] password=view.LoadataPassword();
	 boolean loginResult = LoginDAO.checkPassword(email, password);
	 boolean isEmployee=LoginDAO.isEmployee(email, password);
	if(loginResult==true) {
		if(isEmployee==true) {
			  JOptionPane.showMessageDialog(null, "nhân viên đăng nhập thành công!");
			  openMenu(true);
		}else if(isEmployee==false) {
			  JOptionPane.showMessageDialog(null, "quản lý đăng nhập thành công!");
			  openMenu(false);
		}
		
	
	}else {
		  JOptionPane.showMessageDialog(null, "Đăng nhập thất bại vui lòng kiểm tra email và mật khẩu!");
	}
	}
	}

	
	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
