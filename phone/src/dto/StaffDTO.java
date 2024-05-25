package dto;

public class StaffDTO {
public PersonDTO getperson() {
		return person;
	}
	public void setperson(PersonDTO person) {
		this.person = person;
	}
	public String getrole() {
		return role;
	}
	public void setrole(String role) {
		this.role = role;
	}
	public double getsalary() {
		return salary;
	}
	public void setsalary(double salary) {
		this.salary = salary;
	}
	
	
public int getEmployee_ID() {
		return Employee_ID;
	}
	public void setEmployee_ID(int employee_ID) {
		Employee_ID = employee_ID;
	}

	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}


private PersonDTO person;
private int Employee_ID;
private String role;
private double salary;
private String Password;

public StaffDTO(PersonDTO person, int employee_ID, String role, double salary, String password) {
	super();
	this.person = person;
	Employee_ID = employee_ID;
	this.role = role;
	this.salary = salary;

	Password = password;
}

@Override
public String toString() {
	return "NhanVien [person=" + person + ", Employee_ID=" + Employee_ID + ", role=" + role + ", salary=" + salary
			+ ", Password=" + Password + "]";
}

//public NhanVien(int employee_ID) {
//	super();
//	this.Employee_ID = employee_ID;
//}

public StaffDTO() {
	super();
}





}

