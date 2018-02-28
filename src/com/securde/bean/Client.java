package com.securde.bean;

import javax.persistence.*;


@Entity(name="client")
public class Client
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clientid;
	
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String fName;
	
	@Column(nullable=false)
	private String lName;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String contactNo;
	
	@Column
	private String homeAdd;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getHomeAdd() {
		return homeAdd;
	}

	public void setHomeAdd(String homeAdd) {
		this.homeAdd = homeAdd;
	}

	public int getId() {
		return clientid;
	}

	public void setId(int id) {
		this.clientid = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
