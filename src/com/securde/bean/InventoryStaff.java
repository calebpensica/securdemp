package com.securde.bean;

import javax.persistence.*;

@Entity(name="inventorystaff")
public class InventoryStaff
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int staffid;
	
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
	
	public InventoryStaff() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return staffid;
	}

	public void setId(int id) {
		this.staffid = id;
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
