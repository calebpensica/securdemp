package com.securde.papema.entity;

public class ClientAccount extends Account {

	private String firstName;
	private String lastName;
	private String email;
	private String contactNo;
	private String homeAddress;
	
	public ClientAccount() {
		super();
		firstName = "";
		lastName = "";
		email = "";
		contactNo = "";
		homeAddress = "";
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	

	
}
