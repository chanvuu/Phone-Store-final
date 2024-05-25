package dto;

public class CustomerDTO {
private int customerID;
private PersonDTO PersonDTO;

public CustomerDTO(int customerID, PersonDTO PersonDTO) {
	super();
	this.customerID = customerID;
	this.PersonDTO = PersonDTO;
}



public CustomerDTO() {
	super();
}


public int getCustomerID() {
	return customerID;
}

public void setCustomerID(int customerID) {
	this.customerID = customerID;
}

public PersonDTO getPersonDTO() {
	return PersonDTO;
}

public void setPersonDTO(PersonDTO PersonDTO) {
	this.PersonDTO = PersonDTO;
}

@Override
public String toString() {
	return "Customer [customerID=" + customerID + ", PersonDTO=" + PersonDTO + "]";
}

}
