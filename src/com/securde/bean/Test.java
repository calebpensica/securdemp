package com.securde.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "label")
	private String label;
	
	public Test () {
		
	}
	
	public Test(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}

}
