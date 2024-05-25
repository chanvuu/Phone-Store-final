package dto;

import java.util.Date;

public class PersonDTO {
	private int personId;
	private String name;
	private String gender;
	private Date  birthDay;
	private String email;
	private String phone;
	private String address;
	private String comment;
	public int getPersonId() {
		return personId;
	}
	
	public PersonDTO(int personId, String name, String gender, Date birthDay, String email, String phone,
			String address, String comment) {
		super();
		this.personId = personId;
		this.name = name;
		this.gender = gender;
		this.birthDay = birthDay;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.comment = comment;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "PersonDTO [personId=" + personId + ", name=" + name + ", gender=" + gender + ", birthDay=" + birthDay
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", comment=" + comment + "]";
	}

	public PersonDTO() {
		super();
	}


}


